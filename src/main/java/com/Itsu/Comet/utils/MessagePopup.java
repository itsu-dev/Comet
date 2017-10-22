package com.Itsu.Comet.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessagePopup {
	
	public MessagePopup(){
		
	}
	
	public static void information(String message){
		JOptionPane.showMessageDialog(new JFrame(), message, "情報", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void alert(String message){
		JOptionPane.showMessageDialog(new JFrame(), message, "警告", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void error(String message){
		JOptionPane.showMessageDialog(new JFrame(), message, "エラー", JOptionPane.ERROR_MESSAGE);
	}

}
