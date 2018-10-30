package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainUseTreadPool {

    private static List<Integer> rank = new ArrayList<>();

    private static int interval = 30;

    //    private static int max = 34000000;
    private static int max = 8000000;

    public static synchronized void addRank(final int number) {
        rank.add(number);
    }

    public static void main(final String[] args) throws InterruptedException {

        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("我可以使用的邏輯處理器" + availableProcessors);
        final ExecutorService executor = Executors.newFixedThreadPool(availableProcessors - 2); // 不想用100% cpu跑

        final long start = System.currentTimeMillis();
        for (int i = 1; i < max; i += interval) {
            final Worker worker = new Worker(i, i + (interval - 1));
            executor.execute(worker);
        }

        executor.shutdown();

        if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
            System.out.println("main執行緒結束");
            System.out.println(rank);
            final long end = System.currentTimeMillis();

            System.out.println("結束應用");
            System.out.println("共用了" + (end - start) + "毫秒");
        }

    }
}
