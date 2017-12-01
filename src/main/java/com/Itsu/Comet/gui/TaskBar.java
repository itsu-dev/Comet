package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class TaskBar extends JPanel {

    private JLabel label;

    public TaskBar() {
        super();
        label = new JLabel("準備完了");
        label.setForeground(Color.WHITE);

        this.setBackground(new Color(0, 128, 255));
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, Controller.getDataObject().getWindowWidth(), 30);
        this.add(label, BorderLayout.WEST);
    }

    public void setStatusText(String text) {
        label.setText(text);
    }

}
