package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.listener.ListListener;
import com.Itsu.Comet.ui.BlackScrollBarUI;

public class AutoGUI extends JFrame {

    private JPanel panel;

    private int offset;

    private JList<String> list;
    private JScrollPane scroll;

    private EditorPanel editor;

    public AutoGUI(EditorPanel editor){
        this.editor = editor;

        this.setUndecorated(true);
        this.setTitle("Comet AutoComplete");
        this.setResizable(true);
        this.setBackground(Controller.getColors().get(5));
        this.setSize(200, 300);
        this.setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);

        initComponent();
    }

    private void initComponent() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Controller.getColors().get(5));
        panel.setBorder(new LineBorder(Controller.getColors().get(4)));

        String data[] = {"add", "addBar", "addComponent", "addListener", "addScrollBar", "back", "cross", "dimension", "fall", "getBar", "getComponent", "getComponents", "getDimension", "getWidth", "getX"};
        list = new JList<>(data);
        list.addMouseListener(new ListListener(this));
        scroll = new JScrollPane(list);

        scroll.setPreferredSize(new Dimension(200, 300));
        scroll.getVerticalScrollBar().setUI(new BlackScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new BlackScrollBarUI());

        panel.add(scroll, BorderLayout.CENTER);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public JList<String> getList() {
        return this.list;
    }

    public EditorPanel getEditorPanel() {
        return this.editor;
    }

    public int getOffset() {
        return this.offset;
    }

}
