package com.Itsu.Comet.utils;

import java.io.File;
import java.util.Arrays;

import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;

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

public class BackgroundTask extends SwingWorker<String, File> {
    private final FileSystemView fileSystemView;
    private final File parent;
    protected BackgroundTask(FileSystemView fileSystemView, File parent) {
        super();
        this.fileSystemView = fileSystemView;
        this.parent = parent;
    }
    @Override public String doInBackground() {
        Arrays.stream(fileSystemView.getFiles(parent, true))
            .forEach(this::publish);
        return "done";
    }
}