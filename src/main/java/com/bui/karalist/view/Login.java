package com.bui.karalist.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.bui.karalist.controller.ContrBaiHat;
import com.bui.karalist.controller.ContrUser;
import com.bui.karalist.model.Role;
import com.bui.karalist.model.User;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Login extends JFrame{
	private JTextField txtUname;
	private JTextField txtPass;
	private ContrUser ctrUser;
	private JLabel lblCheckLogin;
	static User user_login = null;
	
	private static Login instance = new Login();
	public static Login getInstance(){
		return instance;
	}
	
	public Login() {
		
		JLabel lblTnngNhp = new JLabel("Tên Đăng Nhập: ");
		
		JLabel lblMtKhu = new JLabel("Mật Khẩu: ");
		
		txtUname = new JTextField();
		txtUname.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		
		JButton btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_login = new User();
				String uname = txtUname.getText();
				String pass = encryptMD5(txtPass.getText());
				user_login = ctrUser.getUser(uname, pass);
				if(user_login == null){
					lblCheckLogin.setText("Sai UserName hoặc Password");
				}else{
					getInstance().setVisible(false);
				}
			}
		});
		
		lblCheckLogin = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCheckLogin, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTnngNhp)
								.addComponent(lblMtKhu))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogin)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtPass)
									.addComponent(txtUname, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTnngNhp)
						.addComponent(txtUname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhu)
						.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCheckLogin)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		this.setTitle("Đăng nhập vào hệ thống");
		this.setSize(350, 220);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(groupLayout);
	}
	
	public String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static Boolean checkPermission(Role role) throws Exception{
		if(user_login != null){
			List<Role> lsRole = user_login.getRoles();
			if(lsRole.contains(role)){
				return true;
			}
		}
		throw new Exception("khong co quyen " + role.name());
	}
}
