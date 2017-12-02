package com.Itsu.Comet.listener;

import java.io.File;
import java.util.List;

import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.project.ProjectManager;
import com.Itsu.Comet.utils.BackgroundTask;

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

public class FolderSelectionListener implements TreeSelectionListener {
	
 private final FileSystemView fileSystemView;
 public FolderSelectionListener(FileSystemView fileSystemView) {
     this.fileSystemView = fileSystemView;
 }
 
 @Override public void valueChanged(TreeSelectionEvent e) {
     final JTree tree = (JTree) e.getSource();
     final DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

     if (!node.isLeaf()) {
         return;
     }
     final File parent = (File) node.getUserObject();
     if (!parent.isDirectory()) {
    	 
    	 if(parent.getName().equals("project.proj")) {
    		 Controller.alert("このファイルはプロジェクトに関する重要な情報が保存されているため開くことができません。");
    		 return;
    	 }
    	 
    	 Controller.setStatusText(parent.getName() + "を開いています...");
    	 ProjectManager.openFile(parent);
    	 Controller.setStatusText("準備完了");
    	 
    	 File f;
    	 while(true){
    		 if(node == null) break;
    		 f = parent.getParentFile();
    		 if(f.getName().equals("src")){
    			 Controller.setNowProject(f.getParentFile().getName());
    			 break;
    		 }
    	 }
         return;
     }

     final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
     SwingWorker<String, File> worker = new BackgroundTask(fileSystemView, parent) {
         @Override protected void process(List<File> chunks) {
             if (isCancelled()) {
                 return;
             }
             if (!tree.isDisplayable()) {
                 cancel(true);
                 return;
             }
             for (File file: chunks) {
                 model.insertNodeInto(new DefaultMutableTreeNode(file), node, node.getChildCount());
             }
         }
     };
     worker.execute();
 }
}