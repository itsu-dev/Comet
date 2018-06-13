package com.Itsu.Comet.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.FileLabel;

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

public class FileBar extends JPanel {
	
	private String[] data;
	private Font font = new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12);
	
	public FileBar() {
		this.setBackground(Controller.getColors().get("MENU_BAR"));
		//this.setBounds(0, 0, Controller.getDataObject().getWindowWidth(), 30);
		this.setPreferredSize(new Dimension(Controller.getDataObject().getWindowWidth(), 20));
		this.setLayout(null);
		
		JLabel label = new JLabel("There is a no project opened.");
		label.setFont(font);
		label.setBackground(Controller.getColors().get("MENU_BAR"));
		label.setForeground(Controller.getColors().get("EDITOR_TEXT"));
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setBounds(0, 0, 300, 20);
		this.add(label);
	}
	
	public void addLabels(File file) {
		this.removeAll();
		String[] data = file.getAbsolutePath().replaceAll("\\\\", "/").split("/");
		int x = 0;
		
		for(String str : data) {
			FileLabel label = new FileLabel(str);
			label.setFont(font);
			label.setBackground(Controller.getColors().get("MENU_BAR"));
			label.setHorizontalAlignment(JLabel.LEFT);
			
			FontMetrics fm = getFontMetrics(label.getFont());
			int width = fm.stringWidth(str) + 40;
			
			label.setBounds(x, 0, width, 20);
			label.repaint();
			this.add(label);
			
			x += width;
		}
		
		this.revalidate();
		this.repaint();
	}
}
