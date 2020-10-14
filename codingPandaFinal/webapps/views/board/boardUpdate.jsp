<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.so.board.model.vo.*" %>

<% Board b = (Board)request.getAttribute("board"); %>
 
<%@ include file="../common/header.jsp" %>

<section class="wrapper">
    
<div style="height: 30px;"></div>

    <div class="container">
        <div class="col-lg-6 mx-auto">
            <div class="dividerHeading">
                <h4><span>게시글 수정</span></h4>
            </div>
        </div>
       
        <form id="updateForm" method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-lg-6 mx-auto" style="margin-top : -30px;">
					<h3>작성자 : <%= b.getbWriter() %></h3>
					<input type="hidden" name="bno" value="<%= b.getBno() %>"/>
				</div>
			</div>
			
			<div style="height: 10px;"></div>
			
            <div class="row">
                <div class="col-lg-6 mx-auto">
                	<h3><span>제목</span></h3>
                	<input type="text" name="title" size="51" class="form-control" value="<%= b.getbTitle() %>">
                </div>
            </div>

            <div style="height: 10px;"></div>

            <div class="row">
                <div class="col-lg-6 images-style-inner mx-auto" id="titleImgArea">
                    <h3><span>사진 수정</span></h3>
                    <div class="text-center" style="color: red;">
                        <img src="<%= request.getContextPath() %>/resources/boardUploadFiles/<%= b.getbPicture() %>" 
                        class="rounded" id="titleImg" style="height: 300px; width: 100%;">
                        ↑ 이곳을 눌러 사진을 변경하세요!
                    </div>
                </div>
            </div>

            <div style="height: 10px;"></div>

            <div class="row">
                <div class="col-lg-6 mx-auto">
                    <h3><span>내용</span></h3>
                    <textarea class="form-control" name="content" cols="20" rows="5" style="resize:none;"><%=b.getbContent() %></textarea>
                </div>
            </div>

            <div style="height: 30px;"></div>
            
            <div class="fileArea" id="fileArea">
				<input type="file" id="thumbnailImg1"
		      				name="thumbnailImg1" onchange="loadImg(this, 1);" />
		  	</div>
		  	
            <div class="row">
                <div class="col-lg-9 text-right">
                    <!-- Standard button -->
                    <button class="btn btn-default btn-sm" onclick="complete();">수정완료</button>
                    <button class="btn btn-default btn-sm" onclick="deleteBoard();">삭제하기</button>
                </div>
   				<script>
					function complete(){
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bUpdate.bo");
					}
					
					function deleteBoard(){
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bDelete.bo");
					}
				
				</script>
            </div>
        </form>
	</div>
	
			<script>
			// 사진 게시판 미리보기 기능 지원 스크립트
			$(function(){
				$('#fileArea').hide();
				
				$('#titleImgArea').click(() => {
					$('#thumbnailImg1').click();
				});
				
				$('#contentImgArea1').click(() => {
					$('#thumbnailImg2').click();
				});
				
				$('#contentImgArea2').click(() => {
					$('#thumbnailImg3').click();
				});

				$('#contentImgArea3').click(() => {
					$('#thumbnailImg4').click();
				});
			});
			
			function loadImg(value, num){
				
				if(value.files && value.files[0])  {
					
					var reader = new FileReader();
					
					reader.onload = function(e){
						
						switch(num) {
						case 1 : $('#titleImg').attr('src', e.target.result);
							break;
						case 2 : $('#contentImg1').attr('src', e.target.result);
							break;
						case 3 : $('#contentImg2').attr('src', e.target.result);
							break;
						case 4 : $('#contentImg3').attr('src', e.target.result);
							break;
						}
					}
					reader.readAsDataURL(value.files[0]);
				}	
			}
		</script>

<div style="height: 50px;"></div>

</section>

<%@ include file="../common/footer.jsp" %>
