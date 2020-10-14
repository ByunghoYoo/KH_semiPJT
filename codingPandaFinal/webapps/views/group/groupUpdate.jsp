<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.kh.so.group.model.vo.*, com.kh.so.group.model.vo.*, java.util.*"%>

<%
	Group g = (Group) request.getAttribute("group");
	ArrayList<Member> mList = (ArrayList<Member>)request.getAttribute("mList");
%>

<% System.out.println("grupUpdateJSP에서 그룹정보 잘 가져오고 있니? : " + g); %>


<!-- 모임정보 수정 페이지 시작 -->


<%@ include file="../common/header.jsp"%>

<!--start wrapper-->
<section class="wrapper">
	<section class="content contact text-center">

		<div class="container">

			<!-- 
					이건 지도 나타내는 부분.
					<div class="row sub_content">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="maps">
								<div id="page_maps"></div>
							</div>
						</div>
					</div> 
				
				-->

			<div class="blog_single">
				<div class="sub_content">
					<div class="col-md-6 mx-auto">
						<div class="dividerHeading">
							<h4>
								<span>그룹 정보 수정하기</span>
							</h4>
						</div>
						<p>그룹에 대한 정보를 수정해보세요!</p>

						<form id="contactForm"
							action="<%= request.getContextPath() %>/gUpdate.do"
							enctype="multipart/form-data" method="post">

							<input type="hidden" name="gno" value="<%= g.getGno() %>">
							<div class="mx-auto">
								<div id="titleImgArea">
									<img id="titleImg"
										src="<%= request.getContextPath() %>/resources/groupUploadFiles/<%= g.getgPicture() %>"
										alt="스터디 대표 이미지"
										style="height: 300px; width: 500px; padding-bottom: 10px;"><br>

								</div>

								<button class="btn btn-warning btn-lg" type="button"
									style="padding-right: 50px; padding-left: 50px" id="insertImg">이미지
									추가하기</button>
								<button class="btn btn-success btn-lg" type="button"
									style="padding-right: 50px; padding-left: 50px" id="changeImg">이미지
									변경하기</button>
							</div>
							<div style="height: 30px;"></div>
							<div class="row form-group">
								<div class="col-lg-6 ">
									<input type="text" id="gTitle" name="gTitle"
										class="form-control" maxlength="100"
										data-msg-required="스터디명을 입력해주세요." value="<%=g.getgTitle()%>"
										required="required">
								</div>
								<div class="col-lg-6 ">
									<input type="number" id="gMax" name="gMax" max="10" min="1"
										value="<%=g.getgMax()%>" step="1" class="form-control"
										maxlength="100">
								</div>
							</div>
							<div class=row>
								<div class="col-lg-6 ">
									<select id="gLang" name="gLang" class="form-control"
										maxlength="100" data-msg-required="언어를 선택해주세요."
										value="<%=g.getgLang()%>">
										<option value="언어선택" hidden></option>
										<option value="C">C</option>
										<option value="C++">C++</option>
										<option value="C#">C#</option>
										<option value="Java">Java</option>
										<option value="Javascript">Javascript</option>
										<option value="Python">Python</option>
										<option value="SQL">SQL</option>
										<option value="기타언어">기타언어</option>
									</select>
								</div>
								<div class="col-lg-6 text-left">
									<input type="text" id="result"
										style="background-color: transparent; border: 0 solid black; text-align: left; color: royalblue; position: absolute; margin: 0, 50, 0, 0; margin-top: 10px;">
								</div>
							</div>


							<div style="height: 20px;"></div>


							<div class="row form-group">
								<div class="col-md-12">
									<textarea id="gContent" class="form-control" name="gContent"
										rows="10" cols="50" data-msg-required="내용을 입력하세요."
										maxlength="5000"><%=g.getgContent()%></textarea>
								</div>
							</div>


							<div class=row>
								<div class="col-lg-6 col-sm-6 ">
									<label for="date1">모임 시작일시</label> <input type="date"
										id="gStartDate" name="gStartDate" class="form-control"
										maxlength="100" required="required"
										value="<%=g.getgStartDate()%>" min="">
									<!-- date1에서 gStartDate로 바꿈. -->
								</div>
								<div id="long" class="col-lg-6 col-sm-6  "
									style="display: none;">
									<label for="date2">모임 종료일시</label> <input type="date"
										id="gEnddate" name="gEnddate" class="form-control"
										maxlength="100" value="<%=g.getgEndDate()%>"
										style="text-align: left" min="">
								</div>
								<div class="row mx-auto">
									<div id="check1" class="col-lg-6 col-sm-6 ">
										<label for="longTck">장기 모임일 경우 체크</label> <input type="radio"
											id="longTck" name="Tck">
									</div>
									<div id="check2" class="col-lg-6 col-sm-6 ">
										<label for="shortTck">단기 모임일 경우 체크</label> <input type="radio"
											id="shortTck" name="Tck">
									</div>
								</div>
							</div>
							<br>


							<div class="row">
								<div class="col-md-6">
									<label for="area">모임 장소</label> <input type="search"
										name="gAddress" id="gAddress" class="form-control"
										maxlength="100" value="<%=g.getgAddress()%>" required>
								</div>
								<div class="col-md-6">
									<label for="money">모임 회비</label> <input type="text"
										name="gMoney" id="gMoney" class="form-control" maxlength="100"
										style="padding-top: 10px; padding-bottom: 10px; padding-left: 20px; padding-right: 2-; padding-right: 20px;"
										value="<%=g.getgMoney()%>">
								</div>
							</div>

							<div style="height: 70px;"></div>

							<div class="row">
								<div class="col-md-6">
									<p style="font-size: 15px;">내보낼 모임원 선택</p> 
								</div>
								<div class="col-md-6">
									<select id="exportMember" name="exportMember" class=form-control data-msg-required="내보낼 모임원 선택">
										<% for(Member ml : mList) { %>
											<% if(!ml.getmId().equals(g.getgLeader())) { %>
											<!-- 모임장은 제외하고! -->
											<option value="<%= ml.getmId() %>"><%= ml.getmName() %>(<%= ml.getmId() %>)</option>
											<% } %>
										<% } %>  
									</select>
									<button class="btn btn-danger btn-sm" id="outBtn">강퇴하기</button>
								</div>
							</div>

							<div style="height: 70px;"></div>


							<div class="row">
								<div class="col-md-4">
									<button type="button" class="btn btn-warning btn-lg"
										style="padding-right: 20px; padding-left: 20px"
										onclick="location.href='/codingPanda/index.jsp'">메인으로이동</button>
								</div>
								<div class="col-md-4">
									<button class="btn btn-success btn-lg"
										style="padding-right: 20px; padding-left: 20px">모임수정하기</button>
								</div>
								<div class="col-md-4">
									<button class="btn btn-danger btn-lg" type="button"
										style="padding-right: 20px; padding-left: 20px"
										onclick="deleteGroup();">모임삭제하기</button>
								</div>
							</div>
							<!--  이미지 파일 넣고 불러오기  -->
							<div class="fileArea" id="fileArea">
								<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this, 1);" >
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>		
	</section>
