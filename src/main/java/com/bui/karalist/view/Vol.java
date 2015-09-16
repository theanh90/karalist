package com.bui.karalist.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

import com.bui.karalist.controller.ContrBaiHat;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vol extends JFrame {

	private JPanel contentPane;
	private JPanel okPane;
	private ContrBaiHat ctrBh;
	int[] minMaxVol;
	private static List<Integer> volSelected;
	private JButton btnOK;
	
	//singleton
	private static Vol instance = new Vol();
	public static Vol getInstance(){
		return instance;
	}
	//
	
	public static List<Integer> getSelectVol(){
		return volSelected;
	}
	
	public Vol() {
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(100, 5));		
		
		ctrBh = new ContrBaiHat();
		minMaxVol = ctrBh.findMinMaxVol();
		final int min = minMaxVol[0];
		final int max = minMaxVol[1];
		volSelected = new ArrayList<>();
//		final int min = 1;
//		final int max = 10;
		final int count = max - min + 1;
		final List<JCheckBox> lsChk = new ArrayList<JCheckBox>();
		for(int i = max; i>=min; i--){
			volSelected.add(i);
		}
		
		final JCheckBox checkAll = new JCheckBox("Tất cả ", true);
		checkAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					for (JCheckBox chk : lsChk) {
						chk.setSelected(true);
					}
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					if(volSelected.size() == count){
						for (JCheckBox chk : lsChk) {
							chk.setSelected(false);
						}
					}
				}
//				System.out.println(0);
			}
		});
		contentPane.add(checkAll);		
		
		for(int i = max; i>=min; i--){
			final int index = i;			
			JCheckBox checkBox = new JCheckBox("Vol " + i, true);
			lsChk.add(checkBox);
			checkBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {					
					if(e.getStateChange() == ItemEvent.SELECTED){
						volSelected.add(index);
						if(volSelected.size() == count){
							checkAll.setSelected(true);
						}
					}
					
					if(e.getStateChange() == ItemEvent.DESELECTED){
						volSelected.remove((Integer)index);
						checkAll.setSelected(false);
					}

				}
			});
			contentPane.add(checkBox);
		}
//		System.out.println(volSelected);
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(contentPane,v,h);

		getContentPane().add(jsp);
		okPane = new JPanel();
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInstance().setVisible(false);
			}
		});
		okPane.add(btnOK);
		getContentPane().add(okPane, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(120, 224);
		
	}

}
