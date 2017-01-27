package com.hgb.uml.e1;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class UMLFirst extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bPane = new BorderPane();
		Group root=new Group(); 
		Scene scene = new Scene(root, 800, 600, Color.WHITE);
		//SubScene s2 = new SubScene(root, 800, 20);
//		s2.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;"
//				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
//				+ "-fx-border-color: blue;");
		primaryStage.setTitle("Sample UML application");
		HBox hBox = new HBox();
		bPane.setTop(hBox);
		//bPane.setCenter(s2);
		bPane.setOpacity(0.25);
		RadioButton rb1 = new RadioButton("Rectangle");
		RadioButton rb2 = new RadioButton("Line");
		hBox.getChildren().addAll(rb1, rb2);
		/*hBox.setStyle("-fx-padding: 10;" + "-fx-spacing: 20;"
				+ "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
				+ "-fx-border-color: blue;");*/
		root.getChildren().add(bPane);
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
