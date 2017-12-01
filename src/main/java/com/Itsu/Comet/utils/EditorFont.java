package com.Itsu.Comet.utils;

import java.awt.Font;

/**
 * 
 * <h6>Comet project</h6>
 * <p>for PMMP/Jupiter/Nukkit plugin
 * 
 * <p>Java（PHP）構文向けIDEプロジェクト
 * <p>Made by Itsu(Twitter: @itsu_dev)
 * 
 * @author Itsu
 *
 */

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
