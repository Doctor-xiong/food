<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>My JSP 'login.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <style type="text/css">
        body {
            width: 100%;
            height: 100%;
            margin: 0px;
            padding: 0px;
        }

        #backgroud {
            width: 100%;
            height: 100%;
            background-size: 100% 100%;
        }

        #title {
            width: 260px;
            height: 130px;
            color: #D2B48C;
            font-weight: bolder;
            font-size: 300%;
            text-align: center;
            margin-left: 580px;
        }

        .login-login {
            width: 400px;
            height: 320px;
            margin-top: 20px;
            background-color: #fffdfe;
            margin: 100px 37.5%
        }

        .login-w {
            width: 346px;
            border-bottom: 1px solid #CCCCCC;
            margin: auto;
            padding-top: 20px;
        }

        .login-table {
            text-align: center;
            font-weight: bold;
        }

        .login-table div {
            margin-left: 10px;
        }

        .login-title-text-2 {
            width: 50px;
            height: 33px;
            color: white;
            text-align: center;
            margin-bottom: 20px;
            padding-right: 62px;
        }

        #chk {
            diaplay: block;
            float: left;
        }

        #rem {
            margin-left: 5px;
            display: bolck;
            float: left;

        }

        #nameError {
            height: 10px;
        }

        #btn {
            background-image: url(img/bj1.jpg);
        }

        .error {
            color: red;
            font-size: 15px;
        }
    </style>

    <script type="text/javascript">
        function validate(obj) {
            //获取input对象
            var value = obj.value;
            //根据id获取span标签的对象
            var spanObj = document.getElementById("nameError");
            //判断用户名是否为空
            if (value == "") {
                spanObj.innerHTML = "name is null !";
                spanObj.className = "error";
                return;
            }
            //判断用户名的长度
            if (value.length < 2 || value.length > 20) {
                spanObj.className = "error";
                return;
            }
            //合法内容
            spanObj.innerHTML = "";
        }

        function pwvalidate(obj) {
            //获取input对象
            var value = obj.value;
            //根据id获取span标签的对象
            var spanObj = document.getElementById("pswError");
            //判断用户名是否为空
            if (value == "") {
                spanObj.innerHTML = "password is null！";
                spanObj.className = "error";
                return;
            }
            //判断用户名的长度
            if (value.length < 6 || value.length > 20) {
                spanObj.className = "error";
                return;
            }
            //合法内容
            spanObj.innerHTML = "";
        }
        function numalidate(obj) {
            //获取input对象
            var value = obj.value;
            //根据id获取span标签的对象
            var spanObj = document.getElementById("numError");
            //判断用户名是否为空
            if (value == "") {
                spanObj.innerHTML = "number is null ！";
                spanObj.className = "error";
                return;
            }
            //判断用户名的长度
            if (value.length < 6 || value.length > 20) {
                spanObj.className = "error";
                return;
            }
            //合法内容
            spanObj.innerHTML = "";
        }

    </script>

</head>

<body>
<div id="backgroud">
    <div id="title" style="margin-left: 650px;">
        <br/>
        <span>自助点餐系统</span>
    </div>
    <div class="login-login">
        <div class="login-w">
            <form class="layui-form" action="${pageContext.request.contextPath }/clientLogin.action">
                <div class="login-table">
                    <div class="layui-form-item">
                        <i class="layui-icon layui-icon-username" style="font-size: 30px; color: #1E9FFF;"></i>
                        <div class="layui-input-block">
                            <input type="text" name="d_name" required lay-verify="required" placeholder="请输入name"
                                   autocomplete="off" class="layui-input" onblur="validate(this);"
                                   autofocus="autofocus">
                        </div>
                        <span id="nameError"></span>
                    </div>
                    <div class="layui-form-item">
                        <i class="layui-icon layui-icon-username" style="font-size: 30px; color: #1E9FFF;"></i>
                        <div class="layui-input-block">
                            <input type="text" name="d_id" required lay-verify="required" placeholder="请输入number"
                                   autocomplete="off" class="layui-input" onblur="numalidate(this);"
                                   autofocus="autofocus">
                        </div>
                        <span id="numError"></span>
                    </div>
                    <div>
                        <i class="layui-icon layui-icon-password" style="font-size: 30px; color: #1E9FFF;"></i>
                        <div class="layui-input-block">
                            <input type="password" name="d_password" required lay-verify="required" placeholder="请输入密码"
                                   autocomplete="off" class="layui-input" placeholder="请输入密码"
                                   onblur="pwvalidate(this);" autofocus="autofocus">
                        </div>
                        <span id="pswError"></span>
                    </div>

                    <div style="color: red;">${error }</div>
                    <div style="color: red;">${msg }</div>
                    <br/><br/><br/>
                    <a class="layui-form layui-btn layui-btn-lg layui-btn-primary layui-btn-radius" style="text-align: center" href="/user.action" target="_blank">用户注册</a>
                    <button class="layui-btn layui-btn-lg layui-btn-primary layui-btn-radius">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;
    });
</script>
</html>