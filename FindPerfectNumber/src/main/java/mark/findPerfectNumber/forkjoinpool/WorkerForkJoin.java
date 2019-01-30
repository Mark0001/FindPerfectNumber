package mark.findPerfectNumber.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;

public class WorkerForkJoin extends RecursiveTask<List<Integer>> {
    private static final long serialVersionUID = 1L;

    final static Logger logger = Logger.getLogger(WorkerForkJoin.class);

    private int threshold;
    private int start;
    private int end;

    public WorkerForkJoin(final int start, final int end, final int threshold) {
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected List<Integer> compute() {

        if (this.end - this.start <= this.threshold) {
            //            logger.info(this.start + "~~" + this.end);
            return this.findAnswer(this.start, this.end);
        } else {
            return divideWorks();
        }
    }

    private List<Integer> divideWorks() {
        final List<Integer> ans = new ArrayList<>();

        final int middle = (this.start + this.end) / 2;

        final WorkerForkJoin w1 = new WorkerForkJoin(this.start, middle, this.threshold);
        final WorkerForkJoin w2 = new WorkerForkJoin(middle + 1, this.end, this.threshold);

        invokeAll(w1, w2);

        ans.addAll(w1.join());
        ans.addAll(w2.join());
        return ans;
    }

    private List<Integer> findAnswer(final int start, final int end) {
        final List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
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
                } else {
                    continue;
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
                result.add(i);
            }
        }
        return result;
    }
}
