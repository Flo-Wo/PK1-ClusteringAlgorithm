package gui;

import readFile.FileReader;

import javafx.scene.shape.Rectangle;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import algorithm.Centroid;
import algorithm.DataPoint;
import algorithm.KMeans;
import algorithm.Utilities;
import algorithm.Vector;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author florianwolf
 *
 */

public class MainGui extends Application{
	
	
	/**
	 * Constructor
	 */
	public MainGui() {
		
	}
	
	
	/**
	 * initialize any kind of algorithm, e.g., sorting
	 */
	public void init(){
		
	}

	/**
	 * initialize the graphical components
	 */
	public void start(Stage primaryStage) throws Exception{
		
		KMeans algo = new KMeans("DataTestBig.txt", 4, 2);
		algo.runAlgo();
		
		//root of the scene graph without any layout
		Pane root = new Pane();  
		

        //the scene gets the root of the scene graph
        Scene scene = new Scene(root, 520, 520);
        
        //the stage uses the scene and displays a title
        primaryStage.setTitle("kMeans algorithm - Scatterplot");        
        primaryStage.setScene(scene);
		
		// get min/max Values
		double[] normVal = Utilities.getMinMax2D(algo.getDataPoints());
		
		// print initial data set
		MainGui.printPoints(algo.getDataPoints(), root, normVal);
		
//		List<Centroid[]> buffer = algo.getBuffer();
		
//		Rectangle rec = new Rectangle(buffer.get(0)[0].getCoord(0), buffer.get(0)[0].getCoord(1), Color.BLACK);
//		root.getChildren().add(rec);
//		
//		
//		SequentialTransition sequentialTransition = new SequentialTransition();
//		
//		for(int i = 1; i < 2; i++) {
//			TranslateTransition translateTransition = new TranslateTransition();
//			translateTransition.setNode(rec);
//			translateTransition.setFromX(buffer.get(i-1)[0].getCoord(0));
//			translateTransition.setFromY(buffer.get(i-1)[0].getCoord(1));
//			//rotateTransition.play();
//			translateTransition.setToX(buffer.get(i)[0].getCoord(0));
//			translateTransition.setToY(buffer.get(i)[0].getCoord(1));
//			translateTransition.setDuration(Duration.seconds(10));
//			//translateTransition.play();
//			sequentialTransition.getChildren().addAll(translateTransition);
//		}
//		sequentialTransition.play();
		
		
//		TranslateTransition translateTransition = new TranslateTransition();
//		Rectangle rectangle = new Rectangle(10,10, Color.BLACK);
//		root.getChildren().add(rectangle);
//		translateTransition.setNode(rectangle);
//		//translateTransition.setFromX(10);
//		//translateTransition.setFromY(10);
//		//rotateTransition.play();
//		translateTransition.setToX(350);
//		translateTransition.setToY(350);
//		translateTransition.setDuration(Duration.seconds(10));
//		translateTransition.play();
		
		primaryStage.show();
        
	}
	
	
	/**
	 * Main Method to test the application
	 * @param args
	 */
	public static void main(String[] args)
	{
		Application.launch(MainGui.class);
	}
	
	
	/**
	 * Print data Points at the beginning of the Gui algorithm 
	 * @param points
	 * @param root
	 * @param normVal
	 * @throws Exception
	 */
	public static void printPoints(DataPoint[] points, Pane root, double[] normVal) throws Exception {
		
		for(int i = 0; i < points.length; i++){
			MainGui.printDP(points[i], root, normVal);
		}
	}
	/**
	 * Print a single data point/centroid
	 * @param point
	 * @param root
	 * @param normVal
	 * @throws Exception
	 */
	public static void printDP(Vector vector, Pane root, double[] normVal) throws Exception {
		Rectangle rec = new Rectangle();
		// scale them up, to get a bigger image, and shift them to right and bottom, to avoid getting intersections
		// between data points and the window borders
		rec.setX(500* Utilities.linNormalize(vector.get2DCoord()[0], normVal[0], normVal[1]) + 10);
		rec.setY(500* Utilities.linNormalize(vector.get2DCoord()[1], normVal[0], normVal[1]) + 10);
		rec.setHeight(5);
		rec.setWidth(5);
		rec.setFill(Color.WHITE);
		rec.setStroke(Color.BLACK);
		root.getChildren().add(rec);
	}
	
}
