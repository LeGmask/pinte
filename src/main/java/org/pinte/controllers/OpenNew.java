package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pinte.models.Canvas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
import java.nio.file.Path;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

/**
 * New project controller
 * Handles the creation of new projects
 */
public class OpenNew {
	/**
	 * Singleton instance of the Canvas
	 */
	private final Canvas canvas = Canvas.getInstance();
	/**
	 * Project name field
	 */
	@FXML
	public TextField projectName;
	/**
	 * Project width field
	 */
	@FXML
	public TextField projectWidth;
	/**
	 * Project height field
	 */
	@FXML
	public TextField projectHeight;
	/**
	 * Project location field
	 */
	@FXML
	public TextField projectLocation;

	/**
	 * Initialize the new project view
	 * - Set default values
	 */
	public void initialize() {
		projectName.setText("New Project");
		projectWidth.setText("800");
		projectHeight.setText("600");
		projectLocation.setText(System.getProperty("user.home"));
	}


	/**
	 * Browse for project location (save directory)
	 *
	 * @param actionEvent Action event
	 */
	public void browse(ActionEvent actionEvent) {
		try{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Select Project Location");
			Stage stage = getStageFromEvent(actionEvent);

			projectLocation.setText(directoryChooser.showDialog(stage).getAbsolutePath());
		} catch (java.lang.NullPointerException e){
			System.out.println("browse cancelled");
		}
	}

	/**
	 * Cancel the new project creation
	 * Close the new project view and the application in general
	 *
	 * @param actionEvent Action event
	 */
	public void cancel(ActionEvent actionEvent) {
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}

	/**
	 * Create a new canvas and switch to the main view
	 *
	 * @param actionEvent Action event
	 * @throws IOException If the main view is not found
	 */
	public void create(ActionEvent actionEvent) throws IOException {
		Stage newStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/main.fxml")); // instantiate the new view
		Parent root = loader.load();
		Scene scene = new Scene(root);
		newStage.setTitle(projectName.getText());
		newStage.setScene(scene);
		newStage.show();
		canvas.setDim(
			Integer.parseInt(projectWidth.getText()),
			Integer.parseInt(projectHeight.getText())
		);
		canvas.setName(projectName.getText());
		canvas.setPath(Path.of(projectLocation.getText() + "/" + projectName.getText() + ".svg"));
		canvas.setCanvastage(newStage);
		Stage stage = getStageFromEvent(actionEvent);
		stage.close();
	}
}
