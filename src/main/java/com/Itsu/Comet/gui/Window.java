package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.SimpleTabbedPaneUI;

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

public class Window extends View{

    private SimpleTabbedPane tab;
    private int w;
    private int h;
    
    public static final int PROJECT = 0;
    public static final int CONSOLE = 1;

    public Window(int... args){
        super("ビュー・ウィンドウ(1)");

        w = (int) Controller.getJFrame().getWidth();
        h = (int) Controller.getJFrame().getHeight() - 105;

        this.setBackground(Controller.getColors().get("EDITOR"));
        this.setBounds(0, 0, (int) (w * 0.2), h - 20);
        this.setView(this);
        this.setViewKey("View1");

        createGui();

        for(int list : args){
            switch(list){
                case PROJECT:
                	ProjectTab tab = new ProjectTab(Controller.getDataObject().getWorkspace());
                	Controller.setProjectTab(tab);
                    this.addTab("プロジェクト", tab);
            }
        }

        this.setVisible(true);
    }

    private void createGui(){
        tab = new SimpleTabbedPane();
        tab.setUI(new SimpleTabbedPaneUI());
        this.add(tab, BorderLayout.CENTER);
    }

    public void addTab(String title, JComponent comp){
        this.tab.addTab(title, comp);
    }
    
    public Component getTabByTitle(String title) {
    	String[] titles = getTabTitles();
    	int count = 0;
    	
    	for(String str : titles) {
    		if(str.equals(titles)) break;
    		count++;
    	}
    	
    	return tab.getTabComponentAt(count);
    }
    
    public String[] getTabTitles() {
    	List<String> titles = new ArrayList<>();
    	for(int i = 0;i < this.tab.getTabCount() - 1;i++) {
    		titles.add(this.tab.getTitleAt(i));
    	}
    	return titles.toArray(new String[0]);
    }
}
