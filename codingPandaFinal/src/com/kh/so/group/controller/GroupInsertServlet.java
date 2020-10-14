package com.kh.so.group.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

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
import com.kh.so.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



/**
 * Servlet implementation class GroupInsertServlet
 */
@WebServlet("/gInsert.do")
public class GroupInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		// 1. 전송받을 최대 크기 설정하기
				// 10MB -> (Byte 크기로 변환하기)
				// (1024 Byte -> 1KB / 1024KB -> 1MB)
				// 1024 * 1024 * 10; 
				int maxSize = 1024 * 1024 * 10; // 10MB;
				
		// 2. multipart/form-data 형식으로 전송되었는지 확인
		if(! ServletFileUpload.isMultipartContent(request)) {
				request.setAttribute("error-msg", "multipart로 전송되지 않았습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp")
					           .forward(request, response);
				}
		
		// 3. 웹 상의 루트 (최상위) 경로를 활용하여 
		//      저장할 폴더 위치 설정하기
				String root = request.getServletContext().getRealPath("/");
				String savePath = root + "resources/groupUploadFiles";
		
		// 4.  설정한 정보들 바탕으로 멀티파트 객체 생성하기
		// request --> MultipartRequest
				MultipartRequest mre = new MultipartRequest(
                                                               request, // 속성 변경을 위한 원본 객체
                                                               savePath, // 저장할 파일 경로
                                                              maxSize, // 저장할 파일 최대 크기
                                                             "UTF-8", // 저장할 문자셋
                                                              new DefaultFileRenamePolicy()
                                                              // 만약 폴더에 같은 이름의 파일이 
                                                             // 새로 저장될 경우 자동으로 
                                                            // 이름을 바꿔주는 정책
                                                           // 000.txt ----> 0001.txt ---> 0002.txt
); 
       // ----------------- 파일 업로드 설정 끝 !! --------------- //
	  // 5-1. 기본 전송값 처리하기
		String gTitle = mre.getParameter("gTitle");
		String gStartDate =mre.getParameter("gStartDate");
		String gEnddate =mre.getParameter("gEnddate");
		String gAddress = mre.getParameter("gAddress");
		String gContent =mre.getParameter("gContent");
		String gLang = mre.getParameter("gLang");
		int gMoney = Integer.parseInt(mre.getParameter("gMoney"));
		int gMax =  Integer.parseInt(mre.getParameter("gMax"));
		String memberId = mre.getParameter("memberId");
		
		Date writeDateStart = new Date(new GregorianCalendar().getTimeInMillis());
		Date writeDateEnd = new Date(new GregorianCalendar().getTimeInMillis());
		
		if(gStartDate != null && ! gStartDate.equals("")) {
			
			String[] dateArr = gStartDate.split("-");
			int[] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDateStart = new Date(new GregorianCalendar(
									intArr[0], intArr[1] - 1, intArr[2]
						).getTimeInMillis());
		}
		
		if(gEnddate != null && ! gEnddate.equals("")) {
			System.out.println("GroupInsert서블릿에서 endDate확인 : " + gEnddate);
			
			String[] dateArr = gEnddate.split("-");
			int[] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDateEnd = new Date(new GregorianCalendar(
									intArr[0], intArr[1] - 1, intArr[2]
						).getTimeInMillis());
		} else if(gEnddate.contentEquals("")) {
			writeDateEnd = null;
		}
		
		// 5-2. 파일 전송 후 저장하기
		// 전달받은 파일을 먼저 저장한 후에 해당 파일의
		// 이름을 가져온다.
				
		String gPicture = mre.getFilesystemName("thumbnailImg1");
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("member");
		String gLeader = m.getmId();

		// 6. 전달받은 값을 서비스로 넘기기 
		Group g = new Group(gTitle, writeDateStart, writeDateEnd, gAddress, gContent, gPicture, gLang, gMoney, gMax, gLeader);
		System.out.println("가입 정보 확인 : " + g);
		
		// 그룹 생성 실행
		GroupService gs =new GroupService();
		int result = gs.insertGroup(g);
		if(result> 0) {
		System.out.println("그룹 생성 성공!");
		response.sendRedirect("index.jsp");
		} else {
		System.out.println("그룹 생성 실패!");
		RequestDispatcher view
		   = request.getRequestDispatcher("views/common/errorPage.jsp");
		
		request.setAttribute("error-msg", "그룹 생성 실패");
		
		view.forward(request, response);
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
