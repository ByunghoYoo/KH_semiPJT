package com.kh.so.group.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.group.model.service.GroupService;

@WebServlet("/gDelete.do")
public class GroupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		GroupService gs = new GroupService();
		
		int result = gs.deleteGroup(gno);
		
		if(result > 0) {
			System.out.println("그룹삭제 성공!");
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("error-msg", "그룹삭제 실패");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
			System.out.println("그룹삭제 실패!");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
