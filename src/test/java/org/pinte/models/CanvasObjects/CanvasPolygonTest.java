package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pinte.models.Utils.CanvasObjectParser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de test de CanvasPolygon
 */
public class CanvasPolygonTest {

	CanvasColor fillColor, strokeColor;
	String SVGString;
	private Point2D[] points;

	@BeforeEach
	public void setUp() {
		points = new Point2D[3];
		points[0] = new Point2D(10, 10);
		points[1] = new Point2D(50, 50);
		points[2] = new Point2D(90, 10);

		fillColor = new CanvasColor(0, 255, 0);
		strokeColor = new CanvasColor(0, 0, 255);
	}

	@Test
	public void testSVGString() {
		CanvasPolygon polygon = new CanvasPolygon(points, fillColor, strokeColor);
		String svgString = polygon.toSVG();
		Point2D[] parsedPoints = CanvasObjectParser.parsePoints(svgString);
		CanvasColor fill = new CanvasColor(CanvasObjectParser.parseKeyword("fill", svgString),
			CanvasObjectParser.parseKeyword("fill-opacity", svgString));
		CanvasColor stroke = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", svgString),
			CanvasObjectParser.parseKeyword("stroke-opacity", svgString));

		assertEquals(parsedPoints.length, points.length);

		for (int i = 0; i < points.length; ++i) {
			assertTrue(Arrays.asList(points).contains(parsedPoints[i]));
		}

		Assertions.assertEquals(fill.asHex(), fillColor.asHex());
		Assertions.assertEquals(stroke.asHex(), strokeColor.asHex());
	}
}
