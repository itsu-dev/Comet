package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.project.ProjectFile;
import com.Itsu.Comet.ui.SimpleTabbedPaneUI;
import com.Itsu.Comet.utils.Version;

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

public class Editor extends View{

    private SimpleTabbedPane tab;
    private int w;
    private int h;

    public Editor(){
        super("エディタ");

        w = (int) Controller.getJFrame().getWidth();
        h = (int) Controller.getJFrame().getHeight() - 105;

        this.setBackground(Controller.getColors().get("EDITOR"));
        this.setBounds((int) (w * 0.2), 0, (int) (w * 0.8), h - 20);
        this.setView(this);
        this.setViewKey("Editor");

        createGui();

        this.setVisible(true);
    }

    private void createGui(){
        tab = new SimpleTabbedPane();
        tab.setUI(new SimpleTabbedPaneUI());
        tab.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				try{
					
					ProjectFile proj = Controller.getProjectFileByIndex(tab.getSelectedIndex());
					
					if(proj == null) {
			            Controller.getJFrame().setTitle("Comet " + Version.VERSION);
						return;
					}
					
					String projectName = proj.getName();
					String projectFilePath = proj.getPath();
					
					Controller.setNowProject(projectName);
					Controller.setNowProjectPath(projectFilePath);
					Controller.setFileBarContent(new File(projectFilePath));
		            Controller.getJFrame().setTitle("Comet " + Version.VERSION + " - " + projectFilePath);
					
				}catch(ArrayIndexOutOfBoundsException ex){
					return;
				}
			}
        });

        this.add(tab, BorderLayout.CENTER);
    }

    public void addTab(String title, String path, JComponent comp){
    	try{
	    	if(!Controller.isOpenedFile(Controller.getProjectFileByPath(path))){
	    		return;
	    	}else{
		        this.tab.addTab(title, comp);
		        return;
	    	}
    	}catch(NullPointerException e) {
    		return;
    	}
    }

    public JTabbedPane getTab(){
        return this.tab;
    }
}
