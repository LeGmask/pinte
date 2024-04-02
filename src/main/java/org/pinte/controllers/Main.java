package org.pinte.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import org.pinte.models.SVG;
import org.w3c.dom.Text;

import java.awt.*;

public class Main {
	public MenuBar menuBar;
	@FXML
	Text label;

	SVG svg = SVG.getInstance();


	public void test(ActionEvent actionEvent) {
		label.setTextContent(svg.label);
	}
}
