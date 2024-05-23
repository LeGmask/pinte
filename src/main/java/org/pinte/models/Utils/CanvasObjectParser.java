package org.pinte.models.Utils;

import javafx.geometry.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CanvasObjectParser class used to parse SVG strings
 */
public class CanvasObjectParser {

	/**
	 * Matches a regex to a string and returns the first match
	 *
	 * @param pattern   the regex pattern to match
	 * @param svgString the string to match
	 * @return the first match of the regex
	 */
	private static String parsePattern(Pattern pattern, String svgString) {
		Matcher m = pattern.matcher(svgString);

		if (!m.find()) {
			return "";
		}
		String res = m.group();

		return res;
	}

	/**
	 * Removes the keyword from the matched text and returns the data
	 *
	 * @param matchedText the matched text
	 * @param keyword     the keyword to remove
	 * @return the data without the keyword
	 */
	private static String fixMatchedText(String matchedText, String keyword) {
		matchedText = matchedText.strip();
		matchedText = matchedText.replace(keyword, "");
		matchedText = matchedText.replace("=", "");
		matchedText = matchedText.replace("\"", "");
		return matchedText;
	}

	/**
	 * Finds a keyword="value" pattern and returns the value for the specified
	 * keyword
	 *
	 * @param keyword   the keyword to search for
	 * @param svgString the string to search in
	 * @return the value for the specified keyword, "" if not found
	 */
	public static String parseKeyword(String keyword, String svgString) {
		Pattern p = Pattern.compile(" " + keyword + "=\"\\S*\""); // parses strings of the form : pattern="value"
		String matchedText = parsePattern(p, svgString);
		return fixMatchedText(matchedText, keyword);
	}

	/**
	 * Parses a string of the form : points="x1,y1 x2,y2 ..." and returns an array
	 * of Point2D objects
	 *
	 * @param svgString the string to parse
	 * @return an array of Point2D objects
	 * @throws IllegalArgumentException if the string is not in the correct format
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
