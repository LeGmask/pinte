package org.pinte.models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pinte.models.CanvasObjects.*;
import org.pinte.models.Utils.CanvasObjectParser;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Scanner;

public class Open {

	Path openpath;// path to the new file
	Stage stage;// stage of the transition window if a warning is needed
	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	/**
	 * Constructor of Open
	 */
	public Open() {
		this.openpath = null;
	}

	/**
	 * constructor of Open with the path to the file to open
	 *
	 * @param path path to the file to open
	 */
	public Open(Path path) {
		this.openpath = path;
	}

	/**
	 * extract a number from a String
	 *
	 * @param input String where the number is extracted
	 * @return the number extracted as a double
	 */
	private double extractnumber(String input) {
		int length = input.length();
		int k = 0;
		String ret = "";
		boolean begin = false;
		boolean end = false;
		while (k < length && !end) {
			char test = input.charAt(k);
			switch (test) {
				case '1':
					ret = ret + "1";
					begin = true;
					break;
				case '2':
					ret = ret + "2";
					begin = true;
					break;
				case '3':
					ret = ret + "3";
					begin = true;
					break;
				case '4':
					ret = ret + "4";
					begin = true;
					break;
				case '5':
					ret = ret + "5";
					begin = true;
					break;
				case '6':
					ret = ret + "6";
					begin = true;
					break;
				case '7':
					ret = ret + "7";
					begin = true;
					break;
				case '8':
					ret = ret + "8";
					begin = true;
					break;
				case '9':
					ret = ret + "9";
					begin = true;
					break;
				case '0':
					ret = ret + "0";
					begin = true;
					break;
				case '.':
					if (begin = true) {
						ret = ret + ".";
					}
					break;
				default:
					if (begin == true) {
						end = true;
					}
			}
			k++;
		}
		if (end == true) {
			return Double.parseDouble(ret);
		} else {
			return -1;
		}
	}

