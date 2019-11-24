package mark.findPerfectNumber;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	private static List<Integer> rank = new ArrayList<>();

	public static synchronized void addRank(final int number) {
		rank.add(number);
	}

	public static void main(final String[] args) {
		final long start = System.currentTimeMillis();
		final Worker w1 = new Worker(1, 8000000);
		Thread tw1 = new Thread(w1);
		tw1.setName("w1");
		tw1.start();

		final Worker w2 = new Worker(8000001, 17000000);
		Thread tw2 = new Thread(w2);
		tw2.setName("w2");
		tw2.start();

		final Worker w3 = new Worker(17000001, 25500000);
		Thread tw3 = new Thread(w3);
		tw3.setName("w3");
		tw3.start();

		final Worker w4 = new Worker(25500001, 34000000);
		Thread tw4 = new Thread(w4);
		tw4.setName("w4");
		tw4.start();

		try {
			tw1.join();
			tw2.join();
			tw3.join();
			tw4.join();
		} catch (final InterruptedException e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("main執行緒結束");
		logger.info(rank);
		final long end = System.currentTimeMillis();

		logger.info("結束應用");
		logger.info("共用了" + (end - start) + "毫秒");

	}
}
