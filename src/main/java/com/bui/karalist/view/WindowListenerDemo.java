package com.bui.karalist.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*; 

import javax.swing.JFrame;

class WindowListenerDemo extends Frame implements WindowListener
{
	public WindowListenerDemo(String title)
	{
		super(title);
		addWindowListener(this);
		setSize(300,200);
		setVisible(true);
	}
	public  void windowActivated(WindowEvent e){
		System.out.println("hhhhhhhhhhhhhhh");
		this.setBackground(Color.blue);
	}
	public  void windowClosed(WindowEvent e){}
	public  void windowClosing(WindowEvent e){
		System.out.println("fffffffffff");
		this.setBackground(Color.red);
	}
	public  void windowDeactivated(WindowEvent e){}
	public  void windowDeiconified(WindowEvent e){}
	public  void windowIconified(WindowEvent e){}
	public  void windowOpened(WindowEvent e){}
	
	public static void main(String args[])
	{
		WindowListenerDemo f=new WindowListenerDemo("I have been Frameed!!!");
	
		
	
	}
}
