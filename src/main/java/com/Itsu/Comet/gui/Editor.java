package com.Itsu.Comet.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.project.ProjectFile;
import com.Itsu.Comet.ui.SimpleTabbedPaneUI;
import com.Itsu.Comet.utils.Version;

public class Editor extends View{

    private SimpleTabbedPane tab;
    private int w;
    private int h;

    public Editor(){
        super("エディタ");

        w = (int) Controller.getJFrame().getWidth();
        h = (int) Controller.getJFrame().getHeight() - 90;

        this.setBackground(Controller.getColors().get("BACKGROUND"));
        this.setBounds((int) (w * 0.2), 0, (int) (w * 0.8), h);
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
		            Controller.getJFrame().setTitle("Comet " + Version.VERSION + " - " + projectFilePath);
					
					System.out.println(projectFilePath);
					
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
