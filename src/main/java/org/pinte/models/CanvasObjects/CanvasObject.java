package org.pinte.models.CanvasObjects;

import javafx.scene.shape.Shape;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * CanvasObject abstract class to build canvas objects
 */
public abstract class CanvasObject {

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
	 * @param fillColor color to fill the object
	 * @param strokeColor color for the stroke of the object
	 */
	public CanvasObject(CanvasColor fillColor, CanvasColor strokeColor) {
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
	}

	/**
	 * Returns a shape that can be rendered on the canvas
	 */
	public abstract Shape render();

	/**
	 * Returns the object as an SVG string
	 */
	public abstract String toSVG();

	/**
	 * Creates a CanvasObject from an SVG string
	 *
	 * @param svgString the SVG string to parse
	 * @return a CanvasObject parsed from the SVG string
	 * @throws IllegalArgumentException if the SVG string is invalid
	 */
	public static CanvasObject parseFromSVG(String svgString) throws IllegalArgumentException {
		var split = svgString.replace("<", "").split(" ");
		var type = split[0];

		return switch (type) {
			case "rect" -> CanvasRectangle.createFromSVG(svgString);
			case "ellipse" -> CanvasEllipse.createFromSVG(svgString);
			case "polygon" -> CanvasPolygon.createFromSVG(svgString);
			default -> throw new IllegalArgumentException("Unknown object type '" + type + "' in SVG string.");
		};
	}

	/**
	 * Creates an SVG string from a shape name and its attributes
	 *
	 * @param shape the shape name
	 * @param attributes <key,value>
	 * @return the SVG string
	 */
	public static String toSVG(String shape, Dictionary<String, String> attributes) {
		String svgString = "<" + shape;
		Enumeration<String> e = attributes.keys();
		while (e.hasMoreElements()) {

			String k = e.nextElement();
			svgString += " " + k + "=\"" + attributes.get(k) + "\"";
		}
		return svgString + "/>";
	}

}
