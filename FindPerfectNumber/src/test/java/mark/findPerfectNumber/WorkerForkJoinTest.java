package mark.findPerfectNumber;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import mark.findPerfectNumber.forkjoinpool.WorkerForkJoin;

public class WorkerForkJoinTest {

    @Test
    public void testCall() {

        final WorkerForkJoin worker = new WorkerForkJoin(1, 30, 30);
        final List<Integer> ans = worker.invoke();
        final Integer[] ansArray = ans.toArray(new Integer[ans.size()]);
        System.out.println(ans);
        assertArrayEquals(new Integer[] { 6, 28 }, ansArray);
    }
}
