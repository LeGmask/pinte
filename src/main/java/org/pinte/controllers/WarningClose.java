package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.pinte.models.Open;
import org.pinte.models.Canvas;
import org.pinte.models.Save;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

public class WarningClose {

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
	 * Save File as then open the new one
	 */
	public void Save_File_As(ActionEvent actionEvent){
		Save save= new Save();
		save.SaveFile_as();
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * Save File then open the new one
	 */
	public void Save_File(ActionEvent actionEvent){
		Save save= new Save();
		save.SaveFile(true);
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * Open the new file
	 */
	public void ignore(ActionEvent actionEvent){
		if(canva.getNw()){
			Open open=new Open();
			open.newproject();
			canva.setNw(false);
		} else if(canva.getOpen()){
			Open open=new Open();
			open.choose(false);
			canva.setOpen(false);
		}
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

}

