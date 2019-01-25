package mark.findPerfectNumber.badcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;

public class WorkerCallableParallelStream implements Callable<List<Integer>> {
    final static Logger logger = Logger.getLogger(WorkerCallableParallelStream.class);

    private int start;
    private int end;

    public WorkerCallableParallelStream(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() {
        final List<Integer> result = new ArrayList<>();
        IntStream.rangeClosed(this.start, this.end).parallel().forEach(v -> {
            final AtomicInteger tempp = new AtomicInteger(1);
            IntStream.rangeClosed(2, v).parallel().filter((count) -> count * count < v && v % count == 0).forEach(count -> {
                tempp.addAndGet(count);
                final int temp1 = v / count;
                if (count != temp1) {
                    tempp.addAndGet(temp1);
                }
            });

            if (tempp.get() == v) {
                if (tempp.get() == 1) {
                    return;
                }
                logger.info(v + "是完美數字喔");
                result.add(v);
            } else {
                return;
            }
        });
        return result;
    }

    public static void main(final String[] args) {
        //        final List<Integer> inNumber = new ArrayList<>();
        //        final AtomicInteger tempp = new AtomicInteger(1);
        //        final int v = 8128;
        //        IntStream.rangeClosed(2, v).parallel().filter((count) -> count * count < v && v % count == 0).forEach(count -> {
        //            tempp.addAndGet(count);
        //            final int temp1 = v / count;
        //            inNumber.add(temp1);
        //            if (count != temp1) {
        //                tempp.addAndGet(temp1);
        //            }
        //            inNumber.add(count);
        //        });
        //
        //        System.out.println(tempp.get());
        //        System.out.println(inNumber);

        final List<Integer> result = new ArrayList<>();
        IntStream.rangeClosed(1, 8128).parallel().forEach(v -> {
            final AtomicInteger tempp = new AtomicInteger(1);
            IntStream.rangeClosed(2, v).parallel().filter((count) -> count * count < v && v % count == 0).forEach(count -> {
                tempp.addAndGet(count);
                final int temp1 = v / count;
                if (count != temp1) {
                    tempp.addAndGet(temp1);
                }
            });

            if (tempp.get() == v) {
                if (tempp.get() == 1) {
                    return;
                }
                logger.info(v + "是完美數字喔");
                result.add(v);
            } else {
                return;
            }
        });

        System.out.println(result);
    }
}
