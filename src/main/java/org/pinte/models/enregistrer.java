package org.pinte.models;

public class enregistrer {

    String chemin;
    public enregistrer(String path){
        this.chemin=path;
    }

    public void sauvegarder(){
        System.out.println(chemin);
    }
}
