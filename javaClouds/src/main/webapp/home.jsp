<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>home</title>
<%--    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <style media="screen">
        .table {
            text-align: center;
        }
    </style>
</head>
<script type="text/javascript">
    function fileHandle(name, path, action) {
        var form = $("<form action='${pageContext.request.contextPath}/fileHandleServlet?pageNum=${FilePage.pageNum}&rows=5&FileType=${FileType}' method='post'></form>");
        var filename = $("<input type='hidden'name='filename'>");
        var filepath = $("<input type='hidden'name='path'>");
        var fileaction = $("<input type='hidden'name='action'>");
        console.log("name: " + name)
        console.log("path: " + path)
        filename.attr("value", name)
        filepath.attr("value", path);
        fileaction.attr("value", action);
        form.append(filename);
        form.append(filepath);
        form.append(fileaction);
        $("html").append(form);
        form.submit();
    }


</script>
<style>
    td {
        align-items: center;
        text-align: center;
    }
</style>
<body style="background-image: url(images/4.jpg)">
<div class="container"></div>
<table class="table table-bordered table-hover" style="width: 80%;margin: 0 auto">
    <c:if test="${FilePage.files.size() == 0}">
        <tr>
            <td style="font-size: 20px;color: red"><h2>文件夹为空</h2></td>
        </tr>
    </c:if>
    <c:if test="${FilePage.files.size() > 0}">
        <tr class="info">
            <td>编号</td>
            <td>文件名</td>
            <td>文件大小</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${FilePage.files}" var="file" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${file.filename}</td>
                <td>${file.fileSize/1024}KB</td>
                <td><a class="btn btn-default btn-sm" href="javascript:void (0);"
                       onclick="fileHandle('${file.filename}','${file.filePath}','download')">下载</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:void (0);"
                       onclick="fileHandle('${file.filename}','${file.filePath}','delete')">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<c:if test="${FilePage.files.size() > 0}">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${FilePage.pageNum == 1}">
            <li class="disabled">
                </c:if>
                <c:if test="${FilePage.pageNum != 1}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/FileSearchServlet?pageNum=${FilePage.pageNum-1}&rows=5&FileType=${FileType}"
                   aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
            </li>
            <c:forEach begin="1" end="${FilePage.totalPage}" var="i">
                <c:if test="${FilePage.pageNum == i}">
                    <li class="active">
                        <a href="javascript:void(0)">${i}</a>
                    </li>
                </c:if>
                <c:if test="${FilePage.pageNum != i}">
                    <li>
                        <a href="${pageContext.request.contextPath}/FileSearchServlet?pageNum=${i}&rows=5&FileType=${FileType}">${i}</a>
                    </li>
                </c:if>
            </c:forEach>
                <%--               此处为向下翻页，当点击翻页到最后的时候，点击仍然有效果，但此次无法控制，只能交由后台代码去控制&#45;&#45;--%>
            <c:if test="${FilePage.pageNum == FilePage.totalPage}">
            <li class="disabled">
                </c:if>
                <c:if test="${FilePage.pageNum != FilePage.totalPage}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/FileSearchServlet?pageNum=${FilePage.pageNum+1}&rows=5&FileType=${FileType}"
                   aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
            </li>
            <span style="font-size: 20px;margin-left: 10px">共${FilePage.totalCount}条记录,共${FilePage.totalPage}页</span>
        </ul>
    </nav>
</c:if>
<script src="js/jquery.min.js"></script>
<script src="js/table.js"></script>
</div>
</body>
</html>
