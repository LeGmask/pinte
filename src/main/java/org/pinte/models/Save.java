package org.pinte.models;
import java.io.*;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Save{

	/**
	 * if the file exist are not
	 */
	boolean exist=false;

	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	/**
	 * Constructor of Save.java
	 */
    public Save(){
    }

	/**
	 * Opening the window to change the name and/or location of the saving file and save
	 */
    public void SaveFile_as(){
        try{
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("../views/changepath.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(url);
			GridPane root =(GridPane) fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Enregistrer sous");
			primaryStage.show();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

	/**
	 * Save the file
	 * 
	 * @param savingas Saving as or saving
	 */
    public void SaveFile(boolean savingas){
		try{
			if(Files.exists(canva.getPath())){
				if(!canva.getSafePath()){
					Stage primaryStage = new Stage();
					URL url = getClass().getResource("../views/warning.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(url);
					GridPane root =(GridPane) fxmlLoader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.setTitle("Warning!");
					primaryStage.show();
				}
				else{
					write();
				}
			}
			else{
				write();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
    }

	/**
	 * Write the information of the canva in the file on svg format
	 * 
	 * @param path path of the file to save
	 * @throws Exception all exception
	 */
    private void write() throws Exception{
		List<CanvasObject> objects = canva.getCanvas();
		List < String > objectsstr = new ArrayList < > ();
		objectsstr.add("<?xml version=\"1.0\"?>");
		objectsstr.add("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"800px\" height=\"800px\" viewBox=\"0 0 800 800\">");
		for(CanvasObject object : objects){
			objectsstr.add(object.toSVG());
		}
		objectsstr.add("</svg>");
		if(exist=true){
			Files.write(canva.getPath(), objectsstr, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.WRITE);
		}
		else{
			Files.write(canva.getPath(), objectsstr, StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			canva.setSafePath(true);
		}
    }

	/**
	 * Delete the file to create it anew
	 * 
	 * @throws Exception all exception
	 */
    public void reecrire() throws Exception{
		exist=true;
		canva.setSafePath(true);
		write();
    }
}
