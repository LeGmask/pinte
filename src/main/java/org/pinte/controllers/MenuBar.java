package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import org.pinte.models.enregistrer;

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
		choice2Item.setOnAction(event->{enregistrer();});
	}
	
	public void chemin(){
		System.out.println("enregistrer");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Project Location");
		path = directoryChooser.showDialog(null).getAbsolutePath();
		enregistrer();
	}

	public void enregistrer(){
		enregistrer save = new enregistrer("path");
		save.sauvegarder();
	}

}
