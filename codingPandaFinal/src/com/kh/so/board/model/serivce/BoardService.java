package com.kh.so.board.model.serivce;

import static com.kh.so.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.so.board.model.dao.BoardDAO;
import com.kh.so.board.model.vo.Board;

public class BoardService {
	private Connection con;
	private BoardDAO bDAO = new BoardDAO();
	
	public int getListCount(int gno) {
		con = getConnection();
		
		int result = bDAO.getListCount(con, gno);
		
		close(con);
		
		return result;
	}

	public ArrayList<Board> selectList(int gno, int currentPage, int limit) {
		con = getConnection();
		
		ArrayList<Board> list = bDAO.selectList(con, gno, currentPage, limit);

		close(con);
		
		return list;
	}

	public int insertBoard(Board b, int gno) {
		con = getConnection();
		int result = bDAO.insertBoard(con, b, gno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Board selectOne(int bno) {
		con = getConnection();
		Board b = bDAO.selectOne(con, bno);
		
		close(con);
		
		return b;
	}

	public int updateBoard(Board b) {
		con = getConnection();
		int result = bDAO.updateBoard(con,b);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bno) {
		con = getConnection();
		int result = bDAO.deleteBoard(con, bno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

}
