package org.pinte.models;

import java.awt.*;

public final class SVG {
	private static SVG INSTANCE;

	public String label;

	private SVG() {
		label = "Hello you!";

	}

	public static SVG getInstance() {
		return INSTANCE == null ? new SVG() : INSTANCE;
	}

}
