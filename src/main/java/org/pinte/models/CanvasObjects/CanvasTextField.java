package org.pinte.models.CanvasObjects;

import javafx.geometry.Point2D;
import org.pinte.models.Utils.CanvasObjectParser;

import java.util.Dictionary;
import java.util.Hashtable;

public class CanvasTextField extends CanvasObject {
	/**
	 * The text written in the text field.
	 */
	String text;

	/**
	 * The baseline starting point of the text
	 */
	Point2D baselineStart;

	/**
	 * The text's font size
	 */
	int fontSize;

	/**
	 * The text's font family
	 */
	String fontFamily;

	/**
	 * Creates a CanvasTextField.
	 * @param text the text written in the text field
	 * @param baselineStart the baseline starting point of the text
	 * @param fillColor the fill color of the text
	 * @param strokeColor the stroke color of the text
	 * @param fontSize the text's font size
	 * @param fontFamily the text's font family
	 */
	public CanvasTextField(String text, Point2D baselineStart, int fontSize, String fontFamily, CanvasColor fillColor, CanvasColor strokeColor) {
		super(fillColor, strokeColor);
		this.text = text;
		this.baselineStart = baselineStart;
		this.fontSize = fontSize;
		this.fontFamily = fontFamily;
	}

	@Override
	public boolean contains(double x, double y) {
		return this.getTextArea().contains(x, y);
	}

	private CanvasRectangle getTextArea() {
		double textWidth = estimateTextWidth(text, fontSize);
		double textHeight = fontSize;

		double textX = baselineStart.getX();
		double textY = baselineStart.getY() - fontSize * 0.8;

		return new CanvasRectangle(new Point2D(textX, textY), textWidth, textHeight,
			new CanvasColor(0, 0, 0), new CanvasColor(0, 0, 0));
	}

	/**
	 * Estimates the width of the text based on the font size.
	 * @param text the text to estimate the width of
	 * @param fontSize the size of the font
	 * @return the estimated width of the text
	 */
	private double estimateTextWidth(String text, int fontSize) {
		return text.length() * fontSize * 0.5;
	}

	@Override
	public CanvasObject duplicate(Point2D offset) {
		return new CanvasTextField(text, baselineStart.add(offset), fontSize, fontFamily, fillColor, strokeColor);
	}

	@Override
	public Point2D getGravityCenter() {
		return getTextArea().getGravityCenter();
	}

	@Override
	public void render() {
		this.setUpDrawingParameters();
		this.setUpWritingParameters(fontFamily, fontSize);

		gc.fillText(text, baselineStart.getX(), baselineStart.getY());
		gc.strokeText(text, baselineStart.getX(), baselineStart.getY());
	}

	@Override
	public String toSVG() {
		Dictionary<String, String> attributes = new Hashtable<>();
		attributes.put("x", Double.toString(this.baselineStart.getX()));
		attributes.put("y", Double.toString(this.baselineStart.getY()));
		attributes.put("fill", this.fillColor.asHex());
		attributes.put("fill-opacity", this.fillColor.opacityString());
		attributes.put("stroke", this.strokeColor.asHex());
		attributes.put("stroke-opacity", this.strokeColor.opacityString());
		attributes.put("font-family", fontFamily);
		attributes.put("font-size", Integer.toString(fontSize));
		return toSVG("text", attributes) + text + "</text>";
	}

	/**
	 * Creates a text filed from an SVG string
	 *
	 * @param args the SVG string to parse
	 * @return a CanvasTextField parsed from the SVG string
	 */
	public static CanvasTextField createFromSVG(String args) {
		double x = Double.parseDouble(CanvasObjectParser.parseKeyword("x", args));
		double y = Double.parseDouble(CanvasObjectParser.parseKeyword("y", args));
		String fontFamily = CanvasObjectParser.parseKeyword("font-family", args);
		int fontSize = Integer.parseInt(CanvasObjectParser.parseKeyword("font-size", args));
		CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", args),
			CanvasObjectParser.parseKeyword("fill-opacity", args));
		CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", args),
			CanvasObjectParser.parseKeyword("stroke-opacity", args));

		//TODO : replace text parameter in next line so that the actual registered text is taken into account
		return new CanvasTextField("text", new Point2D(x, y), fontSize, fontFamily, fillColor, strokeColor);
	}

	@Override
	public void translate(Point2D p) {
		baselineStart = baselineStart.add(p);
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
}
