package com.Itsu.Comet.gui;

import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.listener.FolderSelectionListener;
import com.Itsu.Comet.ui.BlackScrollBarUI;
import com.Itsu.Comet.utils.FileTreeCellRenderer;

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

public class ProjectTab extends JScrollPane{
	
	private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode();
	private DefaultTreeModel treeModel = new DefaultTreeModel(root);
	
	private JTree tree;
	
	private File workspace;
	
	public ProjectTab(File workspace){
		
		this.workspace = workspace;
        
        for (File fileSystemRoot: fileSystemView.getFiles(workspace, true)) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            Arrays.stream(fileSystemView.getFiles(fileSystemRoot, true))
                .forEach(file -> node.add(new DefaultMutableTreeNode(file)));
        }
		
        tree = new JTree(treeModel);
		tree.setBackground(Controller.getColors().get("EDITOR"));
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(new FolderSelectionListener(fileSystemView));
		tree.setCellRenderer(new FileTreeCellRenderer(tree.getCellRenderer(), fileSystemView));
		tree.setComponentPopupMenu(new ProjectPopupMenu());
		
		this.setViewportView(tree);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setAutoscrolls(true);
        this.setBackground(Controller.getColors().get("EDITOR"));
        this.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        this.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        this.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.setPreferredSize(new Dimension(320, 240));
	}
	
	public void update(){
		this.remove(tree);
		tree.removeAll();
		
		fileSystemView = FileSystemView.getFileSystemView();
		root = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);
		
        for (File fileSystemRoot: fileSystemView.getFiles(workspace, true)) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            Arrays.stream(fileSystemView.getFiles(fileSystemRoot, true))
                .forEach(file -> node.add(new DefaultMutableTreeNode(file)));
        }
		
        tree = new JTree(treeModel);
		tree.setBackground(Controller.getColors().get("EDITOR"));
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(new FolderSelectionListener(fileSystemView));
		tree.setCellRenderer(new FileTreeCellRenderer(tree.getCellRenderer(), fileSystemView));
		
		this.setViewportView(tree);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setAutoscrolls(true);
        this.setBackground(Controller.getColors().get("EDITOR"));
        this.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        this.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        this.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        this.setPreferredSize(new Dimension(320, 240));
	}

}
