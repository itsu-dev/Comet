package com.Itsu.Comet.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.Itsu.Comet.core.Controller;

public class MenuBar extends JMenuBar implements ActionListener{

    private JMenu file;
    private JMenu edit;
    private JMenu window;
    private JMenu setting;
    private JMenu plugin;
    
    private JMenuItem newProject;
    private JMenuItem save;
    
    private JMenuItem undo;
    private JMenuItem redo;

    private JMenuItem windowNew;
    private JMenuItem windowNewEditor;

    private JMenuItem nukkitDictionary;
    private JMenuItem nukkitSetting;
    private JMenuItem nukkitTest;
    private JMenuItem pmmpDictionary;
    private JMenuItem pmmpSetting;
    private JMenuItem pmmpTest;

    public MenuBar(){
        setting = new JMenu("設定(S)");

        setting.setBorderPainted(false);

        initFile();
        initEdit();
        initWindow();
        initNukkit();

        this.setBackground(Controller.getColors().get("MENU_BAR"));
        this.add(file);
        this.add(edit);
        this.add(window);
        this.add(plugin);
        this.add(setting);
    }
    
    private void initFile(){
        file = new JMenu("ファイル(F)");
        file.setBorderPainted(false);
        
        newProject = new JMenuItem("新規プロジェクト");
        newProject.setActionCommand("newProject");
        newProject.setBorderPainted(false);
        newProject.addActionListener(this);
        newProject.setPreferredSize(new Dimension(300, (int) newProject.getPreferredSize().getHeight()));
        file.add(newProject);
        
        save = new JMenuItem("保存");
        save.setActionCommand("save");
        save.setBorderPainted(false);
        save.addActionListener(this);
        file.add(save);
    }
    
    private void initEdit(){
        edit = new JMenu("編集(E)");
        edit.setBorderPainted(false);
        
        undo = new JMenuItem("元に戻す");
        undo.setActionCommand("undo");
        undo.setBorderPainted(false);
        undo.addActionListener(this);
        undo.setPreferredSize(new Dimension(300, (int) undo.getPreferredSize().getHeight()));
        edit.add(undo);
        
        redo = new JMenuItem("やり直し");
        redo.setActionCommand("redo");
        redo.setBorderPainted(false);
        redo.addActionListener(this);
        edit.add(redo);
    }

    private void initWindow(){
        window = new JMenu("ビューの表示(V)");
        window.setBorderPainted(false);

        windowNew = new JMenuItem("新規ウィンドウ");
        windowNew.setActionCommand("windowNew");
        windowNew.setBorderPainted(false);
        windowNew.addActionListener(this);
        windowNew.setPreferredSize(new Dimension(300, (int) windowNew.getPreferredSize().getHeight()));
        window.add(windowNew);

        windowNewEditor = new JMenuItem("エディタ");
        windowNewEditor.setActionCommand("windowNewEditor");
        windowNewEditor.setBorderPainted(false);
        windowNewEditor.addActionListener(this);
        window.add(windowNewEditor);
    }

    private void initNukkit(){
    	plugin = new JMenu("プラグイン(P)");
    	plugin.setBorderPainted(false);

        nukkitDictionary = new JMenuItem("Nukkit メソッド辞典");
        nukkitDictionary.setActionCommand("nukkitDictionary");
        nukkitDictionary.setBorderPainted(false);
        nukkitDictionary.addActionListener(this);
        nukkitDictionary.setPreferredSize(new Dimension(300, (int) nukkitDictionary.getPreferredSize().getHeight()));
        
        nukkitTest = new JMenuItem("Nukkitでテスト");
        nukkitTest.setActionCommand("nukkitTest");
        nukkitTest.setBorderPainted(false);
        nukkitTest.addActionListener(this);

        nukkitSetting = new JMenuItem("Nukkitの設定");
        nukkitSetting.setActionCommand("nukkitSetting");
        nukkitSetting.setBorderPainted(false);
        nukkitSetting.addActionListener(this);
        
        pmmpDictionary = new JMenuItem("PMMP 関数辞典");
        pmmpDictionary.setActionCommand("pmmpDictionary");
        pmmpDictionary.setBorderPainted(false);
        pmmpDictionary.addActionListener(this);
        
        pmmpTest = new JMenuItem("PMMPでテスト");
        pmmpTest.setActionCommand("pmmpTest");
        pmmpTest.setBorderPainted(false);
        pmmpTest.addActionListener(this);
        
        pmmpSetting = new JMenuItem("PMMPの設定");
        pmmpSetting.setActionCommand("pmmpSetting");
        pmmpSetting.setBorderPainted(false);
        pmmpSetting.addActionListener(this);

        plugin.add(nukkitDictionary);
        //plugin.add(nukkitTest);
        plugin.add(nukkitSetting);
        plugin.addSeparator();
        plugin.add(pmmpDictionary);
        //plugin.add(pmmpTest);
        plugin.add(pmmpSetting);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
        
	        case "newProject":
	        	Controller.openProjectSetter();
	            break;
	
	        case "save":
	            break;
	            
	        case "undo":
	            break;
	
	        case "redo":
	        	break;
	            
            case "windowNew":
                Controller.newWindow();
                break;

            case "windowNewEditor":
                Controller.openEditor(new Editor(), "Editor");
                break;
                
            case "nukkitTest":
            	if(Controller.getNowProject() == null || Controller.getNowProject().equals("null")){
            		Controller.alert("プロジェクトを選択してください。");
            		return;
            	}
            	Controller.openNukkitTest();
            	break;
                
        }
    }

}
