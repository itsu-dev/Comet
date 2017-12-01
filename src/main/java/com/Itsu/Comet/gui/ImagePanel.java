package com.Itsu.Comet.gui;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.BlackScrollBarUI;

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

public class ImagePanel extends JScrollPane{
	
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
