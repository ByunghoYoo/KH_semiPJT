package com.kh.so.member.model.service;

import static com.kh.so.common.JDBCTemplate.close;
import static com.kh.so.common.JDBCTemplate.commit;
import static com.kh.so.common.JDBCTemplate.getConnection;
import static com.kh.so.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.so.common.exception.MemberException;
import com.kh.so.group.model.vo.Group;
import com.kh.so.member.model.dao.MemberDao;
import com.kh.so.member.model.vo.Member;
import com.kh.so.member.model.vo.Your;


public class MemberService {
	private Connection con;
	private MemberDao mDao = new MemberDao();
	
	public int insertMember(Member m) throws MemberException {
		con = getConnection();
		int result = mDao.insertMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member m) throws MemberException {
		con = getConnection();
		Member result = mDao.selectMember(con, m);
		
		close(con);
		
		if(result == null) throw new MemberException("[Service에러] : 로그인 실패!");
		
		return result;
	}
	
	public int updateMember(Member m) throws MemberException {
		con = getConnection();
		
		int result = mDao.updateMember(con, m);
		
		if( result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String mId) throws MemberException {
		con = getConnection(); 
		int result = mDao.deleteMember(con, mId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public int idDupCheck(String id) {
		con = getConnection();
		
		int result = mDao.idDupCheck(con,id);
		
		close(con);

		return result;
	}

	public Your yourPage(String mId) {
		con = getConnection();
		
		Your y = mDao.yourPage(con, mId);
		
		close(con);
		
		return y;
	}

	public ArrayList<Group> myGroup(String mId) {
		con = getConnection();
		
		ArrayList<Group> glist = mDao.myGroup(con, mId);
		
		close(con);
		
		return glist;
		
	}
	
}
