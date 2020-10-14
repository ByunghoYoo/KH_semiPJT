package com.kh.so.boardComment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.so.boardComment.model.dao.BoardCommentDAO;
import com.kh.so.boardComment.model.vo.BoardComment;

import static com.kh.so.common.JDBCTemplate.*;


public class BoardCommentDAO {
	private Properties prop;

	public BoardCommentDAO() {
		prop = new Properties();
		
		String filePath = BoardCommentDAO.class
				          .getResource("/config/comment.properties")
				          .getPath();
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public int insertComment(Connection con, BoardComment bco) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bco.getbNo());
			pstmt.setString(2, bco.getcWriter());
			pstmt.setString(3, bco.getcContent());
			// 첫 댓글은 참조하는 댓글이 없다.
			// 따라서 refcno 가 0으로 온다.
			// pstmt.setInt(4, bco.getRefcno());
			if(bco.getRefCno() > 0) {
				pstmt.setInt(4,  bco.getRefCno());
			} else {
				pstmt.setNull(4, java.sql.Types.NULL);
			}
			
			pstmt.setInt(5, bco.getcLevel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}


	public ArrayList<BoardComment> selectList(Connection con, int bno) {
		ArrayList<BoardComment> clist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			clist = new ArrayList<BoardComment>();
			
			while(rset.next()) {
				BoardComment bco = new BoardComment();
				
				bco.setCno(rset.getInt("cno"));
				bco.setbNo(rset.getInt("bno"));
				bco.setcContent(rset.getString("ccontent"));
				bco.setcWriter(rset.getString("mname"));
				bco.setmId(rset.getString("cwriter"));
				bco.setcDate(rset.getDate("cdate"));
				bco.setRefCno(rset.getInt("ref_cno"));
				bco.setcLevel(rset.getInt("clevel"));
				bco.setmPicture(rset.getString("mpicture"));
				
				clist.add(bco);				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return clist;
	}


	public int updateComment(Connection con, BoardComment bco) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bco.getcContent());
			pstmt.setInt(2, bco.getCno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}


	public int deleteComment(Connection con, int cno) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	

}