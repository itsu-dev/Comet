package com.Itsu.Comet.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SplashScreen;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.Itsu.Comet.gui.Editor;
import com.Itsu.Comet.gui.NukkitTest;
import com.Itsu.Comet.gui.ProjectSetter;
import com.Itsu.Comet.gui.ProjectTab;
import com.Itsu.Comet.gui.View;
import com.Itsu.Comet.project.ProjectMaker;
import com.Itsu.Comet.project.ProjectManager;
import com.Itsu.Comet.utils.Colors;
import com.Itsu.Comet.utils.Data;
import com.Itsu.Comet.utils.MessagePopup;
import com.Itsu.Comet.utils.SplashWindow;
import com.Itsu.Comet.utils.Utils;

public class Controller {

    private static Data data = new Data();
    private static Colors color = new Colors();
    private static SplashWindow splashWindow;

    public Controller(){

    }

    public static void initData(){
        try {
            data.initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initUI(){
        new UIController();
    }

    public static Data getDataObject(){
        return data;
    }

    public static void openEditor(View view, String key){
        if(!Server.isWindowActive("Editor")){
            Server.removeActiveWindow(view, key);
            Server.addActiveWindow(view, key);
            Server.getDesktopPane().add(view);
        }
    }

    public static void closeEditor(View view, String key){
        if(Server.isWindowActive("Editor")){
            Server.removeActiveWindow(view, key);
        }
    }
    
    public static Editor getEditor(){
    	return Server.getEditor();
    }
    
    public static void setProjectTab(ProjectTab tab){
    	Server.setProjectTab(tab);
    }
    
    public static ProjectTab getProjectTab(){
    	return Server.getProjectTab();
    }

    public static void openView1(View view, String key){
        if(!Server.isWindowActive("View1")){
            Server.removeActiveWindow(view, key);
            Server.addActiveWindow(view, key);
            Server.getDesktopPane().add(view);
        }
    }

    public static void closeView1(View view, String key){
        if(Server.isWindowActive("View1")){
            Server.removeActiveWindow(view, key);
        }
    }
    
    protected static void setSplashWindow(SplashWindow splash) {
    	splashWindow = splash;
    }
    
    public static void setSplashText(String text) {
    	SplashScreen splash = SplashScreen.getSplashScreen();
    	 
        Graphics g = splash.createGraphics();
        g.setColor(new Color(48, 62, 158));
        g.fillRect(200, 160, 250, 22);
        g.setColor(Color.WHITE);
        g.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 15));
        g.drawString(text, 250, 180);
		
        splash.update();
    }

    public static void setDesktopPane(JDesktopPane pane){
        Server.setDesktopPane(pane);
    }

    public static JDesktopPane getDesktopPane(){
        return Server.getDesktopPane();
    }
    
    public static void setJFrame(JFrame frame){
        Server.setJFrame(frame);
    }

    public static JFrame getJFrame(){
        return Server.getJFrame();
    }
    
    public static void setNowProject(String proj){
    	Server.setNowProject(proj);
    }
    
    public static String getNowProject(){
    	return Server.getNowProject();
    }
    
    public static void setNowProjectPath(String proj){
    	Server.setNowProjectPath(proj);
    }
    
    public static String getNowProjectPath(){
    	return Server.getNowProjectPath();
    }

    public static void newWindow(){
        Thread th = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Comet comet = new Comet();
                    new Data().initData();
                    new UIController();
                    comet.installGui(JFrame.HIDE_ON_CLOSE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }
    
    public static void openProjectSetter(){
    	ProjectSetter.showWindow();
    }
    
    public static void openNukkitTest(){
    	NukkitTest.showWindow();
    }
    
    public static void initColor(){
    	color.init();
    }
    
    public static List<Color> getBlackColors(){
    	return color.getBlackColors();
    }
    
    public static void checkFile(){
    	new FileChecker().check();
    }
    
    public static boolean makeProject(String name, String type){
    	boolean bool = ProjectMaker.makeProject("./workspace/", name, type);
    	Controller.getProjectTab().update();
    	return bool;
    }
    
    public static void openFile(File file){
    	ProjectManager.openFile(file);
    }
    
    public static String getType(String path){
    	return Utils.getType(path);
    }
    
    public static String getProjectName(String path){
    	return Utils.getProjectName(path);
    }
    
    public static String getProjectPath(String path){
    	return Utils.getProjectPath(path);
    }
    
    public static String[] getPathArray(String path){
    	return Utils.getPathArray(path);
    }
    
    public static void information(String message){
    	MessagePopup.information(message);
    }
    
    public static void alert(String message){
    	MessagePopup.alert(message);
    }
    
    public static void error(String message){
    	MessagePopup.error(message);
    }

}
