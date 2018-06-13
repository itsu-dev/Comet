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
 * <p>Java�iPHP�j�\������IDE�v���W�F�N�g
 * <p>Made by Itsu(Twitter: @itsu_dev)
 * 
 * @author Itsu
 *
 */

public class JavaParser {

    private JTextPane pane;
    
    /*�\���f�[�^*/
    private String className;//�N���X��
    private String classType;//�N���X�C���q
    private String packageName;//�p�b�P�[�W��
    private String extend;//�p��
    private List<String> imports = new ArrayList<>();//�C���|�[�g
    private Map<String, JavaVariable> methodVars = new HashMap<>();//���\�b�h���Ƃ̕ϐ�
    private List<JavaVariable> fields = new ArrayList<>();//�t�B�[���h
    private List<String> methods = new ArrayList<>();//���\�b�h
    private List<String> implement = new ArrayList<>();//�C���^�[�t�F�[�X
    
    /*�ꎞ�ۑ��p*/
    private String type = "";//�C���q�^�C�v
    private String mod;//�\���
    private boolean variable = false;

    public JavaParser(JTextPane pane) {
        this.pane = pane;
    }

    @SuppressWarnings("resource")
	public void parse() {
        String target = pane.getText();

        Scanner s = new Scanner(target).useDelimiter("\\s");

        while(s.hasNext()) {
            String text = s.next();
            
            if(className != null) {
	            if(text.startsWith(className + "(")) {//�R���X�g���N�^�̏ꍇ(�C���q�Ȃ�)
	            	continue;
	            }
            }
            
            if(text.contains("/*")) {
            	String str;
            	while((str = s.next()).contains("*/")) {
            		continue;
            	}
            	continue;
            }

            switch(text) {

            	/*�C���|�[�g*/
                case "import":
                    String temp = s.next();
                    if(!imports.contains(temp)) {
                        imports.add(temp.replaceAll(";", ""));//�C���|�[�g
                        continue;
                    }
                    continue;
                    
                /*�p�b�P�[�W*/
                case "package":
                	packageName = s.next().replaceAll(";", "");//�p�b�P�[�W
                	continue;

                /*�A�N�Z�X�C���q*/
                case "public":
                case "private":
                case "protected":
                	String temp3 = s.next();
                	type = text;
                	
                	if(className != null) {
	                	if(temp3.startsWith(className)) {//�R���X�g���N�^�̏ꍇ
	                		continue;
	                	}
                	}
                	
                	switch(temp3) {
                	
                		case "class"://�N���X
                		case "interface"://�C���^�[�t�F�[�X
                		case "@interface"://�A�m�e�[�V����
                			this.classType = type;//�N���X�C���q
                			this.className = s.next();//temp1�̎� (=�N���X��)
                			
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
                			
                			s.next();//{(����)
                            continue;
                	
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
                			continue;
                			
                		case "final":
                        case "abstract":
                        case "transient":
                        case "synchronized":
                        	type += ";" + temp3;
                        	variable = true;
                        	continue;
                	}
                	
                	variable = true;
                	
                	continue;
                	
            }
            
            if(text.contains("\\{") || text.contains("\\}")
            		|| text.contains("\\(") || text.contains("\\)")
            			|| text.contains("\\[") || text.contains("\\]")) 
            				continue;
            
            if(variable) {
            	 String temp1 = text;//�^��
                 String temp2 = s.next().replaceAll(";", "");//�^���̎�(�ϐ���or���\�b�h��)
                 
                 if(mod != null) temp1 = mod;//�C���q��null�̏ꍇ
                 else temp1 = text;//�����łȂ��ꍇ
                 	
                 /*
                 if(temp2.contains("(")) {//���\�b�h��������
                 	String methodName = temp2.split("(")[0];//���\�b�h��
                 	if(!methodVars.containsKey(methodName)) {//���\�b�h���X�g�ɂȂ�������
                 		
                 	}
                 	continue;
                 	
                 } else {//�ϐ���������
                 	for(String str : imports) {
		                    if(str.endsWith(temp1)) {
		                        String names = "";
		                        
		                        for(JavaVariable var1 : fields) {
		                        	names += var1.getName() + ";";
		                        }
		                        
		                        if(!names.contains(temp2)) {
		                        	fields.add(new JavaVariable(temp2, type, str, true));//�t�B�[���h
		                         	variable = false;
		                        }
		                    }
                 	}
                 }*/
                 
                 mod = null;
                 continue;
            }

        }
        s.close();
        
        System.out.println("�N���X��: " + className + " (" + classType + ")");
        System.out.println("�p��: " + extend);
        System.out.println("�C���^�[�t�F�[�X: " + implement);
        System.out.println("�p�b�P�[�W: " + packageName);
        System.out.println("�C���|�[�g: " + imports);
        fields.forEach(var -> f += var.getName() + ", ");
        if(f.length() == 0) f = "aa";
        System.out.println("�t�B�[���h: " + f.substring(0, f.length() - 2));
        System.out.println("���\�b�h: " + methods);
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
