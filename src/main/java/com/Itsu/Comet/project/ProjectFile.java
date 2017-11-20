package com.Itsu.Comet.project;

public final class ProjectFile {

    private String name;
    private String path;
    private String type;

    public ProjectFile(String name, String path, String type) {
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

}
