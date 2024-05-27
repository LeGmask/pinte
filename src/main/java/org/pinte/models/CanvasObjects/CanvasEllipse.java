package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.pinte.models.Utils.CanvasObjectParser;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Ellipse canvas object
 */
public class CanvasEllipse extends CanvasObject {
	Point2D center;
	double rx, ry;

	/**
	 * Constructor for an ellipse object on the canvas.
	 *
	 * @param center      center of the ellipse
	 * @param rx          radius in x direction
	 * @param ry          radius in y direction
	 * @param fillColor   fill color of the ellipse
	 * @param strokeColor stroke color of the ellipse
	 */
	public CanvasEllipse(Point2D center, double rx, double ry, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.center = center;
		this.rx = rx;
		this.ry = ry;
	}

	/**
	 * Constructor for a circle object on the canvas.
	 *
	 * @param center      center of the circle
	 * @param r           radius of the circle
	 * @param fillColor   fill color of the circle
	 * @param strokeColor stroke color of the circle
	 */
	public CanvasEllipse(Point2D center, double r, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.center = center;
		this.rx = r;
		this.ry = r;
	}

	/**
	 * Constructor for an ellipse object on the canvas. Creates an ellipse from two
	 * points on the canvas.
	 *
	 * @param a           first point
	 * @param b           second point
	 * @param fillColor   fill color of the ellipse
	 * @param strokeColor stroke color of the ellipse
	 */
	public CanvasEllipse(Point2D a, Point2D b, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.center = a.midpoint(b);
		this.rx = Math.abs(a.getX() - this.center.getX());
		this.ry = Math.abs(a.getY() - this.center.getY());
	}

	/**
	 * Creates an ellipse object from an SVG string.
	 *
	 * @param args SVG string
	 * @return CanvasEllipse object
	 */
	public static CanvasEllipse createFromSVG(String args) {
		double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", args));
		double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", args));

		double rx = Double.parseDouble(CanvasObjectParser.parseKeyword("rx", args));
		double ry = Double.parseDouble(CanvasObjectParser.parseKeyword("ry", args));

		CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args),
			CanvasObjectParser.parseKeyword("fill-opacity", args));
		CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args),
			CanvasObjectParser.parseKeyword("stroke-opacity", args));
		return new CanvasEllipse(new Point2D(cx, cy), rx, ry, fillColor, strokeColor);
	}

	/**
	 * Change the center of the ellipse
	 *
	 * @param center new center
	 */
	public void setCenter(Point2D center) {
		this.center = center;
	}

	/**
	 * Renders the ellipse on the canvas
	 */
	public void render() {
		this.setUpDrawingParameters();
		this.gc.fillOval(center.getX() - rx, center.getY() - ry, 2 * rx, 2 * ry);
		this.gc.strokeOval(center.getX() - rx, center.getY() - ry, 2 * rx, 2 * ry);
	}

	/**
	 * Exports the ellipse to SVG format
	 *
	 * @return the ellipse in SVG format
	 */
	public String toSVG() {
		Dictionary<String, String> d = new Hashtable<>();
		d.put("cx", Double.toString(this.center.getX()));
		d.put("cy", Double.toString(this.center.getY()));
		d.put("rx", Double.toString(this.rx));
		d.put("ry", Double.toString(this.ry));
		d.put("fill", this.fillColor.asHex());
		d.put("fill-opacity", this.fillColor.opacityString());
		d.put("stroke", this.strokeColor.asHex());
		d.put("stroke-opacity", this.strokeColor.opacityString());
		return toSVG("ellipse", d);
	}

	public Point2D getGravityCenter() {
		return center;
	}

	public CanvasObject duplicate(Point2D offset) {
		return new CanvasEllipse(this.center.add(offset),
			rx, ry, fillColor, strokeColor);
	}

	public void translate(Point2D p) {
		this.center = this.center.add(p);
	}

	@Override
	public boolean contains(double x, double y) {
		double p = (Math.pow((x - center.getX()), 2) / Math.pow(rx, 2))
			+ (Math.pow((y - center.getY()), 2) / Math.pow(ry, 2));
		return p <= 1;
	}
}
