package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Itsu.Comet.core.Controller;

public class NukkitTest {

    private static JDialog frame = new JDialog();

    public NukkitTest(){

    }

    private static void init(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel l1 = new JLabel("サーバーソフト");
        JTextField soft = new JTextField();
        JButton search = new JButton("参照");
        
        JButton start = new JButton("起動");

        l1.setBounds(10, 10, 200, 20);

        soft.setBounds(10, 30, 500, 20);
        soft.setEditable(false);

        search.setBounds(510, 30, 80, 20);
        
        start.setBounds(10, 70, 580, 20);

        panel.add(l1);
        panel.add(soft);
        panel.add(search);
        panel.add(start);

        frame.add(panel, BorderLayout.CENTER);
    }

    public static void showWindow(){
        frame.setTitle("Nukkit テスト - " + Controller.getNowProject());
        frame.setSize(600, 130);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        frame.setAlwaysOnTop(true);
        frame.setModal(true);

        init();

        frame.setVisible(true);
    }

}
