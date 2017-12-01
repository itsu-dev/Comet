package com.Itsu.Comet.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class Colors {

    private Map<String, Color> color = new HashMap<>();
    private Map<String, Color> java = new HashMap<>();
    private Map<String, Color> php = new HashMap<>();
    private Map<String, Color> tab = new HashMap<>();

    public Colors(){

    }

    public void initSkinColor(String type){
        try {

            String data = Utils.readFile(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Colors.properties"));
            String colors[] = data.split("\n");
            int i = 1;

            for(String src : colors) {
                String temp[];
                String temp1;

                try{
                    temp = src.split("=");
                    temp1 = temp[0];
                }catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                if(temp1.equals("") || temp1.startsWith("#")) {
                    continue;

                } else if(temp1.equals(type + "_EDITOR_TEXT")) {
                    String[] c = temp[1].split(",");
                    color.put("EDITOR_TEXT", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_EDITOR")) {
                    String[] c = temp[1].split(",");
                    color.put("EDITOR", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_LINE_NUMBER")) {
                    String[] c = temp[1].split(",");
                    color.put("LINE_NUMBER", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_TITLE_BAR")) {
                    String[] c = temp[1].split(",");
                    color.put("TITLE_BAR", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_MENU_BAR")) {
                    String[] c = temp[1].split(",");
                    color.put("MENU_BAR", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_MENU_BAR_SELECT")) {
                    String[] c = temp[1].split(",");
                    color.put("MENU_BAR_SELECT", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_MENU_BAR_TEXT")) {
                    String[] c = temp[1].split(",");
                    color.put("MENU_BAR_TEXT", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_BACKGROUND")) {
                    String[] c = temp[1].split(",");
                    color.put("BACKGROUND", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + i)) {
                    String[] c = temp[1].split(",");
                    color.put(String.valueOf(i), new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initJavaColor(String type){
        try {

            String data = Utils.readFile(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/JavaSyntaxHighlighterColors.properties"));
            String colors[] = data.split("\n");

            for(String src : colors) {
                String temp[];
                String temp1;

                try{
                    temp = src.split("=");
                    temp1 = temp[0];
                }catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                if(temp1.equals("") || temp1.startsWith("#")) {
                    continue;

                } else if(temp1.equals(type + "_keyword")) {
                    String[] c = temp[1].split(",");
                    java.put("keyword", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_annotation")) {
                    String[] c = temp[1].split(",");
                    java.put("annotation", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_returnToken")) {
                    String[] c = temp[1].split(",");
                    java.put("returnToken", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_comment")) {
                    String[] c = temp[1].split(",");
                    java.put("comment", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_oneLineCom")) {
                    String[] c = temp[1].split(",");
                    java.put("oneLineCom", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_muiltLineCom")) {
                    String[] c = temp[1].split(",");
                    java.put("muiltLineCom", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_text")) {
                    String[] c = temp[1].split(",");
                    java.put("text", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initPHPColor(String type){
        try {

            String data = Utils.readFile(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/PHPSyntaxHighlighterColors.properties"));
            String colors[] = data.split("\n");

            for(String src : colors) {
                String temp[];
                String temp1;

                try{
                    temp = src.split("=");
                    temp1 = temp[0];
                }catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                if(temp1.equals("") || temp1.startsWith("#")) {
                    continue;

                } else if(temp1.equals(type + "_keyword")) {
                    String[] c = temp[1].split(",");
                    php.put("keyword", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;
                    
                } else if(temp1.equals(type + "_keyword1")) {
                    String[] c = temp[1].split(",");
                    php.put("keyword1", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_keyword2")) {
                    String[] c = temp[1].split(",");
                    php.put("keyword2", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_variable")) {
                    String[] c = temp[1].split(",");
                    php.put("variable", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_returnToken")) {
                    String[] c = temp[1].split(",");
                    php.put("returnToken", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_comment")) {
                    String[] c = temp[1].split(",");
                    php.put("comment", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_oneLineCom")) {
                    String[] c = temp[1].split(",");
                    php.put("oneLineCom", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_muiltLineCom")) {
                    String[] c = temp[1].split(",");
                    php.put("muiltLineCom", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;

                } else if(temp1.equals(type + "_text")) {
                    String[] c = temp[1].split(",");
                    php.put("text", new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Color> getColors(){
        return color;
    }

    public Map<String, Color> getJavaColors(){
        return java;
    }
    
    public Map<String, Color> getPHPColors(){
        return php;
    }

    public Map<String, Color> getTabColor(String type) {
        try {

            String data = Utils.readFile(this.getClass().getClassLoader().getResourceAsStream("Comet/datas/Colors.properties"));
            String colors[] = data.split("\n");

            for(String src : colors) {
                String temp[];
                String temp1;

                try{
                    temp = src.split("=");
                    temp1 = temp[0];
                }catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                if(temp1.equals("") || temp1.startsWith("#")) {
                    continue;

                } else if(temp1.equals(type + 1)) {
                    String[] c = temp[1].split(",");
                    tab.put(String.valueOf(1), new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));

                } else if(temp1.equals(type + 4)) {
                    String[] c = temp[1].split(",");
                    tab.put(String.valueOf(4), new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2])));
                }
            }

            return tab;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
