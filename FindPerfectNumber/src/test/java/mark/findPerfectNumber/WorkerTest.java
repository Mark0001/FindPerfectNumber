package mark.findPerfectNumber;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

public class WorkerTest {

    @Test
    public void testRun() {
        try {
            final Worker worker = new Worker(1, 30);
            Thread tWorker = new Thread(worker);
            tWorker.start();
            tWorker.join();
            System.out.println(MainUseTreadPool.getRank());
            final List<Integer> ans = MainUseTreadPool.getRank();
            final Integer[] ansArray = ans.toArray(new Integer[ans.size()]);
            assertArrayEquals(new Integer[] { 6, 28 }, ansArray);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
