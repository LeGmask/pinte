package org.pinte.models.states;

import org.pinte.models.CanvasContextualMenu;

public class selectionState extends State {
	public selectionState() {
		canvas.javafxCanvas.setOnMouseClicked(CanvasContextualMenu.getContextualMenu(canvas));
	}
}
