<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>
<section class="wrapper">
        <div class="container">
            <div class="row sub_content">
                <div class="col-md-4 mx-auto">
                    <div class="dividerHeading">
                        <h4><span>Sign Up</span></h4>
                    </div>
                    <form id="registerform" method="post" name="registerform" action="<%= request.getContextPath() %>/mInsert.me">
                        <div class="form-group" style="display: flex; margin-bottom : 6px">
                            <input type="text" class="form-control"  placeholder="아이디 입력" name="mId" id="userId" required="required">
                            <div style="width:30px;"></div>
                            <button class="btn btn-default btn-sm" id="idCheck" style="width:150px; height:46px;">중복 확인</button>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="비밀번호 입력" name="mPwd" id="userPwd" required="required">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="비밀번호 확인" name="mPwd2" id="userPwd2" required="required">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="이름" name="mName" required="required">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="휴대전화" name="mPhone">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="이메일" name="mEmail">
                        </div>
                        <div class="form-group text-right">
                            <button type="submit" class="btn btn-default btn-sm" style="width:350px; height:46px;">가입하기</button>
                        </div>    
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    			<script>
				function insertMember() {
					$("#registerform").submit();
				}
				
				$("#registerform").submit(function(event){
					if($("#userPwd").val() == "" || $("#userId").val() == "") alert("아이디나 비밀번호는 필수 값입니다.");
					else if($('#userPwd').val() != $('#userPwd2').val()) alert("비밀번호를 다시 확인해주세요");
					else return;
					event.preventDefault();
				});
				
				$('#idCheck').on('click',function(){
					$.ajax({
						url : '/codingPanda/idDup.do',
						type : 'post',
						data : { userId : $('#userId').val() },
						success : function(data){
							
							// console.log(data);
							
							// 결과가 0이면 사용자 없음 : 가입 가능
							//        1이면 1 명 사용중 : 가입 불가
							if( data == 0) {
								alert("사용 가능한 아이디입니다.");
							} else {
								alert("이미 사용 중인 아이디입니다.");
							}
							
						}, error : function(){
							console.log("에러 발생~!!");
						}
					});
				});
					
					
				</script>
    
 <%@ include file="../common/footer.jsp" %> 