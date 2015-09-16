package com.bui.karalist.model;

public enum Role {
	VIEW(1,"View"),
	UPDATE(2,"Update"),
	DELETE(3,"Delete");
	
	int id;
	String name;
	
	private Role(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Role getRoleFromId(int id){
		
		switch (id){		
			case 2:
				return UPDATE;
			case 3:
				return DELETE;
			default:
				return VIEW;
		}
		
	}
	
}
