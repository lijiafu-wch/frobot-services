/**
 * 多线程打印abcabcabcabc
 * @author lijiafu
 * @date 2020/3/16 18:37
 * @since 1.0
 */
public class ThreadPrinter implements Runnable{
    // 打印次数
    private static final int printCount = 10;
    // 前一个线程的打印锁
    private final Object upLock;
    // 本线程的打印锁
    private final Object thisLock;
    // 打印字符
    private final char printChar;

    public ThreadPrinter(Object upLock, Object thisLock, char printChar) {
        this.upLock = upLock;
        this.thisLock = thisLock;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        for (int i = 0; i < printCount; i++) {
            // 获取前一个线程的打印锁
            synchronized (upLock) {
                // 获取本线程的打印锁
                synchronized (thisLock) {
                    //打印字符
                    System.out.print(printChar);
                    // 通过本线程的打印锁唤醒后面的线程
                    thisLock.notify();
                }
                // 不是最后一次则通过upLock等待被唤醒
                if(i < printCount - 1){
                    try {
                        // 通过upLock等待被唤醒
                        upLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // 打印A线程的锁
        Object lockA = new Object();
        // 打印B线程的锁
        Object lockB = new Object();
        // 打印C线程的锁
        Object lockC = new Object();

        // 打印a的线程
        Thread threadA = new Thread(new ThreadPrinter(lockC, lockA, 'a'));
        // 打印b的线程
        Thread threadB = new Thread(new ThreadPrinter(lockA, lockB, 'b'));
        // 打印c的线程
        Thread threadC = new Thread(new ThreadPrinter(lockB, lockC, 'c'));

        // 依次开启a b c线程
        threadA.start();
        Thread.sleep(100); // 确保按顺序a、b、c执行
        threadB.start();
        Thread.sleep(100);
        threadC.start();
        Thread.sleep(100);
    }

}
