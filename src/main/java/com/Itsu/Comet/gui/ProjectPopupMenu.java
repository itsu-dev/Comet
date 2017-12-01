package com.Itsu.Comet.gui;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.Itsu.Comet.core.Controller;

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

public class ProjectPopupMenu extends JPopupMenu{

    private JMenuItem test = new JMenuItem("テスト                        ");

    public ProjectPopupMenu(){
        super();

        this.add(test);

        test.addActionListener(e -> {
            test();
        });

        add("削除");
    }

    @Override
    public void show(Component c, int x, int y) {
        if (c instanceof JTree) {
            JTree tree = (JTree) c;
            TreePath path = tree.getPathForLocation(x, y);
            if (tree.getSelectionCount() > 0
                    && Arrays.asList(tree.getSelectionPaths()).contains(path)) {
                super.show(c, x, y);
            }
        }
    }

    private void test(){
        JTree tree = (JTree) getInvoker();
        TreePath[] pathes = tree.getSelectionPaths();
        String temp = pathes[pathes.length - 1].toString();
        String pathes1[] = temp.substring(3, temp.length() - 1).split(", ");
        String path = pathes1[pathes1.length - 1];
        String type = "";
        String projectName = "";
        
        projectName = Controller.getProjectName(path);
        type = Controller.getType(Controller.getProjectPath(path));
        Controller.setNowProject(projectName);
        Controller.setNowProjectPath(path);
        
        if(type.equals("pmmp")){

        }else if(type.equals("Nukkit")){
            Controller.openNukkitTest();

        }else if(type.equals("Java")){

        }
    }
}
