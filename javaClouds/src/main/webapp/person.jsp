<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人中心</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

    <style type="text/css">
        body{background: url(images/4.jpg) no-repeat;background-size: cover;font-size: 16px;}
        hr {border:none; border-bottom:1px solid #ddd;}
    </style>
</head>
<script src="js/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        $("#username").blur(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/registerFindUserServlet",
                type: "POST",		 //请求类型
                data: {
                    "username": $("#username").val()
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
                        $("#submit_btn").addClass("disabled");

                    } else {
                        span.css("color", "green");
                        span.css("font-size", "20px");
                        span.text(resp.msg);
                        $("#submit_btn").removeClass("disabled");
                    }
                },
                //响应失败
                error: function () {
                    alert("响应失败");
                }
            });
        })
    })
</script>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">用户个人信息</h3>
    <form action="${pageContext.request.contextPath}/personServlet" method="post">
        <input name="id" type="hidden" value="${user.id}" >
        <div class="form-group">
            <label for="username">姓名：</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}"  />
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="text" class="form-control" id="password"  name="password"value="${user.password}"  />
        </div>
        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${user.email}" />
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" id="submit_btn" value="保存" />
            <input class="btn btn-default" type="button" onclick="javascript:history.go(-1)" value="返回"/>
        </div>
    </form>
    <div class="progress">
        <div class="progress-bar progress-bar-striped"><span class="percent">0%</span></div>
    </div>
        <div>容量：${user.getUseStorage()/1024/1024}MB/${user.getMaxStorage()/1024/1024}MB</div>
    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert" style="width: 195px ;opacity:0.3;">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span></button>
        <span id="msg">${msg1 == null ? "" : msg1}</span>
    </div>
</div>
<script>
    window.onload = function () {
        $(".progress-bar").width(${(user.getUseStorage())/(user.getMaxStorage())*100}); //上传进度条宽度变宽
        var storage = ${(user.getUseStorage())/(user.getMaxStorage())*100};
        $('.percent').html(storage.toFixed(2)+"%"); //显示使用空间百分比
    }
</script>
</body>
</html>
