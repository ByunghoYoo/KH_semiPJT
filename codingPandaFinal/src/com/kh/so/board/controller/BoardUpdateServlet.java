package com.kh.so.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.so.board.model.vo.Board;
import com.kh.so.board.model.serivce.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/bUpdate.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BoardService bs = new BoardService();

		int maxSize = 1024 * 1024 * 10; // 10MB;

		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", 
					             "multipart로 전송되지 않았습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		MultipartRequest mre = new MultipartRequest(
									request, savePath, maxSize, "UTF-8",
									new DefaultFileRenamePolicy()
				);
		
		int bno = Integer.parseInt(mre.getParameter("bno"));
		String title = mre.getParameter("title");
		String content = mre.getParameter("content");
		
		String fileName = mre.getFilesystemName("thumbnailImg1");
		
		Board b = bs.selectOne(bno);
		
		if(fileName != null && fileName.length() > 0) {

			if(b.getbPicture() != null) {
				
				File originFile = new File(savePath+"/"+b.getbPicture());
				System.out.println("파일 삭제 확인 : " + originFile.delete());
			}
			
			b.setbPicture(fileName);
		}
		
		b.setbTitle(title);
		b.setbContent(content);
		
		int result = new BoardService().updateBoard(b);
		
		if(result > 0) {
			response.sendRedirect("selectOne.bo?bno="+bno);
		} else {
			request.setAttribute("error-msg", "게시글 수정 실패!");
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
