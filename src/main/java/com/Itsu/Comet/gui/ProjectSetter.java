package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Itsu.Comet.core.Controller;

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

public class ProjectSetter extends JFrame{
	
	private static JDialog frame = new JDialog();
	
	public ProjectSetter(){
		
	}
	
	private static void init(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		JLabel l1 = new JLabel("プロジェクト名(必須): ");
		JTextField className = new JTextField();
		
		JCheckBox pmmp = new JCheckBox("Pocketmine-MP");
		JCheckBox Nukkit = new JCheckBox("Nukkit/Jupiter");
		JCheckBox Java = new JCheckBox("Java");
		
		JButton ok = new JButton("完了");
		
		l1.setBounds(10, 10, 200, 20);
		panel.add(l1);
		
		className.setText("");
		className.setBounds(220, 10, 370, 20);
		className.setFont(new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
		panel.add(className);
		
		pmmp.setBounds(10, 100, 400, 20);
		pmmp.setBackground(Color.WHITE);
		pmmp.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if(pmmp.isSelected()){
					if(Nukkit.isSelected()) Nukkit.setSelected(false);
					if(Java.isSelected()) Java.setSelected(false);
				}else{
					if(Nukkit.isSelected()) Nukkit.setSelected(true);
					if(Java.isSelected()) Java.setSelected(true);
				}
			}
		});
		panel.add(pmmp);
		
		Nukkit.setBounds(10, 140, 400, 20);
		Nukkit.setBackground(Color.WHITE);
		Nukkit.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if(Nukkit.isSelected()){
					if(pmmp.isSelected()) pmmp.setSelected(false);
					if(Java.isSelected()) Java.setSelected(false);
				}else{
					if(pmmp.isSelected()) pmmp.setSelected(true);
					if(Java.isSelected()) Java.setSelected(true);
				}
			}
		});
		panel.add(Nukkit);
		
		Java.setBounds(10, 180, 400, 20);
		Java.setBackground(Color.WHITE);
		Java.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if(Java.isSelected()){
					if(pmmp.isSelected()) pmmp.setSelected(false);
					if(Nukkit.isSelected()) Nukkit.setSelected(false);
				}else{
					if(pmmp.isSelected()) pmmp.setSelected(true);
					if(Nukkit.isSelected()) Nukkit.setSelected(true);
				}
			}
		});
		panel.add(Java);
		
		ok.setBounds(10, 220, 580, 20);
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(className.getText() == null || className.getText().equals("")){
					Controller.alert("プロジェクト名が設定されていません。");
					return;
				}
				
				String type = null;
				
				if(pmmp.isSelected()) type = "pmmp";
				else if(Nukkit.isSelected()) type = "Nukkit";
				else if(Java.isSelected()) type = "Java";
				else{
					Controller.alert("プロジェクトタイプを設定してください。");
					return;
				}
				
				Controller.makeProject(className.getText(), type);
				
				frame.setVisible(false);
			}
		});
		panel.add(ok);
		
		frame.add(panel, BorderLayout.CENTER);
	}
	
	public static void showWindow(){
		frame.setTitle("新規プロジェクト");
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		frame.setModal(true);
		
		init();
		
		frame.setVisible(true);
	}
}
