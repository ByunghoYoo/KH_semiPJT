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
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/mLogin.me")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
				request.setCharacterEncoding("UTF-8");
				
				// 2. 정보 받아오기
				String mId = request.getParameter("mId");
				String mPwd = request.getParameter("mPwd");
				String originPwd = (String)request.getAttribute("originPwd");
				
				System.out.println("원본 비밀번호 : " + originPwd);
				System.out.println("암호화 비밀번호 : " + mPwd);
						
				Member m = new Member(mId, mPwd);
				
				// 3. 회원 정보를 담아 데이터베이스와 비교하기
				MemberService ms = new MemberService();
				
				try {
					m = ms.selectMember(m);
					
					HttpSession session = request.getSession();
					
					session.setAttribute("member", m);
					response.sendRedirect("index.jsp");
					
				} catch(MemberException e) {
					
					request.setAttribute("error-msg", "회원 로그인에 실패하였습니다.");
					request.setAttribute("exception", e);
					request.getRequestDispatcher("views/common/errorPage.jsp")
					       .forward(request, response);
				}
				
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
