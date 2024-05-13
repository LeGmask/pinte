package org.pinte.controllers;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import org.pinte.models.Canvas;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.states.*;

public class Main {
	@FXML
	public javafx.scene.canvas.Canvas javafxCanvas;

	@FXML
	public Button demo;

	@FXML
	public Button selection;

	@FXML
	public Button addCircle;

	@FXML
	public Button addEllipse;

	@FXML
	public Button addRectangle;

	Canvas canvas = Canvas.getInstance();

	/**
	 * State of main context
	 */
	private State status;

	public void initialize() {
		canvas.setJavafxCanvas(javafxCanvas); // delegate the canvas to the singleton
		canvas.setDim(800, 800); // resize the canvas

		status = new selectionState();

		new AnimationTimer() {
			public void handle(long now) {
				canvas.clear();
				canvas.render();
			}
		}.start();
	}

	public void handleDemo(ActionEvent actionEvent) {
		CanvasEllipse ellipse = new CanvasEllipse(
			new Point2D(
				Math.random() * 800,
				Math.random() * 800),
			10, 10, new CanvasColor(0, 0, 0), new CanvasColor(255, 0, 0));
		canvas.add(ellipse);
	}

	public void handleSelection(ActionEvent actionEvent) {
		status = new selectionState();
	}

	public void handleAddCircle(ActionEvent actionEvent) {
		status = new addCircleState();
	}

	public void handleAddRectangle(ActionEvent actionEvent) {
		status = new addRectangleState();
	}

	public void handleAddEllipse(ActionEvent actionEvent) {
		status = new addEllipseState();
	}
}
