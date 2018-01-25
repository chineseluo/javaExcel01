<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>导出EXCEL-DEMO</title>
    <script src="/plugins/jquery-1.11.1.min.js"></script>
    <script src="/plugins/jquery.form.js"></script>
</head>
<body>
<h1>contact${contacts}</h1>
<table>
    <thead>
    <td>联系人名称</td>
    <td>邮箱</td>
    <td>电话</td>
    <td>地址</td>
    <td>职位</td>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="contact">
        <tr>
            <td>${contact.name}</td>
            <td>${contact.email}</td>
            <td>${contact.mobile}</td>
            <td>${contact.address}</td>
            <td>${contact.position}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    输入导出EXCEL文件的名称：<input type="text" id="filename" required/>
</p>
<p>
    <button type="button" id="btn-output">导出</button>
</p>
<form id="excelForm" method="post" enctype="multipart/form-data">
    <p>
        选择导入的EXCEL文件:<input type="file" name="excelFile" id="inportfile" onchange="excelChange()"/>
    </p>
    <p>
        <button type="button" id="btn-input">导入</button>
    </p>
</form>
<script>
    //获得当前项目的根目录
    var rootPath = '${rootPath}';
    //检测上传文件是否符合规则
    var flag = false;
    //从数据库导出EXCEL
    $("#btn-output").click(function () {
        var fileName = $("#filename").val();
        alert("filename:" + fileName)
        window.location.href = rootPath + "/index/export/" + fileName;
    })
    //导入EXCEL
    $("#btn-input").click(function () {
        var fileName = $("#inportfile").val();
        alert("inputfilename:" + inportfile);
        if (null == fileName || fileName.length == 0) {
            return false;
        }
        if (flag == true) {
            alert("开始导入")
            // ajaxSubmit()马上由AJAX来提交表单。你可以在任何情况下进行该项提交。
            if (flag == true) {
                alert("文件发送")
                alert("rootpath:" + rootPath);
                alert("excelForm" + $("#excelForm"))
                $("#excelForm").ajaxSubmit({
                    url: rootPath + "/index/import",
                    cache: false,
                    dataType: "json",
                    data: {},
                    success: function (result) {
                        // 刷新页面
                        window.location.href = rootPath + "/index/to_contact";
                    }
                });
            } else {
                return;
            }
        }
    })
    function excelChange() {
        // 验证文件格式(必须是xlsx或者是xls)
        if (/^.*?\.(xlsx|xls)$/.test($("#inportfile").val())) {
            flag = true;
            alert(flag)
        } else {
            alert("请上传xls或xlsx格式的文件");
            flag = false;
            alert(flag)
        }
    }
</script>
</body>
</html>
