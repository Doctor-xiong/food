<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>自助点餐系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath }/layui/layui.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

    <style type="text/css">
        .layui-nav-tree {
            width: 100px;
            padding: 0;
            height: 100%;
        }

        /* .layui-bg-cyan {
            background-color: #6e8cb4!important;
        } */
        .layui-bg-cyan {
        }

        .layui-layout-admin .layui-header .layui-nav {
            font-weight: 800;
            font-size: 16px;

        }

        .layui-layout-admin .layui-footer {
            background-color: rgba(0, 0, 0, 0); /* IE6和部分IE7内核的浏览器(如QQ浏览器)会读懂，但解析为透明 */
            color: rgba(255, 255, 255, .7);
        }

        #${param.nav }{
			background-color: #0C0C0C;
		}
        .layui-layer-btn .layui-layer-btn1 {
            border-color: #00F7DE;
            /*background-color: #fffdfe;*/
            /*color: #fff;*/
        }

        .layui-layer-btn .layui-layer-btn0 {
            border-color: #dedede;;
            /*background-color: #fff;*/
            color: #333;
        }

        ul > li > a {
            margin-left: -10px;
        }

    </style>
</head>

<body>
<div class="layui-layout layui-layout-admin">

    <div class="layui-header" style="background-color: #0C0C0C">
        <div class="layui-logo">自助点餐系统</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item" id="mydesk">
                <a id="bage-before" href="${pageContext.request.contextPath }/mydesk.do">我的餐桌</a>
                <span id="bage" class="layui-badge" style="margin-left: 70px;;">${bageNum }</span>
                <script type="text/javascript">
                    var num = $("#bage").text();
                    if (num == 0) {
                        $("#bage").remove();
                    }
                </script>
            </li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${name}
                </a>
            </li>
            <%--<li class="layui-nav-item"><a--%>
                    <%--href="${pageContext.request.contextPath }/userInfo.action?d_id=${d_id}">个人信息</a></li>--%>
            <li class="layui-nav-item"><a
                    href="${pageContext.request.contextPath }/delUser.action?d_id=${d_id}">注销用户</a></li>
            <li class="layui-nav-item"><a
                    href="${pageContext.request.contextPath }/myLoginOut.action?d_id=${d_id}">退出</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black" style="width: 100px; background-color: #0C0C0C">
        <div class="layui-row" style="width: 100px; background-color: #0C0C0C">
            <ul class="layui-nav layui-nav-tree layui-inline layui-bg-cyan " lay-filter="demo"
                style="margin-right: 10px; background-color: #0C0C0C">

                <li class="layui-nav-item " style="background-color: #0C0C0C" id="orderFood">
                    <a href="${pageContext.request.contextPath }/queryRecommendMenu.do">人气推荐</a>
                </li>
                <li class="layui-nav-item " style="background-color: #0C0C0C" id="food1">
                    <a href="${pageContext.request.contextPath }/queryAllMenuByType.do?m_type=精致小炒">小炒</a>
                </li>
                <li class="layui-nav-item " style="background-color: #0C0C0C" id="food2">
                    <a href="${pageContext.request.contextPath }/queryAllMenuByType.do?m_type=美味大餐">主食</a>
                </li>
                <li class="layui-nav-item " style="background-color: #0C0C0C" id="food3">
                    <a href="${pageContext.request.contextPath }/queryAllMenuByType.do?m_type=招牌干锅">甜点</a>
                </li>
                <li class="layui-nav-item" style="background-color: #0C0C0C" id="food4">
                    <a href="${pageContext.request.contextPath }/queryAllMenuByType.do?m_type=营养靓汤">汤类</a>
                </li>
                <li class="layui-nav-item" style="background-color: #0C0C0C" id="drink">
                    <a href="${pageContext.request.contextPath }/queryAllMenuByType.do?m_type=酒水饮料">酒水</a>
                </li>
                <c:if test="${again eq 'again'}">
                    <li class="layui-nav-item" id="again">
                        <a href="${pageContext.request.contextPath }/ismydesk.do">已付款菜品</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>
