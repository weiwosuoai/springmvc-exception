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
    <link href="<%=contextPath%>/css/index.css" rel="stylesheet">
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
        <div class="col-md-9">
            <!-- 问题列表 -->
            <div class="page-header">
                <h1>问题列表
                    <small style="padding-left: 10px;">(${pageInfo.total})</small>
                </h1>
            </div>

            <!-- List group -->
            <ul class="list-group">

                <c:forEach var="question" items="${pageInfo.list}" varStatus="status">
                    <li class="list-group-item question-list-item">
                        <%--<span class="label label-primary margin-right5px">机器翻译</span>--%>
                        <a href="<%=contextPath%>/questions/${question.id}/detail" class="question-title">${question.titleCh}</a>

                            <!-- 标签 -->
                            <c:if test="${question.qtmaps != null}">
                                <div style="margin-top: 10px; margin-bottom: 6px;">
                                    <c:forEach var="qtmap" items="${question.qtmaps}" varStatus="status">
                                        <a href="<%=contextPath%>/questions/tagged/${qtmap.tag.id}" class="label label-info m-label-info" style="margin-right: 4px;">${qtmap.tag.name}</a>
                                    </c:forEach>
                                </div>
                            </c:if>
                    </li>
                </c:forEach>

                <%--<li class="list-group-item question-list-item">--%>
                <%--<span class="label label-primary margin-right5px">机器翻译</span>--%>
                <%--<a href="#">Cras justo odio</a>--%>
                <%--</li>--%>
                <%--<li class="list-group-item question-list-item">--%>
                <%--<span class="label label-primary margin-right5px">人工校译</span>--%>
                <%--<a href="#">Cras justo odio</a>--%>
                <%--</li>--%>
                <%--<li class="list-group-item question-list-item">--%>
                <%--<span class="label label-primary margin-right5px">译</span>--%>
                <%--<a href="#">Cras justo odio</a>--%>
                <%--</li>--%>
                <%--<li class="list-group-item question-list-item">--%>
                <%--<span class="label label-primary margin-right5px">译</span>--%>
                <%--<a href="#">Cras justo odio</a>--%>
                <%--</li>--%>

            </ul>
            <!-- 分页 -->
            <ul id="pagination" class="pagination"></ul>

        </div>
        <!-- 右边栏 -->
        <div class="col-md-3">
            <!-- 我要维护 -->
            <%--<div>--%>
            <%--<form>--%>
            <%--<button type="submit" class="btn btn-success btn-block btn-to-submit-question">--%>
            <%--<span class="glyphicon glyphicon-send"></span>--%>
            <%--我要校译--%>
            <%--</button>--%>
            <%--</form>--%>
            <%--</div>--%>

            <!-- 热门标签 -->
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <h3 class="panel-title">热门标签</h3>
                    <span class="pull-right"><a class="btn btn-xs" href="#">更多»</a></span>
                </div>

                <!-- List group -->
                <ul class="list-group" id="hot-tags-list">

                    <%--<li class="list-group-item list-group-item-no-border">--%>
                        <%--<a class="label label-info" style="margin-right: 4px;">android</a>--%>
                        <%--<span class="badge">4232</span>--%>
                    <%--</li>--%>
                    <%--<li class="list-group-item">--%>
                        <%--<a href="#">--%>
                            <%--<span class="label label-info" style="margin-right: 4px;">android</span>--%>
                        <%--</a>--%>
                        <%--<span class="badge">4232</span>--%>
                    <%--</li>--%>
                </ul>
            </div>

            <!-- 热门问题 -->
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <h3 class="panel-title">热门问题</h3>
                    <%--<span class="pull-right"><a class="btn btn-xs" href="#">更多»</a></span>--%>
                </div>

                <!-- List group -->
                <ul class="media-list" style="padding-left: 15px; padding-top: 9px; padding-right: 15px;">
                    <li class="media" id="hot-questions-list">
                        <%--<div class="media-left" style="padding-right: 0;">--%>
                            <%--<span class="label label-success">8</span>--%>
                        <%--</div>--%>
                        <%--<div class="media-body" style="padding-left: 10px; padding-right: 0;">--%>
                            <%--<a href="#">如何在两个Android应用程序之间发送信息</a>--%>
                        <%--</div>--%>
                    </li>
                </ul>
            </div>
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
<script src="<%=contextPath%>/js/layui.js"></script>
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
            location.href = "/index?pageNum=" + pageNum;
        }
    });

    // ajax 异步获取热门标签信息
    $.ajax({
        type: "GET",
        async: true,
        url: "<%=contextPath%>/index/tags/hot",
        datatype: "json",
        success: function (data) {
            var html = '';
            $.each(data, function (i, item) {
                html += "<li class='list-group-item list-group-item-no-border'><a href='/questions/tagged/"+item.tag.id+"' class='label label-info m-label-info'>" + item.tag.name
                        + "</a><span class='badge'>" + item.questionNum + "</span></li>";
            });
            $('#hot-tags-list').html(html);
        }
    });

    // ajax 异步获取热门问题
    $.ajax({
        type: "GET",
        async: true,
        url: "<%=contextPath%>/index/questions/hot",
        datatype: "json",
        success: function (data) {
            var html = '';
            $.each(data, function (i, item) {
                html += "<li class='media'><div class='media-left' style='padding-right: 0;'><span class='label ";
                if (item.hasAccepteAnswer == 1) {
                    html += "label-success";
                } else {
                    html += "label-default";
                }

                html += "'>"+item.answerNum+"</span></div><div class='media-body' style='padding-left: 10px; padding-right: 0;'><a href='/questions/" + item.id + "/detail'>" + item.titleCh + "</a></div>";
//                html += "'>" + item.answerNum + "</span><div class='col-sm-10' style='padding-left: 10px; padding-right: 0;'><a href='/questions/" + item.id + "/detail'>" + item.titleCh + "</a></div></div></li>";
            });
            $('#hot-questions-list').html(html);
        }
    });

</script>


</body>
</html>