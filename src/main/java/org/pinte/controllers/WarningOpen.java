package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.pinte.models.Open;
import org.pinte.models.Canvas;

import java.net.URL;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

public class WarningOpen {

	Canvas canva = Canvas.getInstance();
	/**
	 * Cancel Saving and go back to the change path window
	 *
	 * @param actionEvent Action event
	 */
	public void cancel(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * replace the file in the specified location by the new file
	 *
	 * @param actionEvent Action event
	 */
	public void open(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		Open open = new Open(canva.getPathOpen());
		open.read(true);
		stage.close();
	}

}

