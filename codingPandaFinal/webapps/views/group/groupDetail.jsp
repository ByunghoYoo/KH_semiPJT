<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.kh.so.group.model.vo.*, java.util.*, com.kh.so.faq.model.vo.*"%>


<%
	Group g = (Group)request.getAttribute("group");
	ArrayList<Faq> faqList = (ArrayList<Faq>)request.getAttribute("faqList");
	ArrayList<Member> mList = (ArrayList<Member>)request.getAttribute("mList");
	Member join = (Member)request.getAttribute("join");
%>

<%@ include file="../common/header.jsp"%>



<!-- 전체모임페이지 시작 -->


<!--start wrapper-->
<section class="wrapper">
	<section class="content blog">
		<div class="container">
			<div class="row">
				<div class="blog_single mx-auto col-md-8">
					<div class="dividerHeading text-center">
						<h4>
							<span>모임 상세보기</span>
						</h4>
					</div>
					<article class="post">
						<figure class="post_img">
							<!-- 그룹 대표 이미지 설정부분 -->
							<img class="mx-auto"
							<% if(g.getgPicture() != null) { %>
								src="/codingPanda/resources/groupUploadFiles/<%= g.getgPicture() %>"
							<% } else { %>
								src="/codingPanda/resources/images/panda.png"
							<% } %>
								style="height:350px; width:100%;"
								alt="blog post">
						</figure>

						<!-- 화면 맨 오른쪽 날짜 표시된 초록색부분. -->
						<div class="post_date">
							<%
								System.out.println(g.getgRegDate());
							%>
							<span class="day"><%=g.getgRegDate().getDate()%>일</span> <span
								class="month"><%=g.getgRegDate().getMonth() + 1%>월</span>
						</div>
						<div class="post_content">
							<div class="post_meta">
								<h1 style="color:black;">
									<%=g.getgTitle()%>
								</h1>
								<% System.out.println(join.getmId()); %>
								<% if(join.getmId() == ""){  %>
								<button class="btn btn-primary btn-sm" type="button"
								onclick="location.href='<%= request.getContextPath() %>/joinMember.gr?gno='+<%= g.getGno() %>">모임가입</button>
								<% } %>
								
								<% if (m.getmId().equals(g.getgLeader())) { %>
								<button class="btn btn-info btn-sm" type="button"
								onclick="location.href='<%= request.getContextPath() %>/gUpView.do?gno='+<%= g.getGno() %>">모임수정</button>
								<% } %>	
								
								<% for(Member ml : mList) { %>
								<% if(ml.getmId().equals(m.getmId()) && !m.getmId().equals(g.getgLeader())) { %>
								<button class="btn btn-danger btn-sm" type="button"
								onclick="location.href='<%= request.getContextPath() %>/exitMember.gr?gno='+<%= g.getGno() %>">모임탈퇴</button>
								<% } } %>
								
								<div class="metaInfo">
									<br />
									<span><i class="fa fa-user"></i>모임장 : <a href="#"><%=g.getgLeaderName()%></a>
									<span><i class="fa fa-comments"></i> 
 										<%= faqList.size() %>
									개의 댓글</span><br> <span><i class="fa fa-calendar"></i>모임시작일 : <%=g.getgStartDate()%>
									<%
 										if (g.getgEndDate() != null) {
 									%> ~ 모임 종료일 : <%=g.getgEndDate()%> </span>

									<%
										}
									%>
									&nbsp;&nbsp;&nbsp;
									<span><i class="fa fa-laptop">언어 : <%= g.getgLang() %></i></span>
								</div>
							</div>
							<p>
								<%=g.getgContent()%>

								<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
								<br /> <br />
							</p>
	
						</div>
						<div class="text-right">
						<% for(Member ml : mList) { %>
						<% if(ml.getmId().equals(m.getmId())) { %>
						<button class="btn btn-sm btn-default" 
						onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>'">
						모임 게시판 이동</button>
						<% break; }} %>
						</div>

					</article>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 mx-auto">
					<div class="dividerHeading text-center">
						<h4>
							<span>
								가입한 모임원
							</span>
						</h4>
					</div>
					<% for(Member ml : mList) { %>
					<% System.out.println(ml.getmId() + " : 현재 가입한 내역"); %>
					<div class="about_author" style="height:120px;">
						<div class="author_desc" 
						<% if(!ml.getmId().equals(m.getmId())) { %>
						onclick="location.href='<%= request.getContextPath() %>/yourPage.me?mId=<%= ml.getmId() %>'">
						<% } else { %>
						onclick="location.href='<%= request.getContextPath() %>/myPage.me'">
						<% } %>
							<% if(ml.getmPicture() != null) {%>
							<img src="<%= request.getContextPath() %>/resources/memberUploadFiles/<%= ml.getmPicture() %>">
							<% } else { %>
							<img src="<%= request.getContextPath() %>/resources/images/panda.png">
							<% } %>
						</div>

						<!-- 모임장 정보 -->
						<div class="author_bio">
							<h3 class="author_name">
								<a href="#"><%= ml.getmName() %></a>
							</h3>
							
							<p class="author_det">
								<% if(ml.getmContent() != null) { %>
								<%= ml.getmContent().replace("\r\n", "<br>") %>
								<% } else { %>
								자기소개가 없습니다. 자기소개를 작성해보세요~!
								<% } %>
								<br /> <br />
							</p>
						</div>
					</div>
					
					<br />
					<% } %>
					</div>
				</div>

