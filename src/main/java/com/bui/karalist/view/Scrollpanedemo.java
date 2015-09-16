package com.bui.karalist.view;

import java.awt.*;
import javax.swing.*;

public class Scrollpanedemo extends JFrame {
	public Scrollpanedemo()
	{
		JFrame frm = new JFrame("Scroll Pane demo");
		JPanel panel = new JPanel();
		panel. setLayout(new GridLayout(20,5));
		
		for(int i=0;i<100;i++)
		{
			panel. add(new JButton("Button no. " + (i+1)));
		}
		
		int v=ScrollPaneConstants. VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants. HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(panel,v,h);
		frm. getContentPane(). add(jsp);
		frm. setBounds(400,200,380,310);
		frm. setVisible(true);
	}

	public static void main(String a[]) {
		new Scrollpanedemo();
	}
}
