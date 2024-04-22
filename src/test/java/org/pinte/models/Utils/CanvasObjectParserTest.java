package org.pinte.models.Utils;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;

/**
 * Classe de test de CanvasObjectParser
 *
 * @author Louis Thevenet
 */

public class CanvasObjectParserTest {
    @Test
    public void testParsePoints() {

        String svgString = "<polygon points=\"10.0,10.0 50.0,30.0 80.0,60.0\" stroke=\"#FF0000\" fill=\"#0000FF\"/>";
        var points = Arrays.asList(CanvasObjectParser.parsePoints(svgString));
        Assertions.assertTrue(points.contains(new Point2D(10, 10)));
        Assertions.assertTrue(points.contains(new Point2D(50, 30)));
        Assertions.assertTrue(points.contains(new Point2D(80, 60)));
    }

    @Test
    void testParseKeyword() {
        String svgString = "<ellipse cx=\"150.0\" ry=\"50.0\" stroke=\"#FF00FF\" fill=\"#FF0000\" rx=\"70.0\" cy=\"125.0\"/>";
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("cx", svgString), "150.0");
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("cy", svgString), "125.0");
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("rx", svgString), "70.0");
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("ry", svgString), "50.0");
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("fill", svgString), "#FF0000");
        Assertions.assertEquals(CanvasObjectParser.parseKeyword("stroke", svgString), "#FF00FF");
    }

}