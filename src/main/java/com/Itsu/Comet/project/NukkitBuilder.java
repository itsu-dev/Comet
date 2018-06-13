package com.Itsu.Comet.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.swing.SwingWorker;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.Console;
import com.Itsu.Comet.utils.Utils;

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

public class NukkitBuilder {
	
	private static Console console;
	
	public static void build(ProjectFile project) {
		try {
			boolean bool = false;
			for(String str : Controller.getTabTitles()) {
				if(str.equals("コンソール")) {
					bool = true;
					break;
				}
			}
			
			console = null;
			
			if(!bool) {
				Calendar cal = Calendar.getInstance();
				String date = 	cal.get(Calendar.YEAR) + "/" + 
								cal.get(Calendar.MONTH) + 1 + "/" + 
								cal.get(Calendar.DAY_OF_MONTH) + " " +
								cal.get(Calendar.HOUR_OF_DAY) + ":" + 
								((cal.get(Calendar.MINUTE) < 10) ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE)) + ":" + 
								((cal.get(Calendar.MINUTE) < 10) ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE));
								
				Controller.openTab("コンソール", console = new Console(project.getName() + "[Nukkitプロジェクト] " + date + " ", ""));
			
			} else {
				console = (Console) Controller.getTabByTitle("コンソール");
			}
			
			console.reset();
			console.appendString( "> " + project.getName() + "[Nukkitプロジェクト] をビルドしています...\n");
			console.appendString("ファイルを展開しています");
			
			String path = project.getPath() + File.separator + "src" + File.separator;
			String temp = project.getPath() + File.separator + "Command.txt";
			FileWriter fileWriter = new FileWriter(new File(temp), true);
			
			fileWriter.write("javac");
			expansionFile(fileWriter, new File(path));
			
			fileWriter.close();
			
			console.appendString("コンパイルしています");
			
			String command = "cmd " + "/c " + Utils.readFile(temp);
			Process process = Runtime.getRuntime().exec(command.split(" "));
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			Thread th = new Thread(() -> {
				try {
	    			String str = "";
					while((str = reader.readLine()) != null) {
						console.appendString(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			Thread th1 = new Thread(() -> {
				try {
	    			String str = "";
					while((str = reader1.readLine()) != null) {
						console.appendString(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			th.start();
			th1.start();
			
			th.join();
			th1.join();
			
			console.appendString("圧縮しています");
			console.appendString("ビルド完了！（31s）");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void expansionFile(FileWriter writer, File in) throws IOException {
		for(File file : in.listFiles()) {
			if(file.isDirectory()) {
				expansionFile(writer, file);
				
			} else {
				System.out.println(file.toString());
				if(file.getName().endsWith(".java")) writer.write(" " + file.getAbsolutePath());
				else continue;
			}
		}
	}

}
