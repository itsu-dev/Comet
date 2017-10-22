package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.SimpleTabbedPaneUI;

public class Editor extends View{

    private JTabbedPane tab;
    private int w;
    private int h;

    private List<String> tabs = new ArrayList<String>();

    public Editor(){
        super("エディタ");

        w = (int) Controller.getJFrame().getWidth();
        h = (int) Controller.getJFrame().getHeight() - 90;

        this.setBackground(Color.WHITE);
        this.setBounds((int) (w * 0.2), 0, (int) (w * 0.8), h);
        this.setView(this);
        this.setViewKey("Editor");

        createGui();

        this.setVisible(true);
    }

    private void createGui(){
        tab = new JTabbedPane();
        tab.setUI(new SimpleTabbedPaneUI());
        /*
        tab.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				try{
					int i = 0;
					
					for(String str : tabs){
						if(str.endsWith(tab.getTitleAt(tab.getSelectedIndex()))){
							break;
						}
						i++;
					}
					
					System.out.println(i);
					String temp[] = tabs.get(i).replaceAll("\\\\", "/").split("/");
					
					String projectName = null;
					int count = 0;
					
					for(String str : temp){
						if(str.equals("workspace")){
							projectName = temp[count + 1];
							break;
						}
						count++;
					}
					
					Controller.setNowProject(projectName);
					
					System.out.println(projectName);
				}catch(ArrayIndexOutOfBoundsException ex){
					return;
				}
			}
        });
        */

        this.add(tab, BorderLayout.CENTER);
    }

    public void addTab(String title, String path, JComponent comp){
    	if(tabs.contains(path)){
    		return;
    	}else{
	        this.tab.addTab(title, comp);
	        tabs.add(path);
	        return;
    	}
    }

    public JTabbedPane getTab(){
        return this.tab;
    }
}
