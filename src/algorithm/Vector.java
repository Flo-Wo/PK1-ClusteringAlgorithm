package algorithm;

/**
 * 
 * @author florianwolf
 * implementation of the vector interface
 * realization of an n-dimensional vector in space
 * used to represent a data point for the k-means algorithm
 *
 */
// there should not be an instance of the class Vectors
public abstract class Vector implements VectorI{
	
	// properties
	private double[] coordinates;
	
	// constructors
	/**
	 * Constructor
	 * @param coordinates
	 */
	public Vector(double... coordinates) {
		this.coordinates = coordinates;
	}
	
	// set/get methods
	/**
	 * Get vector
	 * @return array (vector)
	 */
	public double[] getVector() {
		return this.coordinates;
	}
	/**
	 * Returns the dimension of a vector
	 * @return dimension
	 */
	public int getDimension() {
		return this.coordinates.length;
	}
	/**
	 * Returns the vector coordinate to a specific index
	 * @param index
	 * @return coordinate
	 * @throws Exception
	 */
	public double getCoord(int index) throws Exception {
		if(index < 0 || index >= this.getDimension()) {
			throw new Exception("Index too high or negative.");
		}
		else {
			return this.coordinates[index];
		}
	}
	/**
	 * Method to print a vector
	 * @return printed vector
	 * @throws Exception
	 */
	public void printVector() throws Exception{
		System.out.print("(");
		for(int j = 0 ; j < this.getDimension() - 1; j++) {
			System.out.print(this.getCoord(j) + "/");
		}
		System.out.print(this.getCoord(this.getDimension() - 1));
		System.out.print(")");
	}

}
