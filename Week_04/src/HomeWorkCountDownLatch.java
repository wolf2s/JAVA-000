import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWorkCountDownLatch {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        final int size = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Integer> futureList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Future<Integer> future = executorService.submit(new CountDownLatchFuture(countDownLatch));
            futureList.add(future.get());
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("执行后的结果: " + futureList.get(0));
    }

    static class CountDownLatchFuture implements Callable {
        private AtomicInteger atomicInteger = new AtomicInteger();
        private CountDownLatch countDownLatch;

        public CountDownLatchFuture(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public AtomicInteger getAtomicInteger() {
            return atomicInteger;
        }

        @Override
        public Object call() throws Exception {
            try {
                atomicInteger.set(sum());
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return atomicInteger;
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
