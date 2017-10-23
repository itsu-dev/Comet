package com.Itsu.Comet.editor;

import javax.swing.JTextPane;

public class IndentAction {
	public int getTabSize(JTextPane jp){
		String str = jp.getText();
		str = str.replaceAll("\r\n", "\n");
		str = str.substring(0, jp.getSelectionEnd()) + "a";
		String line[] = str.split("\n");
		int i=0;
		line[line.length-1] = line[line.length-1] + "a";
		while(line[line.length-1].substring(i,i+1).equals("\t")){
			i++;
		}
		return i;
	}
}
