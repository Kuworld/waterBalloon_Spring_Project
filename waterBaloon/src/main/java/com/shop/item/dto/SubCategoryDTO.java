package com.shop.item.dto;

public class SubCategoryDTO {
    private Integer c2Cat;
    private String c2Name;
	public Integer getC2Cat() {
		return c2Cat;
	}
	public void setC2Cat(Integer c2Cat) {
		this.c2Cat = c2Cat;
	}
	public String getC2Name() {
		return c2Name;
	}
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
	@Override
	public String toString() {
		return "SubCategoryDTO [c2Cat=" + c2Cat + ", c2Name=" + c2Name + "]";
	}
    
 
}
