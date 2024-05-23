package org.pinte.models.states;

import javafx.geometry.Point2D;
import org.pinte.models.CanvasObjects.*;
import org.pinte.models.CanvasContextualMenu;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import java.util.List;

public class translateState extends State {
    Point2D p1, p2;
    List<CanvasObject> toMove;

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
            }
        };
    }

    public EventHandler<MouseEvent> translate() {
        canvas.javafxCanvas.setOnMouseMoved(moveShape());

        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                for (CanvasObject c : toMove) {
                    c.translate(p2.subtract(p1));
                }
                canvas.javafxCanvas.setOnMouseClicked(CanvasContextualMenu.getContextualMenu(canvas));

            }

        };
    }
}
