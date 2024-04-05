package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

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
        double cx = Double.parseDouble(CanvasObjectParser.parse("cx", args));
        double cy = Double.parseDouble(CanvasObjectParser.parse("cy", args));

        double rx = Double.parseDouble(CanvasObjectParser.parse("rx", args));
        double ry = Double.parseDouble(CanvasObjectParser.parse("ry", args));

        CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parse("fill", args));
        CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parse("stroke", args));
        return new CanvasEllipse(new Point2D(cx, cy), rx, ry, fillColor, strokeColor);
    }

    public Shape render() {
        javafx.scene.shape.Ellipse c = new javafx.scene.shape.Ellipse(center.getX(), center.getY(), rx, ry);
        c.setFill(this.fillColor.toPaintColor());
        c.setStroke(this.strokeColor.toPaintColor());
        return c;
    }

    public String toSVG() {
        return """
                <ellipse cx="%f" cy="%f" fill="%s" id="svg_1" rx="%f" ry="%f" stroke="%s"/>
                    """.formatted(center.getX(), center.getY(), fillColor.asHex(), rx, ry, strokeColor.asHex());
    }

}
