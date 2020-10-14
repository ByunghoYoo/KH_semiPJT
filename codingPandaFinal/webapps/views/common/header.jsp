<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.so.member.model.vo.*"%>
    
<%
	Member m = (Member)session.getAttribute("member");
%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" class="no-js" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="ko"> <!--<![endif]-->
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Coding Panda</title>
    <meta name="description" content="">
	<link rel="shortcut icon" href="/codingPanda/resources/images/iconPanda.ico">
    
    <script src="http://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/codingPanda/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/codingPanda/resources/css/jquery.smartmenus.bootstrap-4.css"/>
    <link rel="stylesheet" href="/codingPanda/resources/css/style.css">
    <link rel="stylesheet" href="/codingPanda/resources/css/animate.css"/>
	
</head>
<body class="home">
<header id="header">
<div class="container">
<div class="row">
<div id="logo-bar" class="col-sm-7 mx-auto">
    <div id="logo">
        <h1><a href="/codingPanda/index.jsp"><img src="/codingPanda/resources/images/logoCP.png" style="height: 140px; width: 350px;"/></a></h1>
    </div>
</div>
<div id="menu-bar" class="col-sm-12">
    <nav class="navbar navbar-expand-lg navbar-light  rounded mb-4">
        <!--  <div class="container">-->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        
             <ul class="nav navbar-nav">
                <li class="nav-item dropdown active"><a href="/codingPanda/index.jsp" class="nav-link"><span>HOME</span></a>
                </li>
                <% if ( m == null ) { %>
                <li class="nav-item dropdown"><a href="/codingPanda/views/member/loginform.jsp" class="nav-link"><span>모임 만들기</span></a>
                </li>
                <li class="nav-item dropdown"><a href="/codingPanda/views/member/loginform.jsp" class="nav-link"><span>로그인</span></a>
                </li>
                <li class="nav-item dropdown"><a href="/codingPanda/views/member/signup.jsp" class="nav-link"><span>회원가입</span></a>
                </li>
                <% } else { %>
                <li class="nav-item dropdown"><a href="/codingPanda/views/group/groupInsert.jsp" class="nav-link"><span>모임 만들기</span></a>
                </li>
                <li class="nav-item dropdown"><a href="/codingPanda/mlogout.me" class="nav-link"><span>로그아웃</span></a>
                </li>
                <li class="nav-item dropdown"><a href="/codingPanda/myPage.me" class="nav-link"><span>마이페이지</span></a>
                </li>
                <% } %>
            </ul>
        </div>
        
    </nav>
</div>

<!--/#menu-bar -->
</div>
</div>
</header>
  