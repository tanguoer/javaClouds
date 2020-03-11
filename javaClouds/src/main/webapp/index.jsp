<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>clouds主页面</title>
    <link rel="stylesheet" href="css/sidebar.css">
</head>
<body>
<div id="wrapper" style="left:0;">
    <!-- 侧边栏 -->
    <div class="sidebar">
        <div class="headSculpture">
            <img src="images/headSculpture.jpg" alt="">
            <p>Clouds云盘</p>
        </div>
        <div class="option">
            <ul>
                <li><img src="images/home.png" alt="">
                    <p><a href="${pageContext.request.contextPath}/FileSearchServlet?FileType=6&rows=5&pageNum=1" target="iframe">首页</a></p>
                </li>
                <li><img src="images/works.png" alt="">
                    <p><a href="search.jsp" target="iframe">分类查询</a></p>
                </li>
                <li><img src="images/release.png" alt="">
                    <p><a href="uploadFile.jsp" target="iframe">文件上传</a></p>
                </li>
                <li><img src="images/setup.png" alt="">
                    <p><a href="${pageContext.request.contextPath}/findUserServlet" target="iframe">个人中心</a></p>
                </li>
            </ul>
        </div>
    </div>
    <!-- 侧边栏按钮 -->
    <button></button>
    <!-- 内容区域 -->
    <div class="banner">
        <iframe src="hello.html" name="iframe" style="width: 100%;height: 100%;"></iframe>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/sidebar.js"></script>
</body>
</html>