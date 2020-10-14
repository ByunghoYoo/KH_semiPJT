package com.kh.so.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.so.group.model.service.GroupService;
import com.kh.so.group.model.vo.Group;
import com.kh.so.group.model.vo.GroupList;

/**
 * Servlet implementation class groupListServlet
 */
@WebServlet("/groupList.do")
public class GroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupService gl = new GroupService();
		ArrayList<GroupList> list = gl.selectList();
		System.out.println("-----리스트서블릿실행--------");
		response.setContentType("application/json; charset=utf-8");
		for(int i=0;i<list.size();i++)
		{
		System.out.println(list.get(i));
		}
		new Gson().toJson(list, response.getWriter());
		
		
		
		
		
		
		/*
		
		// 그룹시작
		int limit;
		limit = 10;
		// 총 그룹 수 가져오기
		int listCount = gl.getListCount();
		System.out.println("총 그룹 수 : " + listCount);
		
		list = gl.selectList(limit);
		System.out.println(list);
		
		String page = "";
		if(list!=null) {
			request.setAttribute("list", list);	
			page = "index.jsp";
		} else {
			request.setAttribute("error-msg", "게시글목록조회실패");
			System.out.println("에러발생");
			page = "";
		}
		request.getRequestDispatcher(page).forward(request, response);
		*/	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
