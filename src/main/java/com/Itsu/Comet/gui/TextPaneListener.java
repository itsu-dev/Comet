package com.Itsu.Comet.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextPaneListener implements MouseListener {
	
	private EditorPanel pane;
	
	public TextPaneListener(EditorPanel pane) {
		this.pane = pane;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		pane.setAutoGUIVisible(false);
		
		if(e.getClickCount() == 1) {
			
			if(pane.getHighlighter() != null) {
				pane.removeHighlights();
			}
			
		} else if(e.getClickCount() == 2) {
			pane.changeHighlight();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

}
