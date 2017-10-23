package com.Itsu.Comet.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Colors {
	
	private List<Color> black = new ArrayList<Color>();
	
	public Colors(){
		
	}
	
	public void init(){
		int index = 1;
		String color = "r";
		
		/*
		for(int count=0;count<12;count++){
			color = "r";
			int r = Integer.valueOf(PropertyReader.get(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Color.properties"), "BLACK" + index + color));
			color = "g";
			int g = Integer.valueOf(PropertyReader.get(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Color.properties"), "BLACK" + index + color));
			color = "b";
			int b = Integer.valueOf(PropertyReader.get(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Color.properties"), "BLACK" + index + color));
			
			black.add(new Color(r, g, b));
			index++;
		}
		*/
	}
	
	public List<Color> getBlackColors(){
		return black;
	}

}
