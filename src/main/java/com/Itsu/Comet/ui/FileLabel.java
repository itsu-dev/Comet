package com.Itsu.Comet.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class FileLabel extends JLabel {
	
	public FileLabel(String str) {
		super(str);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(33, 33, 33));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(224, 224, 224));
		g.drawString(this.getText(), 15, 15);
		g.setColor(new Color(66, 66, 66));
		g.drawLine(this.getWidth() - 11, 0, this.getWidth() - 1, this.getHeight() / 2);
		g.drawLine(this.getWidth() - 1, this.getHeight() / 2, this.getWidth() - 11, this.getHeight());
	}

}
