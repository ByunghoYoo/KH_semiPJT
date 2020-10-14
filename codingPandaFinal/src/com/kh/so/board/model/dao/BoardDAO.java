package com.kh.so.board.model.dao;

import static com.kh.so.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.so.board.model.vo.Board;

public class BoardDAO {
	
	private Properties prop;
	
	public BoardDAO() {
		prop = new Properties();
		
		String filePath = BoardDAO.class
				.getResource("/config/board.properties")
				.getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection con, int gno) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, gno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Board> selectList(Connection con, int gno, int currentPage, int limit) {
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, gno);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setGno(rset.getInt("GNO"));
				b.setbWriter(rset.getString("MNAME"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setbDate(rset.getDate("BDATE"));
				b.setmPicture(rset.getString("MPICTURE"));
				
				list.add(b);
				
				// System.out.println(b);
			} 
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoard(Connection con, Board b, int gno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, gno);
			pstmt.setString(2, b.getbWriter());		
			pstmt.setString(3, b.getbTitle());
			pstmt.setString(4, b.getbContent());
			pstmt.setString(5, b.getbPicture());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public Board selectOne(Connection con, int bno) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setGno(rset.getInt("GNO"));
				b.setbWriter(rset.getString("MNAME"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setmId(rset.getString("BWRITER"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setStatus(rset.getString("STATUS"));
				b.setbDate(rset.getDate("BDATE"));
				b.setbPicture(rset.getString("FILEPATH"));
				b.setmPicture(rset.getString("MPICTURE"));
				b.setmId(rset.getString("MID"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public int updateBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setString(3, b.getbPicture());
			pstmt.setInt(4, b.getBno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public int deleteBoard(Connection con, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			close(pstmt);
		}
		
		return result;
	}


}
