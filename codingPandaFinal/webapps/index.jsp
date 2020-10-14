<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.so.group.model.vo.*, java.util.*"%>
<%
	ArrayList<Group> list=(ArrayList<Group>)request.getAttribute("list");
%>

<%@ include file="views/common/header.jsp" %>

<section class="box" style="height:auto; width: 100%;">
    <div class="container text-center">
        <div class="home-search">
          <h1 class="heading-primary">
              <br>
            찾고싶은 모임을 검색해보세요!
            <br><br>
          </h1>
        </div>
    </div>
    <div class="search">

<!--       <form action="/codingPanda/searchGroup.gr" method ="get"> -->
        <!-- Form Row 1 -->
        <div class="form-row">
            <div class="col-md-3 mb-4">
            </div>
          <div class="col-md-3 mb-4">
            <label class="sr-only">Group</label>
            <input type="text" id="groupTitle" name="groupTitle" class="form-control" placeholder="스터디 이름" style="font-size: 15px;">
          </div>
    
          <div class="col-md-3 mb-4">
            <label class="sr-only">Level</label>
            <input type="text" id = "language" name="language" class="form-control" placeholder="프로그래밍 언어" style="font-size: 15px;">
          </div>

             <div class="col-md-3 mb-4">
            </div>
          
        </div>
        <!-- Form Row 2 -->
        <div class="form-row">
            <div class="col-md-3 mb-4">
            </div>

            <div class="col-md-3 mb-4">
                <label class="sr-only">Address</label>
                <input type="text" id="address" name="address" class="form-control" placeholder="지역" style="font-size: 15px;">
              </div>
    
              <div class="col-md-3 mb-4">
                <label class="sr-only">Start Date</label>
                <input type="date" id="start_date" name="start_date" class="form-control">
              </div>
    
          	<div class="col-md-3 mb-4">
            </div>
        	</div>

        <!-- Form Row 3 -->
        <div class="form-row">
            <div class="col-md-3 mb-4"></div>

            <div class="col-md-6 mb-4"><button class="btn btn-secondary btn-block md-4" type="button" onclick="search();">검색하기</button></div>

            <div class="col-md-3 mb-4"></div>
        	</div>
			</div>
			
			
				
			
			<script>
			var currentPage = 1;
			
			function search(){
				
				$('.group-form').not(':eq(0)').remove();
				currentPage = 1;
				$.ajax({
		    		url:'/codingPanda/searchGroup.gr',
		    		type:'get',
		    		data : {
		    			async : false,
		    			group : $('#groupTitle').val(),
		    			language : $('#language').val(),
		    			address : $('#address').val(),
		    			start_date : $('#start_date').val(),
		    			day : $('#day').val(),
		    			currentPage : currentPage++,
		    			limit : 4
		    		},
		    		success:function(list){
		    			
		    			console.log(list);
		    			for(var i in list){
		    				var $groupForm = $('.group-form').eq(0).clone();
		    				console.log($groupForm);
		    				// console.log(list);
			    			// alert(list[i].gno+','+list[i].gTitle+','+list[i].gAddress+','+list[i].gContent+','+list[i].gStartDate);
			    			
			    			$groupForm.find('.gno').val(list[i].gno);
							$groupForm.find('.gAddress').text(list[i].gAddress);
							$groupForm.find('.gTitle').text(list[i].gTitle);
							//$groupForm.find('.gContent').text(list[i].gContent);
							//$groupForm.find('.gStartDate').text(list[i].gStartDate);
							if(list[i].gEndDate==null){
		                           $groupForm.find('.gEndDate').css('border', '3px solid blue');
		                        }else{
		                           $groupForm.find('.gEndDate').css('border', '3px solid red');
		                        }
							//$groupForm.find('.gLang').text(list[i].gLang);
							if(list[i].gPicture != null) {
							$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/groupUploadFiles/" + list[i].gPicture);
							} else {
							$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/images/panda.png");	
							}
							$groupForm.css('display', 'inline-block');
							
							
							
							$('.group-form').eq(0).before($groupForm);
							
		    			}
		    			
							$('.group-form').last().remove();
		    		}, error:function(){
		    			// alert("그룹목록조회실패");
		    		}
		    	});
			}
			$(function(){
				$(window).scroll(function(){ //스크롤 이벤트
					
		           var scrollHeight=$(window).scrollTop()+$(window).height(); 
		           var documentHeight=$(document).height(); //$(document).height()는 스크롤의 전체 길이를 뜻합니다.
		           // console.log(scrollHeight + ", " + documentHeight);
		           if(scrollHeight >= documentHeight - 1)
		           {
		        	   $.ajax({
		        		   async : false,
				    		url:'/codingPanda/searchGroup.gr',
				    		type:'get',
				    		data : {
				    			group : $('#groupTitle').val(),
				    			language : $('#language').val(),
				    			address : $('#address').val(),
				    			start_date : $('#start_date').val(),
				    			day : $('#day').val(),
				    			currentPage : currentPage++,
				    			limit : 4
				    		},
				    		success:function(list){
				    			
				    			console.log(list);
				    			if(list == null  || list.length == 0) {
				    				// alert("더이상 가져 올 모임이 없습니다.");
				    			} else {				    				
					    			for(var i in list){
					    				var $groupForm = $('.group-form').eq(0).clone();
					    				console.log($groupForm);
					    				// console.log(list);
						    			// alert(list[i].gno+','+list[i].gTitle+','+list[i].gAddress+','+list[i].gContent+','+list[i].gStartDate);
						    			
						    			$groupForm.find('.gno').val(list[i].gno);
										$groupForm.find('.gAddress').text(list[i].gAddress);
										$groupForm.find('.gTitle').text(list[i].gTitle);
										//$groupForm.find('.gContent').text(list[i].gContent);
										//$groupForm.find('.gStartDate').text(list[i].gStartDate);
										if(list[i].gEndDate==null){
					                           $groupForm.find('.gEndDate').css('border', '3px solid blue');
					                        }else{
					                           $groupForm.find('.gEndDate').css('border', '3px solid red');
					                        }
										//$groupForm.find('.gLang').text(list[i].gLang);
										if(list[i].gPicture != null) {
										$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/groupUploadFiles/" + list[i].gPicture);
										} else {
										$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/images/panda.png");	
										}
										$groupForm.css('display', 'inline-block');
										$groupForm.find('.gDetail').text(list[i].gno);
										
										
										
										$('.group-form').eq(0).before($groupForm);
					    			}
				    			}
				    			
									// $('.group-form').last().remove();
				    		}, error:function(){
				    			// alert("그룹목록조회실패");
				    		}
				    	});
		           }
			     });				
			});
			
			
			
			</script>
	
