package com.Itsu.Comet.ui;

/**
 * 
 * <h6>Comet project</h6>
 * <p>for PMMP/Jupiter/Nukkit plugin
 * 
 * <p>Java（PHP）構文向けIDEプロジェクト
 * <p>Made by Itsu(Twitter: @itsu_dev)
 * 
 * This ui was made by:
 * http://ui-ideas.blogspot.jp/2012/06/mac-os-x-mountain-lion-scrollbars-in.html
 * 
 * Forked by Itsu
 * 
 * @author Itsu
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class BlackScrollBarUI extends BasicScrollBarUI {
	
    private static final int SCROLL_BAR_ALPHA_ROLLOVER = 150;
    private static final int SCROLL_BAR_ALPHA = 100;
    private static final int THUMB_BORDER_SIZE = 5;
    private static final int THUMB_SIZE = 8;
    private static final Color THUMB_COLOR = Color.WHITE;
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new MyScrollBarButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new MyScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
    	g.setColor(new Color(33, 33, 33));
    	g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        int alpha = isThumbRollover() ? SCROLL_BAR_ALPHA_ROLLOVER : SCROLL_BAR_ALPHA;
        int orientation = scrollbar.getOrientation();
        int x = thumbBounds.x + THUMB_BORDER_SIZE;
        int y = thumbBounds.y + THUMB_BORDER_SIZE;

        int width = orientation == JScrollBar.VERTICAL ?
                THUMB_SIZE : thumbBounds.width - (THUMB_BORDER_SIZE * 2);
        width = Math.max(width, THUMB_SIZE);

        int height = orientation == JScrollBar.VERTICAL ?
                thumbBounds.height - (THUMB_BORDER_SIZE * 2) : THUMB_SIZE;
        height = Math.max(height, THUMB_SIZE);

        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(new Color(THUMB_COLOR.getRed(),
                THUMB_COLOR.getGreen(), THUMB_COLOR.getBlue(), alpha));
        graphics2D.fillRect(x, y, width, height);
        graphics2D.dispose();
    }
}

class MyScrollBarButton extends JButton {
    MyScrollBarButton() {
        setOpaque(false);
        setFocusable(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(33, 33, 33));
    }
}

