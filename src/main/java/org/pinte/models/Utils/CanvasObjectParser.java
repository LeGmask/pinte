package org.pinte.models.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanvasObjectParser {
    /**
     * Parses a string to find a value for a given key
     *
     * @param pattern   - The key to search for (e.g. "fill")
     * @param svgString - The string to search in (e.g.
     *                  "<rect fill="#FF0000" />")
     *
     * @return The value for the given key (e.g. "#FF0000")
     */
    public static String parse(String pattern, String svgString) throws IllegalArgumentException {
        Pattern p = Pattern.compile(pattern + "=\"\\S*\""); // parses strings of the form : pattern="value"
        Matcher m = p.matcher(svgString);

        if (!m.find()) {
            throw new IllegalArgumentException("Pattern " + pattern + " not found in SVG String : " + svgString);
        }

        String matchedText = m.group();
        matchedText = matchedText.replace(pattern, "");
        matchedText = matchedText.replace("=", "");
        matchedText = matchedText.replace("\"", "");
        return matchedText;
    }
}
