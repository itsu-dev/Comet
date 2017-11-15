package com.Itsu.Comet.gui;

import java.awt.Color;

import javax.swing.JFrame;

public class AutoGUI extends JFrame{
	
	public AutoGUI(int x, int y){
		super();
		this.setUndecorated(true);
		this.setBounds(x, y, 300, 500);
		this.setBackground(Color.LIGHT_GRAY);
		this.setVisible(true);
	}

}
