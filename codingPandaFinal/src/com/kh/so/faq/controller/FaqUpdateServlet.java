package com.kh.so.faq.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.faq.model.service.FaqService;
import com.kh.so.faq.model.vo.Faq;

@WebServlet("/update.fq")
public class FaqUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fno = Integer.parseInt(request.getParameter("fno"));
		int gno = Integer.parseInt(request.getParameter("gno"));
		String content = request.getParameter("content");
		
		Faq faq = new Faq();
		
		faq.setFaqNo(fno);
		faq.setfContent(content);
		
		int result = new FaqService().updateComment(faq);
		
		if(result > 0) {
			response.sendRedirect("selectOne.gr?gno="+gno);
		} else {
			request.setAttribute("error-msg", "faq댓글 수정 실패.");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
			System.out.println("FaqUpdate서블릿에서 에러 발생.");
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
