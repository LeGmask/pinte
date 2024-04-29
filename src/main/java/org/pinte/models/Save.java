package org.pinte.models;
import java.io.*;

public class Save{

    String savepath;
    String title;
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
        //écriture du nouveau fichier
        BufferedWriter file = new BufferedWriter(new FileWriter(path));
        file.write("<?xml version=\"1.0\"?>");
        file.newLine();
        file.write("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000px\" height=\"500px\" viewBox=\"0 0 1000 800\">");
        file.newLine();
        file.write("<text x=\"250\" y=\"150\" font-family=\"Verdana\" font-size=\"55\">");
        file.newLine();
        file.write("Hello world !");
        file.newLine();
        file.write("</text>");
        file.newLine();
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
