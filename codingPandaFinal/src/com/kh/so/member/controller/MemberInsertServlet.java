package com.kh.so.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.common.exception.MemberException;
import com.kh.so.member.model.service.MemberService;
import com.kh.so.member.model.vo.Member;



/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 서비스 연동 서블릿
		
				// 1. 인코딩
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				
				// 2. 입력한 회원 정보 받아오기
				String mId = request.getParameter("mId");
				String mPwd = request.getParameter("mPwd");
				String mName = request.getParameter("mName");
				String mEmail = request.getParameter("mEmail");
				String mPhone = request.getParameter("mPhone");
				
				// VO 사용하기
				// 회원 가입 시 정보를 하나로 묶어 전달하는 역할
				Member m = new Member(mId, mName, mPwd, mPhone, mEmail);
			
				System.out.println("가입 정보 확인 : " + m);
				
				// 회원 가입 실행
				MemberService ms = new MemberService();
				
				try {		
					ms.insertMember(m);
				
					System.out.println("회원 가입 성공!");
					response.sendRedirect("index.jsp");
					
				} catch(MemberException e) {
					
					System.out.println("회원 가입 실패!");
					RequestDispatcher view
					   = request.getRequestDispatcher("views/common/errorPage.jsp");
					
					request.setAttribute("error-msg", "회원 가입 실패");
					request.setAttribute("exception", e);
					
					view.forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
