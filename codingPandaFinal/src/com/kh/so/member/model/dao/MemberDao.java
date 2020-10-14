package com.kh.so.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.so.common.exception.MemberException;
import com.kh.so.group.model.vo.Group;
import com.kh.so.member.model.vo.Member;
import com.kh.so.member.model.vo.Your;

import static com.kh.so.common.JDBCTemplate.*;



public class MemberDao {
	
	private Properties prop;
	
	public MemberDao() {
		prop = new Properties();
		
		String filePath = MemberDao.class
				          .getResource("/config/member.properties")
				          .getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int insertMember(Connection con, Member m) throws MemberException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getmId());
			pstmt.setString(2, m.getmName());
			pstmt.setString(3, m.getmPwd());
			pstmt.setString(4, m.getmPhone());
			pstmt.setString(5, m.getmEmail());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new MemberException("[DAO 에러] : " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public Member selectMember(Connection con, Member m) throws MemberException {
		// 1. 사용할 변수 선언
				Member result = null; // 결과를 담을 객체
				PreparedStatement pstmt = null; // 쿼리 실행할 객체
				ResultSet rset = null; // Select 결과를 받아 올 객체
				
				String sql = prop.getProperty("selectMember");
				
				try {
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, m.getmId());
					pstmt.setString(2, m.getmPwd());
					
					rset = pstmt.executeQuery();
					
					System.out.println("result 조회 전 : " + result);
					
					if(rset.next()) {
						result = new Member();
						
						result.setmId(m.getmId());
						result.setmName(rset.getString("MNAME"));
						result.setmPwd(m.getmPwd());
						result.setmEmail(rset.getString("mEmail"));
						result.setmPhone(rset.getString("mPhone"));
						result.setmContent(rset.getString("mContent"));
						result.setmBirth(rset.getDate("mBirth"));
						result.setmAddress(rset.getString("mAddress"));
						result.setmPicture(rset.getString("mPicture"));
						result.setmLang(rset.getString("MLANG"));
					}
					
					System.out.println("result 조회 후 : " + result);
					
				} catch (SQLException e) {

					e.printStackTrace();
					
					throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
					
				} finally {
					close(rset);
					close(pstmt);
				}	
				
				return result;
	}


	public int updateMember(Connection con, Member m) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getmPwd());
			pstmt.setString(2, m.getmName());
			pstmt.setString(3, m.getmEmail());
			pstmt.setString(4, m.getmPhone());
			pstmt.setString(5, m.getmContent());
			pstmt.setDate(6, m.getmBirth());
			pstmt.setString(7, m.getmAddress());
			pstmt.setString(8, m.getmLang());
			pstmt.setString(9, m.getmPicture());
			pstmt.setString(10, m.getmId());
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}		
		
		return result;
	}


	public int deleteMember(Connection con, String mId) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int idDupCheck(Connection con, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idDupCheck");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
		
	}


	public Your yourPage(Connection con, String mId) {
		Your y = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("yourPage");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				y = new Your();
				
				y.setyId(rset.getString("MID"));
				y.setyName(rset.getString("MNAME"));
				y.setyEmail(rset.getString("MEMAIL"));
				y.setyPhone(rset.getString("MPHONE"));
				y.setyContent(rset.getString("MCONTENT"));
				y.setyBirth(rset.getDate("MBIRTH"));
				y.setyAddress(rset.getString("MADDRESS"));
				y.setyPicture(rset.getString("MPICTURE"));
				y.setyLang(rset.getString("MLANG"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return y;
	}


	public ArrayList<Group> myGroup(Connection con, String mId) {
		ArrayList<Group> glist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("myGroup");
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			rset = pstmt.executeQuery();
			
			glist = new ArrayList<Group>();
			
			while(rset.next()) {
				Group g = new Group();
				
				g.setGno(rset.getInt("GNO"));
				g.setgTitle(rset.getString("GTITLE"));
				g.setgContent(rset.getString("GCONTENT"));
				g.setgRegDate(rset.getDate("GREGDATE"));
				g.setgLeader(rset.getString("GLEADER"));
				
				glist.add(g);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return glist;
	}

}
