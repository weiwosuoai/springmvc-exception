<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--顶部导航--%>
<nav class="navbar navbar-default">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/index">exception 问答</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav" id="navbar">
				<li id="questions"><a href="/index">问答 <span class="sr-only">(current)</span></a></li>
				<li id="tags"><a href="/tags">标签</a></li>
				<%--<li id="blogs"><a href="/blogs">博客</a></li>--%>
				<li id="users"><a href="/users/login">用户</a></li>
			</ul>
			<form class="navbar-form navbar-left" action="/index/search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="问题搜索" name="q" value="${q}">
                <span class="input-group-btn">
                  <button type="submit" class="btn btn-default btn-search">
					  <span class="glyphicon glyphicon-search"></span>
				  </button>
                </span>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<%--<li><a href="#">注册</a></li>--%>
				<c:choose>
					<c:when test="${sessionScope.userid == null}">
						<li><a href="/users/login">登录</a></li>
					</c:when>
					<c:otherwise>
						<%--<li><a href="/users/login">退出</a></li>--%>
					</c:otherwise>
				</c:choose>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
<%--服务端返回的 navbar 标识，以便动态为 navbar 添加 active 属性--%>
<input id="navbarRef" type="hidden" value="${navbarRef}">