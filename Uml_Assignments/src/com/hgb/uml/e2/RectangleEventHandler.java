package com.hgb.uml.e2;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
public class RectangleEventHandler implements EventHandler<MouseEvent> {
	private Rectangle rect;
	private String type;
	private static ArrayList<UIChangeListner> listners = new ArrayList<UIChangeListner>();;

	public RectangleEventHandler(Rectangle rect, String type) {
		this.rect = rect;
		this.type = type;
	}
	public RectangleEventHandler() {
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			// Checking if first point is set
			if (RectLine.x == -1 && RectLine.y == -1) {
				System.out.println("Mouse clicked on rectangle");
				// No first point setting values.
				RectLine.x = rect.getX() + rect.getWidth() / 2;
				RectLine.y = rect.getY() + rect.getHeight() / 2;
				RectLine.type = type;
				System.out.println(RectLine.x + " " + RectLine.y + " " + type);
			} else {
				CustomLine cLine = null;
				if ("DashedLine".equalsIgnoreCase(RectLine.tg1
						.getSelectedToggle().getUserData().toString())) {
					if (!(type.equalsIgnoreCase(RectLine.type))) {
						cLine = new CustomLine(
								rect.getX() + rect.getWidth() / 2,
								rect.getY() + rect.getHeight() / 2,
								Constants.dashed);
						Line line = cLine.getLine();
						System.out.println("Adding line");
						RectLine.nodes.add(line);
						updateListners();
					}
				}
				if ("SolidLine".equalsIgnoreCase(RectLine.tg1
						.getSelectedToggle().getUserData().toString())) {
					if (Constants.solid.equalsIgnoreCase(type)&& Constants.solid.equalsIgnoreCase(RectLine.type)) {
						cLine = new CustomLine(
								rect.getX() + rect.getWidth() / 2,
								rect.getY() + rect.getHeight() / 2,
								Constants.solid);
						Line line = cLine.getLine();
						System.out.println(line);
						RectLine.nodes.add(line);
						updateListners();
					}
				}
				RectLine.x = RectLine.y = -1;
				RectLine.type = null;
			}
		} else if (event.getButton() == MouseButton.SECONDARY) {
			TextField tf = new TextField("Please name the object");
			tf.setLayoutX(rect.getX() + 2);
			tf.setLayoutY(rect.getY() + 10);
			RectLine.nodes.add(tf);
			updateListners();
		}
	}

	public void addListner(UIChangeListner uic){
		listners.add(uic);
	}
	public void updateListners() {
		for (UIChangeListner uicl : listners) {
			uicl.onUpdate();
		}
	}
}
