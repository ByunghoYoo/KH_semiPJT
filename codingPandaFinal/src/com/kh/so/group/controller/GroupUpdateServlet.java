package com.kh.so.group.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.so.group.model.service.GroupService;
import com.kh.so.group.model.vo.Group;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

/**
 * Servlet implementation class GroupUpdateServlet
 */
@WebServlet("/gUpdate.do")
public class GroupUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1005L;

	public GroupUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupService gs = new GroupService();
		int maxSize = 1024 * 1024 * 10;
		
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", 
					             "multipart로 전송되지 않았습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/groupUploadFiles";
		
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy()); 
		
		// 멀티파트 객체 생성 끝 --------------------------
		
		
		int gno = Integer.parseInt(mre.getParameter("gno"));
		System.out.println("GroupUpdate서블릿에서 확인한 gno : " + gno);
		
		String Title = mre.getParameter("gTitle"); 
		int gMax = Integer.parseInt(mre.getParameter("gMax"));
		String Lang = mre.getParameter("gLang");
		String Content = mre.getParameter("gContent");

		String StartDate = mre.getParameter("gStartDate");
		String Enddate = mre.getParameter("gEnddate");
		System.out.println("Enddate : " + Enddate);
		
		String Address = mre.getParameter("gAddress");
		//String Regdate = request.getParameter("gRegdate");
		
		int Money = Integer.parseInt(mre.getParameter("gMoney"));
		
		// 날짜 처리 
		Date writeDateStart = new Date(new GregorianCalendar().getTimeInMillis());
		Date writeDateEnd = new Date(new GregorianCalendar().getTimeInMillis());
		
		if(StartDate != null && ! StartDate.equals("")) {
			
			String[] dateArr = StartDate.split("-");
			int[] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDateStart = new Date(new GregorianCalendar(
									intArr[0], intArr[1] - 1, intArr[2]
						).getTimeInMillis());
		}
		
		if(Enddate != null && ! Enddate.equals("")) {
			String[] dateArr = Enddate.split("-");
			int[] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDateEnd = new Date(new GregorianCalendar(
									intArr[0], intArr[1] - 1, intArr[2]
						).getTimeInMillis());
		} else if(Enddate.contentEquals("")) {
			writeDateEnd = null;
		}
		
		
		// 파일 전송 후 저장 
		String Picture = mre.getFilesystemName("thumbnailImg1");  
	    
		// 이전 그룹정보 가져오기 
		Group g = gs.selectOne(gno);

		// 전달한 파일이 있다면 파일 변경, 아니면 이전 파일 사용 
		if(Picture != null && Picture.length() > 0) {

			if(g.getgPicture() != null) {
				// 만약 이전 파일이 있었다면 삭제
				File originFile = new File(savePath+"/"+g.getgPicture());
				System.out.println("파일 삭제 확인 : " + originFile.delete());
			}
			
			g.setgPicture(Picture);
		}
		
		// 기존의 그룹 정보를 새로운 값으로 변경하기
    	System.out.println("Title : " + Title);
		g.setgTitle(Title);
		g.setgStartDate(writeDateStart);
		g.setgEndDate(writeDateEnd);
		g.setgAddress(Address);
		g.setgContent(Content);
		//g.setgRegDate(Regdate);
		g.setgLang(Lang);
		g.setgMoney(Money);
		g.setGno(gno); 
		g.setgMax(gMax);
		
		
		int result= new GroupService().updateGroup(g);
		
		if(result>0) {
			System.out.println("그룹 수정 성공!");
			
			response.sendRedirect("index.jsp");
			
		}else {
			System.out.println("그룹 수정 실패!");
			request.setAttribute("error-msg", "공지사항 수정실패");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
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
