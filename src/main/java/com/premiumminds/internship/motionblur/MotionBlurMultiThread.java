package com.premiumminds.internship.motionblur;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Created by aamado on 05-05-2021.
 * Implemented by asilva
 */
class MotionBlurMultiThread implements MotionBlurFactory {

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

		//parallel streams use a forkJoin pool
		ExecutorService executor = new ForkJoinPool(numberOfWorkers);

		int[][] motionBlurResult = new int[data.length][data[0].length];

		//using .parallel(), the stream numbers are automatically assigned to threads in the pool
		//no need to create and assign tasks to threads manually
		Future<int[][]> result = executor.submit(() -> IntStream.range(0, data.length*data[0].length)
				.parallel()
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
