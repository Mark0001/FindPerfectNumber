package mark.findPerfectNumber;

public class Worker extends Thread {
    private int start;
    private int end;

    public Worker(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        for (int i = this.start; i <= this.end; i++) {
            //            System.out.println(Thread.currentThread().getName() + "正在看" + i + "是不是完全數");
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
                System.out.println(i + "是完美數字喔");
                MainUseTreadPool.addRank(i);
            }
        }

        //        System.out.println(list.toString());
    }

}
