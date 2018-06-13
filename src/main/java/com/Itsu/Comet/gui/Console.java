package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.ui.BlackScrollBarUI;
import com.Itsu.Comet.ui.RotateTextLabelUI;

public class Console extends JPanel {
	
	private JTextArea area;
	private JScrollPane pane;
	private JLabel statusBar;
	
	public Console(String status, String text) {		
		this.setLayout(new BorderLayout());
		this.setBackground(Controller.getColors().get("EDITOR"));
		this.setForeground(Controller.getColors().get("EDITOR_TEXT"));
		this.setBorder(null);
		this.setPreferredSize(new Dimension(320, 500));
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
		area.setBackground(Controller.getColors().get("EDITOR"));
		area.setForeground(Controller.getColors().get("EDITOR_TEXT"));
		area.setText(text);
		area.setLineWrap(true);
		area.setPreferredSize(new Dimension(300, 500));
		area.setBorder(null);
		
		pane = new JScrollPane(area);
		pane.setBackground(Controller.getColors().get("EDITOR"));
		pane.setForeground(Controller.getColors().get("EDITOR_TEXT"));
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setAutoscrolls(true);
		pane.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
		pane.getVerticalScrollBar().setUI(new BlackScrollBarUI());
		
		statusBar = new JLabel();
		statusBar.setFont(new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 15));
		statusBar.setBackground(Controller.getColors().get("EDITOR"));
		statusBar.setForeground(new Color(0, 128, 255));
		statusBar.setBorder(null);
		statusBar.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_LEFT));
		statusBar.setText(status);
		statusBar.setSize(15, 800);
		
		this.add(statusBar, BorderLayout.WEST);
		this.add(pane, BorderLayout.CENTER);
	}
	
	public void appendString(String str) {
		area.append("> " + str + "\n");
	}
	
	public void reset() {
		area.setText("");
	}
	
	public void end() {
		statusBar.setText("<終了> " + statusBar.getText());
	}

}
