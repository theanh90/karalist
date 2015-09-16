package com.bui.karalist.view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

import com.bui.karalist.controller.ContrYeuThich;
import com.bui.karalist.model.BaiHat;
import com.bui.karalist.model.Role;
//import com.sun.j3d.utils.applet.MainFrame;

public class PnlInfo extends JPanel {
	private static JCheckBox chkBookmark;
	private static JTextArea txtaSong;
	private static int idBookmark;
	private static int check_exist;
	private static MainForm mainForm;
		
	
	private static PnlInfo instance = new PnlInfo();
	public static PnlInfo getInstance(MainForm mForm){
		mainForm = mForm;
		return instance;		
	}
	
	public PnlInfo() {
		this.setVisible(false);
		
		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				mainForm.scrList.setViewportView(mainForm.tblList);
				mainForm.btnBookMark.setVisible(true);
				if(mainForm.btnBookMark.getText() == "Thích"){
					mainForm.btnBookMark.setText("Tất cả");					
				}else{
					mainForm.btnBookMark.setText("Thích");
				}
				mainForm.btnBookMark.doClick();
			}
		});
		
		txtaSong = new JTextArea();
		
		chkBookmark = new JCheckBox("Thêm vào Thích", false);
		chkBookmark.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && check_exist == 0){
//					System.out.println("da them: " + idBookmark);
					ContrYeuThich.AddToBookmark(idBookmark);
					check_exist = 1;
				}
				if(e.getStateChange() == ItemEvent.DESELECTED && check_exist == 1){
//					System.out.println("da xoa: " + idBookmark);
					ContrYeuThich.deleteYeuThich(idBookmark);
					check_exist = 0;
				}
				
				
			}
		});
		
		JButton btnModify = new JButton("Sửa");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.checkPermission(Role.UPDATE);
					System.out.println("Đã SỬA thành công :D");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Login.checkPermission(Role.DELETE)){
						System.out.println("Đã XÓA thành công :D");						
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chkBookmark)
									.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
									.addComponent(btnModify)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDelete))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(txtaSong, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtaSong, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chkBookmark)
						.addComponent(btnDelete)
						.addComponent(btnModify))
					.addGap(14))
		);
		setLayout(groupLayout);		

	}
	
	public static void PutSongInfo(int id_song){
		BaiHat bh = new BaiHat();
		bh = mainForm.ctrBH.findBaiHatById(id_song);
		String ms = Integer.toString(bh.getId());		
		String name = bh.getName();
		String author = bh.getAuthor();
		String lyric = bh.getFullLyric();
		
		String info =" Mã số: " + ms + "\n Tên: " + name.toUpperCase() + "\n Tác giả: " + author + "\n\n Lời bài hát: \n" + lyric;
		txtaSong.setText(info);
		idBookmark = id_song;
		
		BaiHat bh_bookmark = ContrYeuThich.findYeuThichById(id_song);
		if(bh_bookmark == null){
			check_exist = 0;
			chkBookmark.setSelected(false);
		}else{
			check_exist = 1;
			chkBookmark.setSelected(true);
		}
		
		
	}
}
