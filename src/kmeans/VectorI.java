package kmeans;

/**
 * 
 * @author florianwolf
 *
 * more abstract interface to simulate an n-dimensional vector and its
 * corresponding methods
 */

public abstract interface VectorI {
	public double[] getVector();
	public int getDimension();
	public double getCoord(int i) throws Exception;
	public void printVector() throws Exception;
}
