package kmeans;

public class Vector implements VectorI{
	
	private double[] coordinates;
	
	public Vector(double... coordinates) {
		this.coordinates = coordinates;
	}
	
	public double[] getVector() {
		return this.coordinates;
	}
	
	public int getDimension() {
		return this.coordinates.length;
	}
	
	public double getCoordinate(int index) throws Exception {
		if(index < 0 || index > this.getDimension()) {
			throw new Exception("Index too high or negative.");
		}
		else {
			return this.coordinates[index];
		}
	}
	public void printVector() throws Exception{
		System.out.print("(");
		for(int j = 0 ; j < this.getDimension() - 1; j++) {
			System.out.print(this.getCoordinate(j) + "/");
		}
		System.out.print(this.getCoordinate(this.getDimension() - 1));
		System.out.print(")");
	}

}
