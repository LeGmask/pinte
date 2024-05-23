package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.pinte.models.Utils.CanvasObjectParser;

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
     * Creates a CanvasPolygon object from an SVG string
     *
     * @param args the SVG string to parse
     * @return a CanvasPolygon object parsed from the SVG string
     */
    public static CanvasPolygon createFromSVG(String args) {
        Point2D[] points = CanvasObjectParser.parsePoints(args);

        CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args),
                CanvasObjectParser.parseKeyword("fill-opacity", args));
        CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args),
                CanvasObjectParser.parseKeyword("stroke-opacity", args));

        return new CanvasPolygon(points, fillColor, strokeColor);
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
        d.put("fill-opacity", this.fillColor.opacityString());
        d.put("stroke", this.strokeColor.asHex());
        d.put("stroke-opacity", this.strokeColor.opacityString());

        return toSVG("polygon", d);
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

    @Override
    public boolean contains(double x, double y) {
        int num_vertices = points.length;
        boolean inside = false;

        Point2D p1 = points[0], p2;
        for (int i = 1; i <= num_vertices; i++) {
            p2 = points[i % num_vertices];

            if (y > Math.min(p1.getY(), p2.getY())) {
                if (y <= Math.max(p1.getY(), p2.getY())) {
                    if (x <= Math.max(p1.getX(), p2.getX())) {
                        double x_intersection = (y - p1.getY()) * (p2.getX() - p1.getX())
                                / (p2.getY() - p1.getY())
                                + p1.getX();

                        if (p1.getX() == p2.getX()
                                || x <= x_intersection) {
                            inside = !inside;
                        }
                    }
                }
            }

            p1 = p2;
        }

        return inside;
    }

    public void translate(Point2D p) {
        for (int i = 0; i < points.length; i++) {
            points[i] = points[i].add(p);
        }
    }

    /**
     * Renders the polygon as a JavaFX Polygon object
     */
    public void render() {

        double[] points_x = new double[points.length];
        double[] points_y = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            points_x[i] = points[i].getX();
            points_y[i] = points[i].getY();
        }

        this.setUpDrawingParameters();

        gc.fillPolygon(
                points_x,
                points_y,
                points.length);
        gc.strokePolygon(points_x, points_y, points.length);
    }

}
