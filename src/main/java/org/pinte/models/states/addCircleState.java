package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasObject;

public class addCircleState extends State {
	/**
	 * Center of the circle to be added
	 */
	Point2D center;

	/**
	 * Point located on the circle to be added
	 */
	Point2D p;

	public addCircleState() {
		canvas.javafxCanvas.setOnMouseClicked(registerCenter());
	}

	public EventHandler<MouseEvent> registerCenter() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				center = new Point2D(e.getX(), e.getY());
				canvas.javafxCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						canvas.removeGhostObject();

						CanvasObject circle = new CanvasEllipse(new Point2D(center.getX(), center.getY()),
								Math.abs(event.getX() - center.getX()),
								new CanvasColor(255, 255, 255, 0), new CanvasColor(128, 128, 0, 1));

						circle.isSelected = true;
						canvas.setGhostObject(circle);

					}
				});

				canvas.javafxCanvas.setOnMouseClicked(registerPointOnCircle());
			}
		};
	}

	public EventHandler<MouseEvent> registerPointOnCircle() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p = new Point2D(e.getX(), e.getY());
				canvas.add(new CanvasEllipse(center, p.distance(center), new CanvasColor(0, 0, 0),
						new CanvasColor(255, 0, 0)));
				canvas.javafxCanvas.setOnMouseClicked(registerCenter());
				canvas.javafxCanvas.setOnMouseMoved(null);
				canvas.removeGhostObject();
			}
		};
	}
}
