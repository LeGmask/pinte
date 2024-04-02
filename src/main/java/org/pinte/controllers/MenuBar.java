package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenuBar {
	public javafx.scene.control.MenuBar menuBar;

	// on create
	public void initialize() {
		Menu menu = new Menu("Menu 1");

		RadioMenuItem choice1Item = new RadioMenuItem("Choice 1");
		RadioMenuItem choice2Item = new RadioMenuItem("Choice 2");
		RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");

		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(choice1Item);
		toggleGroup.getToggles().add(choice2Item);
		toggleGroup.getToggles().add(choice3Item);

		menu.getItems().add(choice1Item);
		menu.getItems().add(choice2Item);
		menu.getItems().add(choice3Item);

		menuBar.getMenus().add(menu);
	}
}
