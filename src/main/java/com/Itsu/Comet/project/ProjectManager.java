package com.Itsu.Comet.project;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.TreeCellRenderer;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.editor.PHPSyntaxHighliter;
import com.Itsu.Comet.editor.Java.JavaSyntaxHighliter;
import com.Itsu.Comet.gui.EditorPanel;
import com.Itsu.Comet.gui.ImagePanel;
import com.Itsu.Comet.utils.PropertyReader;
import com.Itsu.Comet.utils.Utils;

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

public class ProjectManager {

    public ProjectManager(){

    }

    public static void openFile(File file){
        if(file.isDirectory()) return;

        try {
            String name = file.getName();
            String path = file.getAbsolutePath();
            String texts = Utils.readFile(file);
            
            Controller.setNowProjectPath(path);

            if(name.toLowerCase().endsWith(".java")){
            	Controller.setOpenFiles(new ProjectFile(name, path, "java"));
                Controller.getEditor().addTab(name, path, new EditorPanel(new JavaSyntaxHighliter(), texts));

            }else if(name.toLowerCase().endsWith(".php")){
            	Controller.setOpenFiles(new ProjectFile(name, path, "php"));
                Controller.getEditor().addTab(name, path, new EditorPanel(new PHPSyntaxHighliter(), texts));
                
            }else if(name.toLowerCase().endsWith(".png")
        			|| name.toLowerCase().endsWith(".jpg")
        			|| name.toLowerCase().endsWith(".jpeg")
        			|| name.toLowerCase().endsWith(".bmp")
        			|| name.toLowerCase().endsWith(".jpg")
        			|| name.toLowerCase().endsWith(".gif")
        			|| name.toLowerCase().endsWith(".tiff")
        			|| name.toLowerCase().endsWith(".svg")
        			|| name.toLowerCase().endsWith(".ico")
        			|| name.toLowerCase().endsWith(".epsf")
        			|| name.toLowerCase().endsWith(".icns")
        			|| name.toLowerCase().endsWith(".pict")){
            	Controller.setOpenFiles(new ProjectFile(name, path, "image"));
            	Controller.getEditor().addTab(name, path, new ImagePanel(ImageIO.read(file)));
        		
            }else{
            	Controller.setOpenFiles(new ProjectFile(name, path, "other"));
                Controller.getEditor().addTab(name, path, new EditorPanel(null, texts));
                
            }
            
            //Controller.getJFrame().setTitle("Comet " + Version.VERSION + " - " + path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void checkFileType(JLabel c, Object o, File file, TreeCellRenderer renderer, FileSystemView fileSystemView){

        try {
            if(new File(((File) o).getCanonicalPath() + File.separator + "project.proj").exists()){

                String type = Controller.getType(((File) o).getCanonicalPath());
                String name = PropertyReader.get(new File(((File) o).getCanonicalPath() + File.separator + "project.proj"), "name");

                if(type != null){

                    if(type.equals("Nukkit")){
                        c.setIcon(Controller.getDataObject().getNukkitIcon());
                        c.setText(name);

                    }else if(type.equals("pmmp")){
                        c.setIcon(Controller.getDataObject().getPMMPIcon());
                        c.setText(name);

                    }else{
                        c.setIcon(fileSystemView.getSystemIcon(file));
                        c.setText(fileSystemView.getSystemDisplayName(file));
                    }

                }else{
                    c.setIcon(fileSystemView.getSystemIcon(file));
                    c.setText(fileSystemView.getSystemDisplayName(file));
                }

            }else{
                c.setIcon(fileSystemView.getSystemIcon(file));
                c.setText(fileSystemView.getSystemDisplayName(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void checkTextType(JLabel c, Object o, File file, FileSystemView fileSystemView){
    	
    	String filename = file.getName().toLowerCase();
    	
    	if(filename.endsWith(".java")){
    		c.setIcon(Controller.getDataObject().getJavaIcon());
    		
    	}else if(filename.endsWith(".php")){
    		c.setIcon(Controller.getDataObject().getPHPIcon());
    		
    	}else if(filename.endsWith(".txt")
    			|| filename.endsWith(".text")){
    		c.setIcon(Controller.getDataObject().getTextIcon());
    		
    	}else if(filename.endsWith(".json")
    			|| filename.endsWith(".yml")
    			|| filename.endsWith(".yaml")
    			|| filename.endsWith(".properties")
    			|| filename.endsWith(".prop")){
    		c.setIcon(Controller.getDataObject().getDataIcon());
    		
    	}else if(filename.endsWith(".png")
    			|| filename.endsWith(".jpg")
    			|| filename.endsWith(".jpeg")
    			|| filename.endsWith(".bmp")
    			|| filename.endsWith(".jpg")
    			|| filename.endsWith(".gif")
    			|| filename.endsWith(".tiff")
    			|| filename.endsWith(".svg")
    			|| filename.endsWith(".ico")
    			|| filename.endsWith(".epsf")
    			|| filename.endsWith(".icns")
    			|| filename.endsWith(".pict")){
    		c.setIcon(Controller.getDataObject().getImageIcon());
    	
    	}else{
            c.setIcon(fileSystemView.getSystemIcon(file));
    	}

        c.setText(fileSystemView.getSystemDisplayName(file));
    }
}
