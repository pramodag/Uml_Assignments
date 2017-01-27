package com.hgb.uml.e2;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class RectLine extends Application {
	public static double x = -1, y = -1;
	public static String type;
	private Group root;
	// public static Group components=new Group();
	public static ArrayList<Node> nodes = new ArrayList<>();
	// Creating a toggle group to group radio buttons
	public static ToggleGroup tg1 = new ToggleGroup();
	private VBox vb = new VBox();
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Creating window
		primaryStage.setTitle("Rectangle Line Exercise");
		root = new Group();
		BorderPane bp = new BorderPane();
		scene = new Scene(root, 800, 600, Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		// CReating a Hbox for radio buttons
		HBox hbox = new HBox();
		hbox.setMinWidth(scene.getWidth() * .8);
		hbox.setPadding(new Insets(10, 12, 10, 12));
		hbox.setSpacing(15); // Gap between nodes
		// Creating radio buttons.
		RadioButton rb1 = new RadioButton();
		rb1.setText("Solid Rectangle");
		rb1.setUserData("SolidRectangle");
		rb1.setSelected(true);
		rb1.setToggleGroup(tg1);
		RadioButton rb2 = new RadioButton();
		rb2.setText("Solid Line");
		rb2.setUserData("SolidLine");
		rb2.setToggleGroup(tg1);
		// CReating radio buttons for dashed
		RadioButton rb3 = new RadioButton();
		rb3.setText("Dashed Rectangle");
		rb3.setUserData("DashedRectangle");
		rb3.setToggleGroup(tg1);
		RadioButton rb4 = new RadioButton();
		rb4.setText("Dashed Line");
		rb4.setUserData("DashedLine");
		rb4.setToggleGroup(tg1);
		// adding radio buttons to Hbox
		hbox.getChildren().addAll(rb1, rb3, rb2, rb4);
		// adding hbox to root
		// root.getChildren().addAll(hbox);
		HBox container = new HBox();
		container.setMinWidth(scene.getWidth());
		container.setMinSize(scene.getWidth() * .8, scene.getHeight());
		hbox.setPadding(new Insets(10, 12, 10, 12));
		hbox.setSpacing(15); // Gap between nodes
		container.setStyle("-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		root.getChildren().addAll(container, hbox);
		// VBox vb=new VBox();
		vb.setMinHeight(scene.getHeight());
		vb.setMinWidth(scene.getWidth() * .2);
		vb.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
				+ "-fx-border-color: blue;");
		bp.setTop(hbox);
		bp.setCenter(container);
		bp.setRight(vb);
		root.getChildren().addAll(bp);
		// adding on mouse clicked event for creating rectangles
		container.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				for (Node node : container.getChildren()) {
					if (node instanceof Rectangle && node
							.contains(new Point2D(event.getX(), event.getY())))
						return;
				}
				if ("SolidRectangle".equalsIgnoreCase(
						tg1.getSelectedToggle().getUserData().toString())) {
					CustomRectangle cRrect = new CustomRectangle(event.getX(),
							event.getY(), Constants.solid);
					nodes.addAll(cRrect.getCustRectangle());
					redraw();
				} else if ("DashedRectangle".equalsIgnoreCase(
						tg1.getSelectedToggle().getUserData().toString())) {
					CustomRectangle cRrect = new CustomRectangle(event.getX(),
							event.getY(), Constants.dashed);
					nodes.addAll(cRrect.getCustRectangle());
					redraw();
				}
			}
		});
		primaryStage.show();
		new RectangleEventHandler().addListner(new UIChangeListner() {
			@Override
			public void onUpdate() {
				System.out.println("Redrawing");
				redraw();
			}
		});
		new CustomLine().addListner(new UIChangeListner() {
			@Override
			public void onUpdate() {
				System.out.println("Redrawing in custom line");
				redraw();
			}
		});
	}

	public void redraw() {
		System.out.println("In redraw");
		root.getChildren().addAll(nodes);
		Rectangle r = new Rectangle();
		Line l = new Line();
		for (Node n : nodes) {
			if (n instanceof Rectangle) {
				Rectangle r1 = new Rectangle();
				r = (Rectangle) n;
				r1.setX(scene.getWidth() * .8 + (r.getX() / 5));
				r1.setY(scene.getHeight() * .2 + (r.getY() / 5));
				r1.setWidth(r.getHeight() / 5);
				r1.setHeight(r.getWidth() / 5);
				r1.setFill(r.getFill());
				r1.setStrokeWidth(Constants.strokeWidth);
				r1.setStroke(Paint.valueOf(Constants.rectangleColor));
				r1.setStrokeDashOffset(r.getStrokeDashOffset());
				r1.getStrokeDashArray().addAll(r.getStrokeDashArray());
				root.getChildren().add(r1);
			}
			if (n instanceof Line) {
				Line l1 = new Line();
				l = (Line) n;
				l1.setStartX(scene.getWidth() * .8 + (l.getStartX() / 5));
				l1.setStartY(scene.getHeight() * .2 + (l.getStartY() / 5));
				l1.setEndX(scene.getWidth() * .8 + (l.getEndX() / 5));
				l1.setEndY(scene.getHeight() * .2 + (l.getEndY() / 5));
				l1.setStrokeDashOffset(l1.getStrokeDashOffset());
				l1.getStrokeDashArray().addAll(l.getStrokeDashArray());
				root.getChildren().add(l1);
			}
		}
		nodes.clear();
	}
}