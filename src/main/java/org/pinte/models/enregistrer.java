package org.pinte.models;
import java.io.*;

public class enregistrer {

    String chemin;
    String title;
    public enregistrer(String path){
        this.chemin=path;
        this.title="NewFile";
    }

    public void sauvegarder(){
        try{
            this.chemin=this.chemin + "/" + this.title + ".txt";
            File f = new File(chemin);
            if(f.createNewFile()){
                //nouveau fichier crée
                System.out.println("File created");
                FileWriter file = new FileWriter(chemin);
                file.write(chemin);
                file.close();
            } else {
                //le fichier existe déjà
                System.out.println("File already exist");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
