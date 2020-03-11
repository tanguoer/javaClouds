<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/5
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录注册</title>
    <!--用百度的静态资源库的cdn安装bootstrap环境-->
    <!-- Bootstrap 核心 CSS 文件 -->
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome 核心我CSS 文件-->
    <link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- 在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <!--jquery.validate-->
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <%--    <script type="text/javascript" src="js/message.js" ></script>--%>
    <link rel="stylesheet" href="css/register.css">
</head>
<script>
    $(function () {
        $("#chickvcode").click(function () {
            $("#chickvcode").attr("src", "${pageContext.request.contextPath}/vcodeServlet?" + new Date().getTime());
        })
        $("#username1").blur(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/registerFindUserServlet",
                type: "POST",		 //请求类型
                data: {
                    "username": $("#username1").val()
                },	//数据
                dataType: "json",    	//设置接收到的响应数据的格式
                // 响应成功
                success: function (resp) {
                    //  期望的返回值为{userExist:true,msg:'该用户已经存在'}{userExist:false,msg:'注册成功'}
                    var span = $("#msg");
                    if (resp.userExist) {
                        span.css("color", "red");
                        span.css("font-size", "20px");
                        span.text(resp.msg);
                        $("#register_submit").addClass("disabled");

                    } else {
                        span.css("color", "green");
                        span.css("font-size", "20px");
                        span.text(resp.msg);
                        $("#register_submit").removeClass("disabled");
                    }
                },
                //响应失败
                error: function () {
                    alert("响应失败");
                }
            });
        })
        $("#register_submit").click(function () {
            var span = $("#msg");
            var name = $("#username1");
            var pwd = $("#register_password");
            var pwd1 = $("#rpassword");
            var email = $("#email");
            if(name.val() == "" || name.val() == null){
                span.css("color", "red");
                span.css("font-size", "20px");
                span.text("用户名不能为空");
                return;
            }else if(pwd.val() == "" || pwd.val()== null || pwd1.val() =="" || pwd1.val()==null){
                span.css("color", "red");
                span.css("font-size", "20px");
                span.text("密码不能为空");
                return;
            }else if(email.val() == "" || email.val() == null){
                span.css("color", "red");
                span.css("font-size", "20px");
                span.text("Email不能为空");
                return;
            }else if(pwd.val() != pwd1.val()){
                span.css("color", "red");
                span.css("font-size", "20px");
                span.text("两次输入的密码不一致");
                return;
            }else {
                $("#register_form").submit();
            }

         })
    })

</script>
<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form"
              action="${pageContext.request.contextPath}/loginServlet" method="post">
            <h3 class="form-title">登录到您的账户</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="用户名" name="username"
                           autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="密码" name="password"
                           maxlength="8"/>
                </div>
                <div class="form-group">
                    <input class="form-control required" style="width: 48%;float:left;" type="text" placeholder="验证码"
                           name="vcode" maxlength="4"/>
                    <img class="img-rounded" style="width:48%;float: right;" id="chickvcode" alt="看不清？点击更换"
                         title="看不清？点击更换" src="${pageContext.request.contextPath}/vcodeServlet">
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/> 记住账号
                    </label>
                    <hr/>
                    <a href="javascript:;" id="register_btn" class="">注册一个用户</a>
                    <input type="submit" class="btn btn-success pull-right" value="登录 "/>
                </div>
                <!-- 出错显示的信息框 -->
                <div class="alert alert-warning alert-dismissible" role="alert" style="width: 195px ;opacity:0.3;">
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span></button>
                    <strong>${msg == null ? "" : msg}</strong>
                </div>
            </div>
        </form>
    </div>

    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form"
              action="${pageContext.request.contextPath}/registerServlet" method="post">
            <h3 class="form-title">注册您的账户</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="用户名" name="username" id="username1"
                           autofocus="autofocus"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="密码" id="register_password"
                           name="password"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-check fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="重新输入密码" id="rpassword" name="rpassword"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-envelope fa-lg"></i>
                    <input class="form-control eamil" type="text" placeholder="邮箱" name="email" id="email"/>
                </div>
                <div class="form-group">
                    <input type="button" class="btn btn-success pull-right" id="register_submit" value="注册 "/>
                    <input type="button" class="btn btn-info pull-left" id="back_btn" value="返回"/>
                </div>
                <!-- 出错显示的信息框 -->
                <div class="alert alert-warning alert-dismissible" role="alert" style="width: 195px ;opacity:0.3;">
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span></button>
                    <span id="msg">${msg == null ? "" : msg}</span>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="js/main.js"></script>

<body>

</body>
</html>
