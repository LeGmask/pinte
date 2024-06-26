package org.pinte.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.pinte.models.CanvasObjects.CanvasColor;
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
	 * The clipboard to copy paste
	 */
	public List<CanvasObject> clipboard = new ArrayList<>();

	/**
	 * La couleur actuellement utilisée
	 */
	public CanvasColor colorSelect;
	/**
	 * An object used to show information about selection, shape drawing, etc...
	 */
	public CanvasObject ghostObject = null;
	/**
	 * Selected font size
	 */
	private int selectedFontSize;
	/**
	 * Selected font type
	 */
	private String selectedFontType;
	/**
	 * The name of the project
	 */
	private String name;

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
	 * Stage of the project
	 */
	private Stage canvastage;

	/**
	 * the project is saving to close to creat a new
	 */
	private boolean nw = false;

	/**
	 * the project is saving to close to open
	 */
	private boolean open = false;

	/**
	 * Private constructor for the Canvas
	 */
	private Canvas() {
		objects = new ArrayList<>(10);
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
		this.colorSelect = new CanvasColor(120, 120, 250, 101);
		this.selectedFontSize = 25;
		this.selectedFontType = "serif";
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

	/**
	 * Get the dimension of the canvas
	 *
	 * @return Dimension of the canvas
	 */
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
		this.javafxCanvas.getGraphicsContext2D().clearRect(0, 0, this.javafxCanvas.getWidth(),
			this.javafxCanvas.getHeight());
	}

	/**
	 * Reset the canvas
	 */
	public void reset() {
		clear();
		this.dim = null;
		this.path = null;
		setSafePath(false);
		this.objects.clear();
		removeGhostObject();
	}

	/**
	 * Render the canvas
	 */
	public void render() {
		for (CanvasObject object : objects) {
			object.render();
		}
		if (ghostObject != null) {
			ghostObject.render();
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
	 * Set the ghost object of the canvas
	 *
	 * @param ghostObject the new ghost object
	 */
	public void setGhostObject(CanvasObject ghostObject) {
		this.ghostObject = ghostObject;
	}

	/**
	 * Delete the ghost object of the canvas
	 */
	public void removeGhostObject() {
		ghostObject = null;
	}

	/**
	 * Renvoi la liste des objets du Canvas
	 */
	public List<CanvasObject> getCanvas() {
		return this.objects;
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
	 * Get if a new project is being opened
	 *
	 * @return boolean that answer previous statement
	 */
	public boolean getOpen() {
		return this.open;
	}

	/**
	 * Set if a new project is being opened
	 *
	 * @param open boolean that answer previous statement
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Get if a new project is being opened
	 *
	 * @return boolean that answer previous statement
	 */
	public boolean getNw() {
		return this.nw;
	}

	/**
	 * Set if a new project is being opened
	 *
	 * @param nw boolean that answer previous statement
	 */
	public void setNw(boolean nw) {
		this.nw = nw;
	}

	/**
	 * Get the stage of the window opened
	 *
	 * @return stage of the window opened
	 */
	public Stage getCanvastage() {
		return this.canvastage;
	}

	/**
	 * Set the new stage of the window opened
	 *
	 * @param stage stage of the window opened
	 */
	public void setCanvastage(Stage stage) {
		this.canvastage = stage;
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

	/**
	 * Return the clipboard of the canvas
	 */
	public List<CanvasObject> getClipboard() {
		return clipboard;
	}

	/**
	 * Set the clipboard of the canvas
	 */
	public void setClipboard(List<CanvasObject> cb) {
		clipboard = cb;

	}

	/**
	 * Return the name of the project
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the project
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getteur pour la couleur séléctionnée
	 *
	 * @return une couleur qui a les mêmes attributs que la couleur sélectionnée
	 */
	public CanvasColor getCopyColorSelect() {
		return new CanvasColor(this.colorSelect.getRed(), this.colorSelect.getGreen(), this.colorSelect.getBlue(), this.colorSelect.getAlpha());
	}

	/**
	 * getter of selectedFontSize
	 *
	 * @return current selected font size
	 */
	public int getSelectedFontSize() {
		return selectedFontSize;
	}

	/**
	 * setter for selectedFontSize
	 *
	 * @param selectedFontSize new selectedFontSize
	 */
	public void setSelectedFontSize(int selectedFontSize) {
		this.selectedFontSize = selectedFontSize;
	}

	/**
	 * getter of selectedFontType
	 *
	 * @return current selected font type
	 */
	public String getSelectedFontType() {
		return selectedFontType;
	}

	/**
	 * setter for selectedFontType
	 *
	 * @param selectedFontType new selectedFontType
	 */
	public void setSelectedFontType(String selectedFontType) {
		this.selectedFontType = selectedFontType;
	}

}
