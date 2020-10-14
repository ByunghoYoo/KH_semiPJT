package com.kh.so.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
	private String mId;
	private String mName;
	private String mPwd;
	private String mEmail;
	private String mPhone;
	private String mContent; // 메모
	private Date mBirth;
	private String mAddress; // 사는 곳, 주소
	private String mPicture; // 회원 사진
	private String mLang;    // 기술 스택명 
	
	public Member() {
		super();
	}
	
	
	// 로그인에 필요한 생성자 
	public Member(String mId, String mPwd) {
		super();
		this.mId = mId;
		this.mPwd = mPwd;
	}
	
	// 전체 데이터를 받는 생성자 & 조회용 생성자 
	public Member(String mId, String mName, String mPwd, String mEmail, String mPhone, String mContent, Date mBirth,
			String mAddress, String mPicture, String mLang) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mPwd = mPwd;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mContent = mContent;
		this.mBirth = mBirth;
		this.mAddress = mAddress;
		this.mPicture = mPicture;
		this.mLang = mLang;
	}
	
	// 회원가입 시 필요한 생성자
	public Member(String mId, String mName, String mPwd, String mEmail, String mPhone) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mPwd = mPwd;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
	}


	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPwd() {
		return mPwd;
	}
	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public Date getmBirth() {
		return mBirth;
	}
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getmPicture() {
		return mPicture;
	}
	public void setmPicture(String mPicture) {
		this.mPicture = mPicture;
	}
	public String getmLang() {
		return mLang;
	}
	public void setmLang(String mLang) {
		this.mLang = mLang;
	}
	
	
	@Override
	public String toString() {
		return "Member [mId=" + mId + ", mName=" + mName + ", mPwd=" + mPwd + ", mEmail=" + mEmail + ", mPhone=" + mPhone
				+ ", mContent=" + mContent + ", mBirth=" + mBirth + ", mAddress=" + mAddress + ", mPicture=" + mPicture
				+ ", mLang=" + mLang + "]";
	}
	
	
	
	

}
