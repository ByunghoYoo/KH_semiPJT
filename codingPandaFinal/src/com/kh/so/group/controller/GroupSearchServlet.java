package com.kh.so.group.controller;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class GroupSearchServlet
 */
@WebServlet("/searchGroup.gr")
public class GroupSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String group = request.getParameter("group");
		String language = request.getParameter("language");
		String address = request.getParameter("address");
		String start_date = request.getParameter("start_date");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("-----검색서블릿실행--------");
		System.out.println(":서블릿인");
		System.out.println(group);
		System.out.println(language);
		System.out.println(address);
		System.out.println(start_date);
		ArrayList<GroupList> list = new ArrayList<GroupList>();
		
		GroupService ns = new GroupService();

		list = ns.searchGroup(group, language, address, start_date, currentPage);
		System.out.println("서블릿 리턴");
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i).getgEndDate());
		}
		for(int i=0;i<list.size();i++)
		{
		System.out.println(list.get(i));
		}
	      
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
