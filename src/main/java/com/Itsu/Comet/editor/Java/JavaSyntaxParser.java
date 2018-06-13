package com.Itsu.Comet.editor.Java;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.BadLocationException;

import com.Itsu.Comet.gui.EditorPanel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;

public class JavaSyntaxParser {

    public final static char QUOTE = '\'';
    public final static char DOUBLE_QUOTE = '"';

    private StreamTokenizer tokenizer;
    private int token;

    private EditorPanel pane;

    /*構文データ*/
    private ClassOrInterfaceDeclaration classData;
    private MethodDeclaration methodData;
    private LineComment commentData;

    private List<JavaVariable> fields = new ArrayList<>();
    private List<String> imports = new ArrayList<>();

    /*一時保存用*/
    private String type = "";//修飾子タイプ
    private boolean variable = false;

    public JavaSyntaxParser(EditorPanel pane) {
        this.pane = pane;
    }

    public void parse() throws ParseProblemException {
        String target = pane.getEditor().getText();
        CompilationUnit cu = null;
        
        cu = JavaParser.parse(target);
        
        cu.accept(new ClassVisitor(this), null);
        cu.accept(new MethodVisitor(this), null);
        cu.accept(new CommentVisitor(this), null);

        cu.getImports()
            .stream()
            .forEach(importCon -> imports.add(importCon.getNameAsString()));

        classData.getFields()
            .stream()
            .forEach(field -> field.getVariables()
                        .stream()
                        .forEach(field2 -> {
                            JavaVariable var = new JavaVariable("", null, "", false);
                            var.setName(field2.getNameAsString());
                            
                            var.setClassName(imports.stream().filter(str -> str.endsWith(field2.getTypeAsString())).count() > 0
									? imports.stream().filter(str -> str.endsWith(field2.getTypeAsString())).toArray(String[]::new)[0]
									: "java.lang." + field2.getTypeAsString());
                            
                            var.setModifiers(field.getModifiers());
                            var.setImported(imports.stream().filter(str -> str.endsWith(field2.getTypeAsString())).count() > 0);
                            
                            try {
                            	if(field.isStatic()) ((JavaSyntaxHighliter) pane.getEditor().getStyledDocument()).setStaticVariable(field2.getBegin().get().line, field2.getBegin().get().column, field2.getNameAsString());
                            	else ((JavaSyntaxHighliter) pane.getEditor().getStyledDocument()).setVariable(field2.getBegin().get().line, field2.getBegin().get().column, field2.getNameAsString());
                            } catch (BadLocationException e) {
								e.printStackTrace();
							}
                            
                            fields.add(var);
                        }));
        
        classData.getAllContainedComments().forEach(comment -> {
        	try {
				((JavaSyntaxHighliter) pane.getEditor().getStyledDocument()).setComment(comment.getBegin().get().line, comment.getBegin().get().column, comment.getContent());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
        });
        
        classData.getMethods().forEach(method -> {
        	method.getAllContainedComments().stream().forEach(comment -> {
        		try {
					((JavaSyntaxHighliter) pane.getEditor().getStyledDocument()).setComment(comment.getBegin().get().line, comment.getBegin().get().column, comment.getContent());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
        	});
        });
    }

    private static class ClassVisitor extends VoidVisitorAdapter<Void> {

        private JavaSyntaxParser parser;

        public ClassVisitor(JavaSyntaxParser parser) {
            this.parser = parser;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            parser.setClass(n);
            super.visit(n, arg);
        }

    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {

        private JavaSyntaxParser parser;

        public MethodVisitor(JavaSyntaxParser parser) {
            this.parser = parser;
        }

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            parser.setMethods(n);
            super.visit(n, arg);
        }

    }
    
    private static class CommentVisitor extends VoidVisitorAdapter<Void> {

        private JavaSyntaxParser parser;

        public CommentVisitor(JavaSyntaxParser parser) {
            this.parser = parser;
        }

        @Override
        public void visit(LineComment n, Void arg) {
            parser.setComment(n);
            super.visit(n, arg);
        }

    }

    public List<JavaVariable> getFields() {
        return fields;
    }

    private void setClass(ClassOrInterfaceDeclaration classDec) {
        this.classData = classDec;
    }

    private void setMethods(MethodDeclaration methodDec) {
        this.methodData = methodDec;
    }
    
    private void setComment(LineComment commentData) {
        this.commentData = commentData;
    }
    
    
}
