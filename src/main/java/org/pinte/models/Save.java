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

	boolean saving=false;
	Canvas canva = Canvas.getInstance();
    public Save(){
    }


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

    public void SaveFile(boolean saving){
        try{
            //création du chemin vers le fichier
            File f = new File(canva.getPath().toString());
			this.saving=saving;
            create(f);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void write(String path) throws Exception{
		List<CanvasObject> objects = canva.getCanvas();
        //écriture du nouveau fichier
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

    private void create(File f) throws Exception{
        //création du nouveau fichier
        if(f.createNewFile()){
            //nouveau fichier crée
            System.out.println("File created");
            write(canva.getPath().toString());
        } else {
            //le fichier existe déjà
            System.out.println("File already exist");
			if(this.saving==false){
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
				this.saving=false;
			}
        }
    }

    public void delete() throws Exception{
		OutputStream out = Files.newOutputStream(canva.getPath());
		out.close();
		File f = new File(canva.getPath().toString());
        if(f.delete()){
            System.out.println("File deleted with success");
            create(f);
        }else {
            System.out.println("Failed to delete");
        }
    }
}
