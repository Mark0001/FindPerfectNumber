package mark.findPerfectNumber;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import work.Work;

public class WorkerCallable extends Work implements Callable<List<Integer>> {
    final static Logger logger = Logger.getLogger(WorkerCallable.class);

    public WorkerCallable(final int start, final int end) {
        super(start, end);
    }

    @Override
    public List<Integer> call() {
       return super.getPerfactNumber();
    }
}
