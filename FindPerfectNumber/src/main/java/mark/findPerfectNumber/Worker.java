package mark.findPerfectNumber;

import org.apache.log4j.Logger;

public class Worker extends Thread {
    final static Logger logger = Logger.getLogger(Worker.class);

    private int start;
    private int end;

    public Worker(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        for (int i = this.start; i <= this.end; i++) {
            //            logger.info(Thread.currentThread().getName() + "正在看" + i + "是不是完全數");
            int temp = 1;
            for (int count = 2; count < i; count++) {
                if (count * count > i) {
                    break;
                }
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
                logger.info(i + "是完美數字喔");
                MainUseTreadPool.addRank(i);
            }
        }
    }

}
