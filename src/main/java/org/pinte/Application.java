package org.pinte;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	@Override
	public void start(Stage mainStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
//		System.out.println(getClass().getResource("views/main.fxml"));
		loader.setLocation(getClass().getResource("views/main.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);

		mainStage.setTitle("pinte");
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}