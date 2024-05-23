package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasObject;

public class addEllipseState extends State {
	/**
	 * Points on ellipse to be added.
	 */
	Point2D p1, p2;

	public addEllipseState() {
		canvas.javafxCanvas.setOnMouseClicked(registerP1());
	}

	public EventHandler<MouseEvent> registerP1() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p1 = new Point2D(e.getX(), e.getY());

				canvas.javafxCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						canvas.removeGhostObject();

						CanvasObject ellipse = new CanvasEllipse(new Point2D(p1.getX(), p1.getY()),
								Math.abs(event.getX() - p1.getX()), Math.abs(event.getY() - p1.getY()),
								canvas.getCopyColorSelect(), canvas.getCopyColorSelect());

						ellipse.isSelected = true;
						canvas.setGhostObject(ellipse);

					}
				});

				canvas.javafxCanvas.setOnMouseClicked(registerP2());
			}
		};
	}

	public EventHandler<MouseEvent> registerP2() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				canvas.add(new CanvasEllipse(p1, Math.abs(e.getX() - p1.getX()), Math.abs(e.getY() - p1.getY()),
						canvas.getCopyColorSelect(),
						canvas.getCopyColorSelect()));
				canvas.javafxCanvas.setOnMouseClicked(registerP1());
				canvas.javafxCanvas.setOnMouseMoved(null);
				canvas.removeGhostObject();
			}
		};
	}
}
