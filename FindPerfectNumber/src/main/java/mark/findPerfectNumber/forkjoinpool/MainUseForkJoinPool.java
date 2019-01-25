package mark.findPerfectNumber.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.apache.log4j.Logger;

public class MainUseForkJoinPool {

    final static Logger logger = Logger.getLogger(MainUseForkJoinPool.class);

    private static int threadhold = 30;
    private static int max = 34000000;

    public static void main(final String[] args) {

        final ForkJoinPool pool = ForkJoinPool.commonPool();

        logger.info("Parallelism  => " + pool.getParallelism());
        final long start = System.nanoTime();
        final List<Integer> ans = new ArrayList<>();

        ans.addAll(pool.invoke(new WorkerForkJoin(1, max, threadhold)));
        logger.info(ans);
        pool.shutdown();

        final long end = System.nanoTime();
        final long timeInterval = end - start;
        logger.info("結束應用");
        logger.info("共用了" + timeInterval + "奈秒");
        logger.info("共用了" + timeInterval / 1000000 + "毫秒");
    }
}
