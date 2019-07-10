package gui;

import algorithm.KMeans;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.ScatterChart; 
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane; 
 
 
public class MainGui extends Application {
	@Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);        
        final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("x-coordinate");                
        yAxis.setLabel("y-coordinate");
        sc.setTitle("K-Means Algorithm (2D Version)");
       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Equities");
        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.8, 33.6));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Mutual funds");
        series2.getData().add(new XYChart.Data(5.2, 229.2));
 
        sc.getData().addAll(series1, series2);
        
        GridPane grid = new GridPane();
        
        grid.add(sc, 0, 0);
        
        Button button = new Button("Go");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					KMeans algo = new KMeans();
					algo.runAlgo(new KMeansListener() {
						
						@Override
						public void updateGraph(KMeans algo){
							try {
								algo.currCentroids[0].printVector();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        grid.add(button, 1, 0);
        
        
        Scene scene  = new Scene(grid, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    class myKmeansListener implements KMeansListener{

		@Override
		public void updateGraph(KMeans algo) {
			
		}
    	
    }

}