</section>


<!--모임카드-->

<section class="box" style="background: whitesmoke; height: auto;">
    <!--좌측상단글씨-->
    
    <div class="container text-left">
        <div class="home-search" style="padding: 30px 0 0 0;">
            <div class="dividerHeading">
                <h4 style="background: whitesmoke;"><span style="background: whitesmoke;">개설된 모임들</span></h4>
            </div>
            <div></div>
        </div>
    </div>
    
    <div class="row" style="padding: 20px 10%;" id="groupcard">
    
    	<!-- 카드예시 -->
        <div class="group-form col-lg-3 col-md-4 col-sm-6 mb8" style="display:none; margin:10px 0;">       
	            <div class="card card-inverse card-info gEndDate" 
	            		style="overflow-x:hidden; overflow-y:hidden;"> <!-- 나중에 보더라인색을 if문으로 단기,정기 구분하여 색을받을예정 -->
	                <div class="image">
	                    <div class="gAddress" style="padding:5px;"></div>
	                	<img class="card-img-top" name="gpicture" src="" style="height:200px;">               
	                </div>
	                <div class="card-block">
	                    <figure class="profile">
	                    	<img src="" class="profile-avatar" alt="">
	                    </figure>
	                    <h4 id="card-title" class="gTitle mt-3" style="white-space:nowrap; overflow:hidden; font-size:24px; text-align:center;
	                                text-overflow:ellipsis;"></h4>
	                            <div class="meta card-text">
	                                <p style="font-size:13px; white-space:nowrap; overflow:hidden; 
	                                text-overflow:ellipsis;" class="gContent"></p>
	                            </div>
	                </div>
	                <div class="card-footer" style="width:100%">
	                	<input type="hidden" class="gno" name="gno" value=""/>
	                	<div class="left"><h5 class="gStartDate"></h5></div>
	                	<div class="right"><h5 class="gLang"></h5></div>
	                	<%if(m==null){ %><!--로그인전에는 로그인창가는버튼-->
                      	<button class="btn btn-info float-right btn-sm" name="selectgroup" onclick="loginplz(this);">상세보기</button>
                      	<%}else{ %><!-- 로그인후에는 상세보기이동 -->
                      	<button class="btn btn-info float-right btn-sm" name="selectgroup" onclick="detailView(this);">상세보기</button>
                      	<%} %>
	                </div>
	            </div>
	        
        </div>
        
        <script>
        function detailView(obj){
            var gno = $(obj).siblings('.gno').val();
                location.href="/codingPanda/selectOne.gr?gno="+gno;

        }
        
        function loginplz(obj){
              location.href="/codingPanda/views/member/loginform.jsp";
        }
        
	    $(function(){
	    	$.ajax({
	    		async : false,
	    		url:'/codingPanda/groupList.do',
	    		type:'get',
	    		success:function(list){
	    			
	    			console.log(list);
	    			for(var i in list){
	    				var $groupForm = $('.group-form').eq(0).clone();
	    				console.log($groupForm);
	    				// console.log(list);
		    			// alert(list[i].gno+','+list[i].gTitle+','+list[i].gAddress+','+list[i].gContent+','+list[i].gStartDate);
		    			
		    			$groupForm.find('.gno').val(list[i].gno);
						$groupForm.find('.gAddress').text(list[i].gAddress);
						$groupForm.find('.gTitle').text(list[i].gTitle);
						//$groupForm.find('.gContent').text(list[i].gContent);
						//$groupForm.find('.gStartDate').text(list[i].gStartDate);
						if(list[i].gEndDate==null){
	                           $groupForm.find('.gEndDate').css('border', '3px solid blue');
	                        }else{
	                           $groupForm.find('.gEndDate').css('border', '3px solid red');
	                        }
						//$groupForm.find('.gLang').text(list[i].gLang);
						$groupForm.find('.gPicture').text(list[i].gPicture);
						$groupForm.css('display', 'inline-block');
						if(list[i].gPicture != null) {
						$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/groupUploadFiles/" + list[i].gPicture);
						} else {
						$groupForm.find('.card-img-top').attr("src", "/codingPanda/resources/images/panda.png");	
						}
						$('.group-form').eq(0).before($groupForm);
						
						
	    			}
	    			currentPage = 2;
	    		}, error:function(){
	    			// alert("그룹목록조회실패");
	    		}
	    	});
	    });
	
   		</script>
			

	
    </div>
    
    
</section>



<%@ include file="views/common/footer.jsp" %>

