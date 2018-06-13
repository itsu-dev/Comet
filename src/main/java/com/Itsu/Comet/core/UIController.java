package com.Itsu.Comet.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.UIManager;

import com.Itsu.Comet.ui.SimpleSeparatorUI;
import com.Itsu.Comet.utils.Data;

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

public class UIController {
	
	private Data data = new Data();
	
	public UIController(){
        UIManager.put("Menu.background", Controller.getColors().get("MENU_BAR"));
        UIManager.put("Menu.selectionBackground", Controller.getColors().get("MENU_BAR_SELECT"));
        UIManager.put("Menu.foreground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("Menu.selectionForeground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("Menu.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("MenuItem.background", Controller.getColors().get("MENU_BAR"));
        UIManager.put("MenuItem.foreground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("MenuItem.selectionBackground", Controller.getColors().get("MENU_BAR_SELECT"));
        UIManager.put("MenuItem.selectionForeground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("MenuItem.preferredSize", new Dimension(300, 30));
        UIManager.put("MenuItem.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("Button.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("Button.focus", Controller.getColors().get(1));
        UIManager.put("Button.background", Controller.getColors().get(1));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", Controller.getColors().get(3));
        UIManager.put("TabbedPane.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("TabbedPane.foreground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("TabbedPane.selectedForeground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("TabbedPane.tabAreaBackground", Controller.getColors().get(5));
        UIManager.put("TabbedPane.contentAreaColor", Controller.getColors().get(5));
        UIManager.put("TabbedPane.shadow", Controller.getColors().get(5));
        UIManager.put("TabbedPane.darkShadow", Controller.getColors().get(5));
        UIManager.put("TabbedPane.light", Controller.getColors().get(5));
        UIManager.put("TabbedPane.highlight", Controller.getColors().get(5));
        UIManager.put("TabbedPane.tabAreaBackground", Controller.getColors().get(5));
        UIManager.put("TabbedPane.unselectedBackground", Controller.getColors().get(5));
        UIManager.put("TabbedPane.selected", Controller.getColors().get(5));
        UIManager.put("TabbedPane.selectHighlight", Controller.getColors().get(5));
        UIManager.put("TabbedPane.borderHightlightColor", Controller.getColors().get(5));
    	UIManager.put("TabbedPane.focus", Controller.getColors().get(5));
        UIManager.put("OptionPane.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.foreground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("OptionPane.warningDialog.titlePane.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.errorDialog.titlePane.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.informationDialog.titlePane.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.warningDialog.border.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.errorDialog.border.background", Controller.getColors().get("EDITOR"));
        UIManager.put("OptionPane.informationDialog.border.background", Controller.getColors().get("EDITOR"));
        UIManager.put("InternalFrame.useTaskBar", Boolean.TRUE);
        UIManager.put("InternalFrame.activeTitleBackground", Controller.getColors().get("TITLE_BAR"));
        UIManager.put("InternalFrame.activeTitleForeground", Color.WHITE);
        UIManager.put("InternalFrame.inactiveTitleBackground", Controller.getColors().get(1));
        UIManager.put("InternalFrame.inactiveTitleForeground", Color.WHITE);
        UIManager.put("InternalFrame.titleFont", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("InternalFrame.closeIcon", data.getCloseIcon());
        UIManager.put("InternalFrame.maximizeIcon", data.getMaxIcon());
        UIManager.put("InternalFrame.minimizeIcon", data.getMinIcon());
        UIManager.put("InternalFrame.iconifyIcon", data.getIconFlyIcon());
        UIManager.put("List.background", Controller.getColors().get("MENU_BAR"));
        UIManager.put("List.foreground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("List.selectionBackground", Controller.getColors().get("MENU_BAR_SELECT"));
        UIManager.put("List.selectionForeground", Controller.getColors().get("EDITOR_TEXT"));
        UIManager.put("List.focusCellHighlightBorder", Controller.getColors().get("MENU_BAR_SELECT"));
        UIManager.put("List.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        //UIManager.put("PopupMenuSeparatorUI", new SimpleSeparatorUI());
        UIManager.put("Label.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
        UIManager.put("CheckBox.font", new Font(Controller.getDataObject().getSystemFont(), Font.PLAIN, 12));
	}

}
