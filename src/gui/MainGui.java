package gui;

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
	private int windowSize = 800;
	private double scale = windowSize - 3.25 * windowSize/10;
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
		// number of centroids the data set should be clustered with
		int cen = 6;
		// norm the algorithm should use for distance calculcations
		int norm = 2;
		
		KMeans algo = new KMeans("DataTestBig.txt", cen, norm);
		algo.runAlgo();
		
		// get min/max Values for first and second dimension
		double[] normVal1D = Utilities.getMinMax1D(algo.getDataPoints());
		double[] normVal2D = Utilities.getMinMax2D(algo.getDataPoints());
		
		
		
		// +++++ SCENE 1 ++++++++
		//root of the scene graph without any layout
		Pane root = new Pane();  
		// root to align the button and the scene graphing
		BorderPane root2 = new BorderPane();
		
		
		// print initial data set
		GuiUtilities.printPoints(algo.getDataPoints(), root, normVal1D, normVal2D, this.scale, this.size);
		root2.setCenter(root);
		
		//the scene gets the root of the scene graph
        Scene scene1 = new Scene(root2, this.windowSize, this.windowSize);

        
		// +++++ SCENE 2 ++++++++
        // new pane, to draw the final results
        Pane root3 = new Pane();
        Centroid[] finalResult = algo.getFinalData();
        
        
        // CREATE COLOR SET
        Color[] colours = GuiUtilities.createColourSet(cen);
        
        
        GuiUtilities.printResults(finalResult, root3, normVal1D, normVal2D, colours, this.scale, this.size);
        
        //the scene gets the root of the scene graph
        Scene scene2 = new Scene(root3, this.windowSize, this.windowSize);
        
        
        // +++++ Layout and showing the Scenes ++++++++ 

		Button button1= new Button("Show final Clusters");
		button1.setOnAction(event -> primaryStage.setScene(scene2));
		root2.setTop(button1);
        
        
        //the stage uses the scene and displays a title
        primaryStage.setTitle("kMeans algorithm - Scatterplot (Florian Wolf)");        
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
