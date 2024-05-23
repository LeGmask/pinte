package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasObject;
import org.pinte.models.CanvasObjects.CanvasLine;

import java.awt.*;

public class addLineState extends State {
	/**
	 * Extremities of the segment to be added.
	 */
	private Point2D p1, p2;

	public addLineState() {
		canvas.javafxCanvas.setOnMouseClicked(registerP1());
	}

	public EventHandler<MouseEvent> registerP1() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p1 = new Point2D(e.getX(), e.getY());
				canvas.javafxCanvas.setOnMouseClicked(registerP2());

				canvas.javafxCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						canvas.removeGhostObject();

						CanvasObject line = new CanvasLine(p1, new Point2D(event.getX(), event.getY()),
								canvas.getCopyColorSelect(), canvas.getCopyColorSelect());

						line.isSelected = true;
						canvas.setGhostObject(line);

					}
				});

			}
		};
	}

	public EventHandler<MouseEvent> registerP2() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p2 = new Point2D(e.getX(), e.getY());
				canvas.add(new CanvasLine(p1, p2, new CanvasColor(0, 0, 0),
						new CanvasColor(255, 0, 0)));
				canvas.javafxCanvas.setOnMouseClicked(registerP1());
				canvas.javafxCanvas.setOnMouseMoved(null);
				canvas.removeGhostObject();
			}
		};
	}

}
