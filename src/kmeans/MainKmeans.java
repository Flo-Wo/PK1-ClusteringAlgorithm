package kmeans;
import readFile.FileReader;;
/**
 * 
 * @author florianwolf
 *
 * main class for k-means algorithm
 *
 */

public class MainKmeans {
	public static void main(String[] args) throws Exception{
		
		double[][] vectors = FileReader.returnVectors("DataPoints.txt");
		DataPoint[] points = Utilities.initDataPoints(vectors);
		
		// threshold to check for change
		double threshold = Utilities.getMinComponent(points);
		
		// value for p-Norm
		int p = 2;
		
		// amount of centroids
		int k = 4;
		
		
		//current Centroids
		Centroid[] currCentroids;
		currCentroids = new Centroid[k];
		
		// array, to hold updated centroids, to check for changes
		Centroid[] newCentroids;
		newCentroids = new Centroid[k];
		
		
		newCentroids = Utilities.getRandomCentroids(points, newCentroids, k);
		
		do {
			
			// repeat part
			currCentroids = newCentroids;
			
			int index = 0;
			// assign all data points to closest centroid
			for(int j=0; j < points.length; j++) {
				index = Utilities.argMin(points[j], currCentroids, p);
				currCentroids[index].addDataPoint(points[j]);
			}
			// update all centroids
			newCentroids = Utilities.upCurrCentroids(currCentroids, k);
			// check for changes
		}while(Utilities.stillChange(currCentroids, newCentroids, threshold, p));
		// print result
		System.out.print("Result:");
		Utilities.printResults(currCentroids, k);
		
	}
	
}
