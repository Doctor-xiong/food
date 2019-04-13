
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>404</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="//res.layui.com/layui/rc/css/layui.css?t=20181101-1" media="all">
    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body>
<div id="LAY_app"></div>
<script src="//res.layui.com/layui/rc/layui.js?t=20181101-1"></script>
<script>
    layui.config({
        base: './dist/' //指定 layuiAdmin 项目路径
        ,version: '1.2.1'
    }).use('index', function(){
        var layer = layui.layer, admin = layui.admin;
        layer.ready(function(){
            admin.popup({
                content: '单页面专业版默认未开启“多标签”功能，实际运用时，你可以自定义是否开启'
                ,area: '300px'
                ,btnAlign: 'c'
                ,shade: false
            });
        });
    });
</script>
${error}
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?d214947968792b839fd669a4decaaffc";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>

