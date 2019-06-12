package kmeans;

/**
 * 
 * @author florianwolf
 * 
 * This class represents a DataPoint in an n-dimensional space
 *
 */

public class DataPoint {
	// varArg/array to represent the vector coefficients
	private double[] vector;
	// counter, to get the amount of data points
	private static int counter = 0;
	
	// constructors
	public DataPoint(double... vector) {
		this.vector = vector;
		counter++;
	}
	
	// get/set methods
	
	/**
	 * @return amount of data points
	 */
	public static int getCounter() {
		return counter;
	}
	/**
	 * 
	 * @return vector coordinates
	 */
	public double[] getVector() {
		return this.vector;
	}
	
	/**
	 * returns dimension of the vector
	 * @return dimension
	 */
	public int getDimension() {
		return this.vector.length;
	}
	
	/**
	 * @param index
	 * @return vector coordinate
	 */
	public double getCoord(int index) {
		return vector[index];
	}
	
	// others
	
	/**
	 * @return prints vector
	 */
	public void printVector() {
		System.out.print("(");
		for(int j = 0 ; j < vector.length - 1; j++) {
			System.out.print(vector[j] + "/");
		}
		System.out.print(vector[vector.length -1]);
		System.out.print(")");
		//System.out.print("\n");
	}
	
	
}
