package org.pinte.models;

public class Coloration {

	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	/**
	 * Constructeur de coloration.java
	 */
	public Coloration() {
	}

	public void Black() {
		canva.colorSelect.setBlue(0);
		canva.colorSelect.setGreen(0);
		canva.colorSelect.setRed(0);
	}

	public void White() {
		canva.colorSelect.setBlue(255);
		canva.colorSelect.setGreen(255);
		canva.colorSelect.setRed(255);
	}

	public void Blue() {
		canva.colorSelect.setBlue(255);
		canva.colorSelect.setGreen(0);
		canva.colorSelect.setRed(0);
	}

	public void Red() {
		canva.colorSelect.setBlue(0);
		canva.colorSelect.setGreen(0);
		canva.colorSelect.setRed(255);
	}

	public void Green() {
		canva.colorSelect.setBlue(0);
		canva.colorSelect.setGreen(255);
		canva.colorSelect.setRed(0);
	}

	public void Cyan() {
		canva.colorSelect.setBlue(255);
		canva.colorSelect.setGreen(255);
		canva.colorSelect.setRed(0);
	}

	public void Magenta() {
		canva.colorSelect.setBlue(255);
		canva.colorSelect.setGreen(0);
		canva.colorSelect.setRed(255);
	}

	public void Yellow() {
		canva.colorSelect.setBlue(0);
		canva.colorSelect.setGreen(255);
		canva.colorSelect.setRed(255);
	}

	public void Opaq(int alpha) {
		canva.colorSelect.setAlpha(alpha);

	}
}
