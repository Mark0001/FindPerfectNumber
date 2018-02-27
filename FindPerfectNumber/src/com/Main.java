package com;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> rank = new ArrayList<>();

    public static synchronized void addRank(final int number) {
        rank.add(number);
    }

    public static void main(final String[] args) {
        final long start = System.currentTimeMillis();
        final Worker w1 = new Worker(1, 8000000);
        w1.setName("w1");
        w1.start();
        final Worker w2 = new Worker(8000001, 17000000);
        w2.setName("w2");
        w2.start();

        final Worker w3 = new Worker(17000001, 25500000);
        w3.setName("w3");
        w3.start();
        final Worker w4 = new Worker(25500001, 34000000);
        w4.setName("w4");
        w4.start();

        try {
            w1.join();
            w2.join();
            w3.join();
            w4.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main執行緒結束");
        System.out.println(rank);
        final long end = System.currentTimeMillis();

        System.out.println("結束應用");
        System.out.println("共用了" + (end - start) + "毫秒");

    }
}
