package org.pinte.models.CanvasObjects;

import javafx.scene.paint.Color;

public class CanvasColor {
    private int r, g, b;

    public CanvasColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Creates a new CanvasColor from a hex string (e.g. #000000)
     *
     * @param hex
     */
    public CanvasColor(String hex) {
        this.r = Integer.parseInt(hex.substring(1, 3), 16);
        this.g = Integer.parseInt(hex.substring(3, 5), 16);
        this.b = Integer.parseInt(hex.substring(5, 7), 16);
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
