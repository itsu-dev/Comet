package com.Itsu.Comet.ui;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.text.View;

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
 * This source was developed by http://qoofast.blog76.fc2.com/blog-entry-10.html.
 * Customized JLavel UI which rotate text right or left.
 * @author Q's Lab (qoofast)
 */

public class RotateTextLabelUI extends BasicLabelUI {
    private static Rectangle paintIconR = new Rectangle();
    private static Rectangle paintTextR = new Rectangle();
    private static Rectangle paintViewR = new Rectangle();
    private static Insets paintViewInsets = new Insets(0, 0, 0, 0);

    public static final int ROTATE_RIGFHT = 1;
    public static final int ROTATE_LEFT = 3;

    private int rotate = ROTATE_LEFT;
    public RotateTextLabelUI(int rotate) {
        super();
        setRotate(rotate);
    }

    public void setRotate(int rotate){
        this.rotate = rotate;
    }

    public int getRotate(){
        return this.rotate;
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        return new Dimension(d.height, d.width);
    }

    public void paint(Graphics g, JComponent c) {
        JLabel label = (JLabel) c;
        String text = label.getText();
        Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();

        if ((icon == null) && (text == null)) {
            return;
        }

        FontMetrics fm = g.getFontMetrics();
        Insets insets = c.getInsets(new Insets(0, 0, 0, 0));

        paintViewR.y = insets.left;
        paintViewR.x = insets.top;
        paintViewR.height = c.getWidth() - (insets.left + insets.right);
        paintViewR.width = c.getHeight() - (insets.top + insets.bottom);

        paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
        paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

        String clippedText = layoutCL(label, fm, text, icon, paintViewR, paintIconR, paintTextR);

        if (icon != null) {
            icon.paintIcon(c, g, paintIconR.y, paintIconR.x);
        }
        //System.out.print(paintViewR);
        //System.out.println(paintTextR);

        ((Graphics2D) g).rotate(Math.PI * rotate / 2);

        if (text != null) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);

            if (v != null) {
                v.paint(g, paintTextR);
            } else {
                int textX;
                int textY;
                if(rotate == ROTATE_LEFT){
                    textX = -paintTextR.x-paintTextR.width;
                    textY = +paintTextR.y+fm.getAscent();
                }else{//rotate == ROTATE_RIGHT
                    textX = paintTextR.x;
                    textY = -paintTextR.y-fm.getDescent();
                }

                if (label.isEnabled()) {
                    paintEnabledText(label, g, clippedText, textX, textY);
                } else {
                    paintDisabledText(label, g, clippedText, textX, textY);
                }
            }
        }
    }
}