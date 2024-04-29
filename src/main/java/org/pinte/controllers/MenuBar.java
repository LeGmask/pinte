package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import org.pinte.models.Save;

public class MenuBar {
	public javafx.scene.control.MenuBar menuBar;
	public String path;
	// on create
	public void initialize() {
		Save save = new Save();
		Menu menu = new Menu("Fichier");

		MenuItem choice1Item = new MenuItem("enregistrer sous");
		MenuItem choice2Item = new MenuItem("enregistrer");
		RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");

		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(choice3Item);

		menu.getItems().add(choice1Item);
		menu.getItems().add(choice2Item);
		menu.getItems().add(choice3Item);

		menuBar.getMenus().add(menu);
		
		choice1Item.setOnAction(event->{save.SaveFile_as();});
		choice2Item.setOnAction(event->{save.SaveFile();});
	}
}
