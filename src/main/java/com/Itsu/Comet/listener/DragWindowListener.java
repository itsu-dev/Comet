package com.Itsu.Comet.listener;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.Itsu.Comet.core.Controller;
import com.Itsu.Comet.gui.View;

public class DragWindowListener extends MouseAdapter implements ActionListener{
	  private final transient Point startPt = new Point();
	  private JPopupMenu pop;
	  private JInternalFrame window;
	  private JMenuItem close;
	  private JMenuItem max;
	  private JMenuItem min;
	  
	  private View frame;
	  private String key;
	  
	  @Override public void mousePressed(MouseEvent me) {
	    if (window == null) {
	      Object o = me.getSource();
	      if (o instanceof JPanel) {
	        window = (JInternalFrame) me.getComponent().getParent().getParent().getParent().getParent();
	      } else if (o instanceof JLabel) {
	        window = (JInternalFrame) me.getComponent().getParent().getParent();
	      }
	    }
	    startPt.setLocation(me.getPoint());
	  }
	  @Override public void mouseDragged(MouseEvent me) {
	    if (window != null) {
	      Point pt = new Point();
	      pt = window.getLocation(pt);
	      int x = pt.x - startPt.x + me.getX();
	      int y = pt.y - startPt.y + me.getY();
	      window.setLocation(x, y);
	    }
	  }
	  
	  @Override
	  public void mouseClicked(MouseEvent e){
		  if(e.getButton() == MouseEvent.BUTTON3){
			  close = new JMenuItem("閉じる");
			  max = new JMenuItem("最大化");
			  min = new JMenuItem("復元");
				
			  if(frame.isMaximum()){
				  max.setEnabled(false);
				  min.setEnabled(true);
			  }else{
				  max.setEnabled(true);
				  min.setEnabled(false);
			  }
			  
			  close.setPreferredSize(new Dimension(300, (int) close.getPreferredSize().getHeight()));
			  close.setName("close");
			  close.addActionListener(this);
			  max.setName("max");
			  max.addActionListener(this);
			  min.setName("min");
			  min.addActionListener(this);
			  
			  pop = new JPopupMenu();
			  
			  pop.add(close);
			  pop.add(max);
			  pop.add(min);
			  
			  pop.show(e.getComponent(), e.getX(), e.getY());
			  
		  }else if(e.getButton() == MouseEvent.BUTTON1){
			  if(e.getClickCount() == 2){
				  try{
					  if(frame.isMaximum()){
						  frame.setMaximum(false);
					  }else{
						  frame.setMaximum(true);
					  }
				  }catch(PropertyVetoException e1){
					  e1.printStackTrace();
				  }
			  }
		  }
	  }
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JMenuItem){
			switch(((JMenuItem) e.getSource()).getName()){
				case "close":
					try {
						Controller.closeEditor(frame, key);
						frame.setClosed(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "max":
					try {
						frame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "min":
					try {
						frame.setMaximum(false);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "icon":
					frame.setIconifiable(true);
					break;
			}
		}
	}
	
	public void setJInternalFrame(View frame){
		this.frame = frame;
	}
	
	public void setJInternalFrameKey(String key){
		this.key = key;
	}
	
	}