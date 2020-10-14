package com.kh.so.group.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.group.model.service.GroupService;

@WebServlet("/exportM.do")
public class ExportGroupMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExportGroupMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 강퇴시킬 모임원의 id받아오기 
		int gno = Integer.parseInt(request.getParameter("gno"));
		String exportM = request.getParameter("exportMember");
		
		System.out.println("exportM서블릿에서 gno를 잘받아오고있나? : " + gno);
		System.out.println("exportM서블릿에서 강퇴시킬 모임원의 id : " + exportM);
		
		int result = new GroupService().exportMember(gno, exportM);
		
		if(result>0) {
			System.out.println("모임원 강퇴 성공");
			
			response.sendRedirect("gUpView.do?gno="+gno);
			
		}else {
			System.out.println("모임원 강퇴 실패!");
			request.setAttribute("error-msg", "공지사항 수정실패");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
