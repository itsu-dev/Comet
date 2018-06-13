package com.Itsu.Comet.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.EditorPanel;

public class ProjectManager extends TEST implements AAA, BBB , CCC {

    private File root;
    private String text;
    private String fuck;

    public ProjectManager(){

    }

    public static void openFile(File file){
        if(file.isDirectory()) return;

        try {
            String name = file.getName();
            String texts = Utils.readFile(file);



            Controller.getEditor().addTab(name, new EditorPanel(null, texts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}