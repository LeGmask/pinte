package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasTextField;

import java.util.Optional;

public class addTextState extends State {

	/**
	 * Baseline starting point of the text to be added.
	 */
	Point2D baselineStart;

	/**
	 * Text to be added.
	 */
	String text;

	/**
	 * Input dialog window for the user to type the text to add
	 */
	TextInputDialog td;

	public addTextState() {
		canvas.javafxCanvas.setOnMouseClicked(registerBaselineStart());
		td =  new TextInputDialog();
		td.setTitle("Text Input");
		td.setHeaderText("Enter text to be added to the canvas.");
	}

	public EventHandler<MouseEvent> registerBaselineStart() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				baselineStart = new Point2D(e.getX(), e.getY());
				Optional<String> result = td.showAndWait();
				if (result.isPresent()) {
					text = td.getEditor().getText();
					if (text != null && !text.trim().isEmpty()) {
						canvas.add(new CanvasTextField(text, baselineStart, canvas.getSelectedFontSize(), canvas.getSelectedFontType(),
							canvas.getCopyColorSelect(), canvas.getCopyColorSelect()));
					}
				}
			}
		};
	}
}
