package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import org.pinte.models.Save;

public class MenuBar {
	/**
	 * menu bar
	 */
	public javafx.scene.control.MenuBar menuBar;

	/**
	 * initialize the toolbar of the application
	 * 
	 */
	public void initialize() {
		Save save = new Save();
		Menu menu = new Menu("Fichier");

		/**
		 * Items of the toolbar
		 */
		MenuItem choice1Item = new MenuItem("enregistrer sous");
		MenuItem choice2Item = new MenuItem("enregistrer");
		RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");

		/**
		 * Toggle group of first Menu
		 */
		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(choice3Item);

		/**
		 * Adding items to the first menu
		 */
		menu.getItems().add(choice1Item);
		menu.getItems().add(choice2Item);
		menu.getItems().add(choice3Item);

		/**
		 * adding menu to the menubar
		 */
		menuBar.getMenus().add(menu);
		
		/**
		 * Event that trigger when button is on
		 */
		choice1Item.setOnAction(event->{save.SaveFile_as();});
		choice2Item.setOnAction(event->{save.SaveFile(true);});
	}
}
