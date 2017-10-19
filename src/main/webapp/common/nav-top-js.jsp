<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--<%@ include file="/includes/modal-article-post.jsp"%>--%>
<%--<%@ include file="/includes/modal-article-post-js.jsp"%>--%>
<script type="text/javascript">
    <%--动态为 navbar 添加 active 属性--%>
	$(document).ready(function() {
		var navbarRef = $('#navbarRef').val();
		$("#navbar").find("li").removeClass();
		$("#" + navbarRef).addClass("active");
	});

</script>