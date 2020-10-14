package com.kh.so.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Your implements Serializable{
	private String yId;
	private String yName;
	private String yPwd;
	private String yEmail;
	private String yPhone;
	private String yContent;
	private Date yBirth;
	private String yAddress;
	private String yPicture;
	private String yLang;
	
	public Your() {
		super();
	}

	public Your(String yId, String yName, String yPwd, String yEmail, String yPhone, String yContent, Date yBirth,
			String yAddress, String yPicture, String yLang) {
		super();
		this.yId = yId;
		this.yName = yName;
		this.yPwd = yPwd;
		this.yEmail = yEmail;
		this.yPhone = yPhone;
		this.yContent = yContent;
		this.yBirth = yBirth;
		this.yAddress = yAddress;
		this.yPicture = yPicture;
		this.yLang = yLang;
	}

	public String getyId() {
		return yId;
	}

	public void setyId(String yId) {
		this.yId = yId;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public String getyPwd() {
		return yPwd;
	}

	public void setyPwd(String yPwd) {
		this.yPwd = yPwd;
	}

	public String getyEmail() {
		return yEmail;
	}

	public void setyEmail(String yEmail) {
		this.yEmail = yEmail;
	}

	public String getyPhone() {
		return yPhone;
	}

	public void setyPhone(String yPhone) {
		this.yPhone = yPhone;
	}

	public String getyContent() {
		return yContent;
	}

	public void setyContent(String yContent) {
		this.yContent = yContent;
	}

	public Date getyBirth() {
		return yBirth;
	}

	public void setyBirth(Date yBirth) {
		this.yBirth = yBirth;
	}

	public String getyAddress() {
		return yAddress;
	}

	public void setyAddress(String yAddress) {
		this.yAddress = yAddress;
	}

	public String getyPicture() {
		return yPicture;
	}

	public void setyPicture(String yPicture) {
		this.yPicture = yPicture;
	}

	public String getyLang() {
		return yLang;
	}

	public void setyLang(String yLang) {
		this.yLang = yLang;
	}
	
	@Override
	public String toString() {
		return "Your [yId=" + yId + ", yName=" + yName + ", yPwd=" + yPwd + ", yEmail=" + yEmail + ", yPhone=" + yPhone
				+ ", yContent=" + yContent + ", yBirth=" + yBirth + ", yAddress=" + yAddress + ", yPicture=" + yPicture
				+ ", yLang=" + yLang + "]";
	}
	

}
