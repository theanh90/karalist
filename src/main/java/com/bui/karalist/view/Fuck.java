package com.bui.karalist.view;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Fuck {

	public static void main(String[] args) throws Exception {
		try {
			doSomething();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

	public static void doSomething() throws Exception {
		try {
			System.out.println("A");
			throw new Exception("B");
		} catch (Exception ex) {
			System.out.println("C");
			throw ex;
		} finally {
			System.out.println("D");
		}
	}

}
