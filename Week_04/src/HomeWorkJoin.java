import java.util.Vector;

public class HomeWorkJoin {

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        Vector<Integer> vector = new Vector<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                vector.add(sum());
            }
        });
        thread.start();
        thread.join();
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("异步计算结果为："+vector.get(0));
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
