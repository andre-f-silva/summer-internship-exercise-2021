package com.premiumminds.internship.motionblur;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Created by aamado on 05-05-2021.
 * Implemented by asilva
 */
class MotionBlurSingleThread implements MotionBlurFactory {

	/**
	 * Method to start processing the data
	 * 
	 * @param data            matrix of integers
	 * @param numberOfWorkers number of threads that should work in parallel
	 * @return matrix of integers
	 */
	public Future<int[][]> run(int[][] data, int numberOfWorkers) {

		//maybe return null instead of exceptions
		if(data == null) 
			throw new IllegalArgumentException("data cant be null");
		if(numberOfWorkers <= 0)
			throw new IllegalArgumentException("numberOfWorkers must be > 0");

		//ignore numberOfWorkers, its 1 thread
		ExecutorService executor = Executors.newSingleThreadExecutor();

		int[][] motionBlurResult = new int[data.length][data[0].length];

		Future<int[][]> result = executor.submit(() -> IntStream.range(0, data.length*data[0].length)
				.forEach(
						i -> {
							int l = i / data[0].length;
							int c = i % data[0].length;
							motionBlurResult[l][c] = MatrixMotionBlur.calculateMotionBlur(data, c, l);
						})
				, motionBlurResult);

		executor.shutdown();

		return result;
	}
}
