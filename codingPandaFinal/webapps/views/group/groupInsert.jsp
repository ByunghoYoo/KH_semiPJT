<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
									<span>그룹  생성하기</span>
								</h4>
							</div>
							<p>CodingPanda에서 여러분의 코딩메이트를 만들어보세요!</p>

							<!-- 		<div class="alert alert-success hidden alert-dismissable" id="contactSuccess">
						  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						  <strong>Success!</strong> Your message has been sent to us.
						</div>

						<div class="alert alert-error hidden" id="contactError">
						  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						  <strong>Error!</strong> There was an error sending your message.
						</div> -->
						<form id="contactForm" action="<%= request.getContextPath() %>/gInsert.do" enctype="multipart/form-data" method="post">
								
						<div class="mx-auto">
									<div id="titleImgArea">
											<img id="titleImg"  src="http://placehold.it/500x300" alt="스터디 대표 이미지" style = "height : 300px; width : 500px; padding-bottom : 10px;"><br>
										</div>
							
									<button class="btn btn-warning btn-lg" type="button" style="padding-right:50px; padding-left:50px" id="insertImg" >이미지
									추가하기</button>
								<button class="btn btn-success btn-lg" type="button" style="padding-right:50px; padding-left:50px" id="changeImg">이미지
									변경하기</button>
						</div>
						<div style="height:30px;"></div>
								<div class="row form-group">
									<div class="col-lg-6 ">
										<input type="text" id="gname" name="gTitle" class="form-control"
											maxlength="100" data-msg-required="스터디명을 입력해주세요." value=""
											placeholder="스터디명" required>
									</div>
									<div class="col-lg-6 ">
										<input type="number" id="gmemCt" name="gMax"
											placeholder="모임 정원" max="10" min="1" value="5" step="1"
											class="form-control" required>
									</div>
								</div>
								<div class=row>
									<div class="col-lg-6 ">
										<select id="language" name="gLang" class="form-control"
											maxlength="100" data-msg-required="언어를 선택해주세요." value="">
											<option value="언어선택" hidden>언어선택</option>
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
										<input type="text" id="result" name="language" readonly="readonly"
											style="background-color: transparent; border: 0 solid black; text-align: left; color: royalblue; position: absolute; margin: 0, 50, 0, 0; margin-top: 10px;">
									</div>
								</div>
								<div style="height:20px;"></div>
								<div class="row form-group">
									<div class="col-md-12">
										<textarea id="message" class="form-control" name="gContent"
											rows="10" cols="50" data-msg-required="내용을 입력하세요."
											maxlength="5000" placeholder="모임을 소개해주세요." required></textarea>
									</div>
								</div>
								<div class=row>
									<div class="col-lg-6 col-sm-6 ">
										<label for="date1">모임 시작일시</label> <input
											type="date" id=startdate name="gStartDate" min=""
											class="form-control" maxlength="100" required>
									</div>
									<div id="long" class="col-lg-6 col-sm-6"
										style="display: none;">
										<label for="date2">모임 종료일시</label> <input
											type="date" id = "edate" name="gEnddate" class="form-control" min=""
											maxlength="100" style="text-align: left">
									</div>
									<div class="row mx-auto">
										<div id="check1" class="col-lg-6 col-sm-6 ">
											<label for="longTck">장기 모임일 경우 체크</label> <input type="radio"
												id="longTck" name="Tck">
										</div>
										<div id="check2" class="col-lg-6 col-sm-6 ">
											<label for="shortTck">단기 모임일 경우 체크</label> <input
												type="radio" id="shortTck" name="Tck">
										</div>
									</div>
								</div>
								<br />
								<div class="row">
								<div class="col-md-6">
									<label for="area">모임 장소</label>
										<input type="search" name="gAddress" id="location"
											class="form-control" maxlength="100" required>
								</div>
								<div class="col-md-6">
									<label for="money">모임 회비</label>
									<input type="number" name="gMoney" id="money" class="form-control" max="50000" min="0" value="0" step="1000" required>
								</div>
								</div>
								<div style="height:70px;"></div>
								<div class="row">
									<div class="col-md-12">
										<input type="submit" data-loading-text="Loading..."
											class="btn btn-success btn-lg" value="모임 생성하기" style="padding-left:50px; padding-right:50px; width:100%; height:50px; font-size:20px;">
									</div>
								</div>
								<!--  이미지 파일 넣고 불러오기  -->
									<div class="fileArea" id="fileArea">
										<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this, 1);" />
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
			
			
		</script>

	<script>
	
		function selectLanguage() {
			var value = $('option:selected').val();
			$('#result').val(value + " 선택!");
		}
		$('#language').change(selectLanguage);
	
		
		
		
		$('#longTck').click(function() {
			var ischecked = $(this).prop('checked');
			if (ischecked) {
				/* 장기날짜선택 required 추가 */
				$('#edate').prop("required", true);
				
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
				
				/* 장기날짜선택 required 제거 */
				$('#edate').removeAttr("required");
			}
		});
		
	</script>

   <script> //시작날짜 오늘이전으로 클릭x 설정
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
      document.getElementById("startdate").setAttribute("min", today);
   </script>
   
 
   <script> // 시작일 7일 이후 종료날짜 
         $('#startdate').on('change',function(){
         var sdate = $('#startdate').val();
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
          document.getElementById("edate").setAttribute("min", enddate);
          
      });
   </script>

	<%@ include file="../common/footer.jsp"%>
	