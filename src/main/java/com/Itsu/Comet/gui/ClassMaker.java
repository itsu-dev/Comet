package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/*
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
*/

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

public class ClassMaker extends JFrame{
	
	public static final int PMMP = 0;
	public static final int NUKKIT = 1;
	public static final int JUPITER = 2;
	public static final int JAVA = 3;
	
	public ClassMaker(int type){
		this.setTitle("新規クラス");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		switch(type){
			case ClassMaker.PMMP:
				initPMMP();
				break;
				
			case ClassMaker.NUKKIT:
			case ClassMaker.JUPITER:
				initNukkit();
				break;
				
			case ClassMaker.JAVA:
				initJava();
				break;
		}
		
		this.setVisible(true);
	}
	
	private void initPMMP(){
		
	}
	
	private void initNukkit(){
		/*
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		JLabel l1 = new JLabel("クラス名(必須): ");
		JTextField className = new JTextField();
		
		JLabel l2 = new JLabel("継承: ");
		JTextField extend = new JTextField();
		
		JLabel l3 = new JLabel("実装: ");
		JTextField implement = new JTextField();
		
		JCheckBox make = new JCheckBox("雛形の作成");
		JCheckBox command = new JCheckBox("コマンド処理");
		JCheckBox enable = new JCheckBox("起動処理");
		JCheckBox disable = new JCheckBox("終了処理");
		
		JCheckBox event = new JCheckBox("イベント処理");
		JLabel l4 = new JLabel("イベント(;で区切る): ");
		JTextField events = new JTextField();
		
		JButton ok = new JButton("作成");
		*/
	}
	
	/*
	private void initJupiter(){
		
	}
	*/
	
	private void initJava(){
		
	}

}