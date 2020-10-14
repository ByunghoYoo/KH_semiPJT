<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>

<section class="wrapper">
        <div style="height: 30px;"></div>
        <div class="container">
            <div class="col-md-4 offset-md-4">
                <div class="dividerHeading">
                    <h4><span>회원 정보 수정</span></h4>
                </div>

                <form id="registerform" method="post" name="registerform" enctype="multipart/form-data" action="<%= request.getContextPath() %>/mUpdate.me">
                    <div class="form-group text-center" id="titleImgArea" style="color: red;">
						<img class="rounded" id="titleImg" style="height: 200px; width: 100%;">
                        ↑ 이곳을 눌러 사진을 첨부하세요!
                    </div>
                    <div class="form-group">
                    <h3 class="text-left"> 회원님의 ID : <%= m.getmId() %></h3>
                    </div>
                    <div class="form-group">
                    	<p>비밀번호 : </p>
                        <input type="password" class="form-control" name="mPwd" id="userPwd" required="required">
                    </div>
                    <div class="form-group">
                    	<p>비밀번호 확인 : </p>
                        <input type="password" class="form-control" name="mPwd2" id="userPwd2" required="required">
                    </div>
                    <div class="form-group">
                    	<p>이름 : </p>
                        <input type="text" class="form-control" name="mName" value="<%= m.getmName() %>" required="required">
                    </div>
                    <div class="form-group">
                    	<p>생일 : </p>
                        <input type="date" class="form-control" name="mBirth" value="birth">
                    </div>
                    <div class="form-group">
                    	<p>전화번호 : </p>
                        <input type="text" class="form-control" name="mPhone" value="<%= m.getmPhone() %>" placeholder="휴대전화">
                    </div>
                    <div class="form-group">
                    	<p>이메일 : </p>
                        <input type="email" class="form-control" name="mEmail" value="<%= m.getmEmail() %>" placeholder="이메일">
                    </div>
                    <div class="form-group">
                    	<p>주소 : </p>
                        <input type="text" class="form-control" name="mAddress">  
                    </div>
                    <div class="form-group">
                    	<p>관심언어 : </p>
                        <input type="text" class="form-control" name="mLang">  
                    </div>
                    <div class="form-group">
                    	<p>자기소개 : </p>
                        <textarea class="form-control" name="mContent" cols="20" rows="5" style="resize:none;"></textarea>
                    </div>
                	
       	            <div style="height : 20px;"></div>
                	
                    <div class="form-group mx-auto text-center">
                        <button type="button" class="btn btn-default btn-md" style="width:100px;"
                        onclick="location.href='<%= request.getContextPath() %>/index.jsp'">메인으로</button> &nbsp;
                        <button type="submit" class="btn btn-default btn-md" style="width:100px;">수정완료</button> &nbsp;
                        <button type="button" class="btn btn-default btn-md" style="width:100px;"
                        onclick="deleteMember();">회원탈퇴</button>
                    </div>
                    <div class="fileArea" id="fileArea">
						<input type="file" id="thumbnailImg1"
		      			name="thumbnailImg1" onchange="loadImg(this, 1);" />
		  			</div>
                    </form>
                </div>
            </div>
            <div style="height : 20px;"></div>
 </section>
 
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
			
			function insertMember() {
				$("#registerform").submit();
			}
			
			$("#registerform").submit(function(event){
				if($('#userPwd').val() != $('#userPwd2').val()) alert("비밀번호를 다시 확인해주세요");
				else return;
				event.preventDefault();
			});
			
			function deleteMember() {
				if(window.confirm("당신이 이끄는 모임이 전부 사라집니다.\n정말 삭제하실건가요?ㅜㅜ")){
					location.href="/codingPanda/mDelete.me";
				} else {
					alert("앞으로도 많이 이용해주세요 ^^");
				}
			}
			
		</script>
 

 <%@ include file="../common/footer.jsp" %> 