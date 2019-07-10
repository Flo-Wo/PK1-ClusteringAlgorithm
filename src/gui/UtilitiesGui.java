package gui;

import algorithm.DataPoint;
import algorithm.Utilities;

public class UtilitiesGui {
	/**
	 * This method return the min/max values of the first two vector components of the data set
	 * used to linear normlize the data
	 * @param points
	 * @return xMin, xMax, yMin, yMax
	 * @throws Exception
	 */
	public static double[][] getRange(DataPoint[] points) throws Exception{
		double xMin = 0;
		double xMax = 0;
		double yMin = 0;
		double yMax = 0;
		
		// iterate through all Data Points
		for(int i = 0; i < points.length; i++) {
			// iterate through the first and second vector component
			if(points[i].getCoord(0) < xMin) {
				xMin = points[i].getCoord(0);
			}
			if(points[i].getCoord(0) > xMax) {
				xMax = points[i].getCoord(0);
			}
			if(points[i].getCoord(1) < yMin) {
				yMin = points[i].getCoord(0);
			}
			if(points[i].getCoord(1) > yMax) {
				yMax = points[i].getCoord(0);
			}
		}
		double[][] bounds = {{xMin, xMax}, {yMin, yMax}};
		return bounds;
	}
}
