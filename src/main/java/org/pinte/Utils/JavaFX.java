package org.pinte.Utils;

/**
 * A bunch of utility functions for JavaFX.
 */
public class JavaFX {

	/**
	 * Get the stage of the window where the event was triggered
	 *
	 * @param event the event
	 * @return the stage of the event
	 */
	public static javafx.stage.Stage getStageFromEvent(javafx.event.ActionEvent event) {
		javafx.scene.Node node = (javafx.scene.Node) event.getSource();
		return (javafx.stage.Stage) node.getScene().getWindow();
	}

	/**
	 * Switch the scene of the window where the event was triggered
	 *
	 * @param event the event
	 * @param fxml the string pointing to the fxml file
	 * @throws java.io.IOException if the file is not found
	 */
	public static void switchScene(javafx.event.ActionEvent event, String fxml) throws java.io.IOException {
		javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();
		loader.setLocation(JavaFX.class.getResource(fxml));

		javafx.stage.Stage stage = getStageFromEvent(event);
		stage.hide();
		stage.setScene(new javafx.scene.Scene(loader.load()));
		stage.show();
	}
}
