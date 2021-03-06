<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/department.js"></script>
</head>
<body>
<table id="dep_datagrid"></table>
<!-- 数据表格的顶部按钮-->
<div id="dep_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="dep_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="find">搜索</a>
    </div>
</div>
<!-- 对话框 -->
<div id="dep_dialog">
    <form id="dep_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id">
            <tr>
                <td>sn</td>
                <td><input type="text" name="sn"/></td>
            </tr>
            <tr>
                <td>部门名称</td>
                <td><input type="text" name="name"/></td>
            </tr>
        </table>
    </form>
</div>
<!--对话框底部按钮-->
<div id="dep_diglog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
