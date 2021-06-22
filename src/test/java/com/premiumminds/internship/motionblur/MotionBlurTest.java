package com.premiumminds.internship.motionblur;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2021.
 * Completed by andre-f-silva
 */
@RunWith(JUnit4.class)
public class MotionBlurTest {

	/**
	 * The corresponding implementations to test.
	 *
	 * If you want, you can make others :)
	 *
	 */
	public MotionBlurTest() {
	};

	private int M1result[][] = { { 3, 3, 4 }, { 4, 5, 6 }, { 6, 7, 8 } };

	private int[][] M2result = {
			{11, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 13, 13, 13},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{7, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
			{14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{11, 14, 13, 13, 13, 12, 12, 12, 11, 11, 11, 10, 10, 10, 9, 9, 9, 8, 8, 8}
	};

	private int[][] M3result = {
			{123}
	};

	private int[][] M4result = {
			{3, 3},
			{4, 5},
			{6, 7}
	};

	@Test
	public void MotionBlurSingleThreadM1Test()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M1, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M1result));
	}

	@Test
	public void MotionBlurSingleThreadM2Test()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M2, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M2result));
	}

	@Test
	public void MotionBlurSingleThreadM3Test()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M3, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M3result));
	}

	@Test
	public void MotionBlurSingleThreadM4Test()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M4, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M4result));
	}

	@Test
	public void MotionBlurMultiThreadM1With5WorkersTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 5);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M1result));
	}

	@Test
	public void MotionBlurMultiThreadM2With5WorkersTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M2, 5);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M2result));
	}

	@Test
	public void MotionBlurMultiThreadM3With3WorkerTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M3, 3);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M3result));
	}

	@Test
	public void MotionBlurMultiThreadM4With6WorkerTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M4, 6);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M4result));
	}

	@Test
	public void MotionBlurMultiThreadM2With1WorkerTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		//multi thread with 1 thread should still work, like single thread
		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M2, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M2result));
	}

	@Test
	public void MotionBlurMultiThreadM1WithMoreWorkersThanPixelsTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		//more threads then pixels
		Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 10);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, M1result));
	}

	@Test
	public void MotionBlurEmptyMatrixTest()
			throws InterruptedException, ExecutionException, TimeoutException {

		int[][] empty = {{}};
		int[][] expected = {{}};

		Future<int[][]> step1 = new MotionBlurSingleThread().run(empty, 1);
		int[][] result = step1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result, expected));

		Future<int[][]> multiStep1 = new MotionBlurMultiThread().run(empty, 5);
		int[][] result2 = multiStep1.get(10, TimeUnit.SECONDS);
		assertTrue(Arrays.deepEquals(result2, expected));
	}

	@Test(expected=IllegalArgumentException.class)
	public void MotionBlurInvalidArgumentsTest() {

		//expecting IllegalArgumentException
		//or assertNull if the code changes

		Future<int[][]> nullMatrixSingle = new MotionBlurSingleThread().run(null, 1);

		Future<int[][]> negativeWorkersSingle = new MotionBlurSingleThread().run(MatrixData.M1, -1);

		Future<int[][]> nullMatrixMulti = new MotionBlurMultiThread().run(null, 5);

		Future<int[][]> negativeWorkersMulti = new MotionBlurMultiThread().run(MatrixData.M1, -1);

		fail("IllegalArgumentException not thrown");
	}

	private void printHelper(int[][] matrix) {
		for (int l = 0; l < matrix.length; l++) {
			for (int c = 0; c < matrix[0].length; c++) {
				System.out.print(matrix[l][c] + " ");
			}
			System.out.println(" ");
		}
	}

}