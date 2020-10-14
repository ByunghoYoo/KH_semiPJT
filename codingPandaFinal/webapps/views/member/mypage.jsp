<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.so.group.model.vo.*, java.util.*" %>
    
<%
	ArrayList<Group> glist = (ArrayList<Group>)request.getAttribute("glist");
%>

<%@ include file="../common/header.jsp" %>

<section class="wrapper">
      <section class="content right_sidebar">
         <div class="container">
         	<div class="row">
         		<div class="col-md-8 mx-auto">
     			<div class="dividerHeading text-center">
            		<h4><span>내 프로필</span></h4>
        		</div>
        		</div>
         	</div>
            <div class="row">
               <div class="col-md-2"></div>
               <div class="col-md-4">
                
                <div class="widget widget_Profile"> 
                    <ul>
                        <li>
                     		<% if(m.getmPicture() != null) { %>
	                        <img src="<%= request.getContextPath() %>/resources/memberUploadFiles/<%= m.getmPicture() %>"
	                                width="200px" height="150px">
	                        <% } else { %>
	                        <img src="<%= request.getContextPath() %>/resources/images/panda.png" width="200px" height="150px">
	                        <% } %>
                        </li>
                        <hr>
                       <li>
                           <a href="#"> Birth </a>
                           <% if(m.getmBirth() != null) { %>
                           <p><%= m.getmBirth() %></p>
                           <% } else { %>
                           <p></p>
                           <% } %>
                       </li>
                       <hr>
                       <li>
                           <a href="#"> Address </a>
                           <% if(m.getmAddress() != null) { %>
                           <p><%= m.getmAddress() %></p>
                           <% } else { %>
                           <p></p>
                           <% } %>
                       </li>
                       <hr>
                       <li>
                           <a href="#"> E-mail </a>
                           <% if(m.getmEmail() != null) { %>
                           <p><%= m.getmEmail() %></p>
                           <% } else { %>
                           <p></p>
                           <% } %>
                       </li>
                       <hr>
                       <li>
                           <a href="#"> interesting Language </a>
                           <% if(m.getmLang() != null) { %>
                           <p><%= m.getmLang() %></p>
                           <% } else { %>
                           <p></p>
                           <% } %>
                       </li>
                       <hr>
                       <li>
                           <a href="#"> Self Introduction </a>
                           <% if(m.getmContent() != null) { %>
                           <p><%= m.getmContent() %></p>
                           <% } else { %>
                           <p>자기소개가 없습니다. 자기소개를 작성해보세요~! </p>
                           <% } %>
                       </li>
                       <br />
                       <br />
                       <li>
                       		<button type="button" class="btn btn-default btn-sm" style="width:120px;" 
                       		onclick="location.href='/codingPanda/views/member/memberrevise.jsp'">회원 정보 수정</button>
                       </li>
                    </ul>
                 </div>

                </div>
            
               <div class="col-md-4">
               
                  <div class="tab" role="tabpanel">
                     <ul class="nav nav-tabs">
                        <li><a href="#" data-toggle="tab" class="active">My Group</a></li>
                     </ul>

                     <div class="tab-content clearfix">
                        <div class="tab-pane fade active show" id="Popular">
                           <ul class="recent_tab_list">
                           
                           	 <!-- 가입한 모임을 나타내며 클릭시 해당 모임으로 이동 -->
                             <% for(Group g : glist) { %>
                              <li><a href="<%= request.getContextPath() %>/selectOne.gr?gno=<%= g.getGno() %>">
                              <%= g.getgTitle() %></a> <i>등록일 : <%= g.getgRegDate() %></i></li>
                             <% } %>
                             
                             
                           </ul>
                        </div>
                        <div class="tab-pane fade" id="Recent"></div>
                        <div class="tab-pane fade" id="Comment"></div>
                     </div>
                  </div>

                  <div class="widget widget_tag"></div>
               </div>

            </div>
         </div>
      </section>
   </section>


<%@ include file="../common/footer.jsp" %>