	/**
	 * Reading of the file and setting of the canvas
	 *
	 * @param tested the program has been tested to know if we can set it or not
	 * @param warned the user has been warned of potential change in the original
	 *               svg
	 */
	public void read(boolean tested, boolean warned) {
		try {
			if (warned == true) {
				openwindows();
			}
			if (tested == true) {
				canva.reset();
			}
			boolean unknown = false;
			Scanner sc = new Scanner(this.openpath).useDelimiter("<");
			String input = sc.next();
			boolean begin = input.contains("svg") && input.contains("xmlns");
			while (sc.hasNext() && (!begin)) {
				input = sc.next();
				begin = input.contains("svg") && input.contains("xmlns");
			}
			while (sc.hasNext() && (!input.contains("/svg>"))) {
				if (input.contains("svg")) {
					if (tested == true) {
						int intwidth = 1;
						int intheight = 1;
						String parseStr[] = input.split(" ");
						int i = 0;
						while (i < parseStr.length) {
							if (parseStr[i].contains("width")) {
								intwidth = (int) extractnumber(parseStr[i]);
							} else if (parseStr[i].contains("height")) {
								intheight = (int) extractnumber(parseStr[i]);
							}
							i++;
						}
						canva.setDim(intwidth, intheight);
					}
				} else if (input.contains("ellipse")) {
					if (tested == true) {
						canva.add(CanvasEllipse.createFromSVG(input));
					}
				} else if (input.contains("rect")) {
					if (tested == true) {
						canva.add(CanvasRectangle.createFromSVG(input));
					}
				} else if (input.contains("circle")) {
					if (tested == true) {
						double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", input));
						double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", input));
						double r = Double.parseDouble(CanvasObjectParser.parseKeyword("r", input));
						CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", input),
							CanvasObjectParser.parseKeyword("fill-opacity", input));
						CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", input),
							CanvasObjectParser.parseKeyword("stroke-opacity", input));
						canva.add(new CanvasEllipse(new Point2D(cx, cy), r, fillColor, strokeColor));
					}
				} else if (input.contains("line")) {
					if (tested == true) {
						canva.add(CanvasLine.createFromSVG(input));
					}
				} else if (input.contains("polygon")) {
					if (tested == true) {
						canva.add(CanvasPolygon.createFromSVG(input));
					}
				} else if (input.contains("text")) {
					if (tested == true) {
						while (!input.contains("/text>")) {
							input += "<" + sc.next();
						}

						double x = Double.parseDouble(CanvasObjectParser.parseKeyword("x", input));
						double y = Double.parseDouble(CanvasObjectParser.parseKeyword("y", input));
						String fontFamily = CanvasObjectParser.parseKeyword("font-family", input);
						int fontSize = Integer.parseInt(CanvasObjectParser.parseKeyword("font-size", input));
						CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", input),
							CanvasObjectParser.parseKeyword("fill-opacity", input));
						CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", input),
							CanvasObjectParser.parseKeyword("stroke-opacity", input));
						String text = CanvasObjectParser.parseBetweenTags(input, "text");
						canva.add(new CanvasTextField(text, new Point2D(x, y), fontSize, fontFamily, fillColor,
							strokeColor));
					}
				} else {
					System.out.println("untreated type");
					unknown = true;
				}
				input = sc.next();
			}
			sc.close();
			if (tested == false && unknown == true) {
				this.stage.close();
				Stage warningStage = new Stage();
				URL url = getClass().getResource("../views/warningopen.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(url);
				GridPane root = (GridPane) fxmlLoader.load();
				Scene scene = new Scene(root);
				warningStage.setScene(scene);
				warningStage.setTitle("Warning!");
				warningStage.show();
				this.stage = warningStage;
			} else if (tested == false && unknown == false) {
				read(true, false);
			} else {
				if (canva.getCanvastage() != null) {
					canva.getCanvastage().close();
				}
				canva.setCanvastage(this.stage);
				canva.setPath(this.openpath);
				canva.setPathOpen(null);
				canva.setSafePath(true);
				canva.setName(name());
			}
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * extract name from path
	 *
	 * @return name of project
	 */
	public String name() {
		String[] directory = openpath.toString().split("/");
		String newname = directory[directory.length - 1];
		return newname.replaceAll(".svg", "");
	}

	/**
	 * open a new main window that contain a project
	 */
	public void openwindows() {
		try {
			Stage newStage = new Stage();
			this.stage = newStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../views/main.fxml")); // instantiate the new view
			Parent root = loader.load();
			Scene scene = new Scene(root);
			newStage.setTitle(name());
			newStage.setScene(scene);
			newStage.show();
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Choose the file to open
	 *
	 * @param first the first file opened
	 */
	public void choose(boolean first) {
		FileChooser fileChooser = new FileChooser();
		Stage choosestage = new Stage();

		fileChooser.setTitle("Open Project");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Svg Files", "*.svg"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

		File selectedFile = fileChooser.showOpenDialog(choosestage);
		if (selectedFile != null) {
			this.openpath = selectedFile.toPath();
			openwindows();
			canva.setPathOpen(openpath);
			read(false, false);
		} else {
			if (first == true) {
				newproject();
			}
		}
	}

	/**
	 * Open warning close window
	 *
	 * @param nw new canva
	 */
	public void warning(boolean nw) {
		try {
			if (nw) {
				canva.setNw(true);
				Stage warningStage = new Stage();
				URL url = getClass().getResource("../views/warningclose.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(url);
				GridPane root = (GridPane) fxmlLoader.load();
				Scene scene = new Scene(root);
				warningStage.setScene(scene);
				warningStage.setTitle("Warning!");
				warningStage.show();
			} else {
				canva.setOpen(true);
				Stage warningStage = new Stage();
				URL url = getClass().getResource("../views/warningclose.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(url);
				GridPane root = (GridPane) fxmlLoader.load();
				Scene scene = new Scene(root);
				warningStage.setScene(scene);
				warningStage.setTitle("Warning!");
				warningStage.show();
			}
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Open a window to create a new project
	 */
	public void newproject() {
		try {
			Stage openStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../views/opennew.fxml")); // instantiate the new view
			Parent root = loader.load();
			Scene scene = new Scene(root);
			openStage.setTitle(canva.getName());
			openStage.setScene(scene);
			openStage.show();
			canva.getCanvastage().close();
			canva.setCanvastage(openStage);
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
	}

}
