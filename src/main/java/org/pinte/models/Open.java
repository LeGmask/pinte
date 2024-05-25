package org.pinte.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.Parent;

import java.nio.file.Path;
import java.util.Scanner;
import javafx.stage.FileChooser;
import java.io.File;


import javafx.geometry.Point2D;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasRectangle;
import org.pinte.models.CanvasObjects.CanvasPolygon;
import org.pinte.models.CanvasObjects.CanvasLine;
import org.pinte.models.CanvasObjects.CanvasTextField;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.Utils.CanvasObjectParser;

public class Open {
	
	Path openpath;

	public Open(){
		this.openpath=null;
	}

	public Open(Path path){
		this.openpath=path;
	}

	/**
	 * Canva
	 */
	Canvas canva = Canvas.getInstance();

	private double extractnumber(String input){
		int length=input.length();
		int k=0;
		String ret = "";
		boolean begin=false;
		boolean end=false;
		while(k<length && !end){
			char test=input.charAt(k);
			switch (test){
				case '1':
					ret=ret+"1";
					begin=true;
					break;
				case '2':
					ret=ret+"2";
					begin=true;
					break;
				case '3':
					ret=ret+"3";
					begin=true;
					break;
				case '4':
					ret=ret+"4";
					begin=true;
					break;
				case '5':
					ret=ret+"5";
					begin=true;
					break;
				case '6':
					ret=ret+"6";
					begin=true;
					break;
				case '7':
					ret=ret+"7";
					begin=true;
					break;
				case '8':
					ret=ret+"8";
					begin=true;
					break;
				case '9':
					ret=ret+"9";
					begin=true;
					break;
				case '0':
					ret=ret+"0";
					begin=true;
					break;
				case '.':
					if(begin=true){
						ret=ret+".";
					}
					break;
			 default:
				if(begin==true){
					end=true;
				}
			}
			k++;
		}
		if(end==true){
			return Double.parseDouble(ret);
		} else{
			return -1;
		}
	}

	public void read(boolean tested){
		try{
			boolean unknown=false;
			Scanner sc = new Scanner(this.openpath).useDelimiter("<");
			String input = sc.next();
			boolean begin = input.contains("svg") && input.contains("xmlns");
			while(sc.hasNext()&&(!begin)){
				input=sc.next();
				begin = input.contains("svg") && input.contains("xmlns");
			}
			while(sc.hasNext()&&(!input.contains("/svg>"))){
				if(input.contains("svg")){
					if(tested==true){
						int intwidth=1; 
						int intheight=1; 
						String parseStr[] = input.split(" ");
						int i= 0;
						while(i<parseStr.length){
							if(parseStr[i].contains("width")){
								intwidth = (int) extractnumber(parseStr[i]);
							} else if(parseStr[i].contains("height")){
								intheight = (int) extractnumber(parseStr[i]);
							} 	
							i++;
						}
						canva.setDim(intwidth,intheight);
						canva.clear();
					}
				} else if (input.contains("ellipse")){
					if(tested==true){
						canva.add(CanvasEllipse.createFromSVG(input));
					}
				} else if(input.contains("rect")){
					if(tested==true){
						canva.add(CanvasRectangle.createFromSVG(input));
					}
				} else if(input.contains("circle")){
					if(tested==true){
						double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", input));
						double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", input));
						double r = Double.parseDouble(CanvasObjectParser.parseKeyword("r", input));
						CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", input),
						CanvasObjectParser.parseKeyword("fill-opacity", input));
						CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", input),
						CanvasObjectParser.parseKeyword("stroke-opacity", input));
						canva.add(new CanvasEllipse(new Point2D(cx, cy), r, fillColor, strokeColor)); 
					}
				}  else if(input.contains("line")){
				}  else if(input.contains("line")){
					if(tested==true){
						canva.add(CanvasLine.createFromSVG(input));
					}
				} else if(input.contains("polygon")){
					if(tested==true){
						canva.add(CanvasPolygon.createFromSVG(input));
					}
				} else if(input.contains("text")){
					if(tested==true){
						canva.add(CanvasTextField.createFromSVG(input));
					}
				} else {
					System.out.println("type non traitée");
					unknown=true;
					System.out.println(input);
				}
				input=sc.next();
			}
			sc.close();
			if(tested==false && unknown==true){
				Stage primaryStage = new Stage();
				URL url = getClass().getResource("../views/warningopen.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(url);
				GridPane root = (GridPane) fxmlLoader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Warning!");
				primaryStage.show();
			} else if(tested==false && unknown==false){
				read(true);
			} else {

			}
		} catch(java.io.IOException e){
			System.out.println(e);
		}
	}

	public void choose(){
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		
		fileChooser.setTitle("Open Project");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Svg Files", "*.svg"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
		    this.openpath=selectedFile.toPath();
			canva.setPathOpen(openpath);
			try{
				Stage mainStage=new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../views/main.fxml")); // instantiate the new view
				Parent root = loader.load();
				Scene scene = new Scene(root);
				mainStage.setTitle(canva.getName());
				mainStage.setScene(scene);
				mainStage.show();
			} catch(java.io.IOException e){
				System.out.println(e);
			}
			read(false);
		} else {
			try{
				Stage mainStage=new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../views/new.fxml")); // instantiate the new view
				Parent root = loader.load();
				Scene scene = new Scene(root);
				mainStage.setTitle("nouveaux");
				mainStage.setScene(scene);
				mainStage.show();
			} catch(java.io.IOException e){
				System.out.println(e);
			}
		}
	}

}
