package com.Itsu.Comet.editor;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.Itsu.Comet.core.Controller;

public class JavaSyntaxHighliter extends DefaultStyledDocument implements SyntaxHighliter{
    private static final char LB = '\n';
    private static final String OPERANDS = ".,(){;";

    private MutableAttributeSet keyword;
    private MutableAttributeSet text;
    private MutableAttributeSet annotation;
    private MutableAttributeSet comment;
    private MutableAttributeSet oneLineCom;
    private MutableAttributeSet multiLineCom;
    private MutableAttributeSet returnToken;
    private Set<String> keywords;
    private final Style nomal = getStyle(StyleContext.DEFAULT_STYLE);

    private boolean multi = false;
    private int multiOff = 0;

    private JTextPane pane;

    public JavaSyntaxHighliter() {
        super();

        //System token
        keyword = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword, Controller.getJavaColors().get("keyword"));
        StyleConstants.setBold(keyword, true);

        annotation = new SimpleAttributeSet();
        StyleConstants.setForeground(annotation, Controller.getJavaColors().get("annotation"));

        //"return" token
        returnToken = new SimpleAttributeSet();
        StyleConstants.setForeground(returnToken, Controller.getJavaColors().get("returnToken"));
        StyleConstants.setUnderline(returnToken, true);
        StyleConstants.setBold(returnToken, true);

        //comment token
        comment = new SimpleAttributeSet();
        StyleConstants.setForeground(comment, Controller.getJavaColors().get("comment"));

        //one line commentout
        oneLineCom = new SimpleAttributeSet();
        StyleConstants.setForeground(oneLineCom, Controller.getJavaColors().get("oneLineCom"));

        //multi line commentout
        multiLineCom = new SimpleAttributeSet();
        StyleConstants.setForeground(multiLineCom, Controller.getJavaColors().get("multiLineCom"));

        //normal text
        text = new SimpleAttributeSet();
        StyleConstants.setForeground(text, Controller.getJavaColors().get("text"));

        keywords = new HashSet<String>();
        keywords.add( "abstract" );
        keywords.add( "boolean" );
        keywords.add( "break" );
        keywords.add( "byte" );
        keywords.add( "byvalue" );
        keywords.add( "case" );
        keywords.add( "cast" );
        keywords.add( "catch" );
        keywords.add( "char" );
        keywords.add( "class" );
        keywords.add( "const" );
        keywords.add( "continue" );
        keywords.add( "default" );
        keywords.add( "do" );
        keywords.add( "double" );
        keywords.add( "else" );
        keywords.add( "extends" );
        keywords.add( "false" );
        keywords.add( "final" );
        keywords.add( "finally" );
        keywords.add( "float" );
        keywords.add( "for" );
        keywords.add( "future" );
        keywords.add( "generic" );
        keywords.add( "goto" );
        keywords.add( "if" );
        keywords.add( "implements" );
        keywords.add( "import" );
        keywords.add( "inner" );
        keywords.add( "instanceof" );
        keywords.add( "int" );
        keywords.add( "interface" );
        keywords.add( "long" );
        keywords.add( "native" );
        keywords.add( "new" );
        keywords.add( "null" );
        keywords.add( "operator" );
        keywords.add( "outer" );
        keywords.add( "package" );
        keywords.add( "private" );
        keywords.add( "protected" );
        keywords.add( "public" );
        keywords.add( "rest" );
        keywords.add( "return" );
        keywords.add( "short" );
        keywords.add( "static" );
        keywords.add( "super" );
        keywords.add( "switch" );
        keywords.add( "synchronized" );
        keywords.add( "this" );
        keywords.add( "throw" );
        keywords.add( "throws" );
        keywords.add( "transient" );
        keywords.add( "true" );
        keywords.add( "try" );
        keywords.add( "var" );
        keywords.add( "void" );
        keywords.add( "volatile" );
        keywords.add( "while" );

