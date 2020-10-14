package com.kh.so.boardComment.model.vo;

import java.io.Serializable;
import java.util.Date;

public class BoardComment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int cno; //댓글 번호
	private int	bNo; 	// 게시글 번호
	private String cWriter; //작성자
	private String mId;   // 아이디
	private String cContent;  //댓글 내용
	private Date cDate; //댓글 작성날짜
	private int refCno; //참조댓글번호
	private int cLevel; //댓글레벨
	private String mPicture; // 댓글단 사람 사진
	
	public BoardComment() {super();}

	public BoardComment(int cno, int bNo, String cWriter, String mId, String cContent, Date cDate, int refCno,
			int cLevel, String mPicture) {
		super();
		this.cno = cno;
		this.bNo = bNo;
		this.cWriter = cWriter;
		this.mId = mId;
		this.cContent = cContent;
		this.cDate = cDate;
		this.refCno = refCno;
		this.cLevel = cLevel;
		this.mPicture = mPicture;
	}
	
	// 댓글 추가용 생성자
	public BoardComment(int bNo, String cWriter, String cContent, int refCno, int cLevel) {
		super();
		this.bNo = bNo;
		this.cWriter = cWriter;
		this.cContent = cContent;
		this.refCno = refCno;
		this.cLevel = cLevel;
	}
	
	public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public int getRefCno() {
		return refCno;
	}

	public void setRefCno(int refCno) {
		this.refCno = refCno;
	}

	public int getcLevel() {
		return cLevel;
	}

	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}

	public String getmPicture() {
		return mPicture;
	}

	public void setmPicture(String mPicture) {
		this.mPicture = mPicture;
	}

	@Override
	public String toString() {
		return "BoardComment [cno=" + cno + ", bNo=" + bNo + ", cWriter=" + cWriter + ", mId=" + mId + ", cContent="
				+ cContent + ", cDate=" + cDate + ", refCno=" + refCno + ", cLevel=" + cLevel + "]";
	}
	
}
