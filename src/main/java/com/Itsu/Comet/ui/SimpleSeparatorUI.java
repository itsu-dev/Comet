package com.Itsu.Comet.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicSeparatorUI;

public class SimpleSeparatorUI extends BasicSeparatorUI {
	
	@Override
	public void paint(Graphics g, JComponent c) {
        Dimension s = c.getSize();

        if(((JSeparator) c).getOrientation() == JSeparator.VERTICAL){
        	g.setColor(new Color(33, 33, 33));
        	g.fillRect(0, 0, 3, s.height);
            g.setColor(new Color(66, 66, 66));
            g.drawLine(1, 0, 1, s.height);
          
        } else {
        	g.setColor(new Color(33, 33, 33));
        	g.fillRect(0, 0, s.width, 3);
            g.setColor(new Color(66, 66, 66));
            g.drawLine(0, 1, s.width, 1);
        }
	}

}