<!-- FAQ 댓글 구현부 -->
            <%
               System.out.println("댓글개수 : " + faqList.size());
            %>
            <div class="row">
            <div class="col-md-8 mx-auto"></div>
            <div class="news_comments">
               <div class="dividerHeading text-center">
                  <h4>
                     <span>FAQ모임에 관한 질문 (댓글 <%= faqList.size() %>개)
                     </span>
                  </h4>
               </div>
               <%
                  if (faqList.size() != 0) {
               %>
               <!-- 댓글 목록이 있을 경우 if(faqList.size() != 0) -->
               <%
                  for (Faq faq : faqList) {
               %>

               <!-- if조건문 걸어서 수정/삭제버튼 보이고가리게하기. -->
               <div id="comment">
               <% if(faq.getfLevel() == 1) { %>
                  <ul id="comment-list">
                     <li class="comment">
                        <div class="avatar">
                           <img alt=""
                              src="/codingPanda/resources/images/blog/avatar_2.png"
                              class="avatar">
                        </div>
                        <div class="comment-container">
                           <h4 class="comment-author">
                              <a href="#"><%= faq.getwriter() %></a>
                           </h4>
                           <div class="comment-meta">
                              <a href="#" class="comment-date"><%= faq.getfDate() %></a>
                           </div>
                           <div class="comment-body">
                              <p><%= faq.getfContent() %></p>
                           </div>
                        </div>
                        
                        <input type="hidden" name="fno" value="<%= faq.getFaqNo() %>" />
                        <input type="hidden" name="writer" value="<%= m.getmId() %>" /> 
                        <input type="hidden" name="reffno" value="<%= faq.getFaqNo() %>" /> 
                        <input type="hidden" name="flevel" value="<%= faq.getfLevel() %>" />
      
                        <input type="hidden" name="flevel" value="<%= faq.getfLevel() %>" />
      
      					<% if(m.getmId().equals(faq.getwriter())) { %>
                        <button class="btn btn-warning btn-sm updateFaq"
                           onclick="updateFaq(this);">수정하기</button>
                        <button class="btn btn-info btn-sm updateConfirm"
                           onclick="updateConfirm(this);" style="display: none;">수정완료</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteFaq(this)">삭제하기</button>
                        <% } %>

                        <button class="btn btn-primary btn-sm" onclick="replyFaq(this)">리댓달기</button>
                        <button class="btn btn-info btn-sm insertConfirm"
                           onclick="reConfirm(this)" style="display: none;">리댓완료</button>
      
      
                        <!-- 수정하기 클릭시 나오는 새창 -->
                        <div class="comment replyList<%= faq.getfLevel() %>">
                           <div style="background: transparent;">
                              <textarea class="form-control faqContent" cols="105" rows="3"
                                 style="display: none;"><%= faq.getfContent() %></textarea>
                           </div>
                        </div>
      
                        <!-- 리댓달기 클릭시 나오는 새창 -->
                        <div class="comment replyList<%= faq.getfLevel() %>">
                           <div style="background: transparent;">
                              <textarea class="form-control faqContent2" cols="105" rows="3"
                                 style="display: none;"></textarea>
                           </div>
                        </div>
                     <%
                        for (Faq refaq : faqList) {
                           if(refaq.getRefFno() == faq.getFaqNo() && refaq.getfLevel() != 1) {
                     %>
                        <ul class="children">
                           <li class="comment">
                              <div class="avatar">
                                 <img alt=""
                                    src="/codingPanda/resources/images/blog/avatar_3.png"
                                    class="avatar">
                              </div>
                              <div class="comment-container">
                                 <h4 class="comment-author">
                                    <a href="#"><%= refaq.getwriter() %></a>
                                 </h4>
                                 <div class="comment-meta">
                                    <a href="#" class="comment-date"><%= refaq.getfDate() %></a>
                                 </div>
                                 <div class="comment-body">
                                    <p><%= refaq.getfContent() %></p>
                                 </div>
                              </div>
                              <input type="hidden" name="fno" value="<%= refaq.getFaqNo() %>" />
                              <input type="hidden" name="writer" value="<%= m.getmId() %>" /> 
                              <input type="hidden" name="reffno" value="<%= refaq.getRefFno() %>" /> 
                              <input type="hidden" name="flevel" value="<%= refaq.getfLevel() %>" />
            
                               <% if(m.getmId().equals(refaq.getwriter())) { %>
                        		<button class="btn btn-warning btn-sm updateFaq"
                           				onclick="updateFaq(this);">수정하기</button>
                        		<button class="btn btn-info btn-sm updateConfirm"
                           				onclick="updateConfirm(this);" style="display: none;">수정완료</button>
                        		<button class="btn btn-danger btn-sm" onclick="deleteFaq(this)">삭제하기</button>
                        		<% } %>
                              <button class="btn btn-primary btn-sm" onclick="replyFaq(this)">리댓달기</button>
                              <button class="btn btn-info btn-sm insertConfirm"
                                 onclick="reConfirm(this)" style="display: none;">리댓완료</button>
            
            
                              <!-- 수정하기 클릭시 나오는 새창 -->
                              <div class="comment replyList<%= refaq.getfLevel() %>">
                                 <div style="background: transparent;">
                                    <textarea class="form-control faqContent" cols="105" rows="3"
                                       style="display: none;"><%= refaq.getfContent() %></textarea>
                                 </div>
                              </div>
            
                              <!-- 리댓달기 클릭시 나오는 새창 -->
                              <div class="comment replyList<%= refaq.getfLevel() %>">
                                 <div style="background: transparent;">
                                    <textarea class="form-control faqContent2" cols="105" rows="3"
                                       style="display: none;"></textarea>
                                 </div>
                              </div>
                           </li>
                        </ul>
                        <br />
                     <% } } %>                  
                     </li>
                  </ul>
               </div>
                  <% } %>
               <%
                  }} else {
               %>
               <!-- 댓글 목록이 없을 경우 -->
               <p align="center">
                  현재 등록된 FAQ가 없습니다. 첫번째 질문자가 되어주세요~! <br /> <br /> <br />
               </p>
               <%
                  }
               
               %>





               <!-- /#comments -->
               <div class="dividerHeading text-center">
                  <h4>
                     <span>댓글달기</span>
                  </h4>
               </div>
               <!-- 댓글 여기부터. -->



               <!-- ========================================================== -->
               <div class="replyArea mx-auto">
                  <div class="replyWriteArea">
                     <form action="/codingPanda/insert.fq" method="post">
                        <input type="hidden" name="writer" value="<%= m.getmId() %>" />
                        <%-- <%=m.getUserId()%> --%>
                        <input type="hidden" name="gno" value="<%=g.getGno()%>" /> <input
                           type="hidden" name="reffno" value="0" /> <input type="hidden"
                           name="flevel" value="1" />

                        <div class="comment-box row mx-auto">
                           <div class="col-sm-12">
                              <p>
                                 <textarea name="replyContent" class="form-control" rows="6"
                                    cols="40" id="replyContent"
                                    onfocus="if(this.value == 'Message') { this.value = ''; }"
                                    onblur="if(this.value == '') { this.value = 'Message'; }"
                                    placeholder="모임에 대해 어떤 점이 궁금한가요?!"></textarea>
                              </p>
                           </div>
                           <button type="submit" id="addReply"
                              class="btn btn-lg btn-default mx-auto" href="#">댓글
                              남기기</button>
                        </div>
                     </form>
                  </div>
               </div>
               </div>
            </div>
         </div>
      <!--/.row-->
      <!-- </div> -->
      <!--/.container-->
   </section>

