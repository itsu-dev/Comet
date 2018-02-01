package com.Itsu.Comet.editor.Java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.editor.SyntaxHighliter;

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

public class JavaSyntaxHighliter extends DefaultStyledDocument implements SyntaxHighliter{
    private static final char LB = '\n';
    private static final String OPERANDS = ".,(){;";

    private MutableAttributeSet keyword;
    private MutableAttributeSet primitive;
    private MutableAttributeSet text;
    private MutableAttributeSet annotation;
    private MutableAttributeSet comment;
    private MutableAttributeSet oneLineCom;
    private MutableAttributeSet multiLineCom;
    private MutableAttributeSet returnToken;
    private MutableAttributeSet field;
    private Set<String> keywords;
    private Set<String> primitives;
    private Set<String> returns;
    private List<String> fields;
    private boolean multi = false;
    private int multiOff = 0;

    public JavaSyntaxHighliter() {
        super();

        //System token
        keyword = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword, Controller.getJavaColors().get("keyword"));
        StyleConstants.setBold(keyword, true);
        
        primitive = new SimpleAttributeSet();
        StyleConstants.setForeground(primitive, Controller.getJavaColors().get("primitive"));
        StyleConstants.setBold(primitive, true);

        annotation = new SimpleAttributeSet();
        StyleConstants.setForeground(annotation, Controller.getJavaColors().get("annotation"));

        //return/continue/break token
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
        
        //field variable
        field = new SimpleAttributeSet();
        StyleConstants.setForeground(field, Controller.getJavaColors().get("field"));
        
        fields = new ArrayList<>();

        keywords = new HashSet<String>();
        keywords.add( "abstract" );
        keywords.add( "byvalue" );
        keywords.add( "case" );
        keywords.add( "cast" );
        keywords.add( "catch" );
        keywords.add( "class" );
        keywords.add( "default" );
        keywords.add( "do" );
        keywords.add( "else" );
        keywords.add( "extends" );
        keywords.add( "final" );
        keywords.add( "finally" );
        keywords.add( "for" );
        keywords.add( "future" );
        keywords.add( "generic" );
        keywords.add( "goto" );
        keywords.add( "if" );
        keywords.add( "implements" );
        keywords.add( "import" );
        keywords.add( "inner" );
        keywords.add( "instanceof" );
        keywords.add( "interface" );
        keywords.add( "native" );
        keywords.add( "new" );
        keywords.add( "operator" );
        keywords.add( "outer" );
        keywords.add( "package" );
        keywords.add( "private" );
        keywords.add( "protected" );
        keywords.add( "public" );
        keywords.add( "rest" );
        keywords.add( "return" );
        keywords.add( "static" );
        keywords.add( "switch" );
        keywords.add( "synchronized" );
        keywords.add( "throw" );
        keywords.add( "throws" );
        keywords.add( "transient" );
        keywords.add( "try" );
        keywords.add( "volatile" );
        keywords.add( "while" );
        
        primitives = new HashSet<String>();
        primitives.add( "boolean" );
        primitives.add( "char" );
        primitives.add( "double" );
        primitives.add( "float" );
        primitives.add( "short" );
        primitives.add( "long" );
        primitives.add( "int" );
        primitives.add( "byte" );
        primitives.add( "void" );
        primitives.add( "false" );
        primitives.add( "true" );
        primitives.add( "null" );
        primitives.add( "this" );
        primitives.add( "super" );
        primitives.add( "const" );
        
        returns = new HashSet<String>();
        returns.add( "return" );
        returns.add( "break" );
        returns.add( "continue" );
        
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
    
    public void setVariable(int offset, String var) throws BadLocationException {
    	fields.add(var);
    	setCharacterAttributes(offset, var.length(), field, false);
    }
    
    public void setComment(int offset, String var) throws BadLocationException {
    	setCharacterAttributes(offset, var.length(), comment, false);
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
        if(isReturn(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, returnToken, false);

        } else if (isAnnotation(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, annotation, false);

        } else if (isPrimitive(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, primitive, false);
            
        } else if (isKeyword(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, keyword, false);
            
        } else if (isField(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, field, false);
        }

        return endOfToken + 1;
    }

    protected boolean isDelimiter(String character) {
        return Character.isWhitespace(character.charAt(0)) || OPERANDS.indexOf(character) != -1;
    }
    
    public boolean isReturn(String token){
        return returns.contains(token);
    }
    
    public boolean isPrimitive(String token){
        return primitives.contains(token);
    }

    public boolean isKeyword(String token){
        return keywords.contains(token);
    }
    
    public boolean isField(String token){
        return fields.contains(token);
    }

    public boolean isAnnotation(String token){
        return token.startsWith("@");
    }

    public boolean isTab(String token) {
        return token.equals("\t");
    }
}
