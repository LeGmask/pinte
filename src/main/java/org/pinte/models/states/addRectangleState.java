package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasRectangle;

public class addRectangleState extends State {
	public addRectangleState() {
		canvas.javafxCanvas.setOnMouseClicked(ajouterRectangle());
	}

	public EventHandler<MouseEvent> ajouterRectangle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				CanvasRectangle rectangle = new CanvasRectangle(
					new Point2D(
						e.getX(),
						e.getY()),
					10, 10, new CanvasColor(0, 0, 0), new CanvasColor(255, 0, 0));
				canvas.add(rectangle);


			}
		};
	}
}
