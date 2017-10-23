package com.Itsu.Comet.utils;

import java.io.File;
import java.io.IOException;

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
