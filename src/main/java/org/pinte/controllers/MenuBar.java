package org.pinte.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pinte.models.Save;

public class MenuBar {
	/**
	 * menu bar
	 */
	@FXML
	public javafx.scene.control.MenuBar menuBar;

	/**
	 * initialize the toolbar of the application
	 */
	public void initialize() {
		Save save = new Save();
		Menu menu = new Menu("Fichier");

		// Items of the toolbar
		MenuItem menuItemSaveAs = new MenuItem("Enregistrer sous");
		MenuItem menuItemSave = new MenuItem("Enregistrer");

		//adding items to the menu
		menu.getItems().add(menuItemSave);
		menu.getItems().add(menuItemSaveAs);
		
		// adding menu to the menu bar
		menuBar.getMenus().add(menu);

		// adding events to the items
		menuItemSaveAs.setOnAction(event -> {
			save.SaveFile_as();
		});
		menuItemSave.setOnAction(event -> {
			save.SaveFile(true);
		});
	}
}
