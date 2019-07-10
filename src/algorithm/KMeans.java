package algorithm;
import java.util.ArrayList;
import java.util.List;

import readFile.FileReader;;
/**
 * 
 * @author florianwolf
 *
 * main class for k-means algorithm
 *
 */

public class KMeans {
	
	// properties
	private double[][] vectors;
	private DataPoint[] points;
	
	// threshold to check for change
	private double threshold;
	
	// value for p-Norm
	private int p;
	
	// amount of centroids
	private int k;
	
	
	//current Centroids
	private Centroid[] currCentroids;
	
	// array, to hold updated centroids, to check for changes
	private Centroid[] newCentroids;
	
	// Hold Data for GUI implementation
	private List<Centroid[]> DataBuffer = new ArrayList<Centroid[]>();
	
	//+++++++++++ constructors ++++++++++++
	
	/**
	 * Constructor for KMeans Algorithm
	 * @param filename
	 * @param k
	 * @param p
	 * @throws Exception
	 */
	public KMeans(String filename, int k, int p) throws Exception {
		this.k = k;
		
		this.p = p;
		// read file
		this.vectors = FileReader.returnVectors(filename);
		// turn read in vectors into Data Points
		this.points = Utilities.initDataPoints(this.vectors);
		
		// initialize threshold to check for change
		this.threshold = Utilities.getThreshold(this.points);
		
		
		//current Centroids
		this.currCentroids = new Centroid[k];
		
		// array, to hold updated centroids, to check for changes
		this.newCentroids = new Centroid[k];
		
		// init random Centroids to Start-algorithm
		this.newCentroids = Utilities.getRandomCentroids(this.points, this.newCentroids, k);
		
		// add init centroids with Circles
		this.DataBuffer.add(newCentroids);
	}
	
	
	//+++++++++++ get/set Methods ++++++++++++
	
	/**
	 * Method to return the data points, to init the first GUI representation
	 * @return DataPoints
	 */
	public DataPoint[] getDataPoints() {
		return this.points;
	}
	
	/**
	 * Method to get the dataBuffer, after the algorithm finished
	 * @return DataBuffer
	 */
	public List<Centroid[]> getBuffer(){
		return this.DataBuffer;
	}
	
	
	/**
	 * Add current setting of the algorithm to the list buffer
	 * @param currentCentroids
	 */
	public void addBuffer(Centroid[] currentCentroids) {
		this.DataBuffer.add(currentCentroids);
	}
	
	
	
	//+++++++++++ others ++++++++++++
	
	/**
	 * Runs the Algorithm
	 * @throws Exception
	 */
	public void runAlgo() throws Exception {
		// repeat part
		do {
			
			// assign current centroids to new centroids
			this.currCentroids = this.newCentroids;
			
			int index = 0;
			// assign all data points to closest centroid
			for(int j = 0; j < this.points.length; j++) {
				index = Utilities.argMin(this.points[j], this.currCentroids, this.p);
				this.currCentroids[index].addDataPoint(this.points[j]);
			}
			// update all centroids
			this.newCentroids = Utilities.upCurrCentroids(this.currCentroids, this.k);
			
			
			// add current situation to the buffer for the GUI
			// added Centroids and their assigned data points
			this.DataBuffer.add(newCentroids);
			
			
			
			// check for changes
		}while(Utilities.stillChange(this.currCentroids, this.newCentroids, this.threshold, this.p));
		// print result
		System.out.print("Result:");
		Utilities.printResults(this.currCentroids, this.k);
	}
	
	
	
//	/**
//	 * Main Method to test the programm
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception{
//		KMeans algo = new KMeans("DataTestBig.txt", 4, 2);
//		algo.runAlgo();
//	}
//	
	
}
