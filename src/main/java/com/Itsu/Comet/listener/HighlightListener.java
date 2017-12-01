package com.Itsu.Comet.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
