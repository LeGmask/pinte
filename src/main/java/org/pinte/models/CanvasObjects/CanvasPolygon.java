package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Polygon canvas object
 */
public class CanvasPolygon extends CanvasObject {

	/**
	 * The points of the polygon
	 */
	private Point2D[] points;

	/**
	 * Constructor for the CanvasPolygon object
	 *
	 * @param points      The points of the polygon
	 * @param fillColor   The fill color of the polygon
	 * @param strokeColor The stroke color of the polygon
	 */
	public CanvasPolygon(Point2D[] points, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.points = points;
	}

	/**
	 * Renders the polygon as a JavaFX Polygon object
	 */
	public void render() {
		gc.setFill(this.fillColor.toPaintColor());
		gc.setStroke(this.strokeColor.toPaintColor());

		double[] points_x = new double[points.length];
		double[] points_y = new double[points.length];

		for (int i = 0; i < points.length; i++) {
			points_x[i] = points[i].getX();
			points_y[i] = points[i].getY();
		}

		gc.fillPolygon(
			points_x,
			points_y,
			points.length
		);
	}

	/**
	 * Converts the polygon to an SVG string
	 *
	 * @return the SVG representation of the polygon
	 */
	public String toSVG() {
		Dictionary<String, String> d = new Hashtable<>();
		d.put("points", pointsToString());
		d.put("fill", this.fillColor.asHex());
		d.put("stroke", this.strokeColor.asHex());
		return toSVG("polygon", d);
	}

	/**
	 * Creates a CanvasPolygon object from an SVG string
	 *
	 * @param args the SVG string to parse
	 * @return a CanvasPolygon object parsed from the SVG string
	 */
	public static CanvasPolygon createFromSVG(String args) {
		Point2D[] points = CanvasObjectParser.parsePoints(args);

		CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args));
		CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args));
		return new CanvasPolygon(points, fillColor, strokeColor);
	}

	/**
	 * Converts the array of points to a string in SVG points format
	 *
	 * @return the string representation of the points "x1,y1 x2,y2 ..."
	 */
	private String pointsToString() {
		String result = "";
		for (Point2D point : points) {
			result += "%s,%s ".formatted(
				point.getX(),
				point.getY());
		}
		return result.trim(); // remove trailing space
	}
}
