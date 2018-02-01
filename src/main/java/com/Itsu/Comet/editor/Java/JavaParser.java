package com.Itsu.Comet.editor.Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.Itsu.Comet.gui.EditorPanel;
import com.google.common.reflect.ClassPath;

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

    private EditorPanel pane;

    /*構文データ*/
    private String className;//クラス名
    private String classType;//クラス修飾子
    private String packageName;//パッケージ名
    private String extend;//継承
    private List<String> imports = new ArrayList<>();//インポート
    private Map<String, JavaVariable> methodVars = new HashMap<>();//メソッドごとの変数
    private List<JavaVariable> fields = new ArrayList<>();//フィールド
    private List<String> methods = new ArrayList<>();//メソッド
    private List<String> implement = new ArrayList<>();//インターフェース

    /*一時保存用*/
    private String type = "";//修飾子タイプ
    private boolean variable = false;

    public JavaParser(EditorPanel pane) {
        this.pane = pane;
    }

    @SuppressWarnings("resource")
    public void parse() {
        String target = pane.getEditor().getText();

        className = null;
        classType = null;
        packageName = null;
        extend = null;
        imports.clear();
        methodVars.clear();
        fields.clear();
        methods.clear();
        implement.clear();

        type = null;
        variable = false;

        Scanner s = new Scanner(target).useDelimiter("\\s");

        while(s.hasNext()) {
            String text = s.next();

            if(className != null) {
                if(text.startsWith(className + "(")) {//コンストラクタの場合(修飾子なし)
                    continue;
                }
            }

            if(text.contains("/*")) {
                while((s.next()).contains("*/")) {
                    continue;
                }
                continue;
            }

            switch(text) {

                /*インポート*/
                case "import":
                    String temp = s.next();
                    if(!imports.contains(temp)) {
                        imports.add(temp.replaceAll(";", ""));//インポート
                        continue;
                    }
                    continue;

                /*パッケージ*/
                case "package":
                    packageName = s.next().replaceAll(";", "");//パッケージ
                    continue;

                /*アクセス修飾子*/
                case "public":
                case "private":
                case "protected":
                    String temp3 = s.next();
                    type = text;

                    if(className != null) {
                        if(temp3.startsWith(className)) {//コンストラクタの場合
                            continue;
                        }
                    }

                    switch(temp3) {

                        case "class"://クラス
                        case "interface"://インターフェース
                        case "@interface"://アノテーション
                            this.classType = type;//クラス修飾子
                            this.className = s.next();//temp1の次 (=クラス名)

                            if(s.next().equals("extends")) {
                                this.extend = s.next();
                            }

                            if(s.next().equals("implements")) {
                                List<String> temp1 = new ArrayList<>();
                                String temp2 = "";
                                while((temp2 = s.next()).endsWith(",")) {
                                    temp1.add(temp2.replaceAll(",", ""));
                                }
                                temp1.add(temp2.replaceAll(",", ""));
                                this.implement.addAll(temp1);
                                temp1 = null;
                            }

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
                            if(variable) {
                                 String temp0 = text;//修飾子
                                 String temp1 = temp3;//型名
                                 String temp2 = s.next().replaceAll(";", "");//型名の次(変数名orメソッド名)

                                 if(temp2.contains("//")) {
                                     int offset = 0;
                                     char c = "/".charAt(0);

                                     for(int i = 0;i < temp2.length();i++) {
                                         if(temp2.charAt(i) == c) {
                                             if(temp2.charAt(i + 1) == c) {
                                                 offset = i;
                                                 break;
                                             }
                                         }
                                     }

                                     if(offset != 0) {
                                         temp2 = temp2.substring(0, temp2.length() - offset);
                                         System.out.println(temp2);
                                     }
                                 }

                                    if(temp1.startsWith(className + "\\(")) {
                                        continue;//コンストラクタ
                                    }

                                    if(temp1.equals("static")) {
                                        temp2 = s.next().replaceAll(";", "");
                                    }

                                    System.out.println("VAR/ME: " + temp0 + " " + temp1 + " " + temp2);

                                    if(temp2.contains("\\(")) {//メソッドだったら
                                        String methodName = temp2.split("\\(")[0];//メソッド名
                                        System.out.println("METHOD: " + methodName);
                                        if(!methodVars.containsKey(methodName)) {//メソッドリストになかったら
                                            methods.add(temp2);
                                        }
                                        continue;

                                    } else {//変数だったら
                                        for(String str : imports) {
                                               if(str.endsWith(temp1)) {
                                                   String names = "";

                                                   for(JavaVariable var1 : fields) {
                                                       names += var1.getName() + ";";
                                                   }
                                                   
                                                   try {
	                                                   List<String> langClass = new ArrayList<>();
	                                                   ClassLoader loader = Thread.currentThread().getContextClassLoader();
	                                                   Set<Class<?>> allClasses = ClassPath.from(loader)
	                                                		   .getTopLevelClasses("com.Itsu.Comet.core")
	                                                		   .stream()
	                                                		   .map(info -> info.load())
	                                                		   .collect(Collectors.toSet());
	                                                   
	                                                   for(Class<?> cl : allClasses) {
	                                                	   langClass.add(cl.getSimpleName());
	                                                   }
	
	                                                   if(!names.contains(temp2) || langClass.contains(temp2)) {
	                                                       fields.add(new JavaVariable(temp2, temp0, str, true));//フィールド
	                                                       try {
	                                                           int offset = pane.getEditor().getText().indexOf(temp2);
	                                                           ((JavaSyntaxHighliter) pane.getEditor().getStyledDocument()).setVariable(offset, temp2);
	                                                       } catch(Exception e){}
	                                                       variable = false;
	                                                   }
                                                   } catch(Exception e) {
                                                	   e.printStackTrace();
                                                   }
                                               }
                                        }
                                    }
                                continue;
                            }
                    }

                    variable = true;
                    break;

            }

            continue;

        }
        s.close();

        System.out.println("クラス名: " + className + " (" + classType + ")");
        System.out.println("継承: " + extend);
        System.out.println("インターフェース: " + implement);
        System.out.println("パッケージ: " + packageName);
        System.out.println("インポート: " + imports);
        fields.forEach(var -> f += var.getName() + ", ");
        if(f.length() == 0) f = "aa";
        System.out.println("フィールド: " + f.substring(0, f.length() - 2));
        System.out.println("メソッド: " + methods);
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
