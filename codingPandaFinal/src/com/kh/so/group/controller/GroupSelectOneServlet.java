package com.kh.so.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.so.faq.model.service.FaqService;
import com.kh.so.faq.model.vo.Faq;
import com.kh.so.group.model.service.GroupService;
import com.kh.so.group.model.vo.Group;
import com.kh.so.member.model.vo.Member;

@WebServlet("/selectOne.gr")
public class GroupSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupSelectOneServlet() {
        super();
    }

    // faq는 group완성하고 만들자.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));

		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("member");
		String mId = m.getmId();
		
		Group g = new GroupService().selectOne(gno);
		ArrayList<Faq> faqList = new FaqService().selectList(gno);
		ArrayList<Member> mList = new GroupService().myMember(gno);
		Member join = new GroupService().checkMember(gno, mId);
		
		String page = "";
		
		if(g != null) {
			request.setAttribute("group", g);
			request.setAttribute("faqList", faqList);
			request.setAttribute("mList", mList);
			request.setAttribute("join", join);
			
			page = "views/group/groupDetail.jsp";
			
		} else {
			System.out.println("GroupSelectOneServlet : 게시글 상세페이지 조회 실패!");
			request.setAttribute("error-msg", "게시글 상세 보기 실패");
			page = "views/common/error.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
