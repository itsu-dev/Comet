package com.Itsu.Comet.editor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.Element;

import com.Itsu.Comet.core.Controller;

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

public class LineNumberView extends JComponent {
    private static final int MARGIN = 5;
    private final JTextPane textArea;
    private final FontMetrics fontMetrics;
    //private final int topInset;
    private final int fontAscent;
    private final int fontHeight;
    private final int fontDescent;
    private final int fontLeading;

    public LineNumberView(JTextPane jp) {
        super();
        this.textArea = jp;
        Font font   = jp.getFont();
        fontMetrics = getFontMetrics(font);
        fontHeight  = fontMetrics.getHeight();
        fontAscent  = fontMetrics.getAscent();
        fontDescent = fontMetrics.getDescent();
        fontLeading = fontMetrics.getLeading();
        //topInset    = textArea.getInsets().top;

        jp.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                repaint();
            }
            @Override public void removeUpdate(DocumentEvent e) {
                repaint();
            }
            @Override public void changedUpdate(DocumentEvent e) { /* not needed */ }
        });
        jp.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
        Insets i = jp.getInsets();
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 1, Controller.getColors().get("LINE_NUMBER")),
            BorderFactory.createEmptyBorder(i.top, MARGIN, i.bottom, MARGIN - 1)));
        setOpaque(true);
        setBackground(Controller.getColors().get("EDITOR"));
        setForeground(Controller.getColors().get("LINE_NUMBER"));
        setFont(font);
    }
    private int getComponentWidth() {
        Document doc  = textArea.getDocument();
        Element root  = doc.getDefaultRootElement();
        int lineCount = root.getElementIndex(doc.getLength());
        int maxDigits = Math.max(3, String.valueOf(lineCount).length());
        Insets i = getBorder().getBorderInsets(this);
        return maxDigits * fontMetrics.stringWidth("0") + i.left + i.right;
        //return 48;
    }
    private int getLineAtPoint(int y) {
        Element root = textArea.getDocument().getDefaultRootElement();
        int pos = textArea.viewToModel(new Point(0, y));
        return root.getElementIndex(pos);
    }
    @Override public Dimension getPreferredSize() {
        return new Dimension(getComponentWidth(), textArea.getHeight());
    }
    @Override protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        Rectangle clip = g.getClipBounds();
        g.fillRect(clip.x, clip.y, clip.width, clip.height);

        g.setColor(getForeground());
        int base  = clip.y;
        int start = getLineAtPoint(base);
        int end   = getLineAtPoint(base + clip.height);
        int y     = start * fontHeight + 3;
        int rmg   = getBorder().getBorderInsets(this).right;
        for (int i = start; i <= end; i++) {
            String text = String.valueOf(i + 1);
            int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
            y += fontAscent - 2;
            g.drawString(text, x, y);
            y += fontDescent + fontLeading;
        }
    }
}

