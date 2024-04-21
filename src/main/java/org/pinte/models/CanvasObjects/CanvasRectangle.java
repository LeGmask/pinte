package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Rectangle canvas object
 *
 * @author Louis Thevenet
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
     * @param a
     * @param b
     * @param c
     * @param d
     * @param color
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

    public Shape render() {
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(this.a.getX(), this.a.getY(),
                this.a.distance(b), this.a.distance(d));
        r.setFill(this.fillColor.toPaintColor());
        r.setStroke(this.strokeColor.toPaintColor());
        return r;
    }

    public String toSVG() {
        return "<rect fill=\"%s\" height=\"%f\" stroke=\"%s\" width=\"%f\" x=\"%f\" y=\"%f\"/>".formatted(
                this.fillColor.asHex(),
                this.a.distance(this.d),
                this.strokeColor.asHex(),
                this.a.distance(this.b),
                this.a.getX(),
                this.a.getY());
    }

    /**
     * Checks if points A and C are opposite corners
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return true if points A and C are opposite corners, false otherwise
     */
    private boolean isOppositeCorners(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
        double sideAB = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        double sideCD = Math.sqrt(Math.pow(p4.getX() - p3.getX(), 2) + Math.pow(p4.getY() - p3.getY(), 2));
        return sideAB == sideCD;
    }

}
