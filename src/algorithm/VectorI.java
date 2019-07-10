package algorithm;

/**
 * 
 * @author florianwolf
 *
 * more abstract interface to simulate an n-dimensional vector and its
 * corresponding methods
 */

public interface VectorI {
	double[] getVector();
	int getDimension();
	double getCoord(int i) throws Exception;
	void printVector() throws Exception;
}
