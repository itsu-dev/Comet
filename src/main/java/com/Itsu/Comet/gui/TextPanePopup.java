package com.Itsu.Comet.gui;

import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class TextPanePopup extends JPopupMenu {
	
	private JMenuItem save;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem choose;
	private JMenuItem find;
	private JMenuItem replace;
	
	public TextPanePopup() {
		super();
		
		initMenu();
		
        this.add(save);
        this.addSeparator();
        this.add(copy);
        this.add(paste);
        this.add(choose);
        this.addSeparator();
        this.add(find);
        this.add(replace);
        
		this.setBorderPainted(false);
	}
	
	private void initMenu() {
		
		save = new JMenuItem("保存");
		save.setActionCommand("save");
        save.setBorderPainted(false);
        //save.addActionListener(this);
        save.setPreferredSize(new Dimension(300, (int) save.getPreferredSize().getHeight()));
        
        copy = new JMenuItem("コピー");
        copy.setActionCommand("copy");
        copy.setBorderPainted(false);
        //save.addActionListener(this);
        copy.setPreferredSize(new Dimension(300, (int) copy.getPreferredSize().getHeight()));
        
        paste = new JMenuItem("貼り付け");
        paste.setActionCommand("paste");
        paste.setBorderPainted(false);
        //paste.addActionListener(this);
        paste.setPreferredSize(new Dimension(300, (int) paste.getPreferredSize().getHeight()));
        
        choose = new JMenuItem("すべて選択");
        choose.setActionCommand("choose");
        choose.setBorderPainted(false);
        //choose.addActionListener(this);
        choose.setPreferredSize(new Dimension(300, (int) choose.getPreferredSize().getHeight()));
        
        find = new JMenuItem("検索");
        find.setActionCommand("find");
        find.setBorderPainted(false);
        //find.addActionListener(this);
        find.setPreferredSize(new Dimension(300, (int) find.getPreferredSize().getHeight()));
        
        replace = new JMenuItem("置換");
        replace.setActionCommand("replace");
        replace.setBorderPainted(false);
        //replace.addActionListener(this);
        replace.setPreferredSize(new Dimension(300, (int) replace.getPreferredSize().getHeight()));
        
	}

}
