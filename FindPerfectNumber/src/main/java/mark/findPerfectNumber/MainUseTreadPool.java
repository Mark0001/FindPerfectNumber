package mark.findPerfectNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class MainUseTreadPool {
    final static Logger logger = Logger.getLogger(WorkerCallable.class);

    private static List<Integer> rank = new ArrayList<>();

    private static int interval = 30;

    private static int max = 34000000;
    //    private static int max = 8000000;

    public static synchronized void addRank(final int number) {
        rank.add(number);
    }

    public static List<Integer> getRank() {
        return rank;
    }

    public static void main(final String[] args) throws InterruptedException {

        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final int usedProcessors = availableProcessors - 2;
        logger.info("我使用的邏輯處理器個數：" + usedProcessors);
        final ExecutorService executor = Executors.newFixedThreadPool(usedProcessors);// 不想用100% cpu 跑

        final long start = System.nanoTime();
        for (int i = 1; i < max; i += interval) {
            final Worker worker = new Worker(i, i + (interval - 1));
            executor.execute(worker);
        }

        executor.shutdown();

        if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
            logger.info("maincd 執行緒結束");
            logger.info(rank);
            final long end = System.nanoTime();

            final long timeInterval = end - start;
            logger.info("結束應用");
            logger.info("共用了" + timeInterval + "奈秒");
            logger.info("共用了" + timeInterval / 1000000 + "毫秒");
        }

    }
}
