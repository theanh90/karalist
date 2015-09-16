package com.bui.karalist.model;



public class BaiHat implements Comparable<BaiHat>{
	private int id;
	private String name;
	private String author;
	private int vol;
	private String firstLyric;
	private String fullLyric;
	
	
	public BaiHat(){
	}
	
	public BaiHat(int id, String name, String author, int vol,
			String firstLyric, String fullLyric) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.vol = vol;
		this.firstLyric = firstLyric;
		this.fullLyric = fullLyric;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaiHat other = (BaiHat) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	public String toString() {
		return "BaiHat [id=" + id + ", name=" + name + ", author=" + author
				+ ", vol= " + vol + ", first lyric= " + firstLyric + ", full lyric= " + fullLyric + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getVol() {
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
	}
	public String getFirstLyric() {
		return firstLyric;
	}
	public void setFirstLyric(String firstLyric) {
		this.firstLyric = firstLyric;
	}
	public String getFullLyric() {
		return fullLyric;
	}
	public void setFullLyric(String fullLyric) {
		this.fullLyric = fullLyric;
	}

	@Override
	public int compareTo(BaiHat o) {
		return this.getName().compareTo(o.getName());
	}


}
