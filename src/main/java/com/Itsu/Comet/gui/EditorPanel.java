package com.Itsu.Comet.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

import com.Itsu.Comet.editor.IndentAction;
import com.Itsu.Comet.editor.LineNumberView;
import com.Itsu.Comet.editor.SyntaxHighliter;
import com.Itsu.Comet.ui.BlackScrollBarUI;
import com.Itsu.Comet.utils.EditorFont;

public class EditorPanel extends JScrollPane{

    private IndentAction ia = new IndentAction();
    private JTextPane jp = new JTextPane(){
    	@Override
    	public boolean getScrollableTracksViewportWidth() {
    		return false;
    	}
    };
    
    private boolean period = true;
    
    public EditorPanel(SyntaxHighliter jsh){
    	this(jsh, null);
    }

    public EditorPanel(SyntaxHighliter jsh, String text){
    	this.setViewportView(jp);
    	
    	if(jsh != null){
    		jp.setStyledDocument((StyledDocument) jsh);
    	}
    	
    	if(text != null){
    		jp.setText(text);
    	}
    	
    	jp.setPreferredSize(this.getMaximumSize());
        jp.setBackground(Color.WHITE);
        jp.setForeground(Color.BLACK);
        jp.setCaretColor(new Color(14, 139, 252));
        jp.setSelectionColor(new Color(25,118,210));
        jp.setSelectedTextColor(Color.WHITE);
        jp.setFont(new Font(new EditorFont().createEditorFont().getName(), Font.PLAIN, 14));
        jp.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                String insert = null;
                String str = null;
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ /*Enterキーが押されたら*/
                    e.consume(); /* 入力をキャンセル */
                    for(int i=0; i<ia.getTabSize(jp); i++){ insert += "\t"; }/*Tab数分の\tを加算*/
	                    insert = "\n" + insert; /*頭に改行を入れて*/
	                    insert = insert.replaceAll("null", "");
	                    jp.replaceSelection(insert);
                }
                /*else if(e.getKeyCode() == KeyEvent.VK_PERIOD){
                	if(period){
                		new AutoGUI(jp.getMousePosition().x, jp.getMousePosition().y);modelToView
                	}else{
                		period = true;
                	}
                }
                */
            }
                public void keyReleased(KeyEvent e){}
                public void keyTyped(KeyEvent e){}
        });

        FontMetrics fm = jp.getFontMetrics(jp.getFont());
        int charWidth = fm.charWidth('W');
        int tabLength = charWidth * 4;
        TabStop[] tabs = new TabStop[10];
        for (int j = 0; j < tabs.length; j++) {
          tabs[j] = new TabStop((j + 1) * tabLength);
        }
        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setTabSet(attrs, tabSet);
        int l = jp.getDocument().getLength();
        jp.getStyledDocument().setParagraphAttributes(0, l, attrs, false);

        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setAutoscrolls(true);
        this.setBackground(new Color(197,202,233));
        this.setForeground(Color.LIGHT_GRAY);
        this.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        this.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.setRowHeaderView(new LineNumberView(jp));
    }
    
}
