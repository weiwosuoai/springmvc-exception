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
    <link href="<%=contextPath%>/css/login.css" rel="stylesheet">

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

    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#" style="border-top: 2px solid #EB3C0F;">登录</a></li>
    </ul>

    <div class="row margin-top20px">
        <!-- 左边栏 -->
        <div class="col-md-4">
            <form action="/users/login" method="post" id="form">
                <div class="form-group">
                    <label for="exampleInputEmail1">用户名</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="用户名" name="username">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="密码" name="password">
                </div>
                <%--<div class="checkbox">--%>
                    <%--<label>--%>
                        <%--<input type="checkbox"> Check me out--%>
                    <%--</label>--%>
                <%--</div>--%>
                <button type="submi" class="btn btn-default margin-top10px margin-bottom20px">登录</button>
            </form>

        </div>
        <div class="col-md-8">

        </div>
    </div>
</div>

<!-- footer -->
<%@include file="/common/footer.jsp" %>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="<%=contextPath%>/js/jquery.form.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/top-progress.jsp" %>
<%@ include file="/common/nav-top-js.jsp" %>
<script src="<%=contextPath%>/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=contextPath%>/plugins/validate/messages_zh.min.js"></script>
<%--弹出框组件--%>
<script src="<%=contextPath%>/plugins/layer/layer.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        // 表单验证
        $("#form").validate({
            rules:{
                'username':{
                    required:true
                },
                'password':{
                    required:true
                }
            },
            messages:{
                'username':{
                    required:"<span style='color: red;'>请输入用户名</span>"
                },
                'password':{
                    required:"<span style='color: red;'>请输入密码</span>"
                }
            },
            submitHandler : function(form) {
                var options = {
                    success : function(responseText) {
                        var jsonData = $.parseJSON(JSON.stringify(responseText));

                        layer.msg(jsonData.msg);
                        if (jsonData.status == 0) { // 登录成功，跳转首页
                            window.location.href = "/index";
                        }

                    }
                };
                $(form).ajaxSubmit(options);
            }
        });
    });
</script>

</body>
</html>