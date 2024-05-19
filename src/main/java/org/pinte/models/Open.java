package org.pinte.models;

import java.nio.file.Path;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class Open {
	
	public Open(){
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
					String parseStr[] = input.split(" ");
					int i= 0;
					while(i<parseStr.length){
						if(parseStr[i].contains("width")){
							String strwidth[] = parseStr[i].split("=");
							int intwidth = (int)Double.parseDouble(strwidth[1].substring(1,(strwidth[1]).length()-3));
							System.out.println(intwidth);
						} else if(parseStr[i].contains("height")){
							String strheight[] = parseStr[i].split("=");
							int intheight = (int)Double.parseDouble(strheight[1].substring(1,(strheight[1]).length()-3));
							System.out.println(intheight);
						} 	
						i++;
					}
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
			System.out.println(selectedFile.getAbsolutePath());
		    read(selectedFile.toPath());
		}
	}

}
