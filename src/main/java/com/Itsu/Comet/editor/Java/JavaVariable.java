package com.Itsu.Comet.editor.Java;

import java.util.EnumSet;

import com.github.javaparser.ast.Modifier;

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

public class JavaVariable {
	
	public EnumSet<Modifier> modifier;
	public String name;
	public String className;
	public boolean isImported;
	
	public JavaVariable(String name, EnumSet<Modifier> modifier, String className, boolean isImported) {
		this.name = name;
		this.modifier = modifier;
		this.className = className;
		this.isImported = isImported;
	}
	
	public EnumSet<Modifier> getModifiers() {
		return modifier;
	}
	
	public String getName() {
		return name;
	}
	
	public String getClassName() {
		return className;
	}
	
	public boolean isImported() {
		return isImported;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setName(String name) {
		this.name =  name;
	}
	
	public void setModifiers(EnumSet<Modifier> modifier) {
		this.modifier = modifier;
	}

	public void setImported(boolean b) {
		this.isImported = b;
	}

}
