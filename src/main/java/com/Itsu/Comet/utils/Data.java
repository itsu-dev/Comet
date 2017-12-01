package com.Itsu.Comet.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

public class Data implements Serializable{

    private String OSName;
    
    private Dimension desktopSize;
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private int desktopWidth;
    private int desktopHeight;
    
    private Font[] fontList;
    private String systemFont;
    
    private File workspace;
    private String workspacePass;
    
    private ImageIcon close;
    private ImageIcon max;
    private ImageIcon min;
    private ImageIcon iconFly;
    private ImageIcon nukkit;
    private ImageIcon pmmp;
    
    private ImageIcon java;
    private ImageIcon php;
    private ImageIcon data;
    private ImageIcon text;
    private ImageIcon image;
    
    private Image icon;


    public Data(){

    }

    public void initData() throws IOException{
    	
        OSName = System.getProperty("os.name").toLowerCase();
        
        desktopSize = Toolkit.getDefaultToolkit().getScreenSize();
        desktopWidth = desktopSize.width;
        desktopHeight = desktopSize.height;
        
        fontList = ge.getAllFonts();
        
        close = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/close.png")));
        max = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/sizeMax.png")));
        min = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/sizeMin.png")));
        iconFly = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/iconFly.png")));
        nukkit = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/nukkit.jpg")));
        pmmp = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/pmmp.png")));
        
        java = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/java.png")));
        php = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/php.png")));
        data = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/data.png")));
        text = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/text.png")));
        image = new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/image.png")));
        
        icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Comet/images/icon.png"));
        
        systemFont = initSystemFont();
        
        initWorkspace();
        
    }

    private String initSystemFont(){
    	
        String osLower = OSName;

        if(osLower.startsWith("windows")){
            return "メイリオ";
            
        }else if(osLower.startsWith("linux")){
            return "TakaoPゴシック";
            
        }else if(osLower.startsWith("mac")){
            return "ヒラギノ角ゴ";
            
        }else{
            return "ＭＳ Ｐゴシック";
        }
        
    }
    
    public void initWorkspace(){
    	workspacePass = "./workspace/";
		workspace = new File(workspacePass);
    }

    public int getWindowWidth(){
        return this.desktopWidth;
    }

    public int getWindowHeight(){
        return this.desktopHeight;
    }

    public Font[] getFonts(){
        return this.fontList;
    }

    public ImageIcon getCloseIcon(){
        return close;
    }

    public ImageIcon getMaxIcon(){
        return max;
    }

    public ImageIcon getMinIcon(){
        return min;
    }

    public ImageIcon getIconFlyIcon(){
        return iconFly;
    }
    
    public ImageIcon getNukkitIcon(){
        return nukkit;
    }

    public ImageIcon getPMMPIcon(){
        return pmmp;
    }
    
    public ImageIcon getJavaIcon(){
        return java;
    }

    public ImageIcon getPHPIcon(){
        return php;
    }

    public ImageIcon getDataIcon(){
        return data;
    }

    public ImageIcon getTextIcon(){
        return text;
    }

    public ImageIcon getImageIcon(){
        return image;
    }
    
    public Image getIcon(){
        return icon;
    }
    
    public String getSystemFont(){
        return systemFont;
    }
    
    public File getWorkspace(){
    	return workspace;
    }
    
    public String getWorkspacePass(){
    	return workspacePass;
    }
    
    public String getOS(){
    	return this.OSName;
    }
}
