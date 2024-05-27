package org.pinte.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Save {

	/**
	 * if the file exist are not
	 */
	boolean exist = false;

	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	/**
	 * Constructor of Save.java
	 */
	public Save() {
	}

	/**
	 * Opening the window to change the name and/or
	 * location of the saving file and save
	 */
	public void SaveFile_as() {
		try {
			Stage saveStage = new Stage();
			URL url = getClass().getResource("../views/changepath.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(url);
			GridPane root = (GridPane) fxmlLoader.load();
			Scene scene = new Scene(root);
			saveStage.setScene(scene);
			saveStage.setTitle("Enregistrer sous");
			saveStage.show();
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Save the file
	 *
	 * @param savingas Saving as or saving
	 */
	public void SaveFile(boolean savingas) {
		try {
			if (Files.exists(canva.getPath())) {
				if (!canva.getSafePath()) {
					Stage warningStage = new Stage();
					URL url = getClass().getResource("../views/warning.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(url);
					GridPane root = (GridPane) fxmlLoader.load();
					Scene scene = new Scene(root);
					warningStage.setScene(scene);
					warningStage.setTitle("Warning!");
					warningStage.show();
				} else {
					write();
					open();
				}
			} else {
				write();
				open();
			}
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Open a project or create a new project
	 */
	public void open() {
		if (canva.getOpen()) {
			canva.setOpen(false);
			Open open = new Open();
			open.choose(false);
		} else if (canva.getNw()) {
			canva.setNw(false);
			Open open = new Open();
			open.newproject();
		}
	}

	/**
	 * Write the information of the canva in the file on svg format
	 *
	 * @throws Exception all exception
	 */
	private void write() throws java.io.IOException {
		List<CanvasObject> objects = canva.getCanvas();
		List<String> objectsstr = new ArrayList<>();
		objectsstr.add("<?xml version=\"1.0\"?>");
		objectsstr.add("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + canva.getDim().getWidth() + "px\" height=\"" + canva.getDim().getWidth() + "px\" viewBox=\"0 0 " + canva.getDim().getWidth() + " " + canva.getDim().getHeight() + "\">");
		for (CanvasObject object : objects) {
			objectsstr.add(object.toSVG());
		}
		objectsstr.add("</svg>");
		if (exist) {
			Files.write(canva.getPath(), objectsstr, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} else {
			Files.write(canva.getPath(), objectsstr, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			canva.setSafePath(true);
		}
	}

	/**
	 * change the parameter for the write function to erase the content
	 * of the file and replace it
	 *
	 * @throws java.io.IOException failed or interrupted I/O operations
	 */
	public void replace() throws java.io.IOException {
		exist = true;
		canva.setSafePath(true);
		write();
		open();
	}
}
