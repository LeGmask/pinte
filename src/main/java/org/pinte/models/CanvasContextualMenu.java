package org.pinte.models;

import org.pinte.models.CanvasObjects.CanvasObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class CanvasContextualMenu {
    /**
     * Creates a context menu for the canvas
     *
     * @param canvas
     * @return
     */
    public static EventHandler<MouseEvent> getContextualMenu(Canvas canvas) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                var object = isOnShape(e, canvas);
                if (object != null) {
                    shapeContextualMenu(e, canvas, object);
                } else {
                    canvasContextualMenu(e, canvas);
                }

            }
        };
    }

    /**
     * Returns the shape that was clicked on or null if none found.
     *
     * @param e
     * @param canvas
     * @return
     */
    protected static CanvasObject isOnShape(MouseEvent e, Canvas canvas) {
        for (CanvasObject shape : canvas.objects) {
            if (shape.contains(e.getX(), e.getY())) {
                return shape;
            }
        }
        return null;
    }

    protected static void shapeContextualMenu(MouseEvent e, Canvas canvas, CanvasObject object) {
        switch (e.getButton()) {
            case PRIMARY:
                // shape selection here
                break;
            case SECONDARY:
                final ContextMenu contextMenu = new ContextMenu();
                contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("showing shape context menu");
                    }
                });
                contextMenu.setOnShown(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("shown shape context menu");
                    }
                });

                // dummy items for now
                MenuItem item1 = new MenuItem("Copy");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("Copied");
                    }
                });
                MenuItem item2 = new MenuItem("Reshape");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("Reshaped");
                    }
                });
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(canvas.javafxCanvas, e.getScreenX(), e.getScreenY());
                break;

            default:
                break;
        }

    }

    protected static void canvasContextualMenu(MouseEvent e, Canvas canvas) {
        switch (e.getButton()) {
            case PRIMARY:
                break;
            case SECONDARY:
                final ContextMenu contextMenu = new ContextMenu();
                contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("showing canvas context menu");
                    }
                });
                contextMenu.setOnShown(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("shown canvas context menu");
                    }
                });

                // dummy items for now
                MenuItem item1 = new MenuItem("Resize");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("Resized");
                    }
                });
                MenuItem item2 = new MenuItem("Save");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("Saved");
                    }
                });
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(canvas.javafxCanvas, e.getScreenX(), e.getScreenY());
                break;

            default:
                break;
        }

    }

}