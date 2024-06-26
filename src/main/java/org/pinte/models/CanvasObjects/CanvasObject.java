package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.pinte.models.Canvas;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * CanvasObject abstract class to build canvas objects
 */
public abstract class CanvasObject {
	/**
	 * Is the object selected
	 */
	public boolean isSelected = false;
	/**
	 * Canvas instance
	 */
	GraphicsContext gc = Canvas.getInstance().getGraphicsContext2D();
	/**
	 * Fill color of the object
	 */
	CanvasColor fillColor;
	/**
	 * Stroke color of the object
	 */
	CanvasColor strokeColor;

	/**
	 * Constructor for a canvas object
	 *
	 * @param fillColor   color to fill the object
	 * @param strokeColor color for the stroke of the object
	 */
	public CanvasObject(CanvasColor fillColor, CanvasColor strokeColor) {
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
	}

	/**
	 * Creates an SVG string from a shape name and its attributes
	 *
	 * @param shape      the shape name
	 * @param attributes [key,value]
	 * @return the SVG string
	 */
	public static String toSVG(String shape, Dictionary<String, String> attributes) {
		String svgString = "<" + shape;
		Enumeration<String> e = attributes.keys();
		while (e.hasMoreElements()) {

			String k = e.nextElement();
			svgString += " " + k + "=\"" + attributes.get(k) + "\"";
		}
		if (shape.compareTo("text") == 0) {
			return svgString + ">";
		} else {
			return svgString + "/>";
		}
	}

	/**
	 * Returns true if the object contains the given coordinates, false otherwise
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract boolean contains(double x, double y);

	/**
	 * Duplicate the object, offsetted from the given coordinates.
	 *
	 * @param old_center
	 * @param new_center
	 * @return
	 */
	public abstract CanvasObject duplicate(Point2D offset);

	/**
	 * Returns the gravity center of the shape
	 *
	 * @return the gravity center of the shape
	 */
	public abstract Point2D getGravityCenter();

	/**
	 * Returns a shape that can be rendered on the canvas
	 */
	public abstract void render();

	/**
	 * Returns the object as an SVG string
	 */
	public abstract String toSVG();

	/**
	 * Translates the shape by dx and dy
	 *
	 * @param p
	 */
	public abstract void translate(Point2D p);

	/**
	 * Change la couleur de remplissage d'un objet.
	 *
	 * @param couleur la nouvelle couleur de remplissage
	 */

	public void setFillColor(CanvasColor couleur) {

		this.fillColor = couleur;

	}

	/**
	 * Change la couleur de bordure d'un objet.
	 *
	 * @param couleur la nouvelle couleur de bordure
	 */

	public void setStrokeColor(CanvasColor couleur) {

		this.strokeColor = couleur;

	}

	protected void setUpDrawingParameters() {

		gc.setFill(this.fillColor.toPaintColor());
		gc.setStroke(this.strokeColor.toPaintColor());

		gc.setLineWidth(1.0);
		gc.setLineDashes(null);

		if (this.isSelected) {
			gc.setStroke(Color.YELLOW);
			gc.setLineWidth(2);
			gc.setLineDashes(new double[]{5});
		}
	}

	protected void setUpWritingParameters(String fontFamily, int fontSize) {

		gc.setFont(Font.font(fontFamily, fontSize));
	}
}
