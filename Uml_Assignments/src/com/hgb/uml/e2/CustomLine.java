package com.hgb.uml.e2;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
public class CustomLine {
	private static ArrayList<UIChangeListner> listners = new ArrayList<UIChangeListner>();
	Line line;

	public CustomLine() {}

	public CustomLine(double x, double y, String type) {
		// Creating a new line object to draw line.
		line = new Line();
		// Setting line starting point with values
		line.setStartX(RectLine.x);
		line.setStartY(RectLine.y);
		// Getting 2.nd point to draw the line.
		line.setEndX(x);
		line.setEndY(y);
		// setting thickness of line
		line.setStrokeWidth(Constants.lineStroke);
		line.setStroke(Paint.valueOf(Constants.lineColor));
		// removing clipboard content so next rectangle will be
		// treated as first rectangle point for new line
		double phi = Math.toRadians(40);
		int barb = 20;
		double dy = line.getEndY() - line.getStartY();
		double dx = line.getEndX() - line.getStartX();
		double theta = Math.atan2(dy, dx);
		double x2, y2, rho = theta + phi;
		for (int j = 0; j < 2; j++) {
			x2 = line.getEndX() - barb * Math.cos(rho);
			y2 = line.getEndY() - barb * Math.sin(rho);
			Line l=new Line(line.getEndX(), line.getEndY(), x2, y2);
			l.setStroke(Paint.valueOf(Constants.lineColor));
			RectLine.nodes
					.add(l);
			rho = theta - phi;
		}
		
		if (Constants.dashed.equalsIgnoreCase(type)) {
			line.setStrokeDashOffset(Constants.dashOffset);
			line.getStrokeDashArray().addAll(3.0, 6.0);
		}
		line.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					TextField tf = new TextField("Please name the object");
					tf.setLayoutX(line.getStartX()
							+ (line.getEndX() - line.getStartX()) / 2);
					tf.setLayoutY(line.getStartY()
							+ (line.getEndY() - line.getStartY()) / 2);
					RectLine.nodes.add(tf);
					updateListners();
				}
			}
		});
	}

	public Line getLine() {
		return this.line;
	}

	public void updateListners() {
		for (UIChangeListner uicl : listners) {
			uicl.onUpdate();
		}
	}

	public void addListner(UIChangeListner uic) {
		listners.add(uic);
	}
}
