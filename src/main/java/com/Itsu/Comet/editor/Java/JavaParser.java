package com.Itsu.Comet.editor.Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextPane;

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

public class JavaParser {

    private JTextPane pane;
    
    /*構文データ*/
    private String className;//クラス名
    private String packageName;//パッケージ名
    private List<String> imports = new ArrayList<>();//インポート
    private Map<String, JavaVariable> methodVars = new HashMap<>();//メソッドごとの変数
    private List<JavaVariable> fields = new ArrayList<>();//フィールド
    private List<String> methods = new ArrayList<>();//メソッド
    
    /*一時保存用*/
    private String type = "";//修飾子タイプ
    private String mod;//予約語
    private boolean variable = false;

    public JavaParser(JTextPane pane) {
        this.pane = pane;
    }

    @SuppressWarnings("resource")
	public void parse() {
        String target = pane.getText();

        Scanner s = new Scanner(target).useDelimiter("\\s");

        while (s.hasNext()) {
            String text = s.next();
            
            if(className != null) {
	            if(text.startsWith(className + "(")) {//コンストラクタの場合(修飾子なし)
	            	continue;
	            }
            }

            switch(text) {

            	/*インポート*/
                case "import":
                    String temp = s.next();
                    if(!imports.contains(temp)) {
                        imports.add(temp.replaceAll(";", ""));//インポート
                        continue;
                    }
                    break;
                    
                /*パッケージ*/
                case "package":
                	packageName = s.next().replaceAll(";", "");//パッケージ
                	break;

                /*アクセス修飾子*/
                case "public":
                case "private":
                case "protected":
                	String temp3 = s.next();
                	type = text;
                	
                	if(className != null) {
	                	if(temp3.startsWith(className)) {//コンストラクタの場合
	                		break;
	                	}
                	}
                	
                	switch(temp3) {
                	
                		case "class"://クラス
                		case "interface"://インターフェース
                		case "@interface"://アノテーション
                			this.className = s.next();//temp1の次 (=クラス名)
                            break;
                	
                		case "static":
                			type += ";" + temp3;
                			String temp4 = s.next();
                			
                			switch(temp4) {
                				case "final":
                                case "transient":
                                case "synchronized":
                					type += ";" + temp4;
                					variable = true;
                			}
                			break;
                			
                		case "final":
                        case "abstract":
                        case "transient":
                        case "synchronized":
                        	type += ";" + temp3;
                        	variable = true;
                        	break;
                        	
                        default:
                        	mod = temp3;
                        	variable = true;
                	}
                	
                	break;
                	
            }
            
            if(variable) {
            	 String temp1;//型名
                 String temp2 = s.next().replaceAll(";", "");//型名の次(変数名orメソッド名)
                 
                 if(mod != null) temp1 = mod;//修飾子がnullの場合
                 else temp1 = text;//そうでない場合
                 	
                 if(temp2.contains("(")) {//メソッドだったら
                 	String methodName = temp2.split("(")[0];//メソッド名
                 	if(!methodVars.containsKey(methodName)) {//メソッドリストになかったら
                 		
                 	}
                 	break;
                 	
                 } else {//変数だったら
                 	for(String str : imports) {
		                    if(str.endsWith(temp1)) {
		                        String names = "";
		                        
		                        for(JavaVariable var1 : fields) {
		                        	names += var1.getName() + ";";
		                        }
		                        
		                        if(!names.contains(temp2)) {
		                        	fields.add(new JavaVariable(temp2, type, str, true));//フィールド
		                         	variable = false;
		                        }
		                    }
                 	}
                 }
                 
                 mod = null;
            }

        }
        s.close();
        
        System.out.println("クラス名: " + className);
        System.out.println("パッケージ: " + packageName);
        System.out.println("インポート: " + imports);
        fields.forEach(var -> f += var.getName() + ", ");
        System.out.println("フィールド: " + f.substring(0, f.length() - 2));
    }

    String f = "";
    
    public String getPackageName() {
    	return packageName;
    }
    
    public String getClassName() {
    	return className;
    }

    public List<JavaVariable> getFields() {
        return fields;
    }
    
    public List<String> getImports() {
        return imports;
    }
    
    public List<String> getMethods() {
        return methods;
    }

}
