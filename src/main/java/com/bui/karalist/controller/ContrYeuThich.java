package com.bui.karalist.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bui.karalist.dao.DaoYeuThich;
import com.bui.karalist.model.BaiHat;

public class ContrYeuThich {
	

	public static void AddToBookmark(int msbh){
		DaoYeuThich.AddToBookmark(msbh);
	}
	
	public static BaiHat findYeuThichById(int id){
		return DaoYeuThich.findYeuThichById(id);
	}
	
	public static List<BaiHat> findYeuThichByName(String name){
		List<BaiHat> lsbh = DaoYeuThich.findYeuThichByName(name);
		Collections.sort(lsbh, new Comparator<BaiHat>() {
			@Override
			public int compare(BaiHat o1, BaiHat o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		return lsbh;
	}
	
	public static void deleteYeuThich(int msbh){
		DaoYeuThich.deleteYeuThich(msbh);
	}
}
