package com.Itsu.Comet.project;

import java.io.File;
import java.io.IOException;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.utils.Utils;

public class ProjectMaker {
	
	public ProjectMaker(){
		
	}
	
	public static boolean makeProject(String workspace, String name, String type){
		
		if(type.equals("Nukkit") || type.equals("pmmp")){
			try {
				File project = new File(workspace + name + "/");
				File src = new File(workspace + name + "/src/");
				File resource = new File(workspace + name + "/resource/");
				
				if(project.exists()){
					return false;
					
				}else{
					project.mkdirs();
					src.mkdirs();
					resource.mkdirs();
				}
				
				Utils.writeFile(new File(workspace + name + "/project.proj"), "name=" + name + ";type=" + type + ";path" + Controller.getDataObject().getWorkspacePass() + name);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
			
			
		}else{
			try {
				File project = new File(workspace + name + "/");
				File src = new File(workspace + name + "/src/");
				File resource = new File(workspace + name + "/resource/");
				
				if(project.exists()){
					return false;
					
				}else{
					project.mkdirs();
					src.mkdirs();
					resource.mkdirs();
				}
				
				Utils.writeFile(new File(workspace + name + "/project.proj"), "name=" + name + "\ntype=Java" + ";path" + Controller.getDataObject().getWorkspacePass() + name);
				
				
			} catch (IOException e) {
				Controller.exception("プロジェクトの作成中にエラーが発生しました。", e);
				return false;
			}
			
			return true;
		}
	}

}
