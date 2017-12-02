package com.Itsu.Comet.editor.Java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

public class AutoComplete<T> {

    private Class<? extends T> classData;
    private Method[] methods;
    private Field[] fields;

    private Map<String, String> mNames;
    private Map<String, String> fNames;

    private List<String> methodNames;
    private List<String> fieldNames;

    private List<String> datas;

    public AutoComplete() {

    }

    public List<String> autoComplete(Class<? extends T> clazz) {

        classData = clazz;
        methods = classData.getMethods();
        fields = classData.getFields();

        mNames = new HashMap<>();
        fNames = new HashMap<>();

        methodNames = new ArrayList<>();
        fieldNames = new ArrayList<>();

        datas = new ArrayList<>();

        for(Method method : methods) {
            StringBuffer sb = new StringBuffer();
            sb.append(method.getName());
            sb.append("(");

            if(method.getParameterCount() > 0) {
                for(Parameter param : method.getParameters()) {
                    sb.append(param.getType().getSimpleName() + ", ");
                }

                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append(")");
            sb.append(" : ");
            sb.append(method.getReturnType().getSimpleName());

            mNames.put(sb.toString(), sb.toString().split(" : ")[0]);
        }

        for(Field field : fields) {
            StringBuffer sb = new StringBuffer();
            sb.append(field.getName());
            sb.append(" : ");
            sb.append(field.getType().getSimpleName());

            fNames.put(sb.toString(), sb.toString().split(" : ")[0]);
        }

        for(String str : mNames.keySet()) {
            methodNames.add(str);
        }

        for(String str : fNames.keySet()) {
            fieldNames.add(str);
        }

        Collections.sort(methodNames);
        Collections.sort(fieldNames);

        datas.addAll(methodNames);
        datas.addAll(fieldNames);

        for(String str : datas) {
            System.out.println(str);
        }

        return datas;

    }

    public Map<String, String> getMethods() {
        return this.mNames;
    }

    public Map<String, String> getFields() {
        return this.fNames;
    }

}
