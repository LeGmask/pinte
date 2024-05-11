package org.pinte.models.states;

import org.pinte.models.CanvasContextualMenu;

public class noActionState extends State{
	@Override
	public void initialize() {
		canvas.javafxCanvas.setOnMouseClicked(CanvasContextualMenu.getContextualMenu(canvas));
	}
}
