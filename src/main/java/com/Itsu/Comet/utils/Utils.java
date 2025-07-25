package com.Itsu.Comet.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.ImageIcon;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.project.ProjectFile;

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

public class Utils {

    public static void writeFile(String fileName, String content) throws IOException {
        writeFile(fileName, new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
    }

    public static void writeFile(String fileName, InputStream content) throws IOException {
        writeFile(new File(fileName), content);
    }

    public static void writeFile(File file, String content) throws IOException {
        writeFile(file, new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
    }

    public static void writeFile(File file, InputStream content) throws IOException {
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream stream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = content.read(buffer)) != -1) {
            stream.write(buffer, 0, length);
        }
        stream.close();
        content.close();
    }

    public static String readFile(File file) throws IOException {
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        return readFile(new FileInputStream(file));
    }

    public static String readFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        return readFile(new FileInputStream(file));
    }

    public static String readFile(InputStream inputStream) throws IOException {
        return readFile(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    private static String readFile(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String temp;
        StringBuilder stringBuilder = new StringBuilder();
        temp = br.readLine();
        while (temp != null) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(temp);
            temp = br.readLine();
        }
        br.close();
        reader.close();
        return stringBuilder.toString();
    }

    public static void copyFile(File from, File to) throws IOException {
        if (!from.exists()) {
            throw new FileNotFoundException();
        }
        if (from.isDirectory() || to.isDirectory()) {
            throw new FileNotFoundException();
        }
        FileInputStream fi = null;
        FileChannel in = null;
        FileOutputStream fo = null;
        FileChannel out = null;
        try {
            if (!to.exists()) {
                to.createNewFile();
            }
            fi = new FileInputStream(from);
            in = fi.getChannel();
            fo = new FileOutputStream(to);
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        } finally {
            if (fi != null) fi.close();
            if (in != null) in.close();
            if (fo != null) fo.close();
            if (out != null) out.close();
        }
    }
    
    public static void expansionFiles(String from, String temp) {
    	try {
	    	from.replaceAll("\\\\", "/");
	    	temp.replaceAll("\\\\", "/");
	    		
	    	File[] files = new File(from).listFiles();
	    	for(File file : files) {
	    		if(file.isDirectory()) {
	    			expansionFiles(file.getAbsolutePath(), temp);
	    			
	    		} else {
	    			String tempFile = "./" + file.getPath().replaceFirst(Controller.getDataObject().getWorkspacePass(), "");
	    			List<String> pathes = Arrays.asList(tempFile.split("/"));
	    			pathes.remove("src");
	    			pathes.remove("resources");
	    			pathes.add(2, "temp");
	    			String path = "";
	    			
	    			for(String str : pathes) {
	    				path += str + "/";
	    			}
	    			
	    			new File(path).mkdirs();
	    			
	    			copyFile(file, new File(path + File.separator + file.getName()));
	    		}
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public static String getAllThreadDumps() {
        ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
        StringBuilder builder = new StringBuilder();
        for (ThreadInfo info : threads) {
            builder.append('\n').append(info);
        }
        return builder.toString();
    }


    public static String getExceptionMessage(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    public static UUID dataToUUID(String... params) {
        StringBuilder builder = new StringBuilder();
        for (String param : params) {
            builder.append(param);
        }
        return UUID.nameUUIDFromBytes(builder.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static UUID dataToUUID(byte[]... params) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        for (byte[] param : params) {
            try {
                stream.write(param);
            } catch (IOException e) {
                break;
            }
        }
        return UUID.nameUUIDFromBytes(stream.toByteArray());
    }

    public static String rtrim(String s, char character) {
        int i = s.length() - 1;
        while (i >= 0 && (s.charAt(i)) == character) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    public static boolean isByteArrayEmpty(final byte[] array) {
        for (byte b : array) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static long toRGB(byte r, byte g, byte b, byte a) {
        long result = (int) r & 0xff;
        result |= ((int) g & 0xff) << 8;
        result |= ((int) b & 0xff) << 16;
        result |= ((int) a & 0xff) << 24;
        return result & 0xFFFFFFFFL;
    }

    public static Object[][] splitArray(Object[] arrayToSplit, int chunkSize) {
        if (chunkSize <= 0) {
            return null;
        }

        int rest = arrayToSplit.length % chunkSize;
        int chunks = arrayToSplit.length / chunkSize + (rest > 0 ? 1 : 0);

        Object[][] arrays = new Object[chunks][];
        for (int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++) {
            arrays[i] = Arrays.copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
        }
        if (rest > 0) {
            arrays[chunks - 1] = Arrays.copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
        }
        return arrays;
    }

    public static int toInt(Object number) {
        return (int) Math.round((double) number);
    }

    public static String getType(String path){
        return PropertyReader.get(new File(path + File.separator + "project.proj"), "type");
    }

    public static String getProjectName(String path){
        String temp1[] = path.replaceAll("\\\\", "/").split("/");
        String projectName = null;
        int count = 0;

        for(String str : temp1){
            if(str.equals("workspace")){
                projectName = temp1[count + 1];
                break;
            }
            count++;
        }

        return projectName;
    }

    public static String getProjectPath(String path){
        String temp1[] = path.replaceAll("\\\\", "/").split("/");
        String result = "";
        int count = 0;

        for(String str : temp1){
            if(str.equals("workspace")){
                result = result + str + "/" + temp1[count + 1];
                break;
            }
            result = result + str + "/";
            count++;
        }

        return result;
    }
    
    public static ProjectFile buildProjectFile(String path) {
    	ProjectFile result = null;
    	result = new ProjectFile(getProjectName(path), path, getType(path));
    	return result;
    }

    public static String[] getPathArray(String path){
        return path.replaceAll("\\\\", "/").split("/");
    }
    
    public static ImageIcon getIcon(String filename) {
    	
    	if(filename.endsWith(".java")){
    		return Controller.getDataObject().getJavaIcon();
    		
    	}else if(filename.endsWith(".php")){
    		return Controller.getDataObject().getPHPIcon();
    		
    	}else if(filename.endsWith(".txt")
    			|| filename.endsWith(".text")){
    		return Controller.getDataObject().getTextIcon();
    		
    	}else if(filename.endsWith(".json")
    			|| filename.endsWith(".yml")
    			|| filename.endsWith(".yaml")
    			|| filename.endsWith(".properties")
    			|| filename.endsWith(".prop")){
    		return Controller.getDataObject().getDataIcon();
    		
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
    		return Controller.getDataObject().getImageIcon();
    	
    	}else{
    		return Controller.getDataObject().getTextIcon();
    	}
    	
    }
}