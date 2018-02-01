package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.Itsu.Comet.core.Controller;

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

public class TaskBar extends JPanel {

    private JLabel label;
    private JTextField field;
    
    private JPanel panel;

    public TaskBar() {
        super();
        
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(500, 15));
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0, 0, 0, 0));
        
        label = new JLabel("準備完了");
        label.setForeground(Color.WHITE);
        
        field = new JTextField();
        field.setPreferredSize(new Dimension(200, 15));
        field.setFont(new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        field.setBorder(new LineBorder(Controller.getColors().get(5)));
        field.setBackground(Controller.getColors().get("EDITOR"));
        field.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        field.setCaretColor(new Color(14, 139, 252));
        field.setSelectionColor(new Color(25,118,210));
        field.setBounds(0, 0, 200, 30);
        field.addKeyListener(new KeyListener() {
        	
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					EditorPanel pane = (EditorPanel) Controller.getEditor().getTab().getSelectedComponent();
					pane.changeHighlight(field.getText());
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
        	
        });
        
        panel.add(field, BorderLayout.EAST);

        this.setBackground(new Color(0, 128, 255));
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, Controller.getDataObject().getWindowWidth(), 30);
        this.add(label, BorderLayout.WEST);
        this.add(panel, BorderLayout.EAST);
    }

    public void setStatusText(String text) {
        label.setText(text);
    }

}
