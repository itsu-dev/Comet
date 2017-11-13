package com.Itsu.Comet.ui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import sun.swing.SwingUtilities2;

public class SimpleTabbedPaneUI extends BasicTabbedPaneUI{
	
	private static final int TAB_WIDTH = 100;
	
    private static final Color TAB_COLOR = new Color(66, 66, 66);
    private static final Color TAB_FRAME_COLOR = new Color(33, 33, 33);
    
    private static final int TAB_BORDER_SIZE = 5;
    private static final int TAB_HEIGHT = 40;
	
	@Override
	public void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
		Rectangle tabRect = rects[tabIndex];
		ComponentOrientation orientation = ComponentOrientation.getOrientation(tabPane.getLocale());
		
        int x = tabRect.x + TAB_BORDER_SIZE;
        int y = tabRect.y + TAB_BORDER_SIZE;
        int w = tabRect.width;
        int h = tabRect.height + TAB_HEIGHT;

        String title = tabPane.getTitleAt(tabIndex);
        Font font = tabPane.getFont();
        FontMetrics metrics = SwingUtilities2.getFontMetrics(tabPane, g, font);
        Icon icon = getIconForTab(tabIndex);
        int selectedIndex = tabPane.getSelectedIndex();
        boolean isSelected = selectedIndex == tabIndex;
        String clippedTitle = null;
        Rectangle newTextRect = new Rectangle(textRect.x, textRect.y, textRect.width, textRect.height);

        layoutLabel(tabPlacement, metrics, tabIndex, title, icon, tabRect, iconRect, newTextRect, isSelected);

        if (tabPane.getTabComponentAt(tabIndex) == null) {
            clippedTitle = title;

            paintText(g, tabPlacement, font, metrics,
                    tabIndex, clippedTitle, newTextRect, isSelected);

            paintIcon(g, tabPlacement, tabIndex, icon, iconRect, isSelected);
        }
        
        paintFocusIndicator(g, tabPlacement, rects, tabIndex, iconRect, newTextRect, isSelected);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(TAB_COLOR);
        g2.fillRoundRect(x, y, w, h, 10, 10);
        g2.setColor(TAB_FRAME_COLOR);
        g2.drawRoundRect(x, y, w, h, 10, 10);
        
        paintText(g, tabPlacement, font, metrics, tabIndex, clippedTitle, newTextRect, isSelected);
        paintIcon(g, tabPlacement, tabIndex, icon, iconRect, isSelected);
	}
	
	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
		return Math.max(TAB_WIDTH, super.calculateTabWidth(tabPlacement, tabIndex, metrics));
	}
	
}
