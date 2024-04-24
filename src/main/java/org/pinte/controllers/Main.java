package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import org.pinte.models.Canvas;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;

public class Main {
	@FXML
	public javafx.scene.canvas.Canvas javafxCanvas;

	@FXML
	public Button demo;

	Canvas canvas = Canvas.getInstance();

	public void initialize() {
		canvas.setJavafxCanvas(javafxCanvas); // delegate the canvas to the singleton
		canvas.resizeCanvas(800, 800); // resize the canvas
	}

	public void handleDemo(ActionEvent actionEvent) {
		canvas.clear();
		canvas.render();
	}
}
