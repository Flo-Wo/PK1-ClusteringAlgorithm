package gui;

import java.util.Iterator;
import java.util.List;

import algorithm.Centroid;
import algorithm.DataPoint;
import algorithm.Utilities;
import algorithm.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Utilities class for the GUI of the k-means visualization
 * @author florianwolf
 * 
 */
public class GuiUtilities {
	
	
	// static methods
	
	/**
	 * Print data Points at the beginning of the Gui algorithm 
	 * @param points
	 * @param root
	 * @param normVal
	 * @throws Exception
	 */
	public static void printPoints(DataPoint[] points, Pane root, double[] normVal, int scalar, double size) throws Exception {
		
		for(int i = 0; i < points.length; i++){
			GuiUtilities.printDP(points[i], root, normVal, scalar, size);
		}
	}
	/**
	 * Print a single data point
	 * @param point
	 * @param root
	 * @param normVal
	 * @throws Exception
	 */
	public static void printDP(DataPoint vector, Pane root, double[] normVal, int scalar, double size) throws Exception {
		Rectangle rec = new Rectangle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		rec.setX(scalar * Utilities.linNormalize(vector.get2DCoord()[0], normVal[0], normVal[1]) + 20);
		rec.setY(scalar * Utilities.linNormalize(vector.get2DCoord()[1], normVal[0], normVal[1]) + 20);
		rec.setHeight(size);
		rec.setWidth(size);
		rec.setFill(Color.WHITE);
		rec.setStroke(Color.BLACK);
		root.getChildren().add(rec);
	}
	
	/**
	 * Print data Points with color
	 * @param points
	 * @param root
	 * @param normVal
	 * @throws Exception
	 */
	public static void printPoints(List<DataPoint> points, Pane root, double[] normVal, Color colour, int scalar, double size) throws Exception {
		// iterate through the list of points and print every point
		Iterator<DataPoint> iter = points.iterator();
		while(iter.hasNext()) {
			GuiUtilities.printDP(iter.next(), root, normVal, colour, scalar, size);
		}
	}
	
	/**
	 * Print a single data point, filled with colour
	 * @param point
	 * @param root
	 * @param normVal
	 * @param colour
	 * @throws Exception
	 */
	public static void printDP(Vector vector, Pane root, double[] normVal, Color colour, int scalar, double size) throws Exception {
		Rectangle rec = new Rectangle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		rec.setX(scalar* Utilities.linNormalize(vector.get2DCoord()[0], normVal[0], normVal[1]) + 20);
		rec.setY(scalar* Utilities.linNormalize(vector.get2DCoord()[1], normVal[0], normVal[1]) + 20);
		rec.setHeight(size);
		rec.setWidth(size);
		rec.setFill(colour);
		rec.setStroke(Color.BLACK);
		root.getChildren().add(rec);
	}
	
	/**
	 * Prints a single Centroid as a color filled circle
	 * @param vector
	 * @param root
	 * @param normVal
	 * @param color
	 * @throws Exception
	 */
	public static void printC(Vector vector, Pane root, double[] normVal, Color colour, int scalar, double size) throws Exception {
		Circle circ = new Circle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		circ.setCenterX(scalar* Utilities.linNormalize(vector.get2DCoord()[0], normVal[0], normVal[1]) + 20);
		circ.setCenterY(scalar* Utilities.linNormalize(vector.get2DCoord()[1], normVal[0], normVal[1]) + 20);
		circ.setRadius(size + scalar/200);
		circ.setFill(colour);
		circ.setStroke(Color.BLACK);
		circ.toFront();
		root.getChildren().add(circ);
	}
	/**
	 * Prints the final results of the algorithm, including the data points and the centroids (coloured)
	 * @param finalResult
	 * @param root
	 * @param normVal
	 * @param colours
	 * @throws Exception
	 */
	public static void printResults(Centroid[] finalResult, Pane root, double[] normVal, List<Color> colours, int scalar, double size) throws Exception {
		for(int i = 0; i < finalResult.length; i++) {
        	GuiUtilities.printC(finalResult[i], root, normVal, colours.get(i), scalar, size);
        	
        	GuiUtilities.printPoints(finalResult[i].getReferences(), root, normVal, colours.get(i), scalar, size);
        }
	}
}
