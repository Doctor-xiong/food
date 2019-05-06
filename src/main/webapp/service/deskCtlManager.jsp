<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录餐桌</title>

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath }/layui/layui.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

    <style type="text/css">
        body {
            margin: 0px;
            padding: 0px;
            width: 100%;
            height: 100%;
            margin: auto;
        }

        #q {
            text-align: center;
            margin-right: 80px;
            margin-top: 100px;
        }
    </style>
</head>

<body style="background-color: #fffdfe">
<jsp:include page="nav.jsp"></jsp:include>
<div id="q" class="layui-col-xs18 layui-col-sm6 layui-col-md4">
    <form class="layui-form" action="${pageContext.request.contextPath }/AddDesk.do">
        <label class="layui-form-label" style="width: 100%;text-align: center;">添加桌台信息</label>
        <div class="layui-form-item">
            <label class="layui-form-label">桌台号</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required" placeholder="请输入桌台ID" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">校验码</label>
            <div class="layui-input-block">
                <input type="text" name="mask" required lay-verify="required" placeholder="请输入校验码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最多就餐人数</label>
            <div class="layui-input-inline">
                <input type="text" name="maxPersion" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">桌台位置</label>
            <div class="layui-input-inline">
                <input type="text" name="address" required lay-verify="required" placeholder="请输入地址"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" name="desc"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit style="width: 50%" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="width: 30%">重置</button>
            </div>
        </div>
    </form>
    <script>
        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert("提交信息", {
                title: "SUCCESS"
            })
            return false;
        });
        //Demo
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(formDemo)', function (data) {
                layer.msg(JSON.stringify(data.field));
                return false;
            });
        });
    </script>
</div>
</body>
</html>
