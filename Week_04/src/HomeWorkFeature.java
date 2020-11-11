import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWorkFeature {

    public static void main(String[] args) throws Exception {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //jdk8
        futureByCompletableFeature();
        //jdk5
        futureByCallable(executorService);
        futureByRunnable(executorService);
        futureByFutureTask();
        executorService.shutdown();
    }

    private static void futureByCompletableFeature() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            return sum();
        });
        System.out.println("jdk8CompletableFeature使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("jdk8CompletableFeature异步计算结果为：" + completableFuture.get());
    }

    private static void futureByCallable(ExecutorService executorService) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        System.out.println("jdk5Callable使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("jdk5Callable异步计算结果为：" + future.get());
    }

    /**
     * @param executorService
     */
    private static void futureByRunnable(ExecutorService executorService) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger atomicInteger = new AtomicInteger();
        Future<AtomicInteger> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                atomicInteger.set(sum());
            }
        }, atomicInteger);
        System.out.println("jdk5Runnable使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("jdk5Runnable异步计算结果为: " + future.get());
    }

    private static void futureByFutureTask() throws InterruptedException, ExecutionException {
        HomeWorkTask homeWorkTask = new HomeWorkTask();
        long start = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(homeWorkTask);
        System.out.println("jdk5futureTask使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("jdk5futureTask异步计算结果为：" + futureTask.get());
    }

    static class HomeWorkTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return sum();
        }
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
