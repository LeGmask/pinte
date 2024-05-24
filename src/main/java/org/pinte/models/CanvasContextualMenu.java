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
import org.pinte.models.states.translateState;

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
				} else {
					List<CanvasObject> toMove = new ArrayList<CanvasObject>();
					// is one is selected, move all selected. else move the most recent one
					boolean oneSelected = false;

					oneSelected = !pointedShapes.isEmpty() && pointedShapes.getLast().isSelected;

					if (oneSelected) {
						for (CanvasObject shape : canvas.objects) {
							if (shape.isSelected) {
								toMove.add(shape);
							}
						}
					} else {
						toMove.add(pointedShapes.getLast());

					}

					canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, getContextualMenu(canvas));

					translateState translateState = new translateState(toMove, e);
					translateState.enterTranslateState();
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
						System.out.println("Copied");
					}
				});

				MenuItem item3 = new MenuItem("Colorier interieur");
				item3.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						for (CanvasObject objet : canvas.objects) {
							if (objet.isSelected) {
								objet.setFillColor(canvas.getCopyColorSelect());
							}
						}
					}
				});
				MenuItem item4 = new MenuItem("Colorier bordure");
				item4.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						for (CanvasObject objet : canvas.objects) {
							if (objet.isSelected) {
								objet.setStrokeColor(canvas.getCopyColorSelect());
							}
						}
					}
				});

				MenuItem item5 = new MenuItem("Delete");
				item5.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						List<CanvasObject> selected = new ArrayList<CanvasObject>();

						boolean oneSelected = false;

						oneSelected = !pointedShapes.isEmpty() && pointedShapes.getLast().isSelected;

						if (oneSelected) {
							for (CanvasObject shape : canvas.objects) {
								if (shape.isSelected) {
									selected.add(shape);
								}
							}
						} else {
							selected.add(pointedShapes.getLast());

						}
            

            for (CanvasObject object : selected) {

              canvas.objects.remove(object);

            }

          }

        });
        
  MenuItem itemCopy = new MenuItem("Copy");
        itemCopy.setOnAction(new EventHandler<ActionEvent>() {
						canvas.setClipboard(selected);
						System.out.println("Copied :");
				});

				MenuItem itemPaste = new MenuItem("Paste");
				itemPaste.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent ev) {
						Point2D new_position = new Point2D(e.getX(), e.getY());

						List<CanvasObject> clipboard = canvas.getClipboard();

						if (!clipboard.isEmpty() && clipboard != null) {
							Point2D old_center = new Point2D(0, 0);
							for (CanvasObject shape : clipboard) {
								old_center.add(shape.getGravityCenter());

							}
							old_center.multiply(1 / clipboard.size());

							for (CanvasObject shape : clipboard) {
								canvas.add(shape.duplicate(new_position.subtract(old_center)));

							}
						}

					}
				});

				contextMenu.getItems().addAll(item1, item3, item4, item5,itemCopy, itemPaste);

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

				MenuItem item1 = new MenuItem("Save");
				item1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						System.out.println("Saved");
					}
				});
				contextMenu.getItems().addAll(item1);
				contextMenu.show(canvas.javafxCanvas, e.getScreenX(), e.getScreenY());
				break;

			default:
				break;
		}

	}

	/**
	 * Paste a new Ellipse to the Canvas
	 *
	 * @param shapeSVG svg describing the ellipse to copy
	 * @param x        new x center
	 * @param y        new y center
	 * @param canvas   canvas to add the ellipse
	 */
	static protected void pasteEllipse(String shapeSVG, Double x, Double y, Canvas canvas) {
		CanvasEllipse shapeToPaste = CanvasEllipse.createFromSVG(shapeSVG);

		// Change center
		shapeToPaste.setCenter(new Point2D(x, y));

		// Paste to the canvas
		canvas.add(shapeToPaste);

		System.out.println("Paste");
	}

	/**
	 * Paste a new Rectangle to the Canvas
	 *
	 * @param shapeSVGsvg describing the rectangle to copy
	 * @param x           new x center
	 * @param y           new y center
	 * @param canvas      canvas to add the ellipse
	 */
	static protected void pasteRect(String shapeSVG, Double x, Double y, Canvas canvas) {
		CanvasRectangle shapeToPaste = CanvasRectangle.createFromSVG(shapeSVG);

		// Change center
		shapeToPaste.setNewCornersPosition(x, y);

		// Paste to the canvas
		canvas.add(shapeToPaste);


		System.out.println("Paste");
	}
}
