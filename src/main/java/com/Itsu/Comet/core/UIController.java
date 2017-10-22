package com.Itsu.Comet.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.UIManager;

import com.Itsu.Comet.utils.Data;

public class UIController {
	
	private Data data = new Data();
	
	public UIController(){
        UIManager.put("Menu.background", new Color(63,81,181)); //Indigo 500
        UIManager.put("Menu.selectionBackground", new Color(48,63,159)); //Indigo 700
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.selectionForeground", Color.WHITE);
        UIManager.put("Menu.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("MenuItem.background", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.BLACK);
        UIManager.put("MenuItem.selectionBackground", new Color(63,81,181));
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.preferredSize", new Dimension(300, 30));
        UIManager.put("MenuItem.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("Label.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("CheckBox.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("Button.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("Button.focus", new Color(159,168,218)); //Indigo 200
        UIManager.put("Button.background", new Color(159,168,218)); //Indigo 200
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", new Color(92,107,192)); //Indigo 400
        UIManager.put("TabbedPane.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("TabbedPane.foreground", Color.BLACK);
        UIManager.put("InternalFrame.useTaskBar", Boolean.TRUE);
        UIManager.put("InternalFrame.activeTitleBackground", new Color(92,107,192)); //Indigo 400
        UIManager.put("InternalFrame.activeTitleForeground", Color.WHITE);
        UIManager.put("InternalFrame.inactiveTitleBackground", new Color(159,168,218)); //Indigo 200
        UIManager.put("InternalFrame.inactiveTitleForeground", Color.WHITE);
        UIManager.put("InternalFrame.titleFont", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("InternalFrame.closeIcon", data.getCloseIcon());
        UIManager.put("InternalFrame.maximizeIcon", data.getMaxIcon());
        UIManager.put("InternalFrame.minimizeIcon", data.getMinIcon());
        UIManager.put("InternalFrame.iconifyIcon", data.getIconFlyIcon());
	}

}
