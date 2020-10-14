<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.so.board.model.vo.*, java.util.*, com.kh.so.group.model.vo.*, com.kh.so.boardComment.model.vo.* "%>
<%
	Board b = (Board)request.getAttribute("board");
	 ArrayList<BoardComment> clist
		= (ArrayList<BoardComment>)request.getAttribute("clist");
%>
    
<%@ include file="../common/header.jsp" %>

<section class="wrapper">
    <section class="content portfolio_single">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 mx-auto">
                        <div class="item">
                            <div class="testimonial-item">
                                <div class="icon"><i class="fa fa-quote-right"></i></div>
                                <div class="icon-tr"></div>
                                <div class="testimonial-review">
                                	<% if(b.getmPicture() == null) { %>
                                    <img src="<%= request.getContextPath() %>/resources/images/panda.png" onclick="yourPage();">
                                    <% } else { %>
                                    <img src="<%= request.getContextPath() %>/resources/memberUploadFiles/<%= b.getmPicture() %>" onclick="yourPage();">
                                    <% } %>
                                    <h1><%= b.getbWriter() %><small><%= b.getbDate() %></small></h1>
                                </div>
                            </div>
                        </div>
                </div>
            </div>

            <div style="height: 30px;"></div>

            <div class="row">
                <div class="col-lg-6 images-style-inner mx-auto">
                    <div class="mx-auto text-center detailImgArea">
                    <% if(b.getbPicture() != null && b.getbPicture().length() > 0) { %>
                        <img id="detailImg1" class="rounded detailImg" 
                        src="<%= request.getContextPath() %>/resources/boardUploadFiles/<%= b.getbPicture()%>" style="height: 300px; width: 100%;">
                    <% } else { %>
                    	<img src="/coidngPanda/resources/images/logoCP.png" alt="" />
                    <% } %>
                    </div>
                </div>
            </div>

            <div style="height: 30px;"></div>

            <div class="row">
                <div class="col-lg-6 mx-auto">
                    <p><%= b.getbContent().replace("\n", "<br>") %></p>
                </div>
            </div>
            
            <div style="height: 30px;"></div>
            
            <div class="row">
                <div class="col-lg-6"></div>
                <div class="col-lg-3 text-right">
                    <!-- Standard button -->
                    <% if(m != null && m.getmName().equals(b.getbWriter())){ %>
                    <button class="btn btn-default btn-sm" type="button"
                    onclick="location.href='<%= request.getContextPath() %>/bUpView.bo?bno='+<%=b.getBno()%>">수정하기</button>
                    <% } %>
                    <button class="btn btn-default btn-sm" type="button" 
                    onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno='+<%=b.getGno()%>">목록으로</button>
                    
                </div>
            </div>
            
            <div style="height:5px;"></div>
            
            <% if(clist.size() > 0 && clist != null ) { %>
            <div style="height:20px;" class="col-lg-6 mx-auto">
            <hr />
            </div>
            <% } %>
            
            <!-- 댓글 구현!!! -->
       		<div class="replyArea">
       		<div class="row">
			<div class="col-lg-6 mx-auto" id="replySelectArea"> <!-- 게시글의 댓글 목록 구현부 -->
			<% if( clist != null ) { %>
				<% for(BoardComment bco : clist) { %>
				<div style="border-bottom:1px solid gray;">
				<div style="height:5px;"></div>
				<table id="replySelectTable" class="text-left"
	      	 	style="margin-left : <%= (bco.getcLevel()-1) * 30 %>px;
      	 		width : <%= 100 - ((bco.getcLevel()-1) * 5.5)%>%;"
	      	 	class="replyList<%=bco.getcLevel()%>">
		  		<tr>
		  			<td rowspan="2">
		  			
					<% if(m.getmId().equals(bco.getmId())) { %>		  <%-- 회원 아이디 같으면 MyPage로 --%>			
		  				<a href="<%= request.getContextPath() %>/myPage.me">
		  			<% } else { %>		  							  <%-- 회원 아이디 다르면 YourPage로 --%>
		  				<a href="<%= request.getContextPath() %>/yourPage.me?mId=<%=bco.getmId()%>">
		  			<% } %>
		  			
		  			<% if(bco.getmPicture() != null) { %>
		  					<img src="<%= request.getContextPath() %>/resources/memberUploadFiles/<%= bco.getmPicture() %>" 
		  						class="rounded-circle img-responsive" style="height:60px; width:60px;" />
		  			<% } else { %>
		  					<img src="<%= request.getContextPath() %>/resources/images/panda.png" 
		  						class="rounded-circle img-responsive" style="height:60px; width:60px;" />
		  			<% } %>
		  				</a>
		  			</td>
					<td style="width:80px;"><b>&nbsp;&nbsp;<%= bco.getcWriter() %></b></td>
					<td><%= bco.getcDate() %></td>
					<td align="center">
 					<%if(m.getmId().equals(bco.getmId())) { %>
 						<div class="text-right">
						<input type="hidden" name="cno" value="<%=bco.getCno()%>"/>
							  
						<button type="button" class="updateBtn" 
							onclick="updateReply(this);">수정하기</button>
							
						<button type="button" class="updateConfirm"
							onclick="updateConfirm(this);"
							style="display:none;" >수정완료</button> &nbsp;
							
						<button type="button" class="deleteBtn"
							onclick="deleteReply(this);">삭제하기</button> &nbsp;
						</div>
					<% } else if( bco.getcLevel() < 5) { %>
						<div class="text-right">
						<input type="hidden" name="writer" value="<%=m.getmId()%>"/>
						<input type="hidden" name="refcno" value="<%=bco.getCno()%>" />
						<input type="hidden" name="clevel" value="<%=bco.getcLevel() %>" />
						
						<button type="button" class="insertBtn" 
							 onclick="reComment(this);">답글 쓰기</button>&nbsp;&nbsp;
							 
						<button type="button" class="insertConfirm"
							onclick="reConfirm(this);"
							style="display:none;" >답글 추가 완료</button>
						</div>
					<% } else {%>
						<span>긴 대화는 카톡으로 하세요.</span>
					<% } %>
					</td>
				</tr>
				<tr class="comment replyList<%=bco.getcLevel()%>">
					<td colspan="3" style="background : transparent;">
					<textarea class="reply-content" cols="105" rows="2" style="border:none; ,width:100%; resize:none;"
					 readonly="readonly">&nbsp;&nbsp;<%= bco.getcContent() %></textarea>
					</td>
				</tr>
			</table>
			</div>
			
				
			<% } } else { %>
				<p>
					현재 등록된 댓글의 내용이 없습니다. <br>
					첫 댓글의 주인공이 되어 보세요!
				</p>
			<% } %>				
			</div>
			</div>
			
            <div style="height:5px;"></div>
            <div style="height:20px;" class="col-lg-6 mx-auto">
            <hr />
            </div>
			
	       		<div class="row">
	       			<div class="col-lg-6 mx-auto">
						<div class="replyWriteArea" style="border: 1px solid gray; border-radius: 10px;">
							<form action="/codingPanda/insertComment.bo" method="post">
								<div class="text-left">
								<div style="height : 10px"></div>
								<h3>&nbsp;&nbsp;&nbsp;<%= m.getmName() %></h3>
								</div>
								<div class="comment_form mx-auto">
									<input type="hidden" name="writer" value="<%=m.getmId()%>"/>
									<input type="hidden" name="bno" value="<%=b.getBno()%>" />
									<input type="hidden" name="refcno" value="0" />
									<input type="hidden" name="clevel" value="1" />
								
									<textArea rows="3" cols="80" id="replyContent" name="replyContent" style="border:none; ,width:100%; resize:none;" placeholder="&nbsp;&nbsp;댓글을 입력해보세요!"></textArea>
								</div>
								<div class="text-right">
								<button type="submit" class="btn btn-default btn-sm" id="addReply">댓글 등록</button>&nbsp;&nbsp;&nbsp;
								<div style="height : 10px"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
			
		  </div>
        </div>
    </section>
