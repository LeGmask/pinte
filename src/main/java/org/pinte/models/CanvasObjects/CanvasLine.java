package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;

import java.util.Dictionary;
import java.util.Hashtable;

public class CanvasSegment extends CanvasObject {
	private Point2D a, b;

	/**
	 * Creates the segment [A,B] with the given points and color.
	 *
	 * @param a           The top left corner of the rectangle
	 * @param b           The top right corner of the rectangle
	 * @param fillColor   The color to fill the rectangle with
	 * @param strokeColor The color of the outline*
	 */
	public CanvasSegment(Point2D a, Point2D b, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean contains(double x, double y) {
		double delta = 0.5;
		if (a.getY() < b.getY()) {
			return new CanvasRectangle(this.a.add(new Point2D(-delta, -delta)),
				this.a.add(new Point2D(delta, -delta)),
				this.b.add(new Point2D(-delta, delta)),
				this.b.add(new Point2D(delta, delta)),
				this.fillColor,
				this.strokeColor).contains(x, y);
		}else {
			return new CanvasRectangle(this.b.add(new Point2D(-delta, -delta)),
			   this.b.add(new Point2D(delta, -delta)),
			   this.a.add(new Point2D(-delta, delta)),
			   this.a.add(new Point2D(delta, delta)),
			   this.fillColor,
			   this.strokeColor).contains(x, y);
		}
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
}
