package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasLine;
import org.pinte.models.CanvasObjects.CanvasObject;
import org.pinte.models.CanvasObjects.CanvasPolygon;

import java.util.ArrayList;

public class addPolygonState extends State {
	/**
	 * Array of points of the polygon to be added
	 */
	private ArrayList<Point2D> points;

	public addPolygonState() {
		canvas.javafxCanvas.setOnMouseClicked(registerFirstPoint());
	}

	public EventHandler<MouseEvent> registerFirstPoint() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				points = new ArrayList<Point2D>();
				points.add(new Point2D(e.getX(), e.getY()));
				canvas.javafxCanvas.setOnMouseClicked(registerNextPoint());

				canvas.javafxCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						canvas.removeGhostObject();
						Point2D p = new Point2D(event.getX(), event.getY());
						CanvasObject polygon = new CanvasLine(points.getFirst(), p,
								canvas.getCopyColorSelect(), canvas.getCopyColorSelect());
						polygon.isSelected = true;
						canvas.setGhostObject(polygon);

					}
				});

			}
		};
	}

	public EventHandler<MouseEvent> registerNextPoint() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				points.add(new Point2D(e.getX(), e.getY()));

				canvas.javafxCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						canvas.removeGhostObject();
						Point2D p = new Point2D(event.getX(), event.getY());
						ArrayList<Point2D> tmpList = new ArrayList<Point2D>(points);
						tmpList.add(p);
						CanvasObject polygon = new CanvasPolygon(tmpList.toArray(new Point2D[0]),
								canvas.getCopyColorSelect(), canvas.getCopyColorSelect());

						polygon.isSelected = true;
						canvas.setGhostObject(polygon);

					}
				});

				if (e.getButton() == MouseButton.SECONDARY) {
					registerPolygon();
					canvas.javafxCanvas.setOnMouseClicked(registerFirstPoint());
					canvas.javafxCanvas.setOnMouseMoved(null);
					canvas.javafxCanvas.setOnKeyPressed(null);
					canvas.removeGhostObject();
				}
			}
		};
	}

	public void registerPolygon() {
		CanvasObject polygon = points.size() == 2
				? new CanvasLine(points.getFirst(), points.getLast(), canvas.getCopyColorSelect(),
						canvas.getCopyColorSelect())
				: new CanvasPolygon((points.toArray(new Point2D[0])),

						canvas.getCopyColorSelect(), canvas.getCopyColorSelect());

		canvas.add(polygon);
	}
}
