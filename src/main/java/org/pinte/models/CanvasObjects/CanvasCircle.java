package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class CanvasCircle extends CanvasObject {
    Point2D center;
    double radius;

    /**
     * Constructor for a circle object on the canvas.
     *
     * @param center
     * @param radius
     * @param fillColor
     * @param strokeColor
     */
    public CanvasCircle(Point2D center, double radius, CanvasColor fillColor, CanvasColor strokeColor) {
        super(fillColor, strokeColor);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Constructor for a circle object on the canvas.
     *
     * @param center
     * @param a
     * @param fillColor
     * @param strokeColor
     */
    public CanvasCircle(Point2D center, Point2D a, CanvasColor fillColor, CanvasColor strokeColor) {
        super(fillColor, strokeColor);
        this.center = center;
        this.radius = center.distance(a);
    }

    public Shape render() {
        javafx.scene.shape.Circle c = new javafx.scene.shape.Circle(center.getX(), center.getY(), radius);
        c.setFill(this.fillColor.toPaintColor());
        c.setStroke(this.strokeColor.toPaintColor());
        return c;
    }

    public String toSVG() {
        return """
                <ellipse cx="%f" cy="%f" fill="%s" id="svg_1" rx="%f" ry="%f" stroke="%s"/>
                    """.formatted(center.getX(), center.getY(), fillColor.asHex(), radius, radius, strokeColor.asHex());
    }

}
