package org.pinte.models;

import javafx.scene.canvas.GraphicsContext;
import org.pinte.models.CanvasObjects.CanvasObject;

import java.awt.*;
import java.nio.file.Path;
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
	 * The javafx GraphicsContext
	 */
	public javafx.scene.canvas.GraphicsContext javafxGraphicsContext;

	/**
	 * The objects list
	 */
	public List<CanvasObject> objects;

	/**
	 * The dimension of the canvas
	 */
	private Dimension dim;

	/**
	 * Where the project should be saved
	 */
	private Path path;


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
		this.javafxGraphicsContext = javafxCanvas.getGraphicsContext2D();

		if (this.dim != null) {
			this.javafxCanvas.setWidth(this.dim.getWidth());
			this.javafxCanvas.setHeight(this.dim.getHeight());
		}
	}

	/**
	 * Resize the canvas
	 *
	 * @param width  the width of the canvas
	 * @param height the height of the canvas
	 */
	public void setDim(int width, int height) {
		this.dim = new Dimension(width, height);

		if (this.javafxCanvas != null) {
			this.javafxCanvas.setWidth(width);
			this.javafxCanvas.setHeight(height);
		}
	}

	public Dimension getDim() {
		return this.dim;
	}

	/**
	 * Get the GraphicsContext2D of the canvas
	 *
	 * @return the GraphicsContext2D of the canvas
	 */
	public GraphicsContext getGraphicsContext2D() {
		return javafxGraphicsContext;
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

	/**
	 * Set current project path
	 *
	 * @param path the path of the current project
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * Get the current project path
	 *
	 * @return the path of the current project
	 */
	public Path getPath() {
		return this.path;
	}
}
