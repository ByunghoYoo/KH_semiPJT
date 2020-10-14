package com.kh.so.group.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.so.group.model.service.GroupService;
import com.kh.so.member.model.vo.Member;

/**
 * Servlet implementation class ExitMemberServlet
 */
@WebServlet("/exitMember.gr")
public class ExitMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExitMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member");
		
		String mId = m.getmId();
		
		GroupService gs = new GroupService();
		
		int result = gs.exitMember(gno, mId);
		
		if(result > 0) {
			request.getRequestDispatcher("selectOne.gr")
								.forward(request, response);
		} else {
			request.setAttribute("error-msg", "탈퇴 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp")
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
