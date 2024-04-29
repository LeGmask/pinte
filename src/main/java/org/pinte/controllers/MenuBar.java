package org.pinte.controllers;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import org.pinte.models.Save;

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
		
		choice1Item.setOnAction(event->{Path();});
		choice2Item.setOnAction(event->{if(path!=null){CallSave();}});
	}
	/**
	 * ouvre les fênetres pour choisir le nom et l'emplacement du fichier à enregistrer et enregistre
	 */
	public void Path(){
		try{
		System.out.println("enregistrer");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Save Location");
		path = directoryChooser.showDialog(null).getAbsolutePath();
		if(path!=null){
			CallSave();
		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * appel la classe enregistrer
	 */
	public void CallSave(){
		Save save = new Save(path);
		save.SaveFile();
	}

}
