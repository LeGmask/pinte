package org.pinte.models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasObject;
import org.pinte.models.CanvasObjects.CanvasRectangle;

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


                MenuItem itemCopy = new MenuItem("Copy");
                itemCopy.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        final ClipboardContent content = new ClipboardContent();
                        content.putString(pointedShapes.getLast().toSVG());
                        canvas.getClipboard().setContent(content);
                        System.out.println("Copied :");
                        System.out.println(pointedShapes.getLast().toSVG());
                    }
                });

                MenuItem itemPaste = new MenuItem("Paste");
                itemPaste.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ev) {
            
                        //If there is something to paste
                        if (canvas.getClipboard().hasString()) {
                          
                            // Grab the new center for the Object
                            Double mouseX = e.getX();
                            Double mouseY = e.getY();

                            // Convert SVG to CanvasObject
                            String shapeToPasteSVG = canvas.getClipboard().getString();

                            if (shapeToPasteSVG.contains("ellipse")) {
                                pasteEllipse(shapeToPasteSVG , mouseX, mouseY, canvas);
                            } else if (shapeToPasteSVG.contains("rect")) {
                                pasteRect(shapeToPasteSVG , mouseX, mouseY, canvas);
                            } else {
                                System.out.println("Impossible to paste this shape for the moment");
                            }

                            

                        } else {
                            System.out.println("Nothing to paste");
                        }
                    }
                });

                contextMenu.getItems().addAll(itemCopy, itemPaste);
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
                          
                            // Grab the new center for the Object
                            Double mouseX = e.getX();
                            Double mouseY = e.getY();

                            // Convert SVG to CanvasObject
                            String shapeToPasteSVG = canvas.getClipboard().getString();

                            if (shapeToPasteSVG.contains("ellipse")) {
                                pasteEllipse(shapeToPasteSVG , mouseX, mouseY, canvas);
                            } else if (shapeToPasteSVG.contains("rect")) {
                                pasteRect(shapeToPasteSVG , mouseX, mouseY, canvas);
                            } else {
                                System.out.println("Impossible to paste this shape for the moment");
                            }

                            

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

    /**
     *  Paste a new Ellipse to the Canvas
     * @param shapeSVG svg describing the ellipse to copy
     * @param x new x center
     * @param y new y center
     * @param canvas canvas to add the ellipse
     */
    static protected void pasteEllipse(String shapeSVG, Double x, Double y, Canvas canvas) {
        CanvasEllipse shapeToPaste = CanvasEllipse.createFromSVG(shapeSVG);

        // Change center
        shapeToPaste.setCenter(new Point2D(x, y));

        //Paste to the canvas
        canvas.add(shapeToPaste);

        System.out.println("Paste");
    }

    /**
     * Paste a new Rectangle to the Canvas
     * @param shapeSVGsvg describing the rectangle to copy
     * @param x new x center
     * @param y new y center
     * @param canvas canvas to add the ellipse
     */
    static protected void pasteRect(String shapeSVG, Double x, Double y, Canvas canvas) {
        CanvasRectangle shapeToPaste = CanvasRectangle.createFromSVG(shapeSVG);

        // Change center
        shapeToPaste.setNewCornersPosition(x, y);

        //Paste to the canvas
        canvas.add(shapeToPaste);

        System.out.println("Paste");
    }
}
