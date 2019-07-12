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
 * This class includes the following methods and variables:
 * 	- colourSet
 * 	- getColourSet
 * 	- printPoints
 * 	- printDP
 * 	- printC
 * 	- printResults
 * 	- createColourSet
 * 
 */
public class GuiUtilities {
	
	// properties
	
	// Note: this was not coded by hand (wrote a small python script to generate these colours)
	
	private static final String[] colourSet = new String[]{
	        "#FEFFE6", "#FFFF00", "#1CE6FF", "#FF34FF", "#FF4A46", "#008941", "#006FA6", "#A30059",
	        "#FFDBE5", "#7A4900", "#0000A6", "#63FFAC", "#B79762", "#004D43", "#8FB0FF", "#997D87",
	        "#5A0007", "#809693", "#000000", "#1B4400", "#4FC601", "#3B5DFF", "#4A3B53", "#FF2F80",
	        "#61615A", "#BA0900", "#6B7900", "#00C2A0", "#FFAA92", "#FF90C9", "#B903AA", "#D16100",
	        "#DDEFFF", "#000035", "#7B4F4B", "#A1C299", "#300018", "#0AA6D8", "#013349", "#00846F",
	        "#372101", "#FFB500", "#C2FFED", "#A079BF", "#CC0744", "#C0B9B2", "#C2FF99", "#001E09",
	        "#00489C", "#6F0062", "#0CBD66", "#EEC3FF", "#456D75", "#B77B68", "#7A87A1", "#788D66",
	        "#885578", "#FAD09F", "#FF8A9A", "#D157A0", "#BEC459", "#456648", "#0086ED", "#886F4C",
	        "#34362D", "#B4A8BD", "#00A6AA", "#452C2C", "#636375", "#A3C8C9", "#FF913F", "#938A81",
	        "#575329", "#00FECF", "#B05B6F", "#8CD0FF", "#3B9700", "#04F757", "#C8A1A1", "#1E6E00",
	        "#7900D7", "#A77500", "#6367A9", "#A05837", "#6B002C", "#772600", "#D790FF", "#9B9700",
	        "#549E79", "#FFF69F", "#201625", "#72418F", "#BC23FF", "#99ADC0", "#3A2465", "#922329",
	        "#5B4534", "#FDE8DC", "#404E55", "#0089A3", "#CB7E98", "#A4E804", "#324E72", "#6A3A4C",
	        "#83AB58", "#001C1E", "#D1F7CE", "#004B28", "#C8D0F6", "#A3A489", "#806C66", "#222800",
	        "#BF5650", "#E83000", "#66796D", "#DA007C", "#FF1A59", "#8ADBB4", "#1E0200", "#5B4E51",
	        "#C895C5", "#320033", "#FF6832", "#66E1D3", "#CFCDAC", "#D0AC94", "#7ED379", "#012C58"
	};
	
	//get/set methods
	/**
	 * Method to return a colour set with 256 possible colours
	 * @return colour set
	 */
	public static String[] getColourSet() {
		return GuiUtilities.colourSet;
	}
	
	// static methods
	
	/**
	 * Print data Points at the beginning of the Gui algorithm with default color white
	 * @param points
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printPoints(DataPoint[] points, Pane root, double[] normVal1D, double[] normVal2D, double scalar, double size) throws Exception {
		
		for(int i = 0; i < points.length; i++){
			GuiUtilities.printDP(points[i], root, normVal1D, normVal2D, scalar, size);
		}
	}
	/**
	 * Print a single data point with default color white
	 * @param vector
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printDP(DataPoint vector, Pane root, double[] normVal1D, double[] normVal2D, double scalar, double size) throws Exception {
		Rectangle rec = new Rectangle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		// normalize first dimension, scale and shift it
		rec.setX(scalar * Utilities.linNormalize(vector.get2DCoord()[0], normVal1D[0], normVal1D[1]) + 20);
		// normalize second dimension, scale and shift it
		rec.setY(scalar * Utilities.linNormalize(vector.get2DCoord()[1], normVal2D[0], normVal2D[1]) + 20);
		rec.setHeight(size);
		rec.setWidth(size);
		rec.setFill(Color.WHITE);
		rec.setStroke(Color.BLACK);
		root.getChildren().add(rec);
	}
	
	/**
	 * Print all data Points, manually filled with a color
	 * @param points
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param colour
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printPoints(List<DataPoint> points, Pane root, double[] normVal1D, double[] normVal2D, Color colour, double scalar, double size) throws Exception {
		// iterate through the list of points and print every point
		Iterator<DataPoint> iter = points.iterator();
		while(iter.hasNext()) {
			GuiUtilities.printDP(iter.next(), root, normVal1D, normVal2D, colour, scalar, size);
		}
	}
	
	/**
	 * Print a single data point, manually filled with color
	 * @param vector
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param colour
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printDP(Vector vector, Pane root, double[] normVal1D, double[] normVal2D, Color colour, double scalar, double size) throws Exception {
		Rectangle rec = new Rectangle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		// normalize first dimension, scale and shift it
		rec.setX(scalar * Utilities.linNormalize(vector.get2DCoord()[0], normVal1D[0], normVal1D[1]) + 20);
		// normalize second dimension, scale and shift it
		rec.setY(scalar * Utilities.linNormalize(vector.get2DCoord()[1], normVal2D[0], normVal2D[1]) + 20);
		rec.setWidth(size);
		rec.setHeight(size);
		rec.setFill(colour);
		rec.setStroke(Color.BLACK);
		root.getChildren().add(rec);
	}
	
	/**
	 * Prints a single centroid as a circle filled with a specific color
	 * @param vector
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param colour
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printC(Vector vector, Pane root, double[] normVal1D, double[] normVal2D, Color colour, double scalar, double size) throws Exception {
		Circle circ = new Circle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		circ.setCenterX(scalar* Utilities.linNormalize(vector.get2DCoord()[0], normVal1D[0], normVal1D[1]) + 20);
		circ.setCenterY(scalar* Utilities.linNormalize(vector.get2DCoord()[1], normVal2D[0], normVal2D[1]) + 20);
		circ.setRadius(size + scalar/400);
		circ.setFill(colour);
		circ.setStroke(Color.BLACK);
		circ.toFront();
		root.getChildren().add(circ);
	}
	/**
	 * Prints the final results of the algorithm, including the data points and the centroids (coloured)
	 * @param finalResult
	 * @param root
	 * @param normVal1D
	 * @param normVal2D
	 * @param colours
	 * @param scalar
	 * @param size
	 * @throws Exception
	 */
	public static void printResults(Centroid[] finalResult, Pane root, double[] normVal1D, double[] normVal2D, Color[] colours, double scalar, double size) throws Exception {
		for(int i = 0; i < finalResult.length; i++) {
        	GuiUtilities.printC(finalResult[i], root, normVal1D, normVal2D, colours[i], scalar, size);
        	
        	GuiUtilities.printPoints(finalResult[i].getReferences(), root, normVal1D, normVal2D, colours[i], scalar, size);
        }
	}
	/**
	 * Creates an color set of n distinct colors, based on a color set of 256 colors
	 * @param n
	 * @return color set
	 */
	public static Color[] createColourSet(int n) {
		// create color array
		Color[] colours = new Color[n];
		for(int i = 0; i < n; i++) {
			// fill array and transfer hex values to java fx color values
			colours[i] = Color.web(GuiUtilities.getColourSet()[i]);
		}
		return colours;
		
		
	}
	
}
