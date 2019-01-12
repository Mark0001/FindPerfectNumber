package com;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

public class WorkerTest {

	@Test
	public void testRun() {
		try {
			Worker worker = new Worker(1, 10);
			worker.start();
			worker.join();
			System.out.println(MainUseTreadPool.getRank());
			List<Integer> ans =  MainUseTreadPool.getRank();
			Integer[] ansArray =ans.toArray(new Integer[ans.size()]);
			assertArrayEquals(new Integer[] {6}, ansArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
