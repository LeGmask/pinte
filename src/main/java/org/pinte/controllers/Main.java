package org.pinte.controllers;

import javafx.animation.AnimationTimer;
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
				Math.random() * 800), 10, 10, new CanvasColor(0, 0, 0), new CanvasColor(255, 0, 0));
		canvas.add(ellipse);
	}
}
