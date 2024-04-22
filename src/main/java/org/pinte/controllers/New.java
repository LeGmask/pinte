package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

import static org.pinte.Utils.JavaFX.getStageFromEvent;
import static org.pinte.Utils.JavaFX.switchScene;

/**
 * New project controller
 * Handles the creation of new projects
 */
public class New {
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
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Project Location");
		Stage stage = getStageFromEvent(actionEvent);

		projectLocation.setText(directoryChooser.showDialog(stage).getAbsolutePath());
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
		switchScene(actionEvent, "../views/main.fxml");

		// @TODO: create canvas
	}

	/**
	 * Open an existing project
	 *
	 * @param actionEvent Action event
	 * @throws IOException If the main view is not found
	 */
	public void open(ActionEvent actionEvent) throws IOException {
		FileChooser fileChooser = new FileChooser();
		Stage stage = getStageFromEvent(actionEvent);

		fileChooser.setTitle("Open Project");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pinte Project", "*.pinte"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Svg Files", "*.svg"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

		fileChooser.showOpenDialog(stage);

		// @TODO: parse project + create canvas and switch to main view
		switchScene(actionEvent, "../views/main.fxml");
	}
}
