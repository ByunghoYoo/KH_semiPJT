package com.kh.so.member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.so.common.exception.MemberException;
import com.kh.so.member.model.service.MemberService;
import com.kh.so.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mUpdate.me")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			
			request.setAttribute("error-msg", "멀티 파트 형식으로 보내주세요~");
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 10;
		
		String root = request.getServletContext().getRealPath("/resources");
		String savePath = root + "/memberUploadFiles/"; 
		
		MultipartRequest mre = new MultipartRequest(
									request, savePath, maxSize,
									"UTF-8", new DefaultFileRenamePolicy());
		
		String mPwd = getSHA512(mre.getParameter("mPwd"));
		String mName = mre.getParameter("mName");
		String mEmail = mre.getParameter("mEmail");
		String mPhone = mre.getParameter("mPhone");
		String mContent = mre.getParameter("mContent");
		String mBirth = mre.getParameter("mBirth");
		String mAddress = mre.getParameter("mAddress");
		String mLang = mre.getParameter("mLang");
		String mPicture = mre.getFilesystemName("thumbnailImg1");
		
		System.out.println("날짜 값 확인 : " + mBirth);
		Date writeDate = new Date(new GregorianCalendar().getTimeInMillis());
		
		if(mBirth != null && ! mBirth.equals("")) {
			
			String[] dateArr = mBirth.split("-");
			int[] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDate = new Date(new GregorianCalendar(
									intArr[0], intArr[1] - 1, intArr[2]
						).getTimeInMillis());
		}
		
		HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member");
		
		m.setmPwd(mPwd);
		m.setmName(mName);
		m.setmEmail(mEmail);
		m.setmPhone(mPhone);
		m.setmBirth(writeDate);
		m.setmContent(mContent);
		m.setmAddress(mAddress);
		m.setmLang(mLang);
		m.setmPicture(mPicture);

		System.out.println("변경한 회원 정보 확인 : " + m);
		
		MemberService ms = new MemberService();
		
		try {
			ms.updateMember(m);
	
			System.out.println("회원 정보 수정 완료!");
			
			request
			.getRequestDispatcher("index.jsp")
			.forward(request, response);
			
			
			
		} catch(MemberException e) {
			
			request.setAttribute("error-msg", "회원 정보 수정 중 에러 발생!");
			request.setAttribute("exception", e);
			
			request
			.getRequestDispatcher("views/common/errorPage.jsp")
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
	
	
	private static String getSHA512(String pwd) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			
			return Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			
			System.out.println("암호화 에러 발생!");
			e.printStackTrace();
			
			return null;
		}
	}

}
