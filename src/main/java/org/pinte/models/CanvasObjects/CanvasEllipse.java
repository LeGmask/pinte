package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Ellipse canvas object
 *
 * @author Louis Thevenet
 */
public class CanvasEllipse extends CanvasObject {
    Point2D center;
    double rx, ry;

    /**
     * Constructor for a ellipse object on the canvas.
     *
     * @param center
     * @param rx          radius in x direction
     * @param ry          radius in y direction
     * @param fillColor
     * @param strokeColor
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
     * @param center
     * @param radius
     * @param fillColor
     * @param strokeColor
     */
    public CanvasEllipse(Point2D center, double r, CanvasColor fillColor, CanvasColor strokeColor) {
        super(fillColor, strokeColor);
        this.center = center;
        this.rx = r;
        this.ry = r;
    }

    /**
     * Constructor for a ellpise object on the canvas. Creates an ellipse from two
     * points on the canvas.
     *
     * @param center
     * @param a
     * @param b
     * @param fillColor
     * @param strokeColor
     */
    public CanvasEllipse(Point2D a, Point2D b, CanvasColor fillColor, CanvasColor strokeColor) {
        super(fillColor, strokeColor);
        this.center = a.midpoint(b);
        this.rx = Math.abs(a.getX() - this.center.getX());
        this.ry = Math.abs(a.getY() - this.center.getY());
    }

    public static CanvasEllipse createFromSVG(String args) {
        double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", args));
        double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", args));

        double rx = Double.parseDouble(CanvasObjectParser.parseKeyword("rx", args));
        double ry = Double.parseDouble(CanvasObjectParser.parseKeyword("ry", args));

        CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args));
        CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args));
        return new CanvasEllipse(new Point2D(cx, cy), rx, ry, fillColor, strokeColor);
    }

    public Shape render() {
        javafx.scene.shape.Ellipse c = new javafx.scene.shape.Ellipse(center.getX(), center.getY(), rx, ry);
        c.setFill(this.fillColor.toPaintColor());
        c.setStroke(this.strokeColor.toPaintColor());
        return c;
    }

    public String toSVG() {
        Dictionary<String, String> d = new Hashtable<>();
        d.put("cx", Double.toString(this.center.getX()));
        d.put("cy", Double.toString(this.center.getY()));
        d.put("rx", Double.toString(this.rx));
        d.put("ry", Double.toString(this.ry));
        d.put("fill", this.fillColor.asHex());
        d.put("stroke", this.strokeColor.asHex());
        return toSVG("ellipse", d);
    }
}
