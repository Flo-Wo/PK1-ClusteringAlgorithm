package gui;


import java.util.ArrayList;
import java.util.List;

import algorithm.Centroid;
import algorithm.KMeans;
import algorithm.Utilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author florianwolf
 * Main GUI Class
 */

public class MainGui extends Application{
	
	
	
	// default properties
	private int windowSize = 1000;
	private int scale = windowSize - 500;
	private double size = windowSize/100;

	// constructor

	/**
	 * Constructor
	 */
	public MainGui() {
		
	}
	
	
	// get/set methods
	/**
	 * Set new Window size (the scalar for the points will change automatically)
	 * @param newSize
	 */
	public void setSize(int newSize) {
		this.windowSize = newSize;
	}
	/**
	 * Get the size of the window
	 * @return Window size
	 */
	public int getSize() {
		return this.windowSize;
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
		
		
		
		// get min/max Values
		double[] normVal = Utilities.getMinMax2D(algo.getDataPoints());
		
		// +++++ SCENE 1 ++++++++
		
        
		//root of the scene graph without any layout
		Pane root = new Pane();  
		// root to align the button and the scene graphing
		BorderPane root2 = new BorderPane();
		
		
		// print initial data set
		GuiUtilities.printPoints(algo.getDataPoints(), root, normVal, this.scale, this.size);
		root2.setCenter(root);
		
		//the scene gets the root of the scene graph
        Scene scene1 = new Scene(root2, 580, 580);

        
		// +++++ SCENE 2 ++++++++
        
        // new pane, to draw the final results
        Pane root3 = new Pane();
        Centroid[] finalResult = algo.getFinalData();
        
        
        // just for testing
        List<Color> colours = new ArrayList<Color>();
        colours.add(Color.GREEN);
        colours.add(Color.RED);
        colours.add(Color.BLUE);
        colours.add(Color.YELLOW);
        
        GuiUtilities.printResults(finalResult, root3, normVal, colours, this.scale, this.size);
        
        //the scene gets the root of the scene graph
        Scene scene2 = new Scene(root3, 580, 580);
        
        
        // +++++ Layout and showing the Scenes ++++++++ 

		Button button1= new Button("Show final Clusters");
		button1.setOnAction(e -> primaryStage.setScene(scene2));
		root2.setTop(button1);
        
        
        //the stage uses the scene and displays a title
        primaryStage.setTitle("kMeans algorithm - Scatterplot");        
        primaryStage.setScene(scene1);
		
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
	
}
