package com.kh.so.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.group.model.service.GroupService;
import com.kh.so.group.model.vo.Group;
import com.kh.so.member.model.vo.Member;

@WebServlet("/gUpView.do")
public class GroupUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupUpdateViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int gno = Integer.parseInt(request.getParameter("gno"));
	ArrayList<Member> mList = new GroupService().myMember(gno);
	
	System.out.println("GroupUpdateView서블릿에서 gno출력 : " + gno);
	System.out.println("GroupUpdateView서블릿에서 mList출력 : " + mList);
	
	Group g = new GroupService().selectOne(gno);
	System.out.println("GroupUpdateView서블릿에서 g를 출력 : " + g);
	String page = "";
	
	
	if(g != null) {
		request.setAttribute("group", g);
		request.setAttribute("mList", mList);
		
		page = "views/group/groupUpdate.jsp";
	} else {
		request.setAttribute("error-msg", "추가할필요없지만 일단 추가한다.. 그룹 상세보기 실패?");
		page = "views/common/error.jsp";
		System.out.println("GroupUpdateView서블릿에서 실패했음.여기야여기.");
	}
	
	request.getRequestDispatcher(page).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
