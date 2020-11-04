package io.github.kimmking.gateway.outbound.httpClient;

import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HuiFeiDeYuOutboundHandler {

    //客户端
    private CloseableHttpClient httpClient;
    private ExecutorService proxyService;
    //url
    private String tmepUrl;

    public HuiFeiDeYuOutboundHandler(String url) {
        tmepUrl = url;
        httpClient = HttpClientBuilder.create().build();
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        final String url = tmepUrl + fullHttpRequest.uri();
        proxyService.submit(() -> fetchGet(fullHttpRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, String url) {
        HttpGet httpGet = new HttpGet(url);
        HttpHeaders headers = fullHttpRequest.headers();
        //添加请求头
        setHttpGetHeaders(httpGet, headers);
        CloseableHttpResponse response = null;
        FullHttpResponse fullHttpResponse = null;
        try {
            response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            System.out.println(EntityUtils.toString(entity));
            byte[] body = EntityUtils.toByteArray(response.getEntity());
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(response.getFirstHeader("Content-Length").getValue()));
        } catch (IOException e) {
            e.printStackTrace();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();
            close(response);
        }
    }

    /**

     *
     * @param httpGet
     * @param headers
     */
    private void setHttpGetHeaders(HttpGet httpGet, HttpHeaders headers) {
        if (headers != null) {
            headers.forEach(o -> httpGet.addHeader(o.getKey(), o.getValue()));
        }
    }

    private void close(CloseableHttpResponse response) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
