package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

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

    public Shape render() {
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(this.a.getX(), this.a.getY(),
                this.a.distance(b), this.a.distance(d));
        r.setFill(this.fillColor.toPaintColor());
        r.setStroke(this.strokeColor.toPaintColor());
        return r;
    }

    public String toSVG() {
        return """
                <rect fill="#%s" height="%f" id="svg_1" stroke="#%s" width="%f" x="%f" y="%f"/>
                """.formatted("000000", this.a.distance(this.d), "FFFFFF", this.a.distance(this.b), this.a.getX(),
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