</div>
<c:if test="${param.nav eq 'mydesk'}">
    <div class="layui-footer" style="width:100%;margin-left: -100px;height: 80px; text-align: center">
        <!-- 底部固定区域 -->
        <div style="height: 25px;line-height: 25px;margin-left: 130px;font-size: 16px;font-weight: 800 ; text-align: center">
            合计：<span id="mydesk-price"><c:if test="${os.os_allprice == null }">0</c:if>${os.os_allprice }</span>元
        </div>
        <div style="margin-left: 20px;height: 50px; text-align: center" class="site-demo-button" id="layerDemo">
            <button id="empty" class="layui-btn layui-btn-primary" data-method="confirmTrans"><a
                    href="${pageContext.request.contextPath }/isEmpty.do?os_id=${os.os_id}">清空菜单</a></button>
            <button id="pay" data-method="notice" class="layui-btn"> 立即结算</button>
        </div>
    </div>
</c:if>
<c:if test="${param.nav eq 'again'}">
    <div class="layui-footer" style="width:100%;margin-left: -100px;height: 30px;">
        <!-- 底部固定区域 -->
        <div style="height: 25px;line-height: 25px;margin-left: 130px;font-size: 16px;font-weight: 800">
	    	<span>合计：
		    	<c:if test="${os1.os_allprice == null }">
                    0
                </c:if>
	    		${os1.os_allprice }元
	    	</span>
        </div>
    </div>
</c:if>
<%--<div class="layui-footer" style="width:100%;margin-left: -100px; background-color: #0C0C0C">--%>
<%--欢迎光临智能时尚餐厅--%>
<%--</div>--%>
</div>
<script>
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

        var active = {
            notice: function () {
                //示范一个公告层
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: false
                    ,
                    area: '300px;'
                    ,
                    shade: 0.8
                    ,
                    anim: 2
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['继续点餐', '立即支付']
                    ,
                    btnAlign: 'c'
                    ,
                    moveType: 1 //拖拽模式，0或者1
                    ,
                    content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">需要支付的金额为：${os.os_allprice }元</div>'
                    ,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn1').attr({
                            href: '${pageContext.request.contextPath }/alipay.do?os_id=${os.os_id }'
                            , target: '_blank'
                        });
                    }
                });
            },

            //触发事件
            confirmTrans: function () {
                //配置一个透明的询问框
                layer.msg(food, {
                    time: 1000, //1s后自动关闭
                });
            }
        };

        $('#layerDemo #pay').on('click', function () {
            var allprice = $("#mydesk-price").text();
            if (allprice > 0) {
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            } else {
                food = '您还没点餐，无法提交';
                var othis = $(this), method = 'confirmTrans';
                active[method] ? active[method].call(this, othis) : '';
                return false;
            }
        });
    })
    ;

    $("#empty").click(function () {
        var allprice = $("#mydesk-price").text();
        if (allprice > 0) {
            return true;
        } else {
            food1 = '已经很干净了噢';
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
            return false;
        }
    });
    //触发事件
    var active = {
        confirmTrans: function () {
//配置一个透明的询问框
            layer.msg(food1, {
                time: 1000, //1s后自动关闭
            });
        }
    };
</script>
<script
type = "text/javascript" >

</script>

<script >
layui.use('element', function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function (elem) {
        //console.log(elem)
        layer.msg(elem.text());
    });
});

layui.use('table', function () {
    var table = layui.table;
});

$("#notice").click(function () {
    layer.open({
        type: 1,
        maxmin: true,
        title: '餐厅活动',
        area: ['180px', '150px'],
        content: '<p>每天20:00以后点餐酒水全免</p><p>活动时间：5/1--6/1日</p>'
    });
});
$("#desknumber").click(function () {
    layer.open({
        type: 1,
        maxmin: true,
        title: '联系方式',
        area: ['180px', '150px'],
        content: '<br/><br/><p>微信号：18273660815</p><br/><p>电话：15073134890</p>'
    });
});
</script>


</body>
</html>
