package kmeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author florianwolf
 *
 * This class mimics a Centroid for the k-means algorithm (a n-dimensional vector with a list to referenced DataPoints)
 *
 */

public class Centroid {
	// properties
	// array to represent coordinates of the vector
	private double[] vector;
	
	// list with referenced data Points
	private List<DataPoint> references = new ArrayList<DataPoint>();
	
	// constructors
	public Centroid(double[] vector) {
		this.references = new ArrayList<DataPoint>();
		this.vector = vector;
	}
	
	
	// set/get methods
	
	/**
	 * gets a specific coordinate of a vector
	 * @param index
	 * @return vector coordinate to this index
	 */
	public double getCoord(int index) {
		return(vector[index]);
	}
	
	/**
	 * 
	 * @return vector coordinates
	 */
	public double[] getVector() {
		return this.vector;
	}
	
	/**
	 * returns the list with all references
	 * @return references
	 */
	public List<DataPoint> getReferences(){
		return this.references;
	}
	
	/**
	 * returns a Data Point assigned to the centroid ro a given index
	 * @param index
	 * @return DataPoint
	 * @throws Exception
	 */
	public DataPoint getDataPoint(int index) throws Exception{
		if (index > references.size()) {
			throw new Exception("No element with this index.");
		}
		else {
			return references.get(index);
		}
	}
	/**
	 * adds a DataPoint to the reference list
	 * @param dataPoint
	 */
	public void addDataPoint(DataPoint dataPoint) {
		this.references.add(dataPoint);
	}
	
	// others
	
	/**
	 * @return prints centroid as vector
	 */
	public void printCentroid() {
		System.out.print("(");
		for(int j = 0 ; j < vector.length - 1; j++) {
			System.out.print(vector[j] + "/");
		}
		System.out.print(vector[vector.length -1]);
		System.out.print(")\n");
	}
	
	/**
	 * @return prints all DataPoints referenced to Centroid
	 */
	public void printReferences() {
		System.out.print("\ncen: ");
		printCentroid();
		System.out.print("points: ");
		// iterate through assigned data points with list iterator
		Iterator<DataPoint> iter = this.getReferences().iterator();
		while(iter.hasNext()) {
			iter.next().printVector();
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	
}
