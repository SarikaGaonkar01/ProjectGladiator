package com.coop.model;

public class Asset {
	
	private String accno;
	private String asset;
	private double aval;
	private String yield;
	private double yval;
	private double ayield;
	private int asset_id;
	
	
	
	
	public int getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public double getAval() {
		return aval;
	}
	public void setAval(double aval) {
		this.aval = aval;
	}
	public String getYield() {
		return yield;
	}
	public void setYield(String yield) {
		this.yield = yield;
	}
	public double getYval() {
		return yval;
	}
	public void setYval(double yval) {
		this.yval = yval;
	}
	public double getAyield() {
		return ayield;
	}
	public void setAyield(double ayield) {
		this.ayield = ayield;
	}

}
