package com.kh.so.faq.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.faq.model.service.FaqService;
import com.kh.so.faq.model.vo.Faq;

@WebServlet("/insert.fq")
public class FaqInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FaqInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String writer = request.getParameter("writer");
		int gno = Integer.parseInt(request.getParameter("gno"));
		String content = request.getParameter("replyContent");
		int reffno = Integer.parseInt(request.getParameter("reffno"));
		int flevel = Integer.parseInt(request.getParameter("flevel"));
		
		Faq faq = new Faq(gno, writer, content, reffno, flevel);
		
		int result = new FaqService().insertFaq(faq);
		
		if(result > 0) {
			response.sendRedirect("selectOne.gr?gno="+gno);
		} else {
			System.out.println("FaqInsert서블릿에서 faq추가 실패");
			request.setAttribute("error-msg", "faq 추가 실패");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
