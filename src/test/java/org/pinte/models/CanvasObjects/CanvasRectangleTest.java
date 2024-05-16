package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test de CanvasRectangle
 */
public class CanvasRectangleTest {

    Point2D A, B, C, D, E, F, G;
    CanvasColor fillColor, strokeColor;
    int width, length;

    void testEqual(CanvasRectangle rectangle, Point2D a, Point2D b, Point2D c, Point2D d) {
        Assertions.assertEquals(rectangle.a, a);
        Assertions.assertEquals(rectangle.b, b);
        Assertions.assertEquals(rectangle.c, c);
        Assertions.assertEquals(rectangle.d, d);
        Assertions.assertTrue(rectangle.fillColor.equals(fillColor));
        Assertions.assertTrue(rectangle.strokeColor.equals(strokeColor));
    }

    @BeforeEach
    public void setUp() {

        // Initialize points
        this.A = new Point2D(0, 0);
        this.B = new Point2D(5, 0);
        this.C = new Point2D(5, 3);
        this.D = new Point2D(0, 3);
        this.E = new Point2D(10.2, 3.4);
        this.F = new Point2D(0, 3.4);
        this.G = new Point2D(10.2, 0);

        // Initialize Colors
        this.fillColor = new CanvasColor(0, 255, 0);
        this.strokeColor = new CanvasColor(0, 0, 255);

        // Initialize lengths
        this.width = 5;
        this.length = 3;

    }

    // CanvasRectangle(a, b, c, d, fillColor, strokeColor)
    @Test
    public void testConstructorPoints() {
        CanvasRectangle rectangle = new CanvasRectangle(A, B, C, D, fillColor, strokeColor);
        testEqual(rectangle, A, B, C, D);
    }

    @Test
    public void testConstructorLengths() {
        CanvasRectangle rectangle = new CanvasRectangle(A, width, length, fillColor, strokeColor);
        testEqual(rectangle, A, B, C, D);
    }

    @Test
    public void testSVGString() {
        CanvasRectangle rectangle1 = new CanvasRectangle(A, B, C, D, fillColor, strokeColor);
        String str = rectangle1.toSVG();
        CanvasRectangle rectangle2 = (CanvasRectangle) CanvasObject.parseFromSVG(str);

        Assertions.assertEquals(rectangle1.toSVG(), rectangle2.toSVG());
        testEqual(rectangle2, A, B, C, D);
    }
}
