package com.hgb.uml.e2;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public class CustomRectangle {
	Rectangle rect;
	Line line;
	String type;

	public CustomRectangle(double x, double y, String type) {
		this.type = type;
		System.out.println("Creating rectangle object");
		rect = new Rectangle();
		rect.setUserData(type);
		rect = new Rectangle(x, y, Constants.width, Constants.height);
		rect.setFill(Color.TRANSPARENT);
		rect.setStrokeWidth(Constants.strokeWidth);
		rect.setStroke(Paint.valueOf(Constants.rectangleColor));
		line = new Line();
		line.setStartX(x);
		line.setStartY(y + Constants.height / 2);
		line.setEndX(x + Constants.width);
		line.setEndY(y + Constants.height / 2);
		System.out.println(line.getStartX() + " " + line.getStartY() + " "
				+ line.getEndX() + " " + line.getEndY());
		if (Constants.dashed.equalsIgnoreCase(type)) {
			rect.setStrokeDashOffset(Constants.dashOffset);
			rect.getStrokeDashArray().addAll(2.0, 4.0);
			line.setStrokeDashOffset(Constants.dashOffset);
			line.getStrokeDashArray().addAll(2.0, 4.0);
		}
		rect.setOpacity(Constants.opacity);
		line.setOpacity(Constants.opacity);
		rect.setOnMouseClicked(new RectangleEventHandler(rect, type));
	}

	public ArrayList<Shape> getCustRectangle() {
		ArrayList<Shape> temp = new ArrayList<>();
		temp.add(line);
		temp.add(rect);
		return temp;
	}

	public void redraw(Group root) {
		root.getChildren().addAll(rect, line);
	}
}