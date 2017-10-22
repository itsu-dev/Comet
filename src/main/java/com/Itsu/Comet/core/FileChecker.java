package com.Itsu.Comet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileChecker {
	
	public FileChecker(){
		
	}
	
	protected void check(){
		
		if(!new File("./Comet/settings/").exists()){
			new File("./Comet/settings/").mkdirs();
		}
		
		if(!new File("./Comet/settings/Settings.properties").exists()){
			try {
                FileWriter fw = new FileWriter(new File("./Comet/settings/Settings.properties"), true);
                PrintWriter pw = new PrintWriter(fw);
                
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Settings.properties")));

                String str = br.readLine();
                while(str != null){
                	pw.println(str);
                	str = br.readLine();
                }

                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		if(!new File("./workspace/").exists()){
			new File("./workspace/").mkdirs();
		}
		
	}

}
