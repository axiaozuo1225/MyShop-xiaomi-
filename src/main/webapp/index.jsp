<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>小米商城首页</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<!--网站中间内容开始-->
<div id="thred">

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="image/banner2.jpg" width="1230" height="460" />
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="image/bone04.png" width="1230" height="460" />
                <div class="carousel-caption">
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<div id="forth">
   		<span>
        	<a href=""><img src="image/hjh_01.gif"/></a>
            <a href=""><img src="image/hjh_02.gif"/></a>
            <a href=""><img src="image/hjh_03.gif"/></a>
            <a href=""><img src="image/hjh_04.gif"/></a>
            <a href=""><img src="image/hjh_05.gif"/></a>
            <a href=""><img src="image/hjh_06.gif"/></a>
        </span>
    <a href="" id="a_left"><img src="image/hongmi4x.png" width="316" height="170"/></a>
    <a href="" id="a_left"><img src="image/xiaomi5.jpg" width="316" height="170"/></a>
    <a href="" id="a_left"><img src="image/pinghengche.jpg" width="316" height="170"/></a>
</div>
<div id="fifth">
    <span id="fif_text">小米明星单品</span>
</div>
<div id="sixth">
            <span style="margin-left:0px; border-top:#ffa500 1px solid">
            	<a href="" id="siximg"><img src="image/pinpai1.png" width="234" height="234"/></a>
            	<a href="" id="na">小米MIX</a>
                <p id="chip">5月9日-21日享花呗12期分期免息</p>
                <p id="pri">3499元起</p>
            </span>
    <span style=" border-top:#008000 1px solid">
            	<a href="" id="siximg"><img src="image/pinpai2.png" width="234" height="234"/></a>
                <a href="" id="na">小米MIX</a>
                <p id="chip">5月9日-21日享花呗12期分期免息</p>
                <p id="pri">3499元起</p>
            </span>
    <span style="border-top:#0000ff 1px solid">
            	<a href="" id="siximg"><img src="image/pinpai3.png" width="234" height="234"/></a>
                <a href="" id="na">小米MIX</a>
                <p id="chip">5月9日-21日享花呗12期分期免息</p>
                <p id="pri">3499元起</p>
            </span>
    <span style="border-top:#ff0000 1px solid">
            	<a href="" id="siximg"><img src="image/pinpai4.png" width="234" height="234"/></a>
                <a href="" id="na">小米MIX</a>
                <p id="chip">5月9日-21日享花呗12期分期免息</p>
                <p id="pri">3499元起</p>
            </span>
    <span style="border-top:#008080 1px solid">
            	<a href="" id="siximg"><img src="image/pinpai5.png" width="234" height="234"/></a>
                <a href="" id="na">小米MIX</a>
                <p id="chip">5月9日-21日享花呗12期分期免息</p>
                <p id="pri">3499元起</p>
            </span>
</div>
<!-- 底部 -->
<%@ include file="footer.jsp" %>
</body>
</html>