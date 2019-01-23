package mark.findPerfectNumber.forTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainForPerson {
    public static void main(final String[] args) {

        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 1; i++) {
            final Person p = new Person("P" + i);
            executor.execute(p);
        }

        executor.shutdown();
    }
}
