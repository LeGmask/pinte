package org.pinte.models.states;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.pinte.models.CanvasContextualMenu;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.util.List;

public class translateState extends State {
	Point2D p1, p2;
	List<CanvasObject> toMove;
	boolean ended = false;

	public translateState(List<CanvasObject> toMove, MouseEvent e) {
		p1 = new Point2D(e.getX(), e.getY());
		this.toMove = toMove;
	}

	public void enterTranslateState() {
		canvas.javafxCanvas.setOnMouseClicked(translate());
	}

	public EventHandler<MouseEvent> moveShape() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				p2 = new Point2D(e.getX(), e.getY());

				canvas.removeGhostObject();
				if (!ended) {
					CanvasObject ellipse = new CanvasEllipse(p2,
						10, 10,
						new CanvasColor(255, 255, 255, 0), new CanvasColor(128, 128, 0, 1));

					ellipse.isSelected = true;
					canvas.setGhostObject(ellipse);
				}
			}
		};
	}

	public EventHandler<MouseEvent> translate() {
		canvas.javafxCanvas.setOnMouseMoved(moveShape());

		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ended = true;

				for (CanvasObject c : toMove) {
					c.translate(p2.subtract(p1));
				}
				canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_MOVED, moveShape());
				canvas.javafxCanvas.setOnMouseClicked(CanvasContextualMenu.getContextualMenu(canvas));
				canvas.removeGhostObject();

			}

		};
	}
}
