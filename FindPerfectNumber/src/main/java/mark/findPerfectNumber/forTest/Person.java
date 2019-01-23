package mark.findPerfectNumber.forTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import mark.findPerfectNumber.WorkerCallable;

public class Person extends Thread {

    private String name;

    final static Logger logger = Logger.getLogger(Person.class);

    private static List<Integer> rank = new ArrayList<>();

    private static int interval = 30;

    private static int max = 8000000;

    public Person(final String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        logger.info(this.name + " 我可以使用的邏輯處理器：" + availableProcessors);
        final int usedProcessors = availableProcessors - 2;
        logger.info(this.name + " 我使用的邏輯處理器個數：" + usedProcessors);
        final ExecutorService executor = Executors.newFixedThreadPool(usedProcessors);// 不想用100% cpu 跑

        final List<Future<List<Integer>>> futures = new ArrayList<>();
        final long start = System.nanoTime();
        for (int i = 1; i < max; i += interval) {
            futures.add(executor.submit(new WorkerCallable(i, i + (interval - 1))));
        }
        try {
            for (final Future<List<Integer>> future : futures) {
                if (future.isDone() && !future.get().isEmpty()) {
                    rank.addAll(future.get());
                }
            }
        } catch (final ExecutionException e) {
            logger.error(e.getMessage(), e);
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        executor.shutdown();

        try {
            if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {

                logger.info(this.name + "main執行緒結束");
                logger.info(this.name + "：" + rank);
                final long end = System.nanoTime();

                final long timeInterval = end - start;
                logger.info(this.name + " 結束應用");
                logger.info(this.name + " 共用了" + timeInterval + "奈秒");
                logger.info(this.name + " 共用了" + timeInterval / 1000000 + "毫秒");
            }
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
