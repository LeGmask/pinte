package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasRectangle;

public class addRectangleState extends State {
	/**
	 * Opposite corners of the rectangle to be added
	 */
	Point2D p1, p2;

	public addRectangleState() {
		canvas.javafxCanvas.setOnMouseClicked(registerP1());
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
				canvas.add(rectangleFrom2Points(p1, p2, new CanvasColor(0, 0, 0),
														new CanvasColor(255, 0, 0)));
				canvas.javafxCanvas.setOnMouseClicked(registerP1());
			}
		};
	}


	/**
	 * Creates a rectangle with given opposite corners.
	 * If p1.x < p2.x then p1 is the left corner and p2 the right corner and vice-versa.
	 * If p1.y < p2.y then p1 is the top corner and p2 the bottom corner and vice-versa.
	 *
	 * @param p1          First corner
	 * @param p2          Second corner
	 * @param fillColor   The color to fill the rectangle with
	 * @param strokeColor The color of the outline
	 */
	private CanvasRectangle rectangleFrom2Points(Point2D p1, Point2D p2, CanvasColor fillColor, CanvasColor strokeColor) {
		double x1 = Math.min(p1.getX(), p2.getX());
		double y1 = Math.min(p1.getY(), p2.getY());
		double x2 = Math.max(p1.getX(), p2.getX());
		double y2 = Math.max(p1.getY(), p2.getY());

		return new CanvasRectangle(new Point2D(x1, y1), new Point2D(x2, y1), new Point2D(x2, y2), new Point2D(x1, y2), fillColor, strokeColor);
	}

}
