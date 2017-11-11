package com.Itsu.Comet.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.Itsu.Comet.gui.Editor;
import com.Itsu.Comet.gui.MenuBar;
import com.Itsu.Comet.gui.Window;
import com.Itsu.Comet.utils.SplashWindow;
import com.Itsu.Comet.utils.Version;

public class Comet extends JFrame{

    private JDesktopPane mdi;
    private static Comet instance;
    
    private static SplashWindow splashWindow;

    public Comet(){
    	
    }

    public static void main(String[] args){
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        
        try {
        	splashWindow = new SplashWindow();
        	Controller.setSplashWindow(splashWindow);
        	Controller.setSplashText("初期化中...");
		} catch (NullPointerException | IllegalStateException | IOException e) {
			e.printStackTrace();
		}

        Comet comet = new Comet();
        instance = comet;
        
        Controller.setSplashText("ファイルのチェック中...");
        Controller.checkFile();

        Controller.setSplashText("データの取得中...");
        Controller.initData();
        
        Controller.setSplashText("UIを読み込み中...");
        Controller.initUI();
        //Controller.initColor();

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

        mdi = new JDesktopPane();
        mdi.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(mdi, BorderLayout.CENTER);

        Controller.setDesktopPane(mdi);
        Controller.setJFrame(this);

        Controller.openEditor(new Editor(), "Editor");
        Controller.openView1(new Window(Window.PROJECT), "View1");

        this.setVisible(true);
    }

    public static Comet getInstance(){
        return instance;
    }
}
