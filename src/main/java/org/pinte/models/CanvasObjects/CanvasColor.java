package org.pinte.models.CanvasObjects;

import javafx.scene.paint.Color;

/**
 * CanvasColor class to use with Canvas Objects
 */
public class CanvasColor {
	/**
	 * Red, Green and Blue component of the color
	 */
	private int r, g, b;


	/**
	 * Creates a new CanvasColor from RGB components
	 *
	 * @param r red component
	 * @param g green component
	 * @param b blue component
	 */
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
	 * @param hex hex string of the color
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
	 * Checks if the color component is between 0 and 255
	 *
	 * @param c color component
	 * @return true if the color component is between 0 and 255
	 */
	private boolean isColorConform(int c) {
		return c >= 0 && c <= 255;
	}

	/**
	 * Converts a int to a string in hex format (e.g. 4A) (adding leading
	 * zeros to make it two characters long)
	 *
	 * @param number the number to convert
	 * @return the number in hex format
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

	/**
	 * @return the color in the format "Color [r=..., g=..., b=...]"
	 */
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	/**
	 * Checks if the color is the same as another color
	 *
	 * @param c color to compare
	 * @return true if the color is the same
	 */
	public boolean equals(CanvasColor c) {
		return (this.r == c.r && this.g == c.g && this.b == c.b);
	}
}
