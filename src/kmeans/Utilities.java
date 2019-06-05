package kmeans;

/**
 * 
 * @author florianwolf
 *
 * this class provides functions for the k-means algorithm
 *
 */

public class Utilities{	
	
	/**
	 * pNorm, calculates the p-norm between two vectors (refers to linear algebra II)
	 * @param vec1
	 * @param vec2  
	 * @return p-Norm
	 */
	public static double pNorm(double[] vec1, double[] vec2, int p) throws Exception{
		double dist;
		double sum = 0;
		if (vec1.length != vec2.length) {
			throw new Exception("Can't compute p norm, due to wrong dimensions of input vectors.");
		}
		else if(p <= 0) {
			throw new Exception("Illegal for p argument, to compute a norm p has to be greater than zero.");
		}
		else {
			//Iterate through the vector components
			for(int i = 0; i < vec1.length; i++) {
				sum = sum + Math.pow(Math.abs(vec1[i]-vec2[i]),p);
			}
			//pull the p-th root of the sum
			dist = Math.pow(sum, 1.0/p);
			
			return dist;	
		}
	}
	/**
	 * linNormalization, normalizes a data point in respect to the min/max values of the data
	 * @param value
	 * @return normalized data point
	 */
	public static double linNormalize(double value, double maxValue, double minValue) {
		//Declaration of output value
		double normalizedValue;
		//normalize the input value with formula (v-max)/(max-min)
		normalizedValue = (value - minValue)/(maxValue - minValue);
		return normalizedValue;
	}
	/**
	 * Calculates the index of centroid with minimal distance
	 * @param dataPoint
	 * @param meanVectors
	 * @param p (Norm)
	 * @return index
	 * @throws Exception
	 */
	public static int argMin(DataPoint dataPoint, Centroid[] meanVectors, int p) throws Exception {
		int index = 0;
		double dist;
		// current minimal distance (every Centroid has a minimum of one assigned data Point)
		dist = pNorm(dataPoint.getVector(), meanVectors[0].getVector(), p);
		// if he has more, iterate through the rest of them
		if (meanVectors.length > 1) {
			for (int i = 1; i < meanVectors.length; i++) {
				// if pNorm is smaller than current distance
				if( pNorm(dataPoint.getVector(), meanVectors[i].getVector(), p) < dist) {
					// update index and new current smaller distance
					dist = pNorm(dataPoint.getVector(), meanVectors[i].getVector(), p);
					index = i;
				}
			}
			
		}
		return index;
	}
	/**
	 * updates all centroids
	 * @param currCentroids
	 * @param newCentroids
	 * @param k
	 * @return updated centroids
	 * @throws Exception 
	 */
	public static Centroid[] upCurrCentroids(Centroid[] currCentroids, int k) throws Exception {
		Centroid[] tempArray;
		tempArray = new Centroid[currCentroids.length];
		// iterate through all centroids
		for(int j = 0; j < k; j++) {
			// update all of them one by one
//			System.out.print("Old Vector");
//			currCentroids[j].printCentroid();
			
			tempArray[j] = updateCentroid(currCentroids[j]);
			
//			System.out.print("New Vector");
//			tempArray[j].printCentroid();
		}
		return tempArray;
	}
	/**
	 * gets an centroid an updates his coordinates by taking the mean value
	 * of all coordinates with respect to all assigned data points
	 * @param currCentroid
	 * @return newCentroid
	 * @throws Exception
	 */
	public static Centroid updateCentroid(Centroid currCentroid) throws Exception {
		double[] temp;
		int dimension;
		// dimension, the new vector should have
		dimension = currCentroid.getDataPoint(0).getDimension();
		// temp vector, to hold new coordinates
		temp = new double[dimension];
		//iterate through all data points assigned to the current centroid
		for(int k = 0; k < currCentroid.getReferences().size(); k++) {
			for(int i = 0; i < dimension; i++) {
				// sum over all coordinates off all vectors
				temp[i] = temp[i] + currCentroid.getDataPoint(k).getCoord(i);
			}
		}
		// get mean value of each coordinate
		for(int l = 0; l < dimension; l++) {
			temp[l] = temp[l]/currCentroid.getReferences().size();
		}
		// construct new centroid
		return new Centroid(temp);
		
	}
	
	/**
	 * get old and new centroids, if the difference between one centroid to his new one
	 * is bigger than the threshold, than there is still change
	 * @param currCentroids
	 * @param newCentroids
	 * @param threshold
	 * @param p
	 * @return change
	 * @throws Exception
	 */
	public static boolean stillChange(Centroid[] currCentroids, Centroid[] newCentroids, double threshold, int p) throws Exception {
		// not the same number --> exception
		if (newCentroids.length != currCentroids.length) {
			throw new Exception("Number of current and new Centroids differs.");
		}
		else {
			boolean change = false;
			// iterate through every centroid
			for(int k = 0; k < newCentroids.length; k++){
				// look for changes between the old and the new ones
				//System.out.println("Abstand: " + pNorm(currCentroids[k].getVector() , newCentroids[k].getVector() , p));
				if (pNorm(currCentroids[k].getVector() , newCentroids[k].getVector() , p) > threshold) {
					// bigger than the threshold --> still changes ongoing
					change = true;
				}
			}
			return change;
		}
		
	}
	
	/**
	 * prints all centroids and their referenced data points
	 * @param finalCentroids
	 * @param k
	 */
	public static void printResults(Centroid[] finalCentroids, int k) {
		for(int i = 0; i < k; i++) {
			finalCentroids[i].printReferences();
		}
	}
	
	/**
	 * This function calculates the threshold by
	 * using the absolute minimal value and divides it by 1000
	 * @param points
	 * @return minCoeff
	 */
	public static double getMinComponent(DataPoint[] points) {
		// set to positive infinity, to get directly a smaller value
		double minCoeff = Double.POSITIVE_INFINITY;
		// iterate through all data points
		for(int i = 0; i < points.length; i++) {
			// iterate through every component of each vector
			for(int k = 0; k < points[i].getDimension(); k++) {
				// get minimal absolute value, value zero is not allowed
				if(Math.abs(points[i].getCoord(k)) < minCoeff && Math.abs(points[i].getCoord(k)) != 0) {
					minCoeff = Math.abs(points[i].getCoord(k));
				}
			}
		}
		// divide this value by 1000
		return minCoeff/1000.0;
	}
	
	/**
	 * This methods returns random centroids to initialize the algorithm,
	 * by switching the random points to the end and set these random ones to the new centroids
	 * @param points
	 * @param newCentroids
	 * @param k
	 * @return random Centroids
	 * @throws Exception
	 */
	public static Centroid[] getRandomCentroids(DataPoint[] points, Centroid[] newCentroids, int k) throws Exception{
		// if there should be more centroids than existing data points
		if (k > points.length) {
			throw new IllegalArgumentException("Initialize more data points or select less centroids");
		}
		else {
			// temp variable to hold random index for the datapoints
			int index;
			//DataPoint temp = new DataPoint();
			for(int i = 0; i < k; i++) {
				// get random index, possibilities reduced by each loop iteration
				// current last element is random centroid from iteration before
				index = (int) Math.random() * (points.length - i);
				// Switch selected data point to the end, use temp variable
				DataPoint temp = points[index];
				points[index] = points[points.length - (i+1)];
				points[points.length - (i+1)] = temp;
			}
			// set/initilize new Centroids
			for (int j = 0; j < k; j++) {
				newCentroids[j] = new Centroid(points[points.length - (j+1)].getVector());
			}
			// return centroids
			return newCentroids;
		}
		
		
	}
	
	
	
}
