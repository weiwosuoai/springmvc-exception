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
    <title>回答修改</title>

    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/bootstrap-custom.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/nprogress.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/base.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/login.css" rel="stylesheet">
    <link href="<%=contextPath%>/css/markdown.css" rel="stylesheet">

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

    <div class="row">
        <!-- 左边栏 -->
        <div class="col-md-8">
            <%--id--%>
            <input type="hidden" name="id" value="${answerDescVo.id}">

            <c:if test="${sessionScope.userid == null}">
                <div class="alert alert-warning" role="alert">您还没有登录哦！无法提交修改的内容！</div>
            </c:if>

            <form action="/answers/${answerDescVo.id}/update" method="post" id="form">
                <div class="form-group">
                    <label>回答描述</label>
                    <div id="wmd-button-bar" class="wmd-button-bar">
                    </div>
                    <textarea id="wmd-input" rows="22" class="wmd-input form-control" name="descriptionCh">${answerDescVo.descriptionCh}
                    </textarea>
                </div>

                <div class="form-group margin-top20px">
                    <div id="wmd-preview" class="wmd-panel wmd-preview m-wmd-preview">
                    </div>
                </div>
                <%--<div class="checkbox">--%>
                    <%--<label>--%>
                        <%--<input type="checkbox"> Check me out--%>
                    <%--</label>--%>
                <%--</div>--%>
                <button type="submit" class="btn btn-default margin-top10px margin-bottom20px">确认提交</button>
            </form>

        </div>
        <div class="col-md-4">

        </div>
    </div>
</div>

<!-- footer -->
<%@include file="/common/footer.jsp" %>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/plugins/markdown/Markdown.Converter.js"></script>
<script src="<%=contextPath%>/plugins/markdown/Markdown.Editor.js"></script>
<script src="<%=contextPath%>/plugins/markdown/Markdown.Sanitizer.js"></script>
<script src="<%=contextPath%>/js/jquery.form.js"></script>
<%@ include file="/common/top-progress.jsp" %>
<%@ include file="/common/nav-top-js.jsp" %>
<script src="<%=contextPath%>/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=contextPath%>/plugins/validate/messages_zh.min.js"></script>
<%--弹出框组件--%>
<script src="<%=contextPath%>/plugins/layer/layer.js"></script>
<script type="text/javascript">
    // 初始化markdown编辑器
    var converter = Markdown.getSanitizingConverter();
    var editor = new Markdown.Editor(converter);
    editor.run();

    $(document).ready(function(){
        // 表单验证
        $("#form").validate({
            rules:{
                'descriptionCh':{
                    required:true
                }
            },
            messages:{
                'descriptionCh':{
                    required:"<span style='color: red;'>回答描述不能为空</span>"
                }
            },
            submitHandler : function(form) {
                var options = {
                    success : function(responseText) {
                        var jsonData = $.parseJSON(JSON.stringify(responseText));
                        if (jsonData.status == 0) { // 修改成功，跳转问题详情页
                            layer.msg("编辑成功");
                            window.location.href = jsonData.data.returnUrl;
                            return;
                        }
                        layer.msg(jsonData.msg);

                    }
                };
                $(form).ajaxSubmit(options);
            }
        });
    });
</script>

</body>
</html>