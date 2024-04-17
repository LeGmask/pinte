package org.pinte.models.CanvasObjects;

import javafx.scene.paint.Color;

/**
 * CanvasColor class to use with Canvas Objects
 *
 * @author Louis Thevenet
 */
public class CanvasColor {
    private int r, g, b;

    private boolean isColorConform(int c) {
        return c >= 0 && c <= 255;
    }

    public CanvasColor(int r, int g, int b) {
        if (!(isColorConform(r) && isColorConform(g) && isColorConform(b))) {
            throw new IllegalArgumentException("Each composant must be >= 0 and <= 255");
        }
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Creates a new CanvasColor from a hex string (e.g. #000000) (Hash (#) is
     * optional)
     *
     * @param hex
     */
    public CanvasColor(String hex) {
        hex = hex.replace("#", "");
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Length of hex string " + hex + " is not 6");
        }
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        if (!(isColorConform(r) && isColorConform(g) && isColorConform(b))) {
            throw new IllegalArgumentException("Each composant must be >= 0 and <= 255");
        }

        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Converts a int to a string in hex format (e.g. 4A) (adding leading
     * zeros to make it two characters long)
     *
     * @param number
     * @return
     */
    String toHexString(int number) {
        String hex = Integer.toHexString(number).toUpperCase();
        if (hex.length() == 1)
            return "0" + hex;
        else
            return hex;
    }

    /**
     * Returns the color in hex format (e.g. #000000)
     *
     * @return the color in hex format (e.g. #000000)
     */
    public String asHex() {
        return "#" + toHexString(r) + toHexString(g) + toHexString(b);
    }

    /**
     * Converts the color to a javafx color object
     *
     * @return a javafx color
     */
    public Color toPaintColor() {
        return new Color((double) r / 255.0, (double) g / 255.0, (double) b / 255.0, 1.0);
    }
}
