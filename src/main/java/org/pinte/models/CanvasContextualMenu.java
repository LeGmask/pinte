package org.pinte.models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import org.pinte.models.CanvasObjects.CanvasLine;
import org.pinte.models.CanvasObjects.CanvasObject;
import org.pinte.models.CanvasObjects.CanvasTextField;
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
		List<CanvasObject> applyOperationOn = new ArrayList<CanvasObject>();
		boolean oneSelected = false;

		oneSelected = !pointedShapes.isEmpty() && pointedShapes.getLast().isSelected;

		if (oneSelected) {
			for (CanvasObject shape : canvas.objects) {
				if (shape.isSelected) {
					applyOperationOn.add(shape);
				}
			}
		} else {
			applyOperationOn.add(pointedShapes.getLast());

		}

		switch (e.getButton()) {
			case PRIMARY:
				if (e.isControlDown()) {
					pointedShapes.getLast().isSelected = !pointedShapes.getLast().isSelected;
				} else {

					canvas.javafxCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, getContextualMenu(canvas));

					translateState translateState = new translateState(applyOperationOn, e);
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

				addGenericEntriesContextualMenu(contextMenu, e, canvas, applyOperationOn);
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
				addGenericEntriesContextualMenu(contextMenu, e, canvas, new ArrayList<CanvasObject>());
				contextMenu.show(canvas.javafxCanvas, e.getScreenX(), e.getScreenY());
				break;

			default:
				break;
		}

	}

	protected static void addGenericEntriesContextualMenu(ContextMenu contextMenu, MouseEvent e, Canvas canvas,
			List<CanvasObject> selected) {

		MenuItem itemSave = new MenuItem("Save Project");
		itemSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Saved");
			}
		});

		MenuItem itemFill = new MenuItem("Fill with selected color");
		itemFill.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for (CanvasObject object : selected) {
					object.setFillColor(canvas.getCopyColorSelect());
				}
			}
		});
		MenuItem itemStroke = new MenuItem("Stroke with selected color");
		itemStroke.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for (CanvasObject object : selected) {
					object.setStrokeColor(canvas.getCopyColorSelect());
				}
			}
		});

		MenuItem itemFontSize = new MenuItem("Resize text");
		itemFontSize.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for (CanvasObject object : selected) {
					if (object instanceof CanvasTextField) {
						((CanvasTextField)object).setFontSize(canvas.getSelectedFontSize());
					}
				}
			}
		});

		MenuItem itemFontType = new MenuItem("Change font");
		itemFontType.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for (CanvasObject object : selected) {
					if (object instanceof CanvasTextField) {
						((CanvasTextField)object).setFontFamily(canvas.getSelectedFontType());
					}
				}
			}
		});

		MenuItem itemDelete = new MenuItem("Delete");
		itemDelete.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for (CanvasObject object : selected) {
					canvas.objects.remove(object);
				}

			}

		});

		MenuItem itemCopy = new MenuItem("Copy");
		itemCopy.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				canvas.setClipboard(selected);
				System.out.println("Copied :");
			}
		});

		MenuItem itemPaste = new MenuItem("Paste");
		itemPaste.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				Point2D new_position = new Point2D(e.getX(), e.getY());

				List<CanvasObject> clipboard = canvas.getClipboard();

				if (!clipboard.isEmpty() && clipboard != null) {
					Point2D old_center = new Point2D(0, 0);
					for (CanvasObject shape : clipboard) {
						old_center = old_center.add(shape.getGravityCenter());

					}
					old_center = old_center.multiply(1.0 / clipboard.size());

					for (CanvasObject shape : clipboard) {
						canvas.add(shape.duplicate(new_position.subtract(old_center)));

					}
				}

			}
		});
		contextMenu.getItems().addAll(itemFill, itemStroke, itemDelete, itemFontSize, itemFontType, itemCopy, itemPaste, itemSave);

	}

}
