package com.Itsu.Comet.utils;

import java.io.File;
import java.io.IOException;

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

public class PropertyReader {

	public PropertyReader(){
		
	}
	
	public static String get(File file, String key){
		
		try {
			String data = Utils.readFile(file);
			String objects[] = data.split(";");
			
			String result = null;
			
			for(String src : objects){
				if(src.startsWith(key)){
					result = src.replaceFirst(key, "");
					result = result.replaceFirst("=", "");
					return result;
				}
			}
			
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
