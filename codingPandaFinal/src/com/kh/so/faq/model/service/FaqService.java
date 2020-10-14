package com.kh.so.faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.so.faq.model.dao.FaqDAO;
import com.kh.so.faq.model.vo.Faq;

import static com.kh.so.common.JDBCTemplate.*;

public class FaqService {
	private Connection con;
	private FaqDAO faqDAO = new FaqDAO();
	
	// faq 생성 
	public int insertFaq(Faq faq) {
		con = getConnection();
		int result = faqDAO.insertFaq(con, faq);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	// faq 전체 조회 
	public ArrayList<Faq> selectList(int gno){
		System.out.println("FaqService에 도달.");
		con = getConnection();
		ArrayList<Faq> faqList = faqDAO.selectList(con, gno);
		close(con);
		
		return faqList;
	}
	
	// faq 수정 
	public int updateComment(Faq faq) {
		con = getConnection();
		int result = faqDAO.updateFaq(con, faq);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	// faq 삭제 
	public int deleteFaq(int faqNo) {
		con = getConnection();
		int result = faqDAO.deleteFaq(con, faqNo);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	
}
