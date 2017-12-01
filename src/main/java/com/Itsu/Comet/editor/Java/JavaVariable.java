package com.Itsu.Comet.editor.Java;

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
	
	public String type;
	public String name;
	public String className;
	public boolean isImported;
	
	public JavaVariable(String name, String type, String className, boolean isImported) {
		this.name = name;
		this.type = type;
		this.className = className;
		this.isImported = isImported;
	}
	
	public String getType() {
		return type;
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

}
