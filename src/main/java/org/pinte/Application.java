package org.pinte;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("views/new.fxml")); // instantiate the new view
		Parent root = loader.load();

		Scene scene = new Scene(root);

		mainStage.setTitle("pinte");
		mainStage.setScene(scene);
		mainStage.show();
	}
}
