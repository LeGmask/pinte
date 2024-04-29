package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Rectangle canvas object
 */
public class CanvasRectangle extends CanvasObject {
	Point2D a, b, c, d;

	/**
	 * Creates a rectangle with the given points and color. The points must be in
	 * the following order:
	 * <p>
	 * A B
	 * <p>
	 * D C
	 *
	 * @param a           The top left corner of the rectangle
	 * @param b           The top right corner of the rectangle
	 * @param c           The bottom right corner of the rectangle
	 * @param d           The bottom left corner of the rectangle
	 * @param fillColor   The color to fill the rectangle with
	 * @param strokeColor The color of the outline
	 * @throws IllegalArgumentException if point A and C are not opposite corners
	 */
	public CanvasRectangle(Point2D a, Point2D b, Point2D c, Point2D d, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);

		if (!isOppositeCorners(a, b, c, d)) {
			throw new IllegalArgumentException("Points passed do not form a valid rectangle.");
		}
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;

	}

	/**
	 * Creates a rectangle from an SVG string
	 *
	 * @param args the SVG string to parse
	 * @return a CanvasRectangle parsed from the SVG string
	 */
	public static CanvasRectangle createFromSVG(String args) {
		double x = Double.parseDouble(CanvasObjectParser.parseKeyword("x", args));
		double y = Double.parseDouble(CanvasObjectParser.parseKeyword("y", args));

		double w = Double.parseDouble(CanvasObjectParser.parseKeyword("width", args));
		double h = Double.parseDouble(CanvasObjectParser.parseKeyword("height", args));
		CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args));
		CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args));

		return new CanvasRectangle(new Point2D(x, y), w, h, fillColor, strokeColor);
	}

	/**
	 * Creates a rectangle with the given point, width, height and color.
	 *
	 * @param a           The top left corner of the rectangle
	 * @param width       The width of the rectangle
	 * @param height      The height of the rectanglet
	 * @param fillColor   The color to fill the rectangle with
	 * @param strokeColor The color of the outline
	 */
	public CanvasRectangle(Point2D a, double width, double height, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.a = a;
		this.b = new Point2D(a.getX() + width, a.getY());
		this.c = new Point2D(a.getX() + width, a.getY() - height);
		this.d = new Point2D(a.getX(), a.getY() - height);

	}

	/**
	 * Renders the rectangle as a JavaFX Rectangle object
	 */
	public void render() {
		gc.setFill(this.fillColor.toPaintColor());
		gc.setStroke(this.strokeColor.toPaintColor());
		gc.fillRect(
			this.a.getX(), this.a.getY(),
			this.a.distance(b), this.a.distance(d)
		);
	}

	/**
	 * Converts the rectangle to an SVG string
	 *
	 * @return the SVG representation of the rectangle
	 */
	public String toSVG() {
		Dictionary<String, String> attributes = new Hashtable<>();
		attributes.put("height", Double.toString(this.a.distance(d)));
		attributes.put("width", Double.toString(this.a.distance(b)));
		attributes.put("x", Double.toString(this.a.getX()));
		attributes.put("y", Double.toString(this.a.getY()));
		attributes.put("fill", this.fillColor.asHex());
		attributes.put("stroke", this.strokeColor.asHex());
		return toSVG("rect", attributes);
	}

	/**
	 * Checks if points A and C are opposite corners
	 *
	 * @param p1 The top left corner
	 * @param p2 The top right corner
	 * @param p3 The bottom right corner
	 * @param p4 The bottom left corner
	 * @return true if points A and C are opposite corners, false otherwise
	 */
	private boolean isOppositeCorners(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		double sideAB = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
		double sideCD = Math.sqrt(Math.pow(p4.getX() - p3.getX(), 2) + Math.pow(p4.getY() - p3.getY(), 2));
		return sideAB == sideCD;
	}

}
