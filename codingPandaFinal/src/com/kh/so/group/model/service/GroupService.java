package com.kh.so.group.model.service;

import static com.kh.so.common.JDBCTemplate.close;
import static com.kh.so.common.JDBCTemplate.commit;
import static com.kh.so.common.JDBCTemplate.getConnection;
import static com.kh.so.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.so.group.model.dao.GroupDAO;
import com.kh.so.group.model.vo.Group;
import com.kh.so.group.model.vo.GroupList;
import com.kh.so.member.model.vo.Member;

public class GroupService {
	
	private Connection con;
	private GroupDAO gDAO = new GroupDAO();
	
	public ArrayList<GroupList> selectList() {

		con = getConnection();
		
		ArrayList<GroupList> list = gDAO.selectList(con);
		
		close(con);
		
		return list;
	}

	public int getListCount() {
		
			con = getConnection();
			
			int result = gDAO.getListCount(con);
			
			close(con);
			
			return result;
	}

	public ArrayList<GroupList> searchGroup(String group, String language, String address, 
			                            String start_date, int currentPage) {
		con = getConnection();
		ArrayList<GroupList> list = null;
		// 컨디션에 뭐라도 내용이 들어왔다면
		// 조건부 검색 실시(제목, 내용 등)
		System.out.println(":서비스인");
		String[] array= {group, language, address, start_date};
		list = gDAO.searchList(con, array, currentPage);			
		close(con);
		System.out.println(":서비스리턴");
		return list;
	}
	
	// 모임카드 상세보기 
	public Group selectOne(int gno) {
		con = getConnection();
		Group g = gDAO.selectOne(con, gno);
			
		close(con);
		
		return g;
	}	
	
	// 모임정보 등록
	public int insertGroup(Group g) {
		con = getConnection();
		int result = gDAO.insertGroup(con, g);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	
	
	// 모임정보 수정 
	public int updateGroup(Group g) {
		con = getConnection();
		int result = gDAO.updateGroup(con, g);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	
	// 모임정보 삭제 
	public int deleteGroup(int gno) {
		con = getConnection();
		int result = gDAO.deleteGroup(con, gno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Member> myMember(int gno) {
		con = getConnection();
		ArrayList<Member> mList = gDAO.myMember(con, gno);
		
		close(con);
		
		return mList;
	}

	public int joinMember(int gno, String mId) {
		con = getConnection();
		int result = gDAO.joinMember(con, gno, mId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int exitMember(int gno, String mId) {
		con = getConnection();
		int result = gDAO.exitMember(con, gno, mId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Member checkMember(int gno, String mId) {
		con = getConnection();
		Member join = gDAO.checkMember(con, gno, mId);
		
		close(con);
		
		return join;
	}

	public int exportMember(int gno, String exportM) {
		con = getConnection();
		int result = gDAO.exportMember(con, gno, exportM);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	
}
