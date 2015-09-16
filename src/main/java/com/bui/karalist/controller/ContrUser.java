package com.bui.karalist.controller;

import com.bui.karalist.dao.DaoUser;
import com.bui.karalist.model.User;

public class ContrUser {
	public static User getUser(String name, String pass){
		return DaoUser.getUser(name, pass);
	}

}
