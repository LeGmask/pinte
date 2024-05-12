package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.pinte.models.Save;

import java.net.URL;

import static org.pinte.Utils.JavaFX.getStageFromEvent;

public class Warning {

	/**
	 * Cancel Saving and go back to the change path window
	 *
	 * @param actionEvent Action event
	 */
	public void cancel(ActionEvent actionEvent) {
		try {
			Stage stage = getStageFromEvent(actionEvent);
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("../views/changepath.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(url);
			GridPane root = (GridPane) fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Enregistrer sous");
			primaryStage.show();
			stage.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * replace the file in the specified location by the new file
	 *
	 * @param actionEvent Action event
	 */
	public void replace(ActionEvent actionEvent) {
		try {
			Stage stage = getStageFromEvent(actionEvent);
			Save save = new Save();
			save.reecrire();
			stage.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