</section>

	<script>
	function updateReply(obj) {
		// 현재 위치와 가장 근접한 textarea 접근하기
		$(obj).parent().parent().parent().next().find('textarea')
		.removeAttr('readonly');
		
		// 수정 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.updateConfirm').css('display','inline');
		
		// 수정하기 버튼 숨기기
		$(obj).css('display', 'none');
	}
	
	function updateConfirm(obj) {
		// 댓글의 내용 가져오기
		var content
		  = $(obj).parent().parent().parent().next().find('textarea').val();
		
		// 댓글의 번호 가져오기
		var cno = $(obj).siblings('input').val();
		
		// 게시글 번호 가져오기
		var bno = '<%=b.getBno()%>';
		
		location.href="/codingPanda/updateComment.bo?"
				 +"cno="+cno+"&bno="+bno+"&content="+content;
	}
	
	function deleteReply(obj) {
		// 댓글의 번호 가져오기
		var cno = $(obj).siblings('input').val();
		
		// 게시글 번호 가져오기
		var bno = '<%=b.getBno()%>';
		
		location.href="/codingPanda/deleteComment.bo"
		+"?cno="+cno+"&bno="+bno;
	}
	
	function reComment(obj){
		// 추가 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.insertConfirm').css('display','inline');
		
		// 클릭한 버튼 숨기기
		$(obj).css('display', 'none');
		
		// 내용 입력 공간 만들기
		var htmlForm = 
			'<tr class="comment"><td></td>'
				+'<td colspan="3" style="background : transparent;">'
					+ '<textarea class="reply-content" style="background : white; resize:none;" cols="105" rows="2"></textarea>'
				+ '</td>'
			+ '</tr>';
		
		$(obj).parents('table').append(htmlForm);
		
	}
	
	function reConfirm(obj) {
		// 댓글의 내용 가져오기
		
		// 참조할 댓글의 번호 가져오기
		var refcno = $(obj).siblings('input[name="refcno"]').val();
		var level = Number($(obj).siblings('input[name="clevel"]').val()) + 1;
		
		// console.log(refcno + " : " + level);
		
		// 게시글 번호 가져오기
		var bno = '<%=b.getBno()%>';
		
		var parent = $(obj).parent().parent();
		var grandparent = parent.parent();
		var siblingsTR = grandparent.siblings().last();
		
		var content = siblingsTR.find('textarea').val();
		
		
		location.href='/codingPanda/insertComment.bo'
		           + '?writer=<%= m.getmId() %>'
		           + '&replyContent=' + content
		           + '&bno=' + bno
		           + '&refcno=' + refcno
		           + '&clevel=' + level;
	}
	
	// 유어페이지 구현하기
	function yourPage() {
		
		var mId = '<%= m.getmId() %>';
		var bId = '<%= b.getmId() %>';
		
		if(mId == bId) {
			location.href='/codingPanda/myPage.me';
		} else {
			location.href='/codingPanda/yourPage.me?mId=' + '<%= b.getmId() %>';	
		}
	}
	
	</script>


<%@ include file="../common/footer.jsp" %>
