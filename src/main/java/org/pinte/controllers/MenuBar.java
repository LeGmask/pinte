package org.pinte.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pinte.models.Canvas;
import org.pinte.models.Coloration;
import org.pinte.models.Open;
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
		Open open = new Open();
		Menu menuFichier = new Menu("File");
		Menu menuCouleur = new Menu("Color");
		Menu menuOpacite = new Menu("Opacity");
		Menu menuFontType = new Menu("FontType");
		Menu menuFontSize = new Menu("FontSize");

		// Items of the toolbar.
		MenuItem menuItemNew = new MenuItem("New");
		MenuItem menuItemSaveAs = new MenuItem("Save As");
		MenuItem menuItemSave = new MenuItem("Save");
		MenuItem menuItemExport = new MenuItem("Export");
		MenuItem menuItemOpen = new MenuItem("Open");

		//adding items to the menu.
		menuFichier.getItems().add(menuItemNew);
		menuFichier.getItems().add(menuItemSave);
		menuFichier.getItems().add(menuItemSaveAs);
		menuFichier.getItems().add(menuItemExport);
		menuFichier.getItems().add(menuItemOpen);

		// adding menu to the menu bar.
		menuBar.getMenus().add(menuFichier);

		// adding events to the items.
		menuItemSaveAs.setOnAction(event -> {
			save.SaveFile_as();
		});
		menuItemSave.setOnAction(event -> {
			save.SaveFile(true);
		});
		menuItemOpen.setOnAction(event -> {
			open.warning(false);
		});
		menuItemNew.setOnAction(event -> {
			open.warning(true);
		});
		menuItemExport.setOnAction(event -> {
			try {
				new Export();
			} catch (Exception e) {
				System.out.printf("Erreur lors de l'export: %s", e.getMessage());
			}
		});

		// création des choix du menu couleur.
		MenuItem menuItemBlack = new MenuItem("Black");
		MenuItem menuItemWhite = new MenuItem("White");
		MenuItem menuItemBlue = new MenuItem("Blue");
		MenuItem menuItemGreen = new MenuItem("Green");
		MenuItem menuItemRed = new MenuItem("Red");
		MenuItem menuItemCyan = new MenuItem("Cyan");
		MenuItem menuItemMagenta = new MenuItem("Magenta");
		MenuItem menuItemYellow = new MenuItem("Yellow");

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


		// creation of menu for font size choice
		MenuItem menuFontSize10 = new MenuItem("10");
		MenuItem menuFontSize25 = new MenuItem("25");
		MenuItem menuFontSize50 = new MenuItem("50");
		MenuItem menuFontSize60 = new MenuItem("60");
		MenuItem menuFontSize80 = new MenuItem("80");

		menuFontSize.getItems().add(menuFontSize10);
		menuFontSize.getItems().add(menuFontSize25);
		menuFontSize.getItems().add(menuFontSize50);
		menuFontSize.getItems().add(menuFontSize60);
		menuFontSize.getItems().add(menuFontSize80);


		menuBar.getMenus().add(menuFontSize);

		menuFontSize10.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontSize(10);
		});
		menuFontSize25.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontSize(25);
		});
		menuFontSize50.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontSize(50);
		});
		menuFontSize60.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontSize(60);
		});
		menuFontSize80.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontSize(80);
		});

		// creation of menu for font type
		MenuItem menuFontTypeSerif = new MenuItem("serif");
		MenuItem menuFontTypeSansSerif = new MenuItem("sans-serif");
		MenuItem menuFontTypeMonospace = new MenuItem("monospace");

		menuFontType.getItems().add(menuFontTypeSerif);
		menuFontType.getItems().add(menuFontTypeSansSerif);
		menuFontType.getItems().add(menuFontTypeMonospace);

		menuBar.getMenus().add(menuFontType);

		menuFontTypeSerif.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontType("serif");
		});
		menuFontTypeSansSerif.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontType("sans-serif");
		});
		menuFontTypeMonospace.setOnAction(event -> {
			Canvas.getInstance().setSelectedFontType("monospace");
		});


	}


}
