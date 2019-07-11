package algorithm;

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

public class Centroid extends Vector{
	// properties
	// list with referenced data Points
	private List<DataPoint> references = new ArrayList<DataPoint>();
	
	// constructors
	/**
	 * Constructor for a centroid with an empty list of references
	 * @param vector
	 */
	public Centroid(double[] vector) {
		super(vector);
		this.references = new ArrayList<DataPoint>();
	}
	
	
	// set/get methods
	/**
	 * returns the list with all references
	 * @return references
	 */
	public List<DataPoint> getReferences(){
		return this.references;
	}
	
	/**
	 * returns a Data Point assigned to the centroid to a given index
	 * @param index
	 * @return DataPoint
	 * @throws Exception
	 */
	public DataPoint getDataPoint(int index) throws Exception{
		if (index > references.size() || index < 0) {
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
	 * @return prints all DataPoints referenced to Centroid
	 * @throws Exception 
	 */
	public void printReferences() throws Exception {
		System.out.print("\ncen: ");
		printVector();
		System.out.print("\npoints: ");
		// iterate through assigned data points with list iterator
		Iterator<DataPoint> iter = this.getReferences().iterator();
		while(iter.hasNext()) {
			iter.next().printVector();
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	public DataPoint[] toArray() {
		return (DataPoint[]) this.references.toArray();
	}
	
	
}
