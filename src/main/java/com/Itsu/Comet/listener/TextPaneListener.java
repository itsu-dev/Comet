package com.Itsu.Comet.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.Itsu.Comet.gui.EditorPanel;

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
