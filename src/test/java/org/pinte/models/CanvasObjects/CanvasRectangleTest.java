package org.pinte.models.CanvasObjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;

/**
  * Classe de test de CanvasRectangle
  *
  * @author	Guillaume Sablayrolles
  */

public class CanvasRectangleTest {

  Point2D A, B, C, D, E, F, G;
  CanvasColor fillColor, strokeColor;
  String rectangle1ToSVG;

  @BeforeEach
  public void setUp() {

    // Initialize points
    this.A = new Point2D(0 ,0);
    this.B = new Point2D(5, 0);
    this.C = new Point2D(5, -3);
    this.D = new Point2D(0, -3);
    this.E = new Point2D(10.2, 3.4);
    this.F = new Point2D(0, 3.4);
    this.G = new Point2D(10.2, 0);

    //Initialize Colors
    this.fillColor = new CanvasColor(0, 255, 0);
    this.strokeColor = new CanvasColor(0, 0, 255);

    //Initialize results
    this.rectangle1ToSVG = """
      <rect fill="%s" height="%f" stroke="%s" width="%f" x="%f" y="%f"/>
      """.formatted(
      "#00FF00",
      3.000000,
      "#0000FF",
      5.000000,
      0.000000,
      0.000000);
  }

  //CanvasRectangle(a, b, c, d, fillColor, strokeColor)
  @Test
  public void testNormalUseCanvasRectangle() {
    CanvasRectangle rectangle1 = new CanvasRectangle(A, B, C, D , fillColor, strokeColor);
    Assertions.assertEquals(rectangle1ToSVG, rectangle1.toSVG());
  }

}