        //utils:
        //StyleConstants.setForeground(addStyle("//TODO",  def), O);
        /*
        StyleConstants.setForeground(addStyle("@param",  nomal), O);
        StyleConstants.setForeground(addStyle("@author",  nomal), O);
        StyleConstants.setForeground(addStyle("@description",  nomal), O);
        StyleConstants.setForeground(addStyle("@unchecked",  nomal), O);
        StyleConstants.setForeground(addStyle("@Override",  nomal), O);
        StyleConstants.setForeground(addStyle("@see",  nomal), O);
        StyleConstants.setForeground(addStyle("@link",  nomal), O);
        StyleConstants.setForeground(addStyle("@code",  nomal), O);
        StyleConstants.setForeground(addStyle("@deprecated",  nomal), O);
        StyleConstants.setForeground(addStyle("@return",  nomal), O);
        */
    }

    @Override public void insertString(int offset, String text, AttributeSet a) throws BadLocationException {
        // @see PlainDocument#insertString(...)
        int length = 0;
        String str = text;
        if (!(str == null) && str.indexOf(LB) >= 0) {
            StringBuilder filtered = new StringBuilder(str);
            str = filtered.toString();
            length = str.length();
        }
        super.insertString(offset, str, a);
        processChangedLines(offset, length);
    }

    @Override public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        processChangedLines(offset, 0);
    }

    private void processChangedLines(int offset, int length) throws BadLocationException {
        Element root = getDefaultRootElement();
        String content = getText(0, getLength());
        int startLine = root.getElementIndex(offset);
        int endLine = root.getElementIndex(offset + length);

        if (startLine > endLine)
            startLine = endLine;

        for (int i = startLine; i <= endLine; i++) {
            applyHighlighting(content, i);
        }
    }

    private void applyHighlighting(String content, int line) throws BadLocationException {
        Element root = getDefaultRootElement();
        int startOffset   = root.getElement(line).getStartOffset();
        int endOffset     = root.getElement(line).getEndOffset() - 1;
        int lineLength    = endOffset - startOffset;
        int contentLength = content.length();
        endOffset = endOffset >= contentLength ? contentLength - 1 : endOffset;

        if(content.endsWith("*/")){
            setCharacterAttributes(multiOff, lineLength, multiLineCom, true);
            multiOff = 0;
            multi = false;
            return;
        }

        if(multi){
            setCharacterAttributes(multiOff, lineLength, multiLineCom, true);
            return;
        }

        if(content.endsWith("/*")){
            setCharacterAttributes(startOffset, lineLength, multiLineCom, true);
            multiOff = content.replaceAll("/*", "").length();
            multi = true;
            return;
        }
        setCharacterAttributes(startOffset, lineLength, text, true);
        checkForTokens(content, startOffset, endOffset);
    }

    private void checkForTokens(String content, int startOffset, int endOffset) {
        int index = startOffset;
        while (index <= endOffset) {
            while (isDelimiter(content.substring(index, index + 1))) {
                if (index < endOffset) {
                    index++;
                } else {
                    return;
                }
            }
            index = getOtherToken(content, index, endOffset);
        }
    }

    private int getOtherToken(String content, int startOffset, int endOffset) {
        int endOfToken = startOffset + 1;
        while (endOfToken <= endOffset) {
            if (isDelimiter(content.substring(endOfToken, endOfToken + 1))) {
                    break;
            }
            endOfToken++;
        }
        String token = content.substring(startOffset, endOfToken);
        //if (keywords.containsKey(token)) {
        //    setCharacterAttributes(startOffset, endOfToken - startOffset, keywords.get(token), false);
        if(token.equals("return")){
            setCharacterAttributes(startOffset, endOfToken - startOffset, returnToken, false);

        }else if (isAnnotation(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, annotation, false);

        }else if (isKeyword(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, keyword, false);
        }

        return endOfToken + 1;
    }

    protected boolean isDelimiter(String character) {
        return Character.isWhitespace(character.charAt(0)) || OPERANDS.indexOf(character) != -1;
    }

    public boolean isKeyword(String token){
        return keywords.contains(token);
    }

    public boolean isAnnotation(String token){
        return token.startsWith("@");
    }

    public boolean isTab(String token) {
        return token.equals("\t");
    }
}
