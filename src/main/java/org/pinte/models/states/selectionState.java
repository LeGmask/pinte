package org.pinte.models.states;

import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasContextualMenu;

public class selectionState extends State{
	public selectionState() {
		canvas.javafxCanvas.setOnMouseClicked(CanvasContextualMenu.getContextualMenu(canvas));
	}

	public void exitState() {
		canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, CanvasContextualMenu.getContextualMenu(canvas));
	}
}
