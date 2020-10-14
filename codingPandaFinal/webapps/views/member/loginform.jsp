<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>
	<div class="container">
    <section class="wrapper">
        <div class="container">
            <div class="row sub_content">
                <div class="col-md-4 mx-auto">    
                    <div class="dividerHeading">
                        <h4><span>Login</span></h4>
                    </div>
                    <form id="loginform" method="post" name="loginform" action="<%= request.getContextPath() %>/mLogin.me">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="fa fa-user"></i></div>
                                </div>
                                <input type="text" class="form-control" placeholder="UserId" name="mId">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="fa fa-lock"></i></div>
                                </div>
                                <input type="password" class="form-control" placeholder="Password" name="mPwd">
                            </div>
                        </div>
                        <div style="height : 20px;"></div>
                        <div class="form-group text-right">
                            <button type="submit" class="btn btn-default btn-lg button">로그인</button>
                        </div>
                    </form>
                </div>    
            </div>
        </div>
    </section>
</div>
	<%@ include file="../common/footer.jsp" %>