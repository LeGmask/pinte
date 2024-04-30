package org.pinte.controllers;

import javafx.event.ActionEvent;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.pinte.models.Save;
import org.pinte.models.Canvas;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

public class ChangePath {
	/**
	 * Project name field
	 */
	@FXML
	public TextField projectName;

	/**
	 * Project location field
	 */
	@FXML
	public TextField projectLocation;

	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	/**
	 * initialize the default parameters of the Change path window
	 */
	public void initialize(){
		Path oldpath=canva.getPath();
		String[] dossiers = oldpath.toString().split("/");
		String newpath = dossiers[0];
		for(int i=1; i<dossiers.length-1;i++){
			newpath=newpath+"/"+dossiers[i];
		}
		String newname = dossiers[dossiers.length-1];
		newname = newname.replaceAll(".svg","");
		projectName.setText(newname);
		projectLocation.setText(newpath);
	}

	/**
	 * Browse for project location (save directory)
	 *
	 * @param actionEvent Action event
	 */
	public void browse(ActionEvent actionEvent) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Project Location");
		Stage stage = getStageFromEvent(actionEvent);
		projectLocation.setText(directoryChooser.showDialog(stage).getAbsolutePath());
	}

	/**
	 * Cancel saving
	 * 
	 * @param actionEvent Action event
	 */
	public void cancel(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * Save the file in the specified location
	 * 
	 * @param actionEvent Action event
	 */
	public void CallSave(ActionEvent actionEvent){
		canva.setPath(Path.of(projectLocation.getText() + "/" + projectName.getText() + ".svg"));
		Save save = new Save();
		save.SaveFile(false);
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}
}
