package com.bui.karalist.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.bui.karalist.dao.DaoBaiHat;
import com.bui.karalist.model.BaiHat;

public class ContrBaiHat {

	public Set<BaiHat> findBaiHat(int i, String s, List<Integer> ls) {
		int id = 1;
		int name = 2;
		int author = 4;
		int lyric = 8;

		Set<BaiHat> setKQua = new TreeSet<>();
		if ((i & id) > 0) {
			int para = 0;
			try {
				para = Integer.parseInt(s);
				BaiHat bh = DaoBaiHat.findBaiHatById(para, ls);
				if (bh != null) {
					setKQua.add(bh);
				}
			} catch (Exception ex) {
			}

		}

		if ((i & name) > 0) {
			List<BaiHat> lsName = DaoBaiHat.findBaiHatByName(s, ls);
			setKQua.addAll(lsName);
		}

		if ((i & author) > 0) {
			List<BaiHat> lsAu = DaoBaiHat.findBaiHatByAuthor(s, ls);
			setKQua.addAll(lsAu);
		}

		if ((i & lyric) > 0) {
			List<BaiHat> lsLy = DaoBaiHat.findBaiHatByLyric(s, ls);
			setKQua.addAll(lsLy);
		}

		return setKQua;

	}

	public BaiHat findBaiHatById(int id) {
		return DaoBaiHat.findBaiHatById(id);
	}

	public List<BaiHat> findBaiHatByName(String n) {
		List<BaiHat> lsRes = DaoBaiHat.findBaiHatByName(n);
		Collections.sort(lsRes, new Comparator<BaiHat>() {
			@Override
			public int compare(BaiHat o1, BaiHat o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return lsRes;
	}

	public List<BaiHat> findBaiHatByAuthor(String au, List<Integer> ls) {
		return DaoBaiHat.findBaiHatByAuthor(au, ls);
	}

	public List<BaiHat> findBaiHatByLyric(String ly, List<Integer> ls) {
		return DaoBaiHat.findBaiHatByLyric(ly, ls);
	}

	public void AddBaiHat(BaiHat bh) {
		DaoBaiHat.AddBaiHat(bh);
	}

	public void AddListBaiHat(List<BaiHat> lsbh) {
		DaoBaiHat.AddListBaiHat(lsbh);
	}

	public void DeleteBaiHat(int id) {
		DaoBaiHat.DeleteBaiHat(id);
	}

	public void UpdateBaiHat(BaiHat bh) {
		DaoBaiHat.UpdateBaiHat(bh);
	}
	
	public int[] findMinMaxVol(){
		return DaoBaiHat.findMinMaxVol();
	}
	

}
