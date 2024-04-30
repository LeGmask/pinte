package org.pinte.models;
import java.io.*;
import org.pinte.models.CanvasObjects.CanvasObject;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.nio.file.Files;

public class Save{
	/**
	 * Saving as boolean
	 */
	boolean savingas=false;
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
            //création du chemin vers le fichier
            File f = new File(canva.getPath().toString());
			this.savingas=savingas;
            create(f);
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
    private void write(String path) throws Exception{
		List<CanvasObject> objects = canva.getCanvas();
        BufferedWriter file = new BufferedWriter(new FileWriter(path));
        file.write("<?xml version=\"1.0\"?>");
		file.newLine();
		file.write("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"800px\" height=\"800px\" viewBox=\"0 0 800 800\">");
        file.newLine();
		for(CanvasObject object : objects){
			file.write(object.toSVG());
			file.newLine();
		}
        file.write("</svg>");
        file.close();
    }

	/**
	 * create a new file to save the canva
	 * 
	 * @param f File that will be created
	 * @throws Exception all exception
	 */
    private void create(File f) throws Exception{
        //create the new file
        if(f.createNewFile()){
            //new file created with success
            write(canva.getPath().toString());
        } else {
            //File already existing
			if(this.savingas==false){
				try{
					Stage primaryStage = new Stage();
					URL url = getClass().getResource("../views/warning.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(url);
					GridPane root =(GridPane) fxmlLoader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.setTitle("Warning!");
					primaryStage.show();
				} catch(Exception e) {
					System.out.println(e);
				}
			} else {
				delete();
				this.savingas=false;
			}
        }
    }

	/**
	 * Delete the file to create it anew
	 * 
	 * @throws Exception all exception
	 */
    public void delete() throws Exception{
		File f = new File(canva.getPath().toString());
        if(f.delete()){
            //File deleted with success
            create(f);
        }else {
            //failing to delete the file
        }
    }
}
