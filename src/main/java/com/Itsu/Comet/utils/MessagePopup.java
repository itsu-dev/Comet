package com.Itsu.Comet.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.Itsu.Comet.ui.BlackScrollBarUI;

public class MessagePopup {

    public MessagePopup(){

    }

    public static void information(String message){
        JOptionPane.showMessageDialog(new JFrame(), message, "情報", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void alert(String message){
        JOptionPane.showMessageDialog(new JFrame(), message, "警告", JOptionPane.WARNING_MESSAGE);
    }

    public static void exception(String message, Exception e){
        JFrame frame = new JFrame("エラー");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        JLabel label = new JLabel(message);
        label.setIcon(UIManager.getIcon ("OptionPane.errorIcon"));

        JTextArea area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);

        pane.getHorizontalScrollBar().setUI(new BlackScrollBarUI());
        pane.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        pane.setPreferredSize(new Dimension(400, 250));
        pane.setBorder(new LineBorder(Color.GRAY));

        String trace = e.getClass().getName() + ": " + e.getMessage() + "\n";

        for(StackTraceElement element : e.getStackTrace()) {
            trace += "    " + element.toString() + "\n";
        }

        area.setText(trace);
        area.setEditable(false);

        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.getContentPane().add(pane, BorderLayout.CENTER);

        frame.setVisible(true);
        Toolkit.getDefaultToolkit().beep();
    }

    public static void error(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "エラー", JOptionPane.ERROR_MESSAGE);
    }

}
