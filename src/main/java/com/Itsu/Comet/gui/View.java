package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.Itsu.Comet.listener.DragWindowListener;

public class View extends JInternalFrame{
	
	private JPanel titlebar;
	private JLabel title;
	private DragWindowListener wl = new DragWindowListener();
	
	public View(String titleText){
		this.setBorder(BorderFactory.createLineBorder(new Color(48,63,159)));
		this.setLayout(new BorderLayout());
		this.setClosable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setTitle(titleText);
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);	
		
		title = new JLabel(this.getTitle());
		title.setForeground(Color.WHITE);
		title.setSize(300, 15);
		
		
		titlebar = new JPanel();
		titlebar.setMaximumSize(new Dimension(this.getWidth(), 15));
		titlebar.setBackground(new Color(121,134,203));
		titlebar.setLayout(new FlowLayout(FlowLayout.LEFT));
		titlebar.add(title);
		titlebar.addMouseListener(wl);
		titlebar.addMouseMotionListener(wl);
		
		this.add(titlebar, BorderLayout.NORTH);
	}
	
	public void setView(View frame){
		this.wl.setJInternalFrame(frame);
	}
	
	public void setViewKey(String key){
		this.wl.setJInternalFrameKey(key);
	}

}
