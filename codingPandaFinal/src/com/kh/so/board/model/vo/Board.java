package com.kh.so.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{

	private static final long serialVersionUID = 1L; // 조원과 상의 
	
	private int bno; // 게시글 번호 
	private int gno; // 모임번호 
	private String mId; // 회원명/작성자. 지금 entity에 MEMBER_ID로 되어있음. 
	private String bWriter;  // 작성자 
	private String bTitle; // 게시글제목 
	private String bContent; // 게시글 내용 
	private String bCount; // 조회수 
	private String status; // 게시글 삭제 여부 (Y : 일반, N : 삭제함)
	private Date bDate; // 작성일 
	private String bPicture;  // 보드 파일 받아올 변수
	private String mPicture;  // 멤버 사진 받아올 변수
	
	public Board() {
		super();
	}

	public Board(int bno, int gno, String mId, String bWriter, String bTitle, String bContent, String bCount,
			String status, Date bDate, String bPicture, String mPicture) {
		super();
		this.bno = bno;
		this.gno = gno;
		this.mId = mId;
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bCount = bCount;
		this.status = status;
		this.bDate = bDate;
		this.bPicture = bPicture;
		this.mPicture = mPicture;
	}

	// 전체 보드리스트 조회용 생성자
	public Board(int bno, int gno, String bWriter, String bTitle, String bContent, Date bDate, String mPicture) {
		super();
		this.bno = bno;
		this.gno = gno;
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.mPicture = mPicture;
	}
	
	// 보드 추가 생성자
	public Board(String bWriter, String bTitle, String bContent, String bPicture) {
		super();
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bPicture = bPicture;
	}
	
	// TEST용
	public Board(String bTitle, String bContent, String bPicture) {
		super();
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bPicture = bPicture;
	}
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbCount() {
		return bCount;
	}

	public void setbCount(String bCount) {
		this.bCount = bCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public String getbPicture() {
		return bPicture;
	}

	public void setbPicture(String bPicture) {
		this.bPicture = bPicture;
	}

	public String getmPicture() {
		return mPicture;
	}

	public void setmPicture(String mPicture) {
		this.mPicture = mPicture;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", gno=" + gno + ", mId=" + mId + ", bWriter=" + bWriter + ", bTitle=" + bTitle
				+ ", bContent=" + bContent + ", bCount=" + bCount + ", status=" + status + ", bDate=" + bDate
				+ ", bPicture=" + bPicture + ", mPicture=" + mPicture + "]";
	}
	
}
