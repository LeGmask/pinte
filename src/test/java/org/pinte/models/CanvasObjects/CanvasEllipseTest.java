package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pinte.models.Utils.CanvasObjectParser;

/**
 * Classe de test de CanvasEllipse
 */
public class CanvasEllipseTest {

	Point2D center;
	double radiusX, radiusY;
	CanvasColor fillColor, strokeColor;
	String SVGString;

	void testEqual(CanvasEllipse ellipse) {
		Assertions.assertEquals(ellipse.center, center);
		Assertions.assertEquals(ellipse.rx, radiusX);
		Assertions.assertEquals(ellipse.ry, radiusY);
		Assertions.assertTrue(ellipse.fillColor.equals(fillColor));
		Assertions.assertTrue(ellipse.strokeColor.equals(strokeColor));
	}

	@BeforeEach
	public void setUp() {
		center = new Point2D(0, 0);
		radiusX = 3;
		radiusY = 9;
		fillColor = new CanvasColor(0, 255, 0);
		strokeColor = new CanvasColor(0, 0, 255);
	}

	@Test
	public void testConstructorRadiuses() {
		CanvasEllipse ellipse = new CanvasEllipse(center, radiusX, radiusY, fillColor, strokeColor);
		testEqual(ellipse);
	}

	@Test
	public void testConstructorPoints() {
		Point2D topLeft = new Point2D(-3, -9);
		Point2D bottomRight = new Point2D(3, 9);
		CanvasEllipse ellipse = new CanvasEllipse(topLeft, bottomRight, fillColor, strokeColor);
		testEqual(ellipse);
	}

	@Test
	public void testSVGString() {
		CanvasEllipse ellipse = new CanvasEllipse(center, radiusX, radiusY, fillColor, strokeColor);
		String svgString = ellipse.toSVG();
		Double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", svgString));
		Double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", svgString));
		Double rx = Double.parseDouble(CanvasObjectParser.parseKeyword("rx", svgString));
		Double ry = Double.parseDouble(CanvasObjectParser.parseKeyword("ry", svgString));
		CanvasColor fill = new CanvasColor(CanvasObjectParser.parseKeyword("fill", svgString));
		CanvasColor stroke = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", svgString));

		Assertions.assertEquals(cx, center.getX());
		Assertions.assertEquals(cy, center.getY());
		Assertions.assertEquals(rx, radiusX);
		Assertions.assertEquals(ry, radiusY);
		Assertions.assertEquals(fill.asHex(), fillColor.asHex());
		Assertions.assertEquals(stroke.asHex(), strokeColor.asHex());
	}
}
