package org.pinte.models.CanvasObjects;

import javafx.scene.paint.Color;

/**
 * CanvasColor class to use with Canvas Objects
 */
public class CanvasColor {
	/**
	 * Red, Green Blue and Alpha components of the color
	 */
	private int r, g, b, a;

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
		this.a = 255;
	}

	/**
	 * Creates a new CanvasColor from RGBA components
	 *
	 * @param r red component
	 * @param g green component
	 * @param b blue component
	 * @param a alpha component
	 */
	public CanvasColor(int r, int g, int b, int a) {
		this(r, g, b);
		this.a = a;
	}

	/**
	 * Creates a new CanvasColor from a hex string (e.g. #000000) (Hash (#) is
	 * optional)
	 *
	 * @param hex hex string of the color
	 */
	public CanvasColor(String hex, String opacity) {
		hex = hex.replace("#", "");
		if (hex.length() != 6) {
			throw new IllegalArgumentException("Length of hex string " + hex + " is not 6");
		}

		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);
		int a = 255;
		if (opacity != null && opacity.length() > 0) {
			a = (int) (255.0 * Double.parseDouble(opacity));
		}
		if (!(isColorConform(r) && isColorConform(g) && isColorConform(b) && isColorConform(a))) {
			throw new IllegalArgumentException("Each composant must be >= 0 and <= 255");
		}

		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
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

	public String opacityString() {
		Double res = ((double) a / 255.0);
		return res.toString();
	}

	/**
	 * Converts the color to a javafx color object
	 *
	 * @return a javafx color
	 */
	public Color toPaintColor() {
		return new Color((double) r / 255.0, (double) g / 255.0, (double) b / 255.0, (double) a / 255.0);
	}

	/**
	 * @return the color in the format "Color [r=..., g=..., b=...]"
	 */
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + "]";
	}

	/**
	 * Checks if the color is the same as another color
	 *
	 * @param c color to compare
	 * @return true if the color is the same
	 */
	public boolean equals(CanvasColor c) {
		return (this.r == c.r && this.g == c.g && this.b == c.b && this.a == c.a);
	}

	/**
	 * Permet de récuperer l'oppacité d'une couleur
	 *
	 * @return l'opaccité de la couleur.
	 */
	public int getAlpha() {
		return this.a;
	}

	/**
	 * Permet de fixer l'oppacité d'une couleur
	 *
	 * @param Na l'opaccité de la couleur.
	 */
	public void setAlpha(int Na) {
		this.a = Na;
	}

	/**
	 * Permet de récuperer la composante rouge d'une couleur
	 *
	 * @return la composante rouge de la couleur.
	 */
	public int getRed() {
		return this.r;
	}

	/**
	 * Permet de fixer la composante rouge d'une couleur
	 *
	 * @param Nr la composante rouge de la couleur.
	 */
	public void setRed(int Nr) {
		this.r = Nr;
	}

	/**
	 * Permet de récuperer la composante verte d'une couleur
	 *
	 * @return la composante verte de la couleur.
	 */
	public int getGreen() {
		return this.g;
	}

	/**
	 * Permet de fixer la composante verte d'une couleur
	 *
	 * @param Ng la composante verte de la couleur.
	 */
	public void setGreen(int Ng) {
		this.g = Ng;
	}

	/**
	 * Permet de récuperer la composante bleu d'une couleur
	 *
	 * @return la composante bleu de la couleur.
	 */
	public int getBlue() {
		return this.b;
	}

	/**
	 * Permet de fixer la composante rouge d'une couleur
	 *
	 * @param Nb la composante bleu de la couleur.
	 */
	public void setBlue(int Nb) {
		this.b = Nb;
	}
}
