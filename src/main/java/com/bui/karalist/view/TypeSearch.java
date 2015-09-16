package com.bui.karalist.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

//import sun.security.jca.GetInstance.Instance;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TypeSearch extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	//singleton
	private static TypeSearch instance = new TypeSearch("Tìm kiếm theo");
	
	private static int valSel = 15;
	
	public static TypeSearch getInstance(){
		return instance;
	}
	//

	public static int getvalue(){
		return valSel;
	}	
	
	JCheckBox chkAll = new JCheckBox("Tất Cả", true);
	JCheckBox chkMaSo = new JCheckBox("Mã Số", true);
	JCheckBox chkTen = new JCheckBox("Tên Bài Hát", true);
	JCheckBox chkTGia = new JCheckBox("Tác Giả", true);
	JCheckBox chkLoi = new JCheckBox("Lời Bài Hát", true);

	/**
	 * Create the frame.
	 */
	private TypeSearch(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(170, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		this.setResizable(false);
		
		chkAll.addItemListener(new CheckboxItemListener(15));		
		chkMaSo.addItemListener(new CheckboxItemListener(1));		
		chkTen.addItemListener(new CheckboxItemListener(2));		
		chkTGia.addItemListener(new CheckboxItemListener(4));		
		chkLoi.addItemListener(new CheckboxItemListener(8));
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInstance().setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chkMaSo)
						.addComponent(chkAll, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkTen)
						.addComponent(chkTGia)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOK)
							.addComponent(chkLoi)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(chkAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkMaSo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkTen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkTGia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkLoi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOK)
					.addContainerGap(8, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	class CheckboxItemListener implements ItemListener {
		
		int val;
		
		public CheckboxItemListener(int value) {
			this.val = value;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
//				if(e.getSource().equals(chkAll)){ System.out.println("la all ne");}
				
				
				if(val == 15){
					chkMaSo.setSelected(true);
					chkTen.setSelected(true);
					chkTGia.setSelected(true);
					chkLoi.setSelected(true);
					
				}
				else{
					TypeSearch.valSel |= val;
//					System.out.println(TypeSearch.valSel);
				}
			}
			if(e.getStateChange() == ItemEvent.DESELECTED){
				
				if(val == 15 && chkMaSo.isSelected() && chkTen.isSelected() 
						&& chkTGia.isSelected() && chkLoi.isSelected()){
					chkMaSo.setSelected(false);
					chkTen.setSelected(false);
					chkTGia.setSelected(false);
					chkLoi.setSelected(false);
				}
				if(val != 15){
					TypeSearch.valSel &= ~val;
//					System.out.println(TypeSearch.valSel);
					chkAll.setSelected(false); 
					
				}
			
			}
			
			if(TypeSearch.valSel == 15){
				chkAll.setSelected(true);
			}
			
		}
		
	}
	
}
