package org.pinte.models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.util.ArrayList;
import java.util.List;

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
                List<CanvasObject> pointedShapes = isOnShape(e, canvas);
                if (!pointedShapes.isEmpty()) {
                    shapeContextualMenu(e, canvas, pointedShapes);
                } else {
                    canvasContextualMenu(e, canvas);
                }

            }
        };
    }

    /**
     * Returns the list of objects that are on the mouse position
     *
     * @param e
     * @param canvas
     * @return
     */
    protected static List<CanvasObject> isOnShape(MouseEvent e, Canvas canvas) {
        List<CanvasObject> shapes = new ArrayList<CanvasObject>();
        for (CanvasObject shape : canvas.objects) {
            if (shape.contains(e.getX(), e.getY())) {
                shapes.add(shape);
            }
        }
        return shapes;
    }

    /**
     * Creates the contextual menu for the shapes
     *
     * @param e
     * @param canvas
     * @param pointedShapes
     */
    protected static void shapeContextualMenu(MouseEvent e, Canvas canvas, List<CanvasObject> pointedShapes) {
        switch (e.getButton()) {
            case PRIMARY:
                if (e.isControlDown()) {
                    pointedShapes.getLast().isSelected = !pointedShapes.getLast().isSelected;
                }
                break;
            case SECONDARY:
                final ContextMenu contextMenu = new ContextMenu();
                contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("showing shape context menu for");
                        for (CanvasObject shape : pointedShapes) {
                            System.out.println(shape.toSVG());
                        }
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
                        final ClipboardContent content = new ClipboardContent();
                        content.putString(pointedShapes.getLast().toSVG());
                        canvas.getClipboard().setContent(content);
                        System.out.println("Copied :");
                        System.out.println(pointedShapes.getLast().toSVG());
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

    /**
     * Creates the contextual menu for the canvas
     *
     * @param e
     * @param canvas
     */
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

        
                MenuItem itemPaste = new MenuItem("Paste");
                itemPaste.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ev) {
            
                        //If there is something to paste
                        if (canvas.getClipboard().hasString()) {
                          
                            // Convert SVG to CanvasObject
                            String shapeToPasteSVG = canvas.getClipboard().getString();
                            CanvasEllipse shapeToPaste = CanvasEllipse.createFromSVG(shapeToPasteSVG);

                            // Need do paste where the mouse is
                            Double mouseX = e.getX();
                            Double mouseY = e.getY();

                            // Change center
                            shapeToPaste.setCenter(new Point2D(mouseX, mouseY));

                            //Paste to the canvas
                            canvas.add(shapeToPaste);
                            System.out.println("Paste");

                        } else {
                            System.out.println("Nothing to paste");
                        }
                    }
                });

                contextMenu.getItems().addAll(itemPaste);
                contextMenu.show(canvas.javafxCanvas, e.getScreenX(), e.getScreenY());
                break;

            default:
                break;
        }

    }

}
