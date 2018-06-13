package com.Itsu.Comet.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.EditorPanel;

public class ProjectManager {
	
	private File root;
	
	public ProjectManager(){
		
	}
	
	public static void openFile(File file){
		if(file.isDirectory()) return;
		
		try {
			String name = file.getName();
			String texts = Utils.readFile(file);
			
			root.add(tset);
			
			Controller.getEditor().addTab(name, new EditorPanel(null, texts));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}