package com.Itsu.Comet.utils;

import java.awt.Font;

public class EditorFont {
	
	public EditorFont(){
		
	}
	
	public Font createEditorFont(){
		try {
			return Font.createFont(Font.PLAIN, getClass().getClassLoader().getResourceAsStream("Comet/font/RictyDiminished-Regular.ttf"));
		} catch (Exception e) {
			return null;
		}
	}

}
