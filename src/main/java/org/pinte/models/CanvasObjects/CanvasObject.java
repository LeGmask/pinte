package org.pinte.models.CanvasObjects;

import javafx.scene.shape.Shape;

public abstract class CanvasObject {

    CanvasColor fillColor;
    CanvasColor strokeColor;

    /**
     * Constructor for the canvas object
     *
     * @param color
     */
    public CanvasObject(CanvasColor fillColor, CanvasColor strokeColor) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    /**
     * Returns a shape that can be rendered on the canvas
     */
    public abstract Shape render();

    /**
     * Returns the object as an SVG string
     */
    public abstract String toSVG();

    public static CanvasObject parseFromSVG(String svgString) throws IllegalArgumentException {
        var split = svgString.replace("<", "").split(" ");
        var type = split[0];

        switch (type) {
            case "rect":
                return CanvasRectangle.createFromSVG(svgString);

            case "ellipse":
                return CanvasEllipse.createFromSVG(svgString);

            case "polygon":
                return CanvasPolygon.createFromSVG(svgString);

            default:
                throw new IllegalArgumentException("Unknown object type '" + type + "' in SVG string.");
        }

    }

}