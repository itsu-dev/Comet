package com.Itsu.Comet.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.editor.IndentAction;
import com.Itsu.Comet.editor.LineNumberView;
import com.Itsu.Comet.editor.SyntaxHighliter;
import com.Itsu.Comet.editor.Java.JavaParser;
import com.Itsu.Comet.editor.Java.JavaVariable;
import com.Itsu.Comet.listener.HighlightListener;
import com.Itsu.Comet.listener.TextPaneListener;
import com.Itsu.Comet.ui.BlackScrollBarUI;
import com.Itsu.Comet.utils.EditorFont;

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

public class EditorPanel extends JScrollPane{

    private final transient Highlighter.HighlightPainter currentPainter   = new DefaultHighlighter.DefaultHighlightPainter(new Color(56, 250, 46));
    private final transient Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(87, 253, 203));

    private AutoGUI autoGUI;
    private JavaParser parser;

    private Highlighter highlighter;

    private IndentAction ia = new IndentAction();
    private LineNumberView view;
    private JTextPane jp = new JTextPane(){
        @Override
        public boolean getScrollableTracksViewportWidth() {
            return false;
        }
    };

    private boolean period = true;
	private boolean completing = false;

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

        view = new LineNumberView(jp);
        autoGUI = new AutoGUI(this);
        parser = new JavaParser(jp);

        jp.setPreferredSize(this.getMaximumSize());
        jp.setBackground(Controller.getColors().get("EDITOR"));
        jp.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        jp.setCaretColor(new Color(14, 139, 252));
        jp.setSelectionColor(new Color(25,118,210));
        jp.setSelectedTextColor(Color.WHITE);
        jp.setFont(new Font(new EditorFont().createEditorFont().getName(), Font.PLAIN, 14));
        jp.setComponentPopupMenu(new TextPanePopup(this));
        jp.addMouseListener(new TextPaneListener(this));
        jp.getDocument().addDocumentListener(new HighlightListener(this));
        jp.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
            	
            	e.consume();

                String insert = null;
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	
                	if(completing) {
                		
                		int offset = jp.getCaretPosition();
                		int start = 0, end = 0;
                		boolean next = false;
                		
                		for(int i = offset;i < jp.getText().length();i++) {
                			String str = jp.getText().substring(i, i + 1);
                			
                			if(str.equals(")")) {
                				jp.setCaretPosition(i + 1);
                				next = false;
                				completing = false;
                				break;
                				
                			} else if(str.equals(",") || str.equals(" ")) {
                				continue;
                				
                			} else {
                				start = i;
                				for(int ii = start;ii < jp.getText().length();ii++) {
                					
                					String str1 = jp.getText().substring(ii, ii + 1);
                					if(str1.equals(",") || str1.equals(" ") || str1.equals(")")) {
                						end = ii;
                						break;
                						
                					} else {
                						continue;
                					}
                					
                				}
                				
                				for(int ii = end;ii < jp.getText().length();ii++) {
                					
                					String str1 = jp.getText().substring(ii, ii + 1);
                					if(str1.equals(",")) {
                						next = true;
                						break;
                						
                					} else if(str1.equals(" ")) {
                						continue;
                						
                					}
                					
                				}	
                				
                				jp.select(start, end);
                				
                				if(!next) {
                					completing = false;
                				}
                				
                				break;
                			}
                			
                		}
                		
                		return;
                		
                	}
                	
                    for(int i=0; i<ia.getTabSize(jp); i++){
                        insert += "\t";
                    }
                        insert = "\n" + insert;
                        insert = insert.replaceAll("null", "");
                        jp.replaceSelection(insert);

                } else if(e.getKeyCode() == KeyEvent.VK_PERIOD){
                    if(period){
                        try{
                            Point point = jp.getCaret().getMagicCaretPosition();

                            parse();

                            autoGUI.setLocation(Controller.getEditor().getX() + 50 + point.x, Controller.getEditor().getY() + 133 + point.y);
                            autoGUI.setOffset(jp.getCaretPosition());

                            String target = "";
                            int pos = jp.getCaretPosition() - 1;

                            for(int i = pos;i > 0;i--) {
                                String str = jp.getText().substring(i, i + 1);
                                if(str.equals(" ") || str.equals("\t") || str.equals("\n")) break;
                                else target += str;
                            }

                            target = new StringBuffer(target).reverse().toString();

                            for(JavaVariable v : parser.getFields()) {
                                if(v.getName().equals(target)) {
                                    autoGUI.setListData(Controller.autoComplete(Class.forName(v.getClassName())));
                                    autoGUI.setVisible(true);
                                }
                            }

                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }

                    }else{
                        period = true;
                    }

                }
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
        this.setRowHeaderView(view);

        parse();
    }

    private Optional<Pattern> getPattern(String target) {
        String text = target;

        if (text == null || text.isEmpty()) {
            return Optional.empty();
        }
        String cw = "\\b";
        String pattern = String.format("%s%s%s", cw, text, cw);
        int flags = 0;
        try {
            return Optional.of(Pattern.compile(pattern, flags));
        } catch (PatternSyntaxException ex) {
            return Optional.empty();
        }
    }

    public void changeHighlight(String target) {
        highlighter = jp.getHighlighter();
        highlighter.removeAllHighlights();

        Document doc = jp.getDocument();
        getPattern(target).ifPresent(pattern -> {
            try {
                Matcher matcher = pattern.matcher(doc.getText(0, doc.getLength()));
                int pos = 0;
                while (matcher.find(pos)) {
                    int start = matcher.start();
                    int end   = matcher.end();
                    highlighter.addHighlight(start, end, highlightPainter);
                    pos = end;
                }
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        });

        Highlighter.Highlight[] array = highlighter.getHighlights();
        int hits = array.length;
        if (hits == 0) {
            removeHighlights();
        } else {
            Highlighter.Highlight hh = highlighter.getHighlights()[0];
            highlighter.removeHighlight(hh);
            try {
                highlighter.addHighlight(hh.getStartOffset(), hh.getEndOffset(), currentPainter);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeHighlights() {
        highlighter.removeAllHighlights();
    }

    public Highlighter getHighlighter() {
        return this.highlighter;
    }

    public void setAutoGUIVisible(boolean b) {
        if(this.autoGUI != null) this.autoGUI.setVisible(b);
    }

    public JTextPane getEditor() {
        return this.jp;
    }

    public JavaParser getParser() {
        return this.parser;
    }

    public void parse() {
        parser.parse();
    }

    
    public void setCompleting(boolean b) {
    	this.completing = b;
    }

}
