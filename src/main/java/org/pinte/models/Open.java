package org.pinte.models;

import java.nio.file.Path;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.geometry.Point2D;
import org.pinte.models.CanvasObjects.CanvasObject;
import org.pinte.models.CanvasObjects.CanvasEllipse;
import org.pinte.models.CanvasObjects.CanvasRectangle;
import org.pinte.models.CanvasObjects.CanvasPolygon;
import org.pinte.models.CanvasObjects.CanvasColor;
import org.pinte.models.Utils.CanvasObjectParser;

public class Open {
	
	public Open(){
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
			if(test=='1'){
				ret=ret+"1";
				begin=true;
			} else if(test=='2'){
				ret=ret+"2";
				begin=true;
			} else if(test=='3'){
				ret=ret+"3";
				begin=true;
			} else if(test=='4'){
				ret=ret+"4";
				begin=true;
			} else if(test=='5'){
				ret=ret+"5";
				begin=true;
			} else if(test=='6'){
				ret=ret+"6";
				begin=true;
			} else if(test=='7'){
				ret=ret+"7";
				begin=true;
			} else if(test=='8'){
				ret=ret+"8";
				begin=true;
			} else if(test=='9'){
				ret=ret+"9";
				begin=true;
			} else if(test=='0'){
				ret=ret+"0";
				begin=true;
			} else if(test=='.'){
				if(begin=true){
					ret=ret+".";
				}
			} else{
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

	private void read(Path path){
		try{
			Scanner sc = new Scanner(path).useDelimiter("<");
			String input = sc.next();
			boolean begin = input.contains("svg") && input.contains("xmlns");
			while(sc.hasNext()&&(!begin)){
				input=sc.next();
				begin = input.contains("svg") && input.contains("xmlns");
			}
			while(sc.hasNext()&&(!input.contains("/svg>"))){
				if(input.contains("svg")){
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
				} else if (input.contains("ellipse")){
					canva.add(CanvasEllipse.createFromSVG(input));
				} else if(input.contains("rect")){
					canva.add(CanvasRectangle.createFromSVG(input));
				} else if(input.contains("circle")){
					double cx = Double.parseDouble(CanvasObjectParser.parseKeyword("cx", input));
					double cy = Double.parseDouble(CanvasObjectParser.parseKeyword("cy", input));
					double r = Double.parseDouble(CanvasObjectParser.parseKeyword("r", input));
					CanvasColor fillColor = new CanvasColor(CanvasObjectParser.parseKeyword("fill", input));
					CanvasColor strokeColor = new CanvasColor(CanvasObjectParser.parseKeyword("stroke", input));
					canva.add(new CanvasEllipse(new Point2D(cx, cy), r, fillColor, strokeColor)); 
				}  else if(input.contains("line")){
					System.out.println("line");
				}  else if(input.contains("polyline")){
					System.out.println("polyline");
				}  else if(input.contains("polygon")){
					canva.add(CanvasPolygon.createFromSVG(input));
				}  else if(input.contains("path")){
					System.out.println("path");
				} else if(input.contains("defs")){
					System.out.println("defs");
					System.out.println("?");
				} else if(input.contains("path")){
					System.out.println("path");
				} else if(input.contains("pattern")){
					System.out.println("pattern");
				} else if(input.contains("text")){
					System.out.println("text");
				}else if(input.contains("g")){
					System.out.println("g");
				} else if(input.contains("clipath")){
					System.out.println("clipath");
				}else if(input.contains("image")){
					System.out.println("image");
				} else if(input.contains("path")){
					System.out.println("path");
				} else {
					System.out.println("pas traiter parce que y a trop de truc on verra plus tard");
				}
				input=sc.next();
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
		    read(selectedFile.toPath());
		}
	}

}
