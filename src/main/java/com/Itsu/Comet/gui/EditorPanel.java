package com.Itsu.Comet.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
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
import com.Itsu.Comet.listener.HighlightListener;
import com.Itsu.Comet.ui.BlackScrollBarUI;
import com.Itsu.Comet.utils.EditorFont;

public class EditorPanel extends JScrollPane{

    private final transient Highlighter.HighlightPainter currentPainter   = new DefaultHighlighter.DefaultHighlightPainter(new Color(56, 250, 46));
    private final transient Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(87, 253, 203));

    private int current;

    private AutoGUI autoGUI;

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

        jp.setPreferredSize(this.getMaximumSize());
        jp.setBackground(Controller.getColors().get("EDITOR"));
        jp.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        jp.setCaretColor(new Color(14, 139, 252));
        jp.setSelectionColor(new Color(25,118,210));
        jp.setSelectedTextColor(Color.WHITE);
        jp.setFont(new Font(new EditorFont().createEditorFont().getName(), Font.PLAIN, 14));
        jp.setComponentPopupMenu(new TextPanePopup());
        jp.addMouseListener(new TextPaneListener(this));
        jp.getDocument().addDocumentListener(new HighlightListener(this));
        jp.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                String insert = null;
                String str = null;
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    for(int i=0; i<ia.getTabSize(jp); i++){
                        insert += "\t";
                    }
                        insert = "\n" + insert;
                        insert = insert.replaceAll("null", "");
                        jp.replaceSelection(insert);

                } else if(e.getKeyCode() == KeyEvent.VK_PERIOD){
                    if(period){
                        Point point = jp.getCaret().getMagicCaretPosition();

                        autoGUI.setLocation(Controller.getEditor().getX() + 50 + point.x, Controller.getEditor().getY() + 133 + point.y);
                        autoGUI.setOffset(jp.getCaretPosition());
                        autoGUI.setVisible(true);

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
    }

    private Optional<Pattern> getPattern() {
        String text = jp.getSelectedText();

        if (Objects.isNull(text) || text.isEmpty()) {
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

    public void changeHighlight() {
        highlighter = jp.getHighlighter();
        highlighter.removeAllHighlights();

        Document doc = jp.getDocument();
        getPattern().ifPresent(pattern -> {
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
            current = -1;
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

}
