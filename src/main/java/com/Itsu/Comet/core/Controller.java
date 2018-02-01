package com.Itsu.Comet.core;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.Itsu.Comet.editor.Java.AutoComplete;
import com.Itsu.Comet.gui.Editor;
import com.Itsu.Comet.gui.FileBar;
import com.Itsu.Comet.gui.NukkitTest;
import com.Itsu.Comet.gui.ProjectSetter;
import com.Itsu.Comet.gui.ProjectTab;
import com.Itsu.Comet.gui.TaskBar;
import com.Itsu.Comet.gui.View;
import com.Itsu.Comet.project.ProjectFile;
import com.Itsu.Comet.project.ProjectMaker;
import com.Itsu.Comet.project.ProjectManager;
import com.Itsu.Comet.utils.Colors;
import com.Itsu.Comet.utils.Data;
import com.Itsu.Comet.utils.MessagePopup;
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

public class Controller {

    private static Data data = new Data();
    private static Colors color = new Colors();
    private static TaskBar bar;
    private static FileBar fileBar;
    private static AutoComplete<Object> complete = new AutoComplete<>();

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

    public static void addComponent(String title, JComponent component){
        Server.addComponent(title, component);
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
    protected static void setTaskBar(TaskBar bar) {
        Controller.bar = bar;
    }

    public static void setStatusText(String text) {
        bar.setStatusText(text);
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

    public static void setOpenFiles(ProjectFile proj) {
        Server.setOpenFiles(proj);
    }

    public static void removeOpenFiles(ProjectFile proj) {
        Server.removeOpenFiles(proj);
    }

    public static boolean isOpenedFile(ProjectFile proj) {
        return Server.isOpenedFile(proj);
    }

    public static ProjectFile getProjectFileByPath(String path) {
        return Server.getProjectFileByPath(path);
    }

    public static ProjectFile getProjectFileByName(String name) {
        return Server.getProjectFileByName(name);
    }

    public static ProjectFile getProjectFileByIndex(int index) {
        return Server.getProjectFileByIndex(index);
    }
    
    public static void setFileBar(FileBar bar) {
    	fileBar = bar;
    }
    
    public static void setFileBarContent(File file) {
    	fileBar.addLabels(file);
    }

    public static ImageIcon getIcon(String filename) {
        return Utils.getIcon(filename);
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
        new NukkitTest();
    }

    public static void initSkinColor(){
        color.initSkinColor("BLACK");
    }

    public static void initJavaColor(){
        color.initJavaColor("BLACK");
    }

    public static void initPHPColor(){
        color.initPHPColor("BLACK");
    }

    public static Map<String, Color> getColors(){
        return color.getColors();
    }

    public static Map<String, Color> getJavaColors(){
        return color.getJavaColors();
    }

    public static Map<String, Color> getPHPColors(){
        return color.getPHPColors();
    }

    public static Map<String, Color> getTabColor(String type) {
        return color.getTabColor(type);
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

    public static void exception(String message, Exception e){
        MessagePopup.exception(message, e);
    }

    public static void error(String message){
        MessagePopup.error(message);
    }

    public static <T> List<String> autoComplete(Class<? extends T> clazz) {
        return complete.autoComplete(clazz);
    }

    public static Map<String, String> getMethods() {
        return complete.getMethods();
    }

    public static Map<String, String> getFields() {
        return complete.getFields();
    }

    public static String getToken(String key) {

        if(getFields().containsKey(key)) {
            return getFields().get(key);

        } else if(getMethods().containsKey(key)) {
            return getMethods().get(key);

        } else {
            return null;
        }

    }

}
