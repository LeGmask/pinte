package org.pinte.models.CanvasObjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

/**
 * Classe de test de CanvasObject
 */
public class CanvasColorTest {

	// CanvasColor(r, g, b)
	@Test
	public void testNormalUseCanvasColorRGB() {
		CanvasColor color = new CanvasColor(20, 50, 70);
		Assertions.assertEquals("#143246", color.asHex());
	}

	@Test
	public void testNormalUseCanvasColorRGBWhite() {
		CanvasColor color = new CanvasColor(255, 255, 255);
		Assertions.assertEquals("#FFFFFF", color.asHex());
	}

	@Test
	public void testNormalUseCanvasColorRGBBlack() {
		CanvasColor color = new CanvasColor(0, 0, 0);
		Assertions.assertEquals("#000000", color.asHex());
	}

	@Test
	public void testNegativeRCanvasColorRGBValueThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(-30, 10, 70));
	}

	@Test
	public void testNegativeGValueCanvasColorRGBThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(30, -10, 70));
	}

	@Test
	public void testNegativeBValueCanvasColorRGBThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(30, 10, -70));
	}

	@Test
	public void testAbove255RValueCanvasColorRGBThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(256, 10, 70));
	}

	@Test
	public void testAbove255GValueCanvasColorRGBThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(30, 256, 70));
	}

	@Test
	public void testAbove255BValueCanvasColorRGBThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor(30, 10, 256));
	}

	// CanvasColor(hex)
	@Test
	public void testNormalUseCanvasColorHex() {
		CanvasColor color = new CanvasColor("#143246");
		Assertions.assertEquals("#143246", color.asHex());
	}

	// CanvasColor(hex)
	@Test
	public void testNormalUseCanvasColorHexNoHashInput() {
		CanvasColor color = new CanvasColor("143246");
		Assertions.assertEquals("#143246", color.asHex());
	}

	@Test
	public void testNormalUseCanvasColorHexWhite() {
		CanvasColor color = new CanvasColor("#FFFFFF");
		Assertions.assertEquals("#FFFFFF", color.asHex());
	}

	@Test
	public void testNormalUseCanvasColorHexBlack() {
		CanvasColor color = new CanvasColor("#000000");
		Assertions.assertEquals("#000000", color.asHex());
	}

	@Test
	public void testWrongSizeArgumentCanvasColorHexThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor("error"));
	}

	@Test
	public void testWrongTypeArgumentCanvasColorHexThrowsIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor("1111111"));
	}

	@Test
	public void testWrongTypeArgumentCanvasColorHexThrowsIllegalArgumentException2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new CanvasColor("#123g43"));
	}

	// toPaintColor()
	@Test
	public void testNormalUseToPaintColor() {
		Assertions.assertEquals(Color.ALICEBLUE,
			new CanvasColor((int) (Color.ALICEBLUE.getRed() * 255), (int) (Color.ALICEBLUE.getGreen() * 255),
				(int) (Color.ALICEBLUE.getBlue() * 255)).toPaintColor());
	}

}