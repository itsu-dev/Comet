package com.Itsu.Comet.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.Itsu.Comet.gui.EditorPanel;

public class HighlightListener implements DocumentListener {
	
	private EditorPanel pane;
	
	public HighlightListener(EditorPanel pane) {
		this.pane = pane;
	}
	
    @Override public void changedUpdate(DocumentEvent e) { /* not needed */ }
    
    @Override public void insertUpdate(DocumentEvent e) {
        pane.changeHighlight();
    }
    
    @Override public void removeUpdate(DocumentEvent e) {
        pane.changeHighlight();
    }
    
}
