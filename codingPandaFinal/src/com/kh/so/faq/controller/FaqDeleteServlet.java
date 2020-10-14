package com.kh.so.faq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.faq.model.service.FaqService;

@WebServlet("/delete.fq")
public class FaqDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fno = Integer.parseInt(request.getParameter("fno")); 
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		int result = new FaqService().deleteFaq(fno);
		
		if(result > 0) {
			response.sendRedirect("selectOne.gr?gno="+gno);
		} else {
			request.setAttribute("error-msg", "댓글 추가 실패..!");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
			System.out.println("FaqDelete서블릿에서 문제 발생.");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
