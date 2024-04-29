package org.pinte.models;

import javafx.scene.canvas.GraphicsContext;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for the Canvas
 */
public final class Canvas {
	/**
	 * Singleton instance of the Canvas
	 */
	private static Canvas INSTANCE;

	/**
	 * The javafx canvas
	 */
	public javafx.scene.canvas.Canvas javafxCanvas;

	/**
	 * The objects list
	 */
	public List<CanvasObject> objects;


	/**
	 * Private constructor for the Canvas
	 */
	private Canvas() {
		objects = new ArrayList<CanvasObject>(10);
	}

	/**
	 * Singleton instance of the Canvas
	 *
	 * @return the Canvas instance
	 */
	public static Canvas getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Canvas();
		}
		return INSTANCE;
	}

	/**
	 * Set the javafx canvas
	 *
	 * @param javafxCanvas the javafx canvas
	 */
	public void setJavafxCanvas(javafx.scene.canvas.Canvas javafxCanvas) {
		this.javafxCanvas = javafxCanvas;
	}

	/**
	 * Resize the canvas
	 *
	 * @param width  the width of the canvas
	 * @param height the height of the canvas
	 */
	public void resizeCanvas(int width, int height) {
		javafxCanvas.setHeight(height);
		javafxCanvas.setWidth(width);
	}

	/**
	 * Get the GraphicsContext2D of the canvas
	 *
	 * @return the GraphicsContext2D of the canvas
	 */
	public GraphicsContext getGraphicsContext2D() {
		return this.javafxCanvas.getGraphicsContext2D();
	}

	/**
	 * Clear the canvas
	 */
	public void clear() {
		this.javafxCanvas.getGraphicsContext2D().clearRect(0, 0, this.javafxCanvas.getWidth(), this.javafxCanvas.getHeight());
	}

	/**
	 * Render the canvas
	 */
	public void render() {
		for (CanvasObject object : objects) {
			object.render();
		}
	}

	/**
	 * Add an object to the canvas
	 *
	 * @param object the object to add
	 */
	public void add(CanvasObject object) {
		objects.add(object);
	}


}
