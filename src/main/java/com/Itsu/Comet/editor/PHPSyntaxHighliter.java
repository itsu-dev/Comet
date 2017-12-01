package com.Itsu.Comet.editor;

import java.util.HashSet;
import java.util.Set;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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

public class PHPSyntaxHighliter extends DefaultStyledDocument implements SyntaxHighliter{
    private static final char LB = '\n';
    private static final String OPERANDS = ".,(){}[];+-*/%=";

    private MutableAttributeSet keyword;
    private MutableAttributeSet keyword1;
    private MutableAttributeSet keyword2;
    private MutableAttributeSet variable;
    private MutableAttributeSet text;
    private MutableAttributeSet comment;
    private MutableAttributeSet oneLineCom;
    private MutableAttributeSet multiLineCom;
    private MutableAttributeSet returnToken;
    private Set<String> keywords;
    private Set<String> keywords1;
    private Set<String> keywords2;

    public PHPSyntaxHighliter() {
        super();

        //System token
        keyword = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword, Controller.getPHPColors().get("keyword"));
        StyleConstants.setBold(keyword, true);

        keyword1 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword1, Controller.getPHPColors().get("keyword1"));
        StyleConstants.setBold(keyword1, true);
        StyleConstants.setUnderline(keyword1, true);

        keyword2 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword2, Controller.getPHPColors().get("keyword2"));

        variable = new SimpleAttributeSet();
        StyleConstants.setForeground(variable, Controller.getPHPColors().get("variable"));

        //"return" token
        returnToken = new SimpleAttributeSet();
        StyleConstants.setForeground(returnToken, Controller.getPHPColors().get("returnToken"));
        StyleConstants.setUnderline(returnToken, true);
        StyleConstants.setBold(returnToken, true);

        //comment token
        comment = new SimpleAttributeSet();
        StyleConstants.setForeground(comment, Controller.getPHPColors().get("comment"));

        //one line commentout
        oneLineCom = new SimpleAttributeSet();
        StyleConstants.setForeground(oneLineCom, Controller.getPHPColors().get("oneLineCom"));

        //multi line commentout
        multiLineCom = new SimpleAttributeSet();
        StyleConstants.setForeground(multiLineCom, Controller.getPHPColors().get("multiLineCom"));

        //normal text
        text = new SimpleAttributeSet();
        StyleConstants.setForeground(text, Controller.getPHPColors().get("text"));

        keywords = new HashSet<String>();
        keywords.add( "__halt_compiler" );
        keywords.add( "abstract" );
        keywords.add( "and" );
        keywords.add( "array" );
        keywords.add( "as" );
        keywords.add( "break" );
        keywords.add( "callable" );
        keywords.add( "case" );
        keywords.add( "catch" );
        keywords.add( "class" );
        keywords.add( "clone" );
        keywords.add( "const" );
        keywords.add( "continue" );
        keywords.add( "declare" );
        keywords.add( "default" );
        keywords.add( "die" );
        keywords.add( "do" );
        keywords.add( "echo" );
        keywords.add( "else" );
        keywords.add( "elseif" );
        keywords.add( "empty" );
        keywords.add( "enddeclare" );
        keywords.add( "endfor" );
        keywords.add( "endforeach" );
        keywords.add( "endif" );
        keywords.add( "endswitch" );
        keywords.add( "endwhile" );
        keywords.add( "eval" );
        keywords.add( "exit" );
        keywords.add( "extends" );
        keywords.add( "final" );
        keywords.add( "finally" );
        keywords.add( "for" );
        keywords.add( "foreach" );
        keywords.add( "function" );
        keywords.add( "global" );
        keywords.add( "goto" );
        keywords.add( "if" );
        keywords.add( "implements" );
        keywords.add( "include" );
        keywords.add( "include_once" );
        keywords.add( "instanceof" );
        keywords.add( "insteadof" );
        keywords.add( "interface" );
        keywords.add( "isset" );
        keywords.add( "list" );
        keywords.add( "namespace" );
        keywords.add( "new" );
        keywords.add( "or" );
        keywords.add( "print" );
        keywords.add( "private" );
        keywords.add( "protected" );
        keywords.add( "public" );
        keywords.add( "require" );
        keywords.add( "require_once" );
        keywords.add( "return" );
        keywords.add( "static" );
        keywords.add( "switch" );
        keywords.add( "throw" );
        keywords.add( "trait" );
        keywords.add( "try" );
        keywords.add( "unset" );
        keywords.add( "use" );
        keywords.add( "var" );
        keywords.add( "while" );
        keywords.add( "xor" );
        keywords.add( "yield" );
        keywords.add( "int" );
        keywords.add( "float" );
        keywords.add( "bool" );
        keywords.add( "string" );
        keywords.add( "true" );
        keywords.add( "false" );
        keywords.add( "null" );
        keywords.add( "void" );
        keywords.add( "iterable" );
        keywords.add( "resource" );
        keywords.add( "object" );
        keywords.add( "mixed" );
        keywords.add( "numeric" );

        keywords1 = new HashSet<String>();
        keywords1.add("PHP_VERSION");
        keywords1.add("PHP_MAJOR_VERSION");
        keywords1.add("PHP_MINER_VERSION");
        keywords1.add("PHP_RELEASE_VERSION");
        keywords1.add("PHP_VERSION_ID");
        keywords1.add("PHP_EXTRA_VERSION");
        keywords1.add("PHP_ZTS");
        keywords1.add("PHP_DEBUG");
        keywords1.add("PHP_MAXPATHLEN");
        keywords1.add("PHP_OS");
        keywords1.add("PHP_OS_FAMILY");
        keywords1.add("PHP_SAPI");
        keywords1.add("PHP_EOL");
        keywords1.add("PHP_INT_MAX");
        keywords1.add("PHP_INT_MIN");
        keywords1.add("PHP_INT_SIZE");
        keywords1.add("PHP_FLOAT_DIG");
        keywords1.add("PHP_FLOAT_EPSILON");
        keywords1.add("PHP_FLOAT_MIN");
        keywords1.add("PHP_FLOAT_MAX");
        keywords1.add("DEFAULT_INCLUDE_PATH");
        keywords1.add("PEAR_INSTALL_DIR");
        keywords1.add("PEAR_EXTENSION_DIR");
        keywords1.add("PHP_PREFIX_");
        keywords1.add("PHP_BINDIR");
        keywords1.add("PHP_BINALY");
        keywords1.add("PHP_MANDIR");
        keywords1.add("PHP_LIBDIR");
        keywords1.add("PHP_DATADIR");
        keywords1.add("PHP_SYSCONFDIR");
        keywords1.add("PHP_LOCALSTATEDIR");
        keywords1.add("PHP_CONFIG_FILE_PATH");
        keywords1.add("PHP_CONFIG_FILE_SCAN_DIR");
        keywords1.add("PHP_SHLIB_SUFFIX");
        keywords1.add("PHP_FD_SETSIZE");
        keywords1.add("E_ERROR");
        keywords1.add("E_WARNING");
        keywords1.add("E_PARSE");
        keywords1.add("E_NOTICE");
        keywords1.add("E_CORE_ERROR");
        keywords1.add("E_CORE_WARNING");
        keywords1.add("E_COMPILE_ERROR");
        keywords1.add("E_COMPILE_WARNING");
        keywords1.add("E_USER_ERROR");
        keywords1.add("E_USER_WARNING");
        keywords1.add("E_USER_NOTICE");
        keywords1.add("E_DEPRECATED");
        keywords1.add("E_USER_DEPRECATED");
        keywords1.add("E_ALL");
        keywords1.add("E_STRICT");
        keywords1.add("__COMPILER_HALT_OFFSET__");
        keywords1.add("TRUE");
        keywords1.add("FALSE");
        keywords1.add("NULL");
        keywords1.add("__LINE__");
        keywords1.add("__FILE__");
        keywords1.add("__DIR__");
        keywords1.add("__FUNCTION__");
        keywords1.add("__CLASS__");
        keywords1.add("__TRAIT__");
        keywords1.add("__METHOD__");
        keywords1.add("__NAMESPACE__");
        keywords1.add("::CLASS");

        keywords2 = new HashSet<String>();
        keywords2.add("<?php");
        keywords2.add("?>");

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

        /*
        if(content.endsWith("")){
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
        */
        setCharacterAttributes(startOffset, lineLength, text, true);
        checkForTokens(content, startOffset, endOffset);
    }
    private void checkForTokens(String content, int startOffset, int endOffset) {
        int index = startOffset;
        while (index <= endOffset) {
            while ((isDelimiter(content.substring(index, index + 1)))) {
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
        }else if(isVariable(token)){
            setCharacterAttributes(startOffset, endOfToken - startOffset, variable, false);
        }else if (isKeyword(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, keyword, false);
        }else if (isKeyword1(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, keyword1, false);
        }else if (isKeyword2(token)) {
            setCharacterAttributes(startOffset, endOfToken - startOffset, keyword2, false);
        }
        /*else if(token.startsWith("\"") && token.endsWith("\"")){
            setCharacterAttributes(startOffset, endOfToken - startOffset, comment, false);
        }*/
        return endOfToken + 1;
    }
    protected boolean isDelimiter(String character) {
        return Character.isWhitespace(character.charAt(0)) || OPERANDS.indexOf(character) != -1;
    }
    public boolean isVariable(String token){
        return token.startsWith("$");
    }
    public boolean isKeyword(String token){
        return keywords.contains(token);
    }
    public boolean isKeyword1(String token){
        return keywords1.contains(token);
    }
    public boolean isKeyword2(String token){
        return keywords2.contains(token);
    }
}
