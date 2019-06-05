package kmeans;

/**
 * 
 * @author florianwolf
 *
 * this class provides functions for the k-means algorithm
 *
 */

public class MainKmeans {
	public static void main(String[] args) throws Exception{
		
		// initialize data points
		DataPoint[] points = {new DataPoint(1.0, 1.0),
		new DataPoint(1.0, 2.0), new DataPoint(2.0, 2.0), new DataPoint(2.0, 1.0),
		new DataPoint(4.0, 5.0),new DataPoint(4.0, 4.0), new DataPoint(5.0, 4.0),
		new DataPoint(5.0, 5.0)};
		
		// threshold to check for change
		double threshold = Utilities.getMinComponent(points);
		
		// value for p-Norm
		int p = 2;
		
		// amount of centroids
		int k = 2;
		
		
		//current Centroids
		Centroid[] currCentroids;
		currCentroids = new Centroid[k];
		
		// array, to hold updated centroids, to check for changes
		Centroid[] newCentroids;
		newCentroids = new Centroid[k];
		
		
//		newCentroids = Utilities.getRandomCentroids(newCentroids, points, k);
//		for(int i = 0; i< k; i++) {
//			newCentroids[i].printCentroid();
////		}
//		for(int i = 0; i< k; i++) {
//			newCentroids[i].printCentroid();
//		}
		
		newCentroids = Utilities.getRandomCentroids(points, newCentroids, k);
		
		do {
			
			// repeat part
			currCentroids = newCentroids;
			
			
			
			int index;
			//assign all data points to closest centroid
			for(int j=0; j < points.length; j++) {
				index = Utilities.argMin(points[j], currCentroids, p);
				//System.out.println("Index: " + index);
				currCentroids[index].addDataPoint(points[j]);
				//currCentroids[1].printReferences();
			}
//			currCentroids[0].printReferences();
//			currCentroids[1].printReferences();
			newCentroids = Utilities.upCurrCentroids(currCentroids, k);
			
//			System.out.println("Test 1:");
//			for(int i = 0; i< k; i++) {
//				currCentroids[i].printCentroid();
//			}
//			System.out.println("Test 2:");
//			for(int i = 0; i< k; i++) {
//				newCentroids[i].printCentroid();
//			}
//			
//			System.out.println("Test: " + Utilities.stillChange(currCentroids, newCentroids, threshold, p));
//			System.out.print("\n\n");
		}while(Utilities.stillChange(currCentroids, newCentroids, threshold, p));
		System.out.println("Result:");
		Utilities.printResults(currCentroids, k);
		
	}
	
}
