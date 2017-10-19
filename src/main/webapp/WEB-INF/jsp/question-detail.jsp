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
    <title>${questionVo.titleCh}</title>

    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/bootstrap-custom.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/nprogress.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/base.css" rel="stylesheet">
    <link href="<%=contextPath%>/plugins/google-code-prettify/prettify.css"
          rel="stylesheet">
    <link href="<%=contextPath%>/css/question-detail.css" rel="stylesheet">

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
<div class="container margin-bottom20px">

    <!-- 标题 -->
    <div class="page-header">
        <h1>${questionVo.titleCh}</h1>
        <c:if test="${questionVo.status == 0}">
            <span class="label label-primary margin-right5px">机器翻译</span>
        </c:if>
        <c:if test="${questionVo.status == 1}">
            <span class="label label-success margin-right5px">人工校译</span>
        </c:if>
        <a href="${questionVo.originalLink}" target="_blank">[原文链接]</a>
            &nbsp;&nbsp;&nbsp;${questionVo.voteUp} <span class="color-grey">次点赞</span>
        &nbsp;&nbsp;&nbsp;<span class="color-grey">浏览</span> ${questionVo.viewNum} <span class="color-grey">次</span>
    </div>

    <div class="row">
        <!-- 左边栏 -->
        <div class="col-md-9">

            <!-- 问题描述 -->
            ${questionVo.questionDesc.descriptionChHtml}

            <!-- 标签 -->
            <c:if test="${questionVo.qtmaps != null}">
                <div style="margin-top: 25px;">
                    <c:forEach var="qtmap" items="${questionVo.qtmaps}" varStatus="status">
                        <%--<span class="label label-info" style="margin-right: 4px;">${qtmap.tag.name}</span>--%>
                        <a href="/questions/tagged/${qtmap.tag.id}" class="label label-info m-label-info" style="margin-right: 4px;">${qtmap.tag.name}</a>
                    </c:forEach>
                </div>
            </c:if>

            <%--操作（编辑）--%>
            <div class="margin-top15px">
                <a href="/questions/${questionVo.id}/update" class="color-grey desc-operate">编辑</a>
            </div>

            <!-- 解决方案 -->
            <div class="page-header description-page-header" style="margin-top: 40px;">
                <h2>stackoverflow 回答
                    <small>(${questionVo.answers.size()})</small>
                </h2>
            </div>
            <!-- 问题解决方案 -->
            <c:forEach var="answer" items="${questionVo.answers}" varStatus="status">
                <c:choose>
                    <c:when test="${answer.isAccepted == 1}">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title">回答&nbsp;${status.index+1}</h3>
                                <div class="pull-right">
                                    <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;有用&nbsp;${answer.voteCount}
                                    &nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span>&nbsp;已采纳
                                    &nbsp;&nbsp;
                                    <a href="${questionVo.originalLink}/${answer.originalId}#${answer.originalId}" target="_blank">[原回答]</a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <c:choose>
                                    <c:when test="${answer.answerDesc.descriptionCh eq '?' || answer.answerDesc.descriptionCh eq ''}">
                                        ${answer.answerDesc.answerHtml}
                                    </c:when>
                                    <c:otherwise>
                                        ${answer.answerDesc.answerChHtml}
                                    </c:otherwise>
                                </c:choose>

                                <%--操作（编辑）--%>
                                <div class="margin-top15px">
                                    <a href="/answers/${answer.answerDesc.id}/update" class="color-grey desc-operate">编辑</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">回答&nbsp;${status.index+1}</h3>
                                <div class="pull-right">
                                    <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;有用&nbsp;${description.voteCount}
                                    &nbsp;&nbsp;
                                    <a href="${questionVo.originalLink}/${answer.originalId}#${answer.originalId}" target="_blank">[原回答]</a>
                                </div>

                            </div>
                            <div class="panel-body">
                                <c:choose>
                                    <c:when test="${answer.answerDesc.descriptionCh eq '?' || answer.answerDesc.descriptionCh eq ''}">
                                        ${answer.answerDesc.answerHtml}
                                    </c:when>
                                    <c:otherwise>
                                        ${answer.answerDesc.answerChHtml}
                                    </c:otherwise>
                                </c:choose>

                                <%--操作（编辑）--%>
                                <div class="margin-top15px">
                                    <a href="/answers/${answer.answerDesc.id}/update" class="color-grey desc-operate">编辑</a>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </c:forEach>

            <%--<div class="panel panel-success">--%>
            <%--<div class="panel-heading">--%>
            <%--<h3 class="panel-title">方案2</h3>--%>
            <%--</div>--%>
            <%--<div class="panel-body">--%>
            <%--Panel content--%>
            <%--</div>--%>
            <%--</div>--%>
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
<script src="<%=contextPath%>/plugins/google-code-prettify/prettify.js"></script>
<%@ include file="/common/top-progress.jsp" %>
<%@ include file="/common/nav-top-js.jsp" %>
<script type="text/javascript">
    $(document).ready(function(){
        // 代码高亮
        $("pre").addClass("prettyprint");
        prettyPrint();

        // ajax 异步为文章被阅读数+1
        $.ajax({
            type: "GET",
            async: true,
            url: "<%=contextPath%>/questions/"+${questionVo.id}+"/view/num/increment",
            success: function (data) {
            }
        });
    });
</script>

</body>
</html>