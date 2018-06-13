package com.Itsu.Comet.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.Itsu.Comet.gui.Editor;
import com.Itsu.Comet.gui.FileBar;
import com.Itsu.Comet.gui.MenuBar;
import com.Itsu.Comet.gui.TaskBar;
import com.Itsu.Comet.gui.Window;
import com.Itsu.Comet.utils.SplashWindow;
import com.Itsu.Comet.utils.Version;

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

public class Comet extends JFrame {

    private JDesktopPane mdi;
    private TaskBar bar;
    private FileBar fileBar;
    private static Comet instance;

    public Comet() {

    }

    public static void main(String[] args){
        Toolkit.getDefaultToolkit().setDynamicLayout(true);

        try {
            new SplashWindow();
        } catch (NullPointerException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        Comet comet = new Comet();
        instance = comet;

        Controller.checkFile();
        Controller.checkVersion();

        Controller.initData();
        Controller.initSkinColor();
        Controller.initJavaColor();
        Controller.initPHPColor();
        
        Controller.initUI();

        comet.installGui(JFrame.EXIT_ON_CLOSE);
    }

    public void installGui(int type){
        this.setTitle("Comet " + Version.VERSION);
        this.setBounds(0, 0, Controller.getDataObject().getWindowWidth(), Controller.getDataObject().getWindowHeight());
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new MenuBar());
        this.setDefaultCloseOperation(type);
        this.setIconImage(Controller.getDataObject().getIcon());
        
        fileBar = new FileBar();
        this.getContentPane().add(fileBar, BorderLayout.NORTH);

        mdi = new JDesktopPane();
        mdi.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(mdi, BorderLayout.CENTER);
        
        bar = new TaskBar();
        this.getContentPane().add(bar, BorderLayout.SOUTH);

        Controller.setFileBar(fileBar);
        Controller.setDesktopPane(mdi);
        Controller.setJFrame(this);

        Controller.openEditor(new Editor(), "Editor");
        Controller.openView1(new Window(Window.PROJECT), "View1");
        
        Controller.setTaskBar(bar);
        Controller.setStatusText("準備完了");

        this.setVisible(true);
    }

    public static Comet getInstance(){
        return instance;
    }
}
