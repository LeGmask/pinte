package org.pinte.models.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Point2D;

public class CanvasObjectParser {

    /**
     * Matches a regex to a string and returns the first match
     *
     * @param pattern
     * @param svgString
     * @return the first match of the regex
     */
    private static String parsePattern(Pattern pattern, String svgString) {
        Matcher m = pattern.matcher(svgString);

        if (!m.find()) {
            throw new IllegalArgumentException("Pattern " + pattern + " not found in SVG String : " + svgString);
        }

        String matchedText = m.group();
        return matchedText;
    }

    /**
     * Removes the keyword from the matched text and returns the data
     *
     * @param matchedText
     * @param keyword
     * @return
     */
    private static String fixMatchedText(String matchedText, String keyword) {
        matchedText = matchedText.replace(keyword, "");
        matchedText = matchedText.replace("=", "");
        matchedText = matchedText.replace("\"", "");
        return matchedText;
    }

    /**
     * Finds a keyword="value" pattern and returns the value for the specified
     * keyword
     *
     * @param keyword
     * @param svgString
     * @return the value for the specified keyword
     * @throws IllegalArgumentException
     */
    public static String parseKeyword(String keyword, String svgString) throws IllegalArgumentException {
        Pattern p = Pattern.compile(keyword + "=\"\\S*\""); // parses strings of the form : pattern="value"
        String matchedText = parsePattern(p, svgString);
        matchedText = fixMatchedText(matchedText, keyword);
        return matchedText;
    }

    /**
     * Parses a string of the form : points="x1,y1 x2,y2 ..." and returns an array
     * of Point2D objects
     *
     * @param svgString
     * @return an array of Point2D objects
     * @throws IllegalArgumentException
     */
    public static Point2D[] parsePoints(String svgString) throws IllegalArgumentException {
        Pattern p = Pattern.compile("points=\"((\\d*(\\.\\d*)?,\\d*(\\.\\d*)?)| )*\"");
        String matchedText = parsePattern(p, svgString);
        matchedText = fixMatchedText(matchedText, "points"); // "x1,y1 x2,y2 ..."

        String[] points = matchedText.split(" "); // ["x1,y1", "x2,y2", ...]
        Point2D[] pointArray = new Point2D[points.length];
        for (int i = 0; i < points.length; i++) {
            String[] xy = points[i].split(",");
            if (xy.length != 2) {
                throw new IllegalArgumentException("Invalid points string : " + points[i]);
            }

            pointArray[i] = new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
        }
        return pointArray;
    }
}
