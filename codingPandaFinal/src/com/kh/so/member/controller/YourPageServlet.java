package com.kh.so.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.group.model.vo.Group;
import com.kh.so.member.model.service.MemberService;
import com.kh.so.member.model.vo.Your;

/**
 * Servlet implementation class YourPageServlet
 */
@WebServlet("/yourPage.me")
public class YourPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mId = request.getParameter("mId");
		
		System.out.println("아이디 : " +  mId);
		
		Your y = new Your();
		
		MemberService ms = new MemberService();
		
		y = ms.yourPage(mId);
		
		ArrayList<Group> glist = ms.myGroup(mId);
		
		System.out.println("조회할 회원 정보 : " + y);
		
		String page = "";
		
		if(y != null) {
			request.setAttribute("your", y);
			request.setAttribute("glist", glist);
			
			page = "views/member/yourPage.jsp";
			
		} else {
			request.setAttribute("error-msg", "유어 페이지 보기 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**ㅇ
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
