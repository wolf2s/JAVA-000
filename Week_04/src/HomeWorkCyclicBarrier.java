import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWorkCyclicBarrier {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int size = 2;
        long start = System.currentTimeMillis();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);
        List<Future> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            Future<Integer> future = executorService.submit(new CyclicBarrierCallable(cyclicBarrier));
            futureList.add(future);
        }
        for (Future future : futureList) {
            System.out.println("jdk8CompletableFeature使用时间：" + (System.currentTimeMillis() - start) + " ms");
            System.out.println("执行后的结果: " + future.get());
        }
        executorService.shutdown();
    }
}

class CyclicBarrierCallable implements Callable {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private CyclicBarrier cyclicBarrie;

    public CyclicBarrierCallable(CyclicBarrier cyclicBarrie) {
        this.cyclicBarrie = cyclicBarrie;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    @Override
    public Object call() throws Exception {
        try {
            atomicInteger.set(sum());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cyclicBarrie.await();
        }
        return atomicInteger;
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
