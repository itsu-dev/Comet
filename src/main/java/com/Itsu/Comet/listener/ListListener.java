package com.Itsu.Comet.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.text.BadLocationException;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.AutoGUI;

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
				gui.getEditorPanel().getEditor().getDocument().insertString(gui.getOffset() + 1, src, null);
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
