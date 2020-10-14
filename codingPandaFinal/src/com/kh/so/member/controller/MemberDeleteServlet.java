package com.kh.so.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.so.common.exception.MemberException;
import com.kh.so.member.model.service.MemberService;
import com.kh.so.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mDelete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String mId = ((Member)session.getAttribute("member")).getmId();
		
		System.out.println("회원 기존 아이디  : " + mId);
		
		MemberService ms = new MemberService();
		
		try {
			ms.deleteMember(mId);
			
			System.out.println("회원 탈퇴 성공!");
			
			session.invalidate();
			
			response.sendRedirect("index.jsp");
			
		} catch(MemberException e) {
			request.setAttribute("error-msg", "회원 탈퇴 수행 중 에러 발생!");
			request.setAttribute("exception", e);
			
			request
			.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
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
