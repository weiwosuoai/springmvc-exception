<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Exception问答社区</title>

    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/bootstrap-custom.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/nprogress.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/base.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/tag.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!-- 导航条 -->
<%@ include file="/common/nav-top.jsp" %>

<!-- main container -->
<div class="container">
    <div class="row">

        <!-- 左边栏 -->
        <div class="col-md-12">

            <!-- 标签列表 -->
            <div class="page-header">
                <h1>标签列表
                    <small style="padding-left: 10px;">(${pageInfo.total})</small>
                </h1>
            </div>
           <%--标签展览--%>
            <div class="row" style="padding-top: 20px;">

                <c:forEach var="questionTagMap" items="${pageInfo.list}" varStatus="status">
                    <%--${(status.index)%4}--%>
                    <%--<c:if test="${(status.index)%4 == 0}">--%>
                        <%--<div class="row tag-row">--%>
                    <%--</c:if>--%>

                    <div class="col-md-3 tag-col-md-3">
                        <a href="/questions/tagged/${questionTagMap.tag.id}" class="label label-info m-label-info">${questionTagMap.tag.name}</a>&nbsp;&nbsp;<span class="x">×</span>&nbsp;
                        <span class="badge">${questionTagMap.questionNum}</span>
                        <div class="summary">
                            ${questionTagMap.tag.descriptionCh}
                        </div>
                    </div>



                    <%--<c:if test="${(status.index)%4 == 0}">--%>
                        <%--</div>--%>
                    <%--</c:if>--%>
                    <%--<div class="row tag-row">--%>
                    <%--<div class="col-md-3">--%>

                    <%--<a href="#" class="label label-info m-label-info">android</a>&nbsp;&nbsp;<span class="x">×</span>&nbsp;--%>
                    <%--<span class="badge">4232</span>--%>
                    <%--<div class="summary">--%>
                    <%--Android是Google的移动操作系统，用于编程或开发数字设备（智能手机，平板电脑，汽车，电视，磨损，玻璃）--%>
                    <%--</div>--%>


                    <%--</div>--%>
                </c:forEach>
            </div>
            <!-- 分页 -->
            <ul id="pagination" class="pagination"></ul>
        </div>
    </div>
</div>

<!-- footer -->
<%@include file="/common/footer.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="js/jquery.twbsPagination.min.js"></script> -->
<script src="<%=contextPath%>/js/jquery.simplePagination.js"></script>
<%@ include file="/common/top-progress.jsp" %>
<%@ include file="/common/nav-top-js.jsp" %>
<script type="text/javascript">
    // 分页
    $('#pagination').pagination({
        items: ${pageInfo.pages},
        itemOnPage: ${pageInfo.pageSize},
        currentPage: ${pageInfo.pageNum},
        cssStyle: '',
        prevText: '<span aria-hidden="true">&laquo;</span>',
        nextText: '<span aria-hidden="true">&raquo;</span>',
        onInit: function () {
            // fire first page loading
        },
        onPageClick: function (pageNum, evt) {
            // some code
            location.href = "/tags?pageNum=" + pageNum;
        }
    });


</script>
</body>
</html>