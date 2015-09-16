package com.bui.karalist.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.bui.karalist.controller.ContrBaiHat;
import com.bui.karalist.controller.ContrYeuThich;
import com.bui.karalist.model.BaiHat;

public class MainForm extends JFrame implements KeyListener{
	private JTextField textSearch;
	public JTable tblList;
	private JButton btnTypeSearch;
	public JScrollPane scrList;
	private DefaultTableModel model;
	public ContrBaiHat ctrBH = new ContrBaiHat();;
//	private JPanel pnlInfo;
	private JTextArea txtaInfo;
	final JButton btnBookMark;
	
	public MainForm(){				
		
		//========================== JFrame ==========================
		this.setTitle("Tra cứu danh sách Karaoke");
		this.setSize(400, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(this);
		textSearch.setColumns(10);
		
		btnTypeSearch = new JButton("Kiểu");
		btnTypeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeSearch.getInstance().setVisible(true);
				TypeSearch.getInstance().setAlwaysOnTop(true);
			}
		});		
		
		
		tblList = new JTable();
		tblList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
	                e.consume();
	                eventTable();
	            }
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		scrList = new JScrollPane(tblList);				
				
		JButton btnVol = new JButton("Vol");
		btnVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vol.getInstance().setVisible(true);;
			}
		});
		
		btnBookMark = new JButton("Thích");
		btnBookMark.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnBookMark.getText() == "Thích"){
					List<BaiHat> lsBookmark = ContrYeuThich.findYeuThichByName("");
					PutDataToTable(lsBookmark);
					btnBookMark.setText("Tất cả");
				}else{
					List<BaiHat> list = ctrBH.findBaiHatByName("");
					PutDataToTable(list);
					btnBookMark.setText("Thích");
				}
			}
		});
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.getInstance().setVisible(true);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrList, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnVol)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBookMark)
							.addGap(111)
							.addComponent(btnLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnTypeSearch)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTypeSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrList, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVol)
						.addComponent(btnBookMark)
						.addComponent(btnLogin))
					.addGap(8))
		);
		getContentPane().setLayout(groupLayout);
		
		List<BaiHat> list = ctrBH.findBaiHatByName("");
		this.PutDataToTable(list);
		
	}
	
	private void eventTable(){
		int index_row = tblList.getSelectedRow();
        int ms = (int)model.getValueAt(index_row, 0);
        btnBookMark.setVisible(false);
        PnlInfo.getInstance(this).setVisible(true);;
        PnlInfo.PutSongInfo(ms);
        scrList.setViewportView(PnlInfo.getInstance(this));
	}
	
	public void PutDataToTable(List<BaiHat> ls){

		model = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
        //Set Column Title
        Vector column = new Vector();
        column.add("Mã Số");
        column.add("Tên - Lời Bài Hát");
        model.setColumnIdentifiers(column);
        
        //comlumn data
        for (int i = 0; i < ls.size(); i++) {
            BaiHat bh = (BaiHat)ls.get(i);
            Vector row = new Vector();
            row.add(bh.getId());
            
            String f = (bh.getName() + " - " + bh.getFirstLyric().toLowerCase());
            row.add(f);
            model.addRow(row);
        }
        
        
        tblList.setModel(model);
        tblList.getColumn("Mã Số").setMaxWidth(55);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		String input = textSearch.getText();
		List<Integer> ls = Vol.getSelectVol();
		Set<BaiHat> setRes = ctrBH.findBaiHat(TypeSearch.getvalue(), input, ls);
//		System.out.println("gia tri value select: " + TypeSearch.getvalue());
		List<BaiHat> list = new ArrayList<>(setRes);
		PutDataToTable(list);
	}
	
	
	public static void main(String[] args) {
		MainForm frmMain = new MainForm();
	}
}
