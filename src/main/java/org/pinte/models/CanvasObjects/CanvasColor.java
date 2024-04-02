package org.pinte.models.CanvasObjects;

import javafx.scene.paint.Color;

public class CanvasColor {
    private int r, g, b, a;

    public CanvasColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }

    public CanvasColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Returns the color in hex format (e.g. #000000)
     *
     * @return
     */
    public String asHex() {
        return "#" + r + g + b;
    }

    public Color toPaintColor() {
        return new Color((double) r / 255.0, (double) g / 255.0, (double) b / 255.0, (double) a / 255.0);
    }
}
