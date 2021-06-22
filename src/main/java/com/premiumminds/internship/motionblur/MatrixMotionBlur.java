package com.premiumminds.internship.motionblur;

/**
 * Class that provides matrix motion blur calculation
 * 
 * @author andre-f-silva
 */
public class MatrixMotionBlur {

	private MatrixMotionBlur() {
		//hide public constructor
	}

	/**
	 * Applies motion blur calculation of the pixel in the given position
	 * (M(x, y)+M(x-1, y)+M(x, y-1)+M(x, y+1)) / pixelsInvolved.
	 * Assumes natural order of 2d array, (0,0) is top left
	 * 
	 * @param data	matrix of integers
	 * @param x		x coordinate of the pixel
	 * @param y		y coordinate of the pixel
	 * @return motion blur value
	 * @throws ArrayIndexOutOfBoundsException when coordinates are wrong given data
	 */
	protected static int calculateMotionBlur(int[][] data, int x, int y) {

		//center
		int pixelsInvolved = 1;
		int result = data[y][x];

		//top pixel
		if(y != 0) {
			pixelsInvolved++;
			result += data[y-1][x];
		}

		//bottom pixel
		if(y != data.length - 1) {
			pixelsInvolved++;
			result += data[y+1][x];
		}

		//left pixel
		if(x != 0) {
			pixelsInvolved++;
			result += data[y][x-1];
		}

		//for tests
		//return the thread Id to visualize which thread calculated this
		//return (int) Thread.currentThread().getId();

		return Math.round((float) result/pixelsInvolved);
	}
}
