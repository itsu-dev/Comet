package com.Itsu.Comet.project;

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

public final class Project {
	
	private String name;
	private String path;
	private String type;
	
	public Project(String name, String path, String type) {
		this.name = name;
		this.path = path;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getType() {
		return type;
	}
}
