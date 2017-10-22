package com.Itsu.Comet.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.Itsu.Comet.gui.Editor;
import com.Itsu.Comet.gui.ProjectTab;
import com.Itsu.Comet.gui.View;

public class Server{
	
	private static List<View> activeWindows = new ArrayList<View>();
	private static List<String> activeWindowsKeys = new ArrayList<String>();
	
	private static JDesktopPane mdi;
	private static JFrame frame;
	
	private static ProjectTab projectTab;
	
	private static String nowProject;
	private static String nowProjectPath;
	
	protected static void addActiveWindow(View frame, String key){
		activeWindows.add(frame);
		activeWindowsKeys.add(key);
	}
	
	protected static void removeActiveWindow(View frame, String key){
		activeWindows.remove(frame);
		activeWindowsKeys.remove(key);
	}
	
	protected static Editor getEditor(){
		if(isWindowActive("Editor")){
			for(View view : activeWindows){
				if(view instanceof Editor){
					return (Editor) view;
				}
			}
		}else{
			return null;
		}
		return null;
	}
	
	protected static boolean isWindowActive(String key){
		return activeWindowsKeys.contains(key);
	}
	
	protected static void setDesktopPane(JDesktopPane mdi){
		Server.mdi = mdi;
	}
	
	protected static JDesktopPane getDesktopPane(){
		return mdi;
	}

	protected static void setJFrame(JFrame frame){
		Server.frame = frame;
	}
	
	protected static JFrame getJFrame(){
		return frame;
	}
	
	protected static void setProjectTab(ProjectTab tab){
		projectTab = tab;
	}
	
	protected static ProjectTab getProjectTab(){
		return projectTab;
	}
	
	protected static void setNowProject(String proj){
		nowProject = proj;
	}
	
	protected static String getNowProject(){
		return nowProject;
	}
	
	protected static void setNowProjectPath(String proj){
		nowProjectPath = proj;
	}
	
	protected static String getNowProjectPath(){
		return nowProjectPath;
	}
}
