package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.pinte.models.Utils.CanvasObjectParser;

import java.util.Dictionary;
import java.util.Hashtable;

public class CanvasLine extends CanvasObject {
	private Point2D a, b;

	/**
	 * Creates the segment [A,B] with the given points and color.
	 *
	 * @param a           The top left corner of the rectangle
	 * @param b           The top right corner of the rectangle
	 * @param fillColor   The color to fill the rectangle with
	 * @param strokeColor The color of the outline*
	 */
	public CanvasLine(Point2D a, Point2D b, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.a = a;
		this.b = b;
	}

	/**
	 * Creates a line from an SVG string
	 *
	 * @param args the SVG string to parse
	 * @return a CanvasLine parsed from the SVG string
	 */
	public static CanvasLine createFromSVG(String args) {
		double x1 = Double.parseDouble(CanvasObjectParser.parseKeyword("x1", args));
		double y1 = Double.parseDouble(CanvasObjectParser.parseKeyword("y1", args));
		double x2 = Double.parseDouble(CanvasObjectParser.parseKeyword("x2", args));
		double y2 = Double.parseDouble(CanvasObjectParser.parseKeyword("y2", args));
		CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args),
			CanvasObjectParser.parseKeyword("fill-opacity", args));
		CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args),
			CanvasObjectParser.parseKeyword("stroke-opacity", args));

		return new CanvasLine(new Point2D(x1, y1), new Point2D(x2, y2), fillColor, strokeColor);
	}

	private double dotProduct(Point2D a, Point2D b) {
		return a.getX() * b.getX() + a.getY() * b.getY();
	}

	private double length(Point2D p) {
		return Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
	}

	@Override
	public boolean contains(double x, double y) {
		double delta = 8;

		Point2D p = new Point2D(x, y);

		Point2D ab = b.subtract(a);
		Point2D ap = p.subtract(a);
		Point2D bp = p.subtract(b);

		double abLength = length(ab);
		double dot_ab_ap = ab.dotProduct(ap);
		double dot_ab_ab = ab.dotProduct(ab);

		double distance;

		// Projection of p onto the line defined by a and b
		double projection = dot_ab_ap / dot_ab_ab;

		if (projection < 0) {
			// Closest to point A
			distance = length(ap);
		} else if (projection > 1) {
			// Closest to point B
			distance = length(ab);
		} else {
			// Projection is on the segment
			Point2D projectionPoint = new Point2D(
				a.getX() + projection * ab.getX(),
				a.getY() + projection * ab.getY());
			distance = length(p.subtract(projectionPoint));
		}

		return distance <= delta;
	}

	@Override
	public void render() {
		this.setUpDrawingParameters();
		gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
	}

	@Override
	public String toSVG() {
		Dictionary<String, String> attributes = new Hashtable<>();
		attributes.put("x1", Double.toString(this.a.getX()));
		attributes.put("y1", Double.toString(this.a.getY()));
		attributes.put("x2", Double.toString(this.b.getX()));
		attributes.put("y2", Double.toString(this.b.getY()));
		attributes.put("stroke", this.strokeColor.asHex());
		attributes.put("stroke-opacity", this.strokeColor.opacityString());
		return toSVG("line", attributes);
	}

	@Override
	public void translate(Point2D p) {
		a = a.add(p);
		b = b.add(p);
	}
}
