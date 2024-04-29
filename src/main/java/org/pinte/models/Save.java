package org.pinte.models;
import java.io.*;
import org.pinte.models.CanvasObjects.CanvasObject;
import java.util.List;

public class Save{

    String savepath;
    String title;
	Canvas canva = Canvas.getInstance();
    public Save(String path){
        this.savepath=path;
        this.title="NewFile";
    }

    public void SaveFile(){
        try{
            //création du chemin vers le fichier
            this.savepath=this.savepath + "/" + this.title + ".svg";
            File f = new File(this.savepath);
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
            write(this.savepath);
        } else {
            //le fichier existe déjà
            System.out.println("File already exist");
            delete(f);
        }
    }

    private void delete(File f) throws Exception{
        if(f.delete()){
            System.out.println("File deleted with success");
            create(f);
        }else {
            System.out.println("Failed to delete");
        }
    }
}
