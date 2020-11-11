import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWorkSemaphore {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(1);
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread thread = new Thread(() -> {
            try {
                semaphore.acquire();
                atomicInteger.set(sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });
        thread.start();
        thread.join();
        System.out.println("异步计算结果为："+atomicInteger.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
