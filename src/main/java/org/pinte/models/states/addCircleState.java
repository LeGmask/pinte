package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;


public class addCircleState extends State{
	/**
	 * Reference to the circle to be added
	 */
	private CanvasEllipse circle;

	public addCircleState() {
		canvas.javafxCanvas.setOnMouseClicked(verifyCircle());
		canvas.javafxCanvas.setOnMousePressed(createCircle());
	}

	public void exitState() {
		canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, verifyCircle());
		canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, createCircle());
	}

	public EventHandler<MouseEvent> createCircle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				circle = new CanvasEllipse(new Point2D(e.getX(), e.getY()), 0,
						 new CanvasColor(0, 0, 0), new CanvasColor(255, 0, 0));
				canvas.add(circle);
				canvas.javafxCanvas.setOnMouseDragged(modifyCircle());
			}
		};
	}

	public EventHandler<MouseEvent> modifyCircle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				circle.setRx(circle.getCenter().distance(new Point2D(e.getX(), e.getY())));
				circle.setRy(circle.getCenter().distance(new Point2D(e.getX(), e.getY())));
			}
		};
	}

	public EventHandler<MouseEvent> verifyCircle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (circle.getRx() == 0) {
					canvas.removeLast();
				}
				canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, modifyCircle());
			}
		};
	}
}
