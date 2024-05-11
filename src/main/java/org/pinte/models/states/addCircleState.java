package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;


public class addCircleState extends State{
	public addCircleState() {
		canvas.javafxCanvas.setOnMouseClicked(ajouterCercle());
	}

	public EventHandler<MouseEvent> ajouterCercle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				CanvasEllipse ellipse = new CanvasEllipse(
					new Point2D(
						e.getX(),
						e.getY()),
					10, 10, new CanvasColor(0, 0, 0), new CanvasColor(255, 0, 0));
				canvas.add(ellipse);


			}
		};
	}
}
