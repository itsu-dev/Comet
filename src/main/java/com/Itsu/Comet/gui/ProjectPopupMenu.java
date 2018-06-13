package com.Itsu.Comet.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.project.NukkitBuilder;
import com.Itsu.Comet.project.Project;
import com.Itsu.Comet.project.ProjectFile;
import com.Itsu.Comet.utils.Colors;

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

	private PopupMenu newFile = new PopupMenu("新規");
	private ButtonGroup group1 = new ButtonGroup();
	
	private JMenuItem packages = new JMenuItem("パッケージ");
	private JMenuItem classFile = new JMenuItem("クラス");
	private JMenuItem interfaces = new JMenuItem("インターフェース");
	private JMenuItem enums = new JMenuItem("列挙型");
	private JMenuItem annotation = new JMenuItem("注釈");
	private JMenuItem folder = new JMenuItem("フォルダ");
	private JMenuItem yml = new JMenuItem("YAMLファイル");
	private JMenuItem plugin = new JMenuItem("プラグイン定義（plugin.yml）");
	private JMenuItem file = new JMenuItem("その他のファイル");
	
	private PopupMenu project = new PopupMenu("プロジェクト");
	private ButtonGroup group2 = new ButtonGroup();
	
	private JMenuItem pocketmine = new JMenuItem("PocketMine-MPプロジェクト");
	private JMenuItem nukkit = new JMenuItem("Nukkitプロジェクト");
	private JMenuItem java = new JMenuItem("Javaプロジェクト");
	
    private JMenuItem build = new JMenuItem("ビルド                   ");
    
    private TreePath path;

    public ProjectPopupMenu(){
        super();
        
        createProjectMenu();
        createNewFileMenu();
        newFile.setPreferredSize(new Dimension(300, 23));
        this.add(newFile);
        
        this.add(build);
        build.addActionListener(e -> {
        	build();
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
            	this.path = tree.getSelectionPath();
                super.show(c, x, y);
            }
        }
    }
    
    private void createNewFileMenu() {
    	group1.add(project);
    	group1.add(packages);
    	group1.add(classFile);
    	group1.add(interfaces);
    	group1.add(enums);
    	group1.add(annotation);
    	group1.add(folder);
    	group1.add(yml);
    	group1.add(plugin);
    	group1.add(file);
    	
    	newFile.add(project);
    	newFile.add(packages);
    	newFile.add(classFile);
    	newFile.add(interfaces);
    	newFile.add(enums);
    	newFile.add(annotation);
    	newFile.add(folder);
    	newFile.add(yml);
    	newFile.add(plugin);
    	newFile.add(file);
    }
    
    private void createProjectMenu() {
    	group2.add(pocketmine);
    	group2.add(nukkit);
    	group2.add(java);
    	
    	project.add(pocketmine);
    	project.add(nukkit);
    	project.add(java);
    }

    private void build(){
        String path = this.path.toString().substring(3, this.path.toString().length() - 1);
        System.out.println(path);
        
        ProjectFile project = Controller.getProjectFileByPath(path);
        
        if(project == null) {
        	Controller.alert("プロジェクトを選択してください。");
        	return;
        }
        
        if(project.getType().equals("pmmp")){

        }else if(project.getType().equals("Nukkit")){
            NukkitBuilder.build(project);

        }else if(project.getType().equals("Java")){

        }
    }
    
}

class PopupMenu extends JMenu {
	
	public PopupMenu(String str) {
		super(str);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(this.isSelected()) {
			g.setColor(Controller.getColors().get("MENU_BAR_SELECT"));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
		} else {
			g.setColor(Controller.getColors().get("MENU_BAR"));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		g.setColor(Controller.getColors().get(("EDITOR_TEXT")));
		g.drawString(this.getText(), 6, this.getHeight() / 2 + 4);
	}
}
