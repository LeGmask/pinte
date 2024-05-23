package org.pinte.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pinte.models.Coloration;
import org.pinte.models.Save;

public class MenuBar {
	/**
	 * menu bar
	 */
	@FXML
	public javafx.scene.control.MenuBar menuBar;

	/**
	 * initialize the toolbar of the application.
	 */
	public void initialize() {
		Coloration coloration = new Coloration();
		Save save = new Save();
		Menu menuFichier = new Menu("Fichier");
		Menu menuCouleur = new Menu("Couleur");
		Menu menuOpacite = new Menu("Opacité");

		// Items of the toolbar.
		MenuItem menuItemSaveAs = new MenuItem("Enregistrer sous");
		MenuItem menuItemSave = new MenuItem("Enregistrer");
		MenuItem menuItemExport = new MenuItem("Exporter");

		//adding items to the menu.
		menuFichier.getItems().add(menuItemSave);
		menuFichier.getItems().add(menuItemSaveAs);
		menuFichier.getItems().add(menuItemExport);

		// adding menu to the menu bar.
		menuBar.getMenus().add(menuFichier);

		// adding events to the items.
		menuItemSaveAs.setOnAction(event -> {
			save.SaveFile_as();
		});
		menuItemSave.setOnAction(event -> {
			save.SaveFile(true);
		});
		menuItemExport.setOnAction(event -> {
			try {
				new Export();
			} catch (Exception e) {
				System.out.printf("Erreur lors de l'export: %s", e.getMessage());
			}
		});

		// création des choix du menu couleur.
		MenuItem menuItemBlack = new MenuItem("Noire");
		MenuItem menuItemWhite = new MenuItem("Blanc");
		MenuItem menuItemBlue = new MenuItem("Bleu");
		MenuItem menuItemGreen = new MenuItem("Vert");
		MenuItem menuItemRed = new MenuItem("Rouge");
		MenuItem menuItemCyan = new MenuItem("Cyan");
		MenuItem menuItemMagenta = new MenuItem("Magenta");
		MenuItem menuItemYellow = new MenuItem("Jaune");

		// rajout des choix au menu couleur.
		menuCouleur.getItems().add(menuItemBlack);
		menuCouleur.getItems().add(menuItemWhite);
		menuCouleur.getItems().add(menuItemBlue);
		menuCouleur.getItems().add(menuItemGreen);
		menuCouleur.getItems().add(menuItemRed);
		menuCouleur.getItems().add(menuItemCyan);
		menuCouleur.getItems().add(menuItemMagenta);
		menuCouleur.getItems().add(menuItemYellow);

		// rajout du menu couleur à la barre des menu.
		menuBar.getMenus().add(menuCouleur);

		// Rajout des évènements associées aux options de couleurs.
		menuItemBlack.setOnAction(event -> {
			coloration.Black();
		});
		menuItemWhite.setOnAction(event -> {
			coloration.White();
		});
		menuItemBlue.setOnAction(event -> {
			coloration.Blue();
		});
		menuItemGreen.setOnAction(event -> {
			coloration.Green();
		});
		menuItemRed.setOnAction(event -> {
			coloration.Red();
		});
		menuItemCyan.setOnAction(event -> {
			coloration.Cyan();
		});
		menuItemMagenta.setOnAction(event -> {
			coloration.Magenta();
		});
		menuItemYellow.setOnAction(event -> {
			coloration.Yellow();
		});

		// création des choix du menu couleur.
		MenuItem menuItemOpaq0 = new MenuItem("0 %");
		MenuItem menuItemOpaq2 = new MenuItem("20 %");
		MenuItem menuItemOpaq4 = new MenuItem("40 %");
		MenuItem menuItemOpaq6 = new MenuItem("60 %");
		MenuItem menuItemOpaq8 = new MenuItem("80 %");
		MenuItem menuItemOpaq10 = new MenuItem("100 %");

		// rajout des choix au menu couleur.
		menuOpacite.getItems().add(menuItemOpaq0);
		menuOpacite.getItems().add(menuItemOpaq2);
		menuOpacite.getItems().add(menuItemOpaq4);
		menuOpacite.getItems().add(menuItemOpaq6);
		menuOpacite.getItems().add(menuItemOpaq8);
		menuOpacite.getItems().add(menuItemOpaq10);

		// rajout du menu couleur à la barre des menu.
		menuBar.getMenus().add(menuOpacite);

		// Rajout des évènements associées aux options de couleurs.
		menuItemOpaq0.setOnAction(event -> {
			coloration.Opaq(0);
		});
		menuItemOpaq2.setOnAction(event -> {
			coloration.Opaq(51);
		});
		menuItemOpaq4.setOnAction(event -> {
			coloration.Opaq(102);
		});
		menuItemOpaq6.setOnAction(event -> {
			coloration.Opaq(153);
		});
		menuItemOpaq8.setOnAction(event -> {
			coloration.Opaq(204);
		});
		menuItemOpaq10.setOnAction(event -> {
			coloration.Opaq(255);
		});
	}
}
