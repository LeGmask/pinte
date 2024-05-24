package org.pinte.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pinte.models.Canvas;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Export {
	/**
	 * canvas instance
	 */
	private final Canvas canvas = Canvas.getInstance();

	/**
	 * The file to export to
	 */
	private File file;

	/**
	 * Export constructor and main logic
	 */
	public Export() throws IOException {
		setFile();
		write();
	}

	/**
	 * Export canvas to a file
	 */
	private void write() throws IOException {
		WritableImage writableImage = new WritableImage(canvas.getDim().width, canvas.getDim().height);
		canvas.javafxCanvas.snapshot(null, writableImage);

		// export image
		RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
		ImageIO.write(renderedImage, "png", file);
	}

	/**
	 * Set file instance
	 */
	private void setFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");
		fileChooser.setInitialFileName(canvas.getName() + ".png");

		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Png Files", "*.png"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

		file = fileChooser.showSaveDialog(new Stage());
	}
}
