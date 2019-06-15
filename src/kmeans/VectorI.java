package kmeans;

public interface VectorI {
	public double[] getVector();
	public int getDimension();
	public double getCoordinate(int i) throws Exception;
	public void printVector() throws Exception;
}