</section>
<!--end wrapper-->

<!-- 전체모임페이지 끝. -->
<script>
	/* faq 수정 */
	function updateFaq(obj){
		$(obj).parent().find('.faqContent').css('display', 'inline');
		
		/* $(obj).parent().parent().next().find('textarea')
		.removeAttr('readonly'); */
		
		// 수정 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.updateConfirm').css('display','inline');
		
		// 수정하기 버튼 숨기기
		$(obj).css('display', 'none');
	
	}

	/* faq update complete */
	function updateConfirm(obj){
		var content = $(obj).parent().find('.faqContent').val(); 	
		
		var fno = $(obj).siblings('input').val();
		var gno = '<%= g.getGno() %>';
		
		location.href = "/codingPanda/update.fq?" + "fno="+fno+"&gno="+gno+"&content="+content; 
		
		
	}
	
	/* faq 삭제 */
	function deleteFaq(obj){
		console.log("삭제버튼눌림.");
		var fno = $(obj).siblings('input').val();
		var gno = '<%= g.getGno() %>';
		
		location.href = "/codingPanda/delete.fq?" + "fno="+fno+"&gno="+gno; 
		
	}
	
	
	/* faq 리댓달기 */
	function replyFaq(obj){
		$(obj).parent().find('.faqContent2').css('display', 'inline');
		console.log($(obj).parent().find('.faqContent2'));
		
		/* // faq 리댓완료 버튼을 화면 보이게 하기 */
		$(obj).siblings('.insertConfirm').css('display','inline');
		
		// 리댓달기 버튼 숨기기
		$(obj).css('display', 'none');
	}
	
	/* faq 리댓 완료 */
	function reConfirm(obj){
		// 참조할 댓글의 번호 가져오기
		var reffno = $(obj).siblings('input[name="reffno"]').val();
		var level = Number($(obj).siblings('input[name="flevel"]').val()) + 1;
		
		console.log(reffno + " : " + level);
		
		// 게시글 번호 가져오기
		var gno = '<%= g.getGno() %>';
		
		
		var content = $(obj).parent().find('.faqContent2').val(); 	
		console.log(content);
		
		
		// writer, replyContent
		// bno, refcno, clevel
		
		location.href='/codingPanda/insert.fq'
			   + '?writer=<%= m.getmId() %>' 
	           + '&replyContent=' + content
	           + '&gno=' + gno
	           + '&reffno=' + reffno
	           + '&flevel=' + level; 		
	}
	


</script>


<%@ include file="../common/footer.jsp"%>