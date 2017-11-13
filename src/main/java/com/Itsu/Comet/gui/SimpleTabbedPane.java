package com.Itsu.Comet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.Itsu.Comet.core.Controller;

public class SimpleTabbedPane extends JTabbedPane {

      public SimpleTabbedPane() {
        super();
      }

      @Override
      public void addTab(String title, final Component content) {
        JPanel tab = new JPanel(new BorderLayout());
        tab.setOpaque(false);

        JLabel label = new JLabel(title);
        label.setForeground(Controller.getColors().get("EDITOR_TEXT"));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));

        JLabel button = new JLabel("x", JLabel.RIGHT);
        button.setPreferredSize(new Dimension(15, 15));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.LIGHT_GRAY);
        button.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                removeTabAt(indexOfComponent(content));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

        });

        tab.add(label,  BorderLayout.WEST);
        tab.add(button, BorderLayout.EAST);
        tab.setBorder(BorderFactory.createEmptyBorder(6, 1, 1, 1));

        super.addTab(null, content);

        setTabComponentAt(getTabCount() - 1, tab);
      }
}
