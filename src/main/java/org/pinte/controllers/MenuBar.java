package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MenuBar {
	public javafx.scene.control.MenuBar menuBar;
	
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
		
		choice1Item.setOnAction(event->{enregistrerss();});
		choice1Item.setOnAction(event->{enregistrer();});
	}
	
	public void enregistrerss(){
		System.out.println("enregistrer");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Project Location");
		System.out.println(directoryChooser.showDialog(null).getAbsolutePath());
	}

	public void enregistrer(){
		
	}

}
