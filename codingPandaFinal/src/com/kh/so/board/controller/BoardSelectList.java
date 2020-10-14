package com.kh.so.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.so.board.model.serivce.BoardService;
import com.kh.so.board.model.vo.Board;
import com.kh.so.board.model.vo.PageInfo;
import com.kh.so.group.model.vo.Group;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/selectList.bo")
public class BoardSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSelectList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServl	etRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> list = new ArrayList<Board>();
		BoardService bs = new BoardService();
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		Group g = new Group();
		
		g.setGno(gno);
		
		int startPage;
		int endPage;
		int maxPage;
		int currentPage;
		int limit;
		
		currentPage = 1;
		
		limit = 10;
		if(request.getParameter("currentPage") != null) {
			currentPage
			   = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int listCount = bs.getListCount(gno);
		
		// System.out.println("총 게시글 수 : " + listCount);
		
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;

		endPage = startPage + limit - 1;
		
		if( endPage > maxPage) {
			endPage = maxPage;
		}
		
		list = bs.selectList(gno, currentPage, limit);
		String page = "";
		
		if(list != null){
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, 
	                   maxPage, startPage, endPage);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("g", g);
			
			page = "views/board/boardList.jsp";
			
		} else {
			request.setAttribute("error-msg", "게시글 목록 조회 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
