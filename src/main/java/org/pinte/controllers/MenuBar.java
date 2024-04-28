package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import org.pinte.models.enregistrer;
import javafx.stage.Stage;

public class MenuBar {
	public javafx.scene.control.MenuBar menuBar;
	public String path;
	// on create
	public void initialize() {
		Menu menu = new Menu("Fichier");

		RadioMenuItem choice1Item = new RadioMenuItem("enregistrer sous");
		RadioMenuItem choice2Item = new RadioMenuItem("enregistrer");
		RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");

		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(choice1Item);
		toggleGroup.getToggles().add(choice2Item);
		toggleGroup.getToggles().add(choice3Item);

		menu.getItems().add(choice1Item);
		menu.getItems().add(choice2Item);
		menu.getItems().add(choice3Item);

		menuBar.getMenus().add(menu);
		
		choice1Item.setOnAction(event->{chemin();});
		choice2Item.setOnAction(event->{if(path!=null){enregistrer();}});
	}
	/**
	 * ouvre les fênetres pour choisir le nom et l'emplacement du fichier à enregistrer et enregistre
	 */
	public void chemin(){
		try{
		System.out.println("enregistrer");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Save Location");
		path = directoryChooser.showDialog(null).getAbsolutePath();
		if(path!=null){
			enregistrer();
		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * appel la classe enregistrer
	 */
	public void enregistrer(){
		enregistrer save = new enregistrer(path);
		save.sauvegarder();
	}

}
