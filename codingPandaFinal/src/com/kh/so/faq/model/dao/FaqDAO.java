package com.kh.so.faq.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.so.boardComment.model.dao.BoardCommentDAO;
import com.kh.so.faq.model.vo.Faq;

import static com.kh.so.common.JDBCTemplate.*;

public class FaqDAO {
	private Properties prop;
	
	
	public FaqDAO() {
		prop = new Properties();
		
		String filePath = BoardCommentDAO.class
          		.getResource("/config/faq.properties")
          		.getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			System.out.println("FaqDAO에서 faq.properties를 읽지못하고 있음.");
			e.printStackTrace();
		}
		
		
	}

//	insertComment = INSERT INTO CP_FAQ \
//		    VALUES(SEQ_FNO.NEXTVAL, 
//		    		?gno, ?fwriter, ?fcontent, DEFAULT, 
//		    				?ref-fno, flevel?)
//    FNO NUMBER CONSTRAINT PK_FNO PRIMARY KEY,
//    GNO NUMBER NOT NULL,
//    FWRITER VARCHAR2(20) NOT NULL,
//    FCONTENT VARCHAR2(2000) NOT NULL,
//    FDATE DATE  DEFAULT SYSDATE,
//    REF_FNO NUMBER,
//    FLEVEL NUMBER,
	
	
	// faq 댓글 생성하기  
	public int insertFaq(Connection con, Faq faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, faq.getGno());
			pstmt.setString(2, faq.getwriter());
			pstmt.setString(3, faq.getfContent());

			
			// myWeb의 BoardCommentDAO를 참조. 
			if(faq.getRefFno() > 0) {
				pstmt.setInt(4, faq.getRefFno());
			} else {
				pstmt.setNull(4, java.sql.Types.NULL);
			}
			
			pstmt.setInt(5, faq.getfLevel());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL에러 : FaqDAO에서 insertComment도중 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 그룹상세보기 페이지에 faq 전체 불러오기 
	public ArrayList<Faq> selectList(Connection con, int gno){
		System.out.println("FaqDAO에 도달");
		ArrayList<Faq> faqList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, gno);
			
			rset = pstmt.executeQuery();
			
			faqList = new ArrayList<Faq>();
			while(rset.next()) {
				Faq faq = new Faq();

				faq.setFaqNo(rset.getInt("FNO"));
				faq.setGno(rset.getInt("GNO"));
				faq.setwriter(rset.getString("FWRITER"));
				faq.setfContent(rset.getString("FCONTENT"));
				faq.setfDate(rset.getDate("FDATE"));
				faq.setRefFno(rset.getInt("REF_FNO"));
				faq.setfLevel(rset.getInt("FLEVEL"));
				
				faqList.add(faq);
			}
			
			System.out.println(faqList);
			
		} catch (SQLException e) {
			System.out.println("SQL에러 : FaqDAO에서 selectList도중 에러 발생");
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faqList;
	}
	
	
	// faq 수정하기 
	public int updateFaq(Connection con, Faq faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, faq.getfContent());
			pstmt.setInt(2, faq.getFaqNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL에러 : FaqDAO에서 updateComment도중 에러 발생");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// faq 삭제하기 
	public int deleteFaq(Connection con, int fno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, fno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL에러 : FaqDAO에서 deleteComment도중 에러 발생");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
}
