package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.pinte.models.Canvas;
import org.pinte.models.Open;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

public class WarningOpen {

	Canvas canva = Canvas.getInstance();

	/**
	 * Cancel opening and go back to canva
	 *
	 * @param actionEvent Action event
	 */
	public void cancel(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * open the specified file
	 *
	 * @param actionEvent Action event
	 */
	public void open(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		Open open = new Open(canva.getPathOpen());
		open.read(true, true);
		stage.close();
	}

}

