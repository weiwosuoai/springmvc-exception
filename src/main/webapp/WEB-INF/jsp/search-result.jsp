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
    <link href="<%=contextPath%>/css/search-result.css" rel="stylesheet">
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
<input id="q" type="hidden" value="${q}">
<!-- main container -->
<div class="container">
    <div class="row">
        <!-- 左边栏 -->
        <div class="col-md-9">
            <!-- 问题列表 -->
            <div class="page-header">
                <h1>搜索结果
                    <small style="padding-left: 10px;">(为您找到 ${pageInfo.total} 个结果)</small>
                </h1>
            </div>

            <!-- List group -->
            <ul class="list-group">

                <c:forEach var="question" items="${pageInfo.list}" varStatus="status">
                    <li class="list-group-item question-list-item">
                        <%--<span class="label label-primary margin-right5px">机器翻译</span>--%>
                        <a href="/question/${question.id}/detail">${question.titleCh}</a>

                            <!-- 摘要 -->
                            <div class="summary">${question.descriptionCh}</div>

                            <!-- 标签 -->
                            <%--<c:if test="${question.qtmaps != null}">--%>
                                <%--<div style="margin-top: 10px; margin-bottom: 6px;">--%>
                                    <%--<c:forEach var="qtmap" items="${question.qtmaps}" varStatus="status">--%>
                                        <%--<span class="label label-info" style="margin-right: 4px;">${qtmap.tag.name}</span>--%>
                                    <%--</c:forEach>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                    </li>
                </c:forEach>


            </ul>
            <!-- 分页 -->
            <ul id="pagination" class="pagination"></ul>
        </div>
        <!-- 右边栏 -->
        <div class="col-md-3">
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
<%@ include file="/common/nav-top-js.jsp" %>
<%@ include file="/common/top-progress.jsp" %>
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
            location.href = "<%=contextPath%>/index/search?pageNum=" + pageNum + "&q=" + $('#q').val();;
        }
    });

</script>
</body>
</html>