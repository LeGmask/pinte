package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;

public class addEllipseState extends State {
	/**
	 * Points on ellipse to be added.
	 */
	Point2D p1, p2;

	public addEllipseState() {
		canvas.javafxCanvas.setOnMouseClicked(registerP1());
	}

	public void exitState() {
		canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, registerP1());
	}

	public EventHandler<MouseEvent> registerP1() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p1 = new Point2D(e.getX(), e.getY());
				canvas.javafxCanvas.setOnMouseClicked(registerP2());
			}
		};
	}

	public EventHandler<MouseEvent> registerP2() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p2 = new Point2D(e.getX(), e.getY());
				canvas.add(new CanvasEllipse(p1, p2, new CanvasColor(0, 0, 0),
					new CanvasColor(255, 0, 0)));
				canvas.javafxCanvas.setOnMouseClicked(registerP1());
			}
		};
	}
}
