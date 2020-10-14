<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.so.board.model.vo.*, com.kh.so.group.model.vo.*, java.util.* " %>

<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	Group g = (Group)request.getAttribute("g");
	
%>

<%@ include file="../common/header.jsp" %>

<div class="container">

<div class="row sub_content">

    <div class="col-lg-8 mx-auto">
        <div class="dividerHeading">
            <h4><span>우리 모임의 게시글</span></h4>
        </div>
    </div>

    <div class="col-lg-8 table-responsive mx-auto">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>번호</th>
                <th>사진</th>
                <th>작성자</th>
                <th>제목</th>
                <th>작성일</th>
            </tr>
            </thead>
            <tbody>
            <% for(Board b : list) { %>
            <tr>
            	<input type="hidden" value="<%= b.getBno() %>"/>
                <td><%= b.getBno() %></td>
                <td>
                <% if(b.getmPicture() != null) { %>
                <img class="rounded-circle img-responsive", width="30px;" height="30px;"
                src="<%= request.getContextPath() %>/resources/memberUploadFiles/<%= b.getmPicture() %>">
                <% } else { %>
                <span><img class="rounded-circle img-responsive" src="/codingPanda/resources/images/panda.png"
                	   width="30px;" height="30px;"></span>
                <% } %>
                </td>
                <td><%= b.getbWriter() %></td>
                <td><%= b.getbTitle() %></td>
                <td><%= b.getbDate() %></td>
            </tr>
			<% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col-lg-10 text-right">
        <!-- Standard button -->
        <button class="btn btn-default btn-md" type="button" onclick="location.href='<%= request.getContextPath() %>/boardInsertView.bo?gno=<%= g.getGno() %>'">작성하기</button>
    </div>
</div>
</div>

<div style="height: 50px;"></div>

		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<button class="btn btn-default btn-sm" onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>&currentPage=1'"><<</button>
			<% if(currentPage <= 1){  %>
			<button class="btn btn-default btn-sm" disabled><</button>
			<%  }else{ %>
			<button class="btn btn-default btn-sm" onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>&currentPage=<%=currentPage - 1 %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button class="btn btn-default btn-sm" disabled><%= p %></button>
			<%      }else{ %>
				<button class="btn btn-default btn-sm" onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>&currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button class="btn btn-default btn-sm" disabled>></button>
			<%  }else{ %>
			<button class="btn btn-default btn-sm" onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>&currentPage=<%=currentPage + 1 %>'">></button>
			<%  } %>
			<button class="btn btn-default btn-sm" onclick="location.href='<%= request.getContextPath() %>/selectList.bo?gno=<%= g.getGno() %>&currentPage=<%= maxPage %>'">>></button>
			
		</div>
		
		<div style="height: 30px;"></div>

<script>
	$(function(){
		$("td") .click(function(){
			var bno = $(this).parent().find("input").val();
			location.href="<%=request.getContextPath()%>/selectOne.bo?bno=" + bno + "&gno=" + "<%=g.getGno()%>";
		});
	});
</script>

<%@ include file="../common/footer.jsp" %>
