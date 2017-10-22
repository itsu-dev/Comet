package com.Itsu.Comet.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.Itsu.Comet.ui.BlackScrollBarUI;

public class ImagePanel extends JScrollPane{
	
	private JPanel panel = new JPanel();
	private JLabel label;
	
	public ImagePanel(BufferedImage img){
		label = new JLabel(new ImageIcon(img));
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		
		this.setViewportView(label);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setAutoscrolls(true);
        this.setBackground(new Color(197,202,233));
        this.setForeground(Color.LIGHT_GRAY);
        this.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        this.getHorizontalScrollBar().setUnitIncrement(25);
        this.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.getVerticalScrollBar().setUnitIncrement(25);
	}

}
