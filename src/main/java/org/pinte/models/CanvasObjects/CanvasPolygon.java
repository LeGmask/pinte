package org.pinte.models.CanvasObjects;

import org.pinte.models.Utils.CanvasObjectParser;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CanvasPolygon extends CanvasObject {

    private Point2D[] points;

    /**
     * Constructor for the CanvasPolygon object
     *
     * @param points      The points of the polygon
     * @param fillColor
     * @param strokeColor
     */
    public CanvasPolygon(Point2D[] points, CanvasColor fillColor, CanvasColor strokeColor) {
        super(fillColor, strokeColor);
        this.points = points;
    }

    public Shape render() {
        Polygon polygon = new Polygon();
        polygon.setFill(this.fillColor.toPaintColor());
        polygon.setStroke(this.strokeColor.toPaintColor());
        for (Point2D point : points) {
            polygon.getPoints().addAll(point.getX(), point.getY());
        }
        return polygon;
    }

    public String toSVG() {
        return """
                <polygon points="%s" fill="%s" stroke="%s"/>
                """.formatted(
                pointsToString(),
                this.fillColor.asHex(),
                this.strokeColor.asHex());
    }

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
