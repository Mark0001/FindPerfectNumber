package mark.findPerfectNumber;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class WorkerCallableTest {

    @Test
    public void testCall() {
        final WorkerCallable worker = new WorkerCallable(1, 30);
        final List<Integer> ans = worker.call();
        final Integer[] ansArray = ans.toArray(new Integer[ans.size()]);
        System.out.println(ans);
        assertArrayEquals(new Integer[] { 6, 28 }, ansArray);
    }
}
