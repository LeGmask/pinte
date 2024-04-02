package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;

public class CanvasVector {
    private Point2D a, b;

    /**
     * Creates a new vector from two points
     *
     * @param a the starting point of the vector
     * @param b the ending point of the vector
     */
    public CanvasVector(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Gets the starting point of the vector
     *
     * @return the starting point of the vector
     */
    public Point2D getA() {
        return a;
    }

    /**
     * Gets the ending point of the vector
     *
     * @return the ending point of the vector
     */
    public Point2D getB() {
        return b;
    }

    /**
     * Sets the starting point of the vector
     *
     * @param a the starting point of the vector
     */
    public void setA(Point2D a) {
        this.a = a;
    }

    /**
     * Sets the ending point of the vector
     *
     * @param b the ending point of the vector
     */
    public void setB(Point2D b) {
        this.b = b;
    }

    /**
     * Gets the length of the vector
     *
     * @return the length of the vector
     */
    public double getLength() {
        return a.distance(b);
    }

    public String toString() {
        return "Vector: (" + a.toString() + ", " + b.toString() + ")";
    }
}
