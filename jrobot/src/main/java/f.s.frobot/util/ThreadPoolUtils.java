package f.s.frobot.util;

import f.s.frobot.config.TaskPoolConfig;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * @author lijiafu
 * @date 2019/6/24 16:26
 * @since 1.0
 */
public class ThreadPoolUtils {

    /**
     * 阻塞线程池
     */
    private ThreadPoolExecutor pool;

    public ThreadPoolUtils(TaskPoolConfig taskPoolConfig) {
        this.pool  = new ThreadPoolExecutor(taskPoolConfig.getCorePoolSize(), taskPoolConfig.getMaxPoolSize(), taskPoolConfig.getKeepAliveSeconds()
                , TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());;
    }

    /**
     * 异步执行任务
     * @author lijiafu
     * @date 2019/6/26 16:32
     */
    public void execute(Runnable command) {
        pool.execute(command);
    }

    /**
     * 停止线程
     * @author lijiafu
     * @date 2019/6/26 16:32
     */
    public void shutdown() {
        pool.shutdown();
    }

    /**
     * 线程池中线程数目
     * @author lijiafu
     * @date 2019/6/26 17:36
     */
    public int getPoolSize(){
        return pool.getPoolSize();
    }
    /**
     * 队列中等待执行的任务数目
     * @author lijiafu
     * @date 2019/6/26 17:36
     */
    public int getQueueSize(){
        return pool.getQueue().size();
    }
    /**
     * 已执行完的任务数目
     * @author lijiafu
     * @date 2019/6/26 17:36
     */
    public long getCompletedTaskCount(){
        return pool.getCompletedTaskCount();
    }
}