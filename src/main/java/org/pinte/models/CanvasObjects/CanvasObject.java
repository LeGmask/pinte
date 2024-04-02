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

}