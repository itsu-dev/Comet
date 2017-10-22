package com.Itsu.Comet.gui;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.utils.FileTreeCellRenderer;

public class ProjectPopupMenu extends JPopupMenu{
	
	public ProjectPopupMenu(){
		super();
		add("テスト                        ").addActionListener(e -> {
			test();
		});
		add("削除");
	}
	
	@Override 
	public void show(Component c, int x, int y) {
	    if (c instanceof JTree) {
	    	JTree tree = (JTree) c;
	    	FileTreeCellRenderer ren = (FileTreeCellRenderer) tree.getCellRenderer();
	    	TreePath path = tree.getPathForLocation(x, y);
	    	if (tree.getSelectionCount() > 0
	    			&& Arrays.asList(tree.getSelectionPaths()).contains(path)) {
	    		super.show(c, x, y);
	    	}
	    }
	}
	
	private void test(){
		JTree tree = (JTree) getInvoker();
		String temp = (tree.getSelectionPaths()[tree.getSelectionPaths().length - 1]).toString();
		String path = temp.substring(3, temp.length() - 1);
		String type = Controller.getType(path);
		
		String temp1[] = path.replaceAll("\\\\", "/").split("/");
		String projectName = null;
		int count = 0;
		
		for(String str : temp1){
			if(str.equals("workspace")){
				projectName = temp1[count + 1];
				break;
			}
			count++;
		}
		
		Controller.setNowProject(projectName);
		Controller.setNowProjectPath(path);
		
		if(type.equals("pmmp")){
			
		}else if(type.equals("Nukkit")){
			Controller.openNukkitTest();
			
		}else if(type.equals("Java")){
			
		}
	}
}
