package com;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread {
    private int start;
    private int end;

    public Worker(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        final List<Integer> list = new ArrayList<Integer>();

        for (int i = this.start; i <= this.end; i++) {
            //            System.out.println(Thread.currentThread().getName() + "正在看" + i + "是不是完全數");
            int temp = 1;
            for (int count = 2; count < Math.sqrt(i); count++) {
                if (i % count == 0) {
                    temp = temp + count;
                    final int temp1 = i / count;
                    if (count != temp1) {
                        temp = temp + temp1;
                    }
                }
                if (temp > i) {
                    break;
                }
            }
            if (temp == i) {
                if (temp == 1) {
                    continue;
                }
                System.out.println(i + "是完美數字喔");
                list.add(i);
                MainUseTreadPool.addRank(i);
            }
        }

        //        System.out.println(list.toString());
    }

    public static void main(final String[] args) {
        final long start = System.currentTimeMillis();

        final List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i <= 34000000; i++) {
            System.out.println(i);
            int temp = 1;
            for (int count = 2; count < Math.sqrt(i); count++) {
                if (i % count == 0) {
                    temp = temp + count;
                    final int temp1 = i / count;
                    if (count != temp1) {
                        temp = temp + temp1;
                    }
                }
                if (temp > i) {
                    break;
                }
            }
            if (temp == i) {
                if (temp == 1) {
                    continue;
                }
                list.add(i);
                continue;
            }
        }

        System.out.println(list.toString());

        final long end = System.currentTimeMillis();

        System.out.println("結束應用");
        System.out.println("共用了" + (end - start) + "豪秒");
    }
}
