package com.Itsu.Comet.utils;

import java.io.File;
import java.util.Arrays;

import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;

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