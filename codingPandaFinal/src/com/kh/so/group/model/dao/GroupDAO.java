package com.kh.so.group.model.dao;



import static com.kh.so.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import com.kh.so.group.model.vo.Group;
import com.kh.so.group.model.vo.GroupList;
import com.kh.so.member.model.vo.Member;

public class GroupDAO {
	private Properties prop;
	
	public GroupDAO() {
		prop = new Properties();
		
		String filePath = GroupDAO.class
				.getResource("/config/group.properties")
				.getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<GroupList> selectList(Connection con) {
		ArrayList<GroupList> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<GroupList>();
			
			while(rset.next()) {
				GroupList g = new GroupList();
				
				g.setGno(rset.getInt("GNO"));
				g.setgAddress(rset.getString("GADDRESS"));
				g.setgTitle(rset.getString("GTITLE"));
				g.setgContent(rset.getString("GCONTENT"));
				g.setgStartDate(rset.getDate("GSTARTDATE"));
				g.setgEndDate(rset.getDate("GENDDATE"));
				g.setgLang(rset.getString("GLANG"));
				g.setgPicture(rset.getString("GPICTURE"));
				
				list.add(g);

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getListCount(Connection con) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
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

	public ArrayList<GroupList> searchList(Connection con, String[] array, int currentPage) {
		ArrayList<GroupList> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println(":다오 IN");
		System.out.println(array[0]);
		System.out.println(array[1]);
		System.out.println(array[2]);
		System.out.println(array[3]);
		
		String sql = prop.getProperty("searchList");
		
		try {
			pstmt = con.prepareStatement(sql);
			// sql은 1부터 시작하니깐
			System.out.println(sql);

			for(int i = 0; i < 4; i++) {
				if(i < 3) {
					pstmt.setString((i+1), array[i]);
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if(array[3].length() == 0) 
						array[3] = sdf.format(new java.util.Date());
					pstmt.setDate((i+1), new Date(sdf.parse(array[i]).getTime()));
				}
			}
			
			int startRow = (currentPage - 1) * 4 + 1;
			int endRow = startRow + 3;
			
			pstmt.setInt(5, endRow);
			pstmt.setInt(6, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<GroupList>();
			
			while(rset.next()) {
				GroupList g = new GroupList();
				
				g.setGno(rset.getInt("GNO"));
				g.setgAddress(rset.getString("GADDRESS"));
				g.setgTitle(rset.getString("GTITLE"));
				g.setgContent(rset.getString("GCONTENT"));
				g.setgStartDate(rset.getDate("GSTARTDATE"));
				g.setgEndDate(rset.getDate("GENDDATE"));
				g.setgLang(rset.getString("GLANG"));
				g.setgPicture(rset.getString("GPICTURE"));
				
				list.add(g);
			}
			System.out.println(list);
		} catch(SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		System.out.println("다오리턴");
		return list;
	}
	
	public Group selectOne(Connection con, int gno) {
		Group g = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				g = new Group();
				
				g.setGno(rset.getInt("GNO"));
				g.setgTitle(rset.getString("GTITLE"));
				g.setgStartDate(rset.getDate("GSTARTDATE"));
				g.setgEndDate(rset.getDate("GENDDATE"));
				g.setgAddress(rset.getString("GADDRESS"));
				g.setgContent(rset.getString("GCONTENT"));
				g.setgRegDate(rset.getDate("GREGDATE"));
				g.setgPicture(rset.getString("GPICTURE"));
				g.setgLang(rset.getString("GLANG"));
				g.setgMoney(rset.getInt("GMONEY"));
				g.setgLeader(rset.getString("GLEADER"));
				g.setgLeaderName(rset.getString("MNAME"));
//				g.setgMax(rset.getInt("GMAX"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("GroupDAO : selectOne을 하는 도중 문제 발생!");
			e.printStackTrace();
		
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return g;
		
	}
	
	public int insertGroup(Connection con, Group g) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertGroup");
	
		try {
			pstmt = con.prepareStatement(sql);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
			
			
			pstmt.setString(1, g.getgTitle());
			pstmt.setDate(2, g.getgStartDate());
			
			if(g.getgEndDate() != null && ! g.getgEndDate().equals("")) {
				pstmt.setDate(3, g.getgEndDate());
			} else {
				pstmt.setNull(3, java.sql.Types.NULL);
			}
			
			pstmt.setString(4, g.getgAddress());
			pstmt.setString(5, g.getgContent());
			pstmt.setString(6, g.getgPicture());
			pstmt.setString(7, g.getgLang());
			pstmt.setInt(8, g.getgMoney());
			pstmt.setInt(9, g.getgMax());
			pstmt.setString(10, g.getgLeader());	
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql에러 : insertGroup을 실행하던 중 에러 발생!!!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateGroup(Connection con, Group g) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateGroup");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			
			pstmt.setString(1, g.getgTitle());
			pstmt.setDate(2, g.getgStartDate());
			pstmt.setDate(3, g.getgEndDate());
			pstmt.setString(4, g.getgAddress());
			pstmt.setString(5, g.getgContent());
			pstmt.setString(6, g.getgPicture());
			pstmt.setString(7, g.getgLang());
			pstmt.setInt(8, g.getgMoney());
			pstmt.setInt(9, g.getgMax());
			pstmt.setInt(10, g.getGno());	
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql에러 : updateGroup을 실행하던 중 에러 발생!!!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteGroup(Connection con, int gno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteGroup");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql에러 : deleteGroup을 실행하던 중 에러 발생!!!");
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> myMember(Connection con, int gno) {
		ArrayList<Member> mList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("myMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			
			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setmId(rset.getString("MID"));
				m.setmName(rset.getString("MNAME"));
				m.setmContent(rset.getString("MCONTENT"));
				m.setmPicture(rset.getString("MPICTURE"));
				
				mList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mList;
	}

	public int joinMember(Connection con, int gno, String mId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("joinMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.setString(2, mId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int exitMember(Connection con, int gno, String mId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("exitMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.setString(2, mId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member checkMember(Connection con, int gno, String mId) {
		Member join = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.setString(2, mId);
			
			rset = pstmt.executeQuery();
			join = new Member();
			
			if(rset.next()) {				
				join.setmId(rset.getString("MID"));
			} else {
				join.setmId("");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return join;
	}

	public int exportMember(Connection con, int gno, String exportM) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("exportMember");
//		exportMember=UPDATE CP_ATTEND SET STATUS = 'N' \
//				  WHERE GNO=? AND MID=?
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.setString(2, exportM);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("GroupDAO에서 exportMember실행 실패ㅠ!!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
