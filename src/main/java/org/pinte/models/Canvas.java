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
	 * There is a file opened by the app at the end of the path
	 */
	private boolean safePath = false;
	/**
	 * The dimension of the canvas
	 */
	private Dimension dim;

	/**
	 * Where the project should be saved
	 */
	private Path path;

	/**
	 * path that user want to open
	 */
	private Path openpath;

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
	 * Renvoi la liste des objets du Canvas
	 */
	public List<CanvasObject> getCanvas() {
		List<CanvasObject> list = this.objects;
		return list;
	}

	/**
	 * Get the current project path
	 *
	 * @return the path of the current project
	 */
	public Path getPath() {
		return this.path;
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
	 * Get if the path don't point to an unrelatd file
	 * 
	 * @return boolean that answer previous statement
	 */
	public boolean getSafePath() {
		return this.safePath;
	}

	/**
	 * Set if the path led to an unrelated file
	 * 
	 * @param safe boolean that answer previous statement
	 */
	public void setSafePath(boolean safe) {
		this.safePath = safe;
	}

	/**
	 * Get the path of file to open
	 *
	 * @return the path of the project to open
	 */
	public Path getPathOpen() {
		return this.openpath;
	}

	/**
	 * Set the path of file to open
	 *
	 * @param path the path of the project to open
	 */
	public void setPathOpen(Path path) {
		this.openpath = path;
	}
}