</section>
<!--end wrapper-->

<script>
			// 사진 게시판 미리보기 기능 지원 스크립트
			$(function(){
				$('#fileArea').hide();
				
				$('#insertImg').click(() => {
					$('#thumbnailImg1').click();
					
					
				});
	
			});
			
			$(function(){
				$('#fileArea').hide();
				
				$('#changeImg').click(() => {
					$('#thumbnailImg1').click();
					
					
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
			
			
		/* 모임 삭제하기 */
		function deleteGroup() {
			if(window.confirm("정말 삭제하시겠습니까?")){
				
				location.href="/codingPanda/gDelete.do?gno="+<%= g.getGno() %>;
			} else {
				alert("휴");
			}
		}	
			
			
		function selectLanguage() {
			var value = $('option:selected').val();
			$('#result').val(value + " 선택!");
		}
		$('#language').change(selectLanguage);

		
		
		
		$('#longTck').click(function() {
			var ischecked = $(this).prop('checked');
			if (ischecked) {
				$('#long').css('display','inline-block');
				$('#check>label').text('단기모임일 경우 체크');
				
			} else {
				$('#long').css('display','none');
				// 여기다가 단기모임일 경우 체크. 이벤트 넣어줘라.
				$('#check>label').text('장기모임일 경우 체크');
				$(this).prop("checked", false);
				console.log("ischecked : " + ischeckd);
			}
		});
		
		$('#shortTck').click(function(){
			var ischecked = $(this).prop('checked');
			if(ischecked){
				$('#long').css('display', 'none');
				$('#longTck').attr('checked',false);
			}
		});
		
		
	    
	    $('#outBtn').on('click', function(){
			if(window.confirm($('#exportMember').val()+"님을 정말 강퇴하시겠습니까?")){
				
		    	location.href='/codingPanda/exportM.do'
		    		+'?exportMember=' + $('#exportMember').val()
		    	    +'&gno='+<%= g.getGno()%>;
		    	    
			} 
			
	    });

		
	</script>

<script> //시작날짜 오늘이전으로 클릭x 설정

/*  모임원 내보내기 기능 */

$('#outBtn').on('click', function(){ 
    	/* $.ajax({
    		url : '/CODINGPANDA/gMemberDelete.do',
    		type : 'get',
    		success : function(result){
    			    console.log(result);
    			
    			var $select = $('#select');
    			$select.find("option").remove(); // 선택 박스 초기화
    			for(var i = 0; i < result.length ; i ++){
					$select.append("<option value=' " + result[i].pno + " '>"
								  + result[i].pname + "</option>");
				}
    		}, error : function() {
    			alert("에러 발생 !!");
    		}
    	});
    });  


      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth()+1; 
      var yyyy = today.getFullYear();
      // alert(today);
       if(dd<10){
              dd='0'+dd
          } 
          if(mm<10){
              mm='0'+mm
          } 
   
      today = yyyy+'-'+mm+'-'+dd;
      document.getElementById("gStartDate").setAttribute("min", today);
   </script>


<script> // 시작일 7일 이후 종료날짜 
         $('#gStartDate').on('change',function(){
         var sdate = $('#gStartDate').val();
         var dd = sdate.split('-')[2];
         var mm = sdate.split('-')[1]; 
         var yyyy = sdate.split('-')[0];
         
          sdate = yyyy+'-'+mm+'-'+dd;
            let date = new Date(sdate); // 데이트 로 형변환 해주는 ?
            
            date.setDate(date.getDate() +7);
          
          var dd = date.getDate();
         var mm = date.getMonth()+1; 
         var yyyy = date.getFullYear();
         
          if(dd<10){
                 dd='0'+dd
             } 
             if(mm<10){
                 mm='0'+mm
             } 
             
         enddate = yyyy+'-'+mm+'-'+dd;
          document.getElementById("gEnddate").setAttribute("min", enddate);
          
      });
   </script>

<%@ include file="../common/footer.jsp"%>
