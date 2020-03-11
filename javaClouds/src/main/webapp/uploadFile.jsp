<%@ page import="com.clouds.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <script type='text/javascript' src='js/jquery-2.0.3.min.js'></script>
    <link href="css/uploadFile.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="upload">
    注意：<br>
    上传的文件不能超过1.5G；
    <input type="hidden" id="CurrentFolder" name="CurrentFolder" value="${CurrentFolder}"/>
</div>

<div id="upbt">
    <div id="btn"><input type="button" class="uploadbtn" value="选择文件" onClick="uploadfile.click()" id="select"><input
            type="button" value="开始上传" id="ups"></div>
</div>
<div style="width:940px;margin:10px auto;   overflow:hidden; margin-top:10px;">
    <form id='myupload' action='#' method='post' enctype='multipart/form-data'>
        <input type="file" id="uploadfile" name="uploadfile" value="请点击上传文件" style="display:none"
               onChange="filesize(this)"/>
    </form>

    <table width="100%" border="0" id="table" cellpadding="1" cellspacing="1">
        <tr bgcolor="#4a5464" align="center">
            <td width="27%">文件名</td>
            <td width="17%">文件大小</td>
            <td width="30%">上传进度</td>
            <td width="12%">上传状态</td>
            <td width="14%">操作</td>
        </tr>
        <tr id="list" style="background:url(images/bg.jpg); display:none;">
            <td>
                <div id="text"></div>
            </td>
            <td>
                <div id="big"></div>
            </td>
            <td>
                <div class="progress">
                    <div class="progress-bar progress-bar-striped"><span class="percent">0%</span></div>
                </div>
            </td>
            <td>
                <div id="uped">等待上传</div>
            </td>
            <td><a href="javascript:void(0)" id="del">移除</a></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function filesize(ele) {
        <% User user = (User)session.getAttribute("user");%>
        var UseStorage = <%=user.getUseStorage()%>;
        var MaxStorage = <%=user.getMaxStorage()%>;
        var filesize = ele.files[0].size;
        if((UseStorage+filesize)>MaxStorage){
            alert("用户存储空间不足，无法上传");
            var objFile = document.getElementsByTagName('input')[2];
            objFile.value = "";
            $("#list").hide();
            return;
        }
        if (filesize > 1024 * 1024 * 1024 * 1.5) {
            alert("文件大小不能超过1.5G,请将文件压缩后上传");
            var objFile = document.getElementsByTagName('input')[2];
            objFile.value = "";
            $("#list").hide();
            return;
        }
        if (filesize < 1024) {
            $('#big').html(filesize + "B");
        } else if (filesize >= 1024 && filesize <= (1024 * 1024)) {
            $('#big').html((filesize / 1024).toFixed(2) + "KB");
        } else {
            $('#big').html((filesize / 1024 / 1024).toFixed(2) + "MB");
        }
        $('#text').html(ele.files[0].name);
        $('#uped').html("等待上传");
        $(".progress-bar").width(0); //上传进度条宽度变宽
        $('.percent').html(0); //显示上传进度百分比
    }

    var progress_bar = $(".progress-bar");
    var percent = $('.percent');
    //移除文件
    $("#del").click(function () {
        var objFile = document.getElementsByTagName('input')[2];
        objFile.value = "";
        $("#list").hide();
    });
    $("#uploadfile").change(function () {
        $("#list").show();
    });
    $("#ups").click(function () {
        //var file = $("#uploadfile").val();
        var file = $("#uploadfile")[0].files[0];
        if (file != null) {
            $('#uped').html("上传中……");
            //文件切割上传
            var flag = false;
            var LENGTH = 1024 * 1024 * 40;   //40MB //文件切割长度
            var start = 0;
            var end = start + LENGTH;
            var blob = new Blob();
            var fd = null;
            var index = 0;      //计算切割了多少段
            var name = file.name;
            var totalsize = file.size;
            var date = new Date().getTime();    //获取系统时间
            var DocumentIdentificationCode = date + totalsize;
            var precent = 0;
            var interval = setInterval(function () {
                if (start < totalsize) {
                    index = index + 1;
                    blob = file.slice(start, end);
                    fd = new FormData();
                    fd.append('name', DocumentIdentificationCode + "L" + index);
                    fd.append('part', blob);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/FileUpload",
                        type: "post",
                        processData: false,
                        contentType: false,
                        data: fd,
                        success: function (data) {
                        }
                    });
                    precent = (end / totalsize).toFixed(1);
                    if (precent > 1) {
                        precent = 1;
                    }
                    if (precent <= 1) {
                        var percentVal = precent * 100 + '%'; //获得进度
                        progress_bar.width(percentVal); //上传进度条宽度变宽
                        percent.html(percentVal); //显示上传进度百分比
                    }
                    start = end;
                    if (totalsize - start > LENGTH) {
                        end = end + LENGTH;
                    } else {
                        end = totalsize;
                    }
                } else {
                    clearInterval(interval);
                    $.post("${pageContext.request.contextPath}/fileMergeServlet", {
                        CurrentFolder: $("#CurrentFolder").val(),
                        filename: DocumentIdentificationCode + "L" + name,
                        index: index
                    }, function (data, status) {
                        if (data == "true") {
                            $('#uped').html("上传成功");
                        }
                    })
                }
            }, 10);
        } else {
            alert("请选择文件");
        }
});
</script>
</body>
</html>