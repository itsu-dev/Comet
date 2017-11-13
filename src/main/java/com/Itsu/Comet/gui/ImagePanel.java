package com.Itsu.Comet.gui;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.BlackScrollBarUI;

public class ImagePanel extends JScrollPane{
	
	private JPanel panel = new JPanel();
	private JLabel label;
	
	public ImagePanel(BufferedImage img){
		label = new JLabel(new ImageIcon(img));
		label.setBackground(Controller.getColors().get("EDITOR"));
		label.setForeground(Controller.getColors().get("EDITOR"));
		
		this.setViewportView(label);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setAutoscrolls(true);
        this.setBackground(Controller.getColors().get("EDITOR"));
        this.setForeground(Controller.getColors().get("EDITOR"));
        this.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        this.getHorizontalScrollBar().setUnitIncrement(25);
        this.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.getVerticalScrollBar().setUnitIncrement(25);
	}

}
