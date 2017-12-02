package com.Itsu.Comet.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.AutoGUI;

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

public class ListListener implements MouseListener {
	
	private AutoGUI gui;
	
	public ListListener(AutoGUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 2) {
			String src = Controller.getToken((String) gui.getList().getSelectedValue());
			try {
				JTextPane pane = gui.getEditorPanel().getEditor();
				
				pane.getDocument().insertString(gui.getOffset() + 1, src, null);
				
				if(src.contains("(")) {
					
					int start = 0, end = 0;
					boolean isSplit = false;
					
					for(int i = pane.getCaretPosition(); i > 0;i--) {
						if(pane.getText().substring(i, i + 1).equals("(")) {
							start = i + 1;
							break;
							
						} else if(!isSplit && pane.getText().substring(i, i + 1).equals(" ")) {
							
							if(pane.getText().substring(i, i + 1).equals(" ") || pane.getText().substring(i, i + 1).equals(",")) {
								isSplit = true;
								continue;
							}
							
							start = i + 1;
							break;
						}
					}
					
					for(int i = start; i < pane.getText().length();i++) {
						if(pane.getText().substring(i, i + 1).equals(",")) {
							end = i;
							break;
							
						} else if(pane.getText().substring(i, i + 1).equals(" ")) {
							end = i;
							break;
							
						} else if(pane.getText().substring(i, i + 1).equals(")")) {
							end = i;
							break;
						}
					}
					
					pane.select(start, end);
				
				}
				
				gui.getEditorPanel().setCompleting(true);
				gui.setVisible(false);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
}
