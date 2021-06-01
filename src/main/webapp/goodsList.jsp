<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>商品列表页</title>

</head>
<body>
<%@ include file="header.jsp" %>


<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
    <div class="panel-heading">
        <h3 class="panel-title"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;商品列表</h3>
    </div>
    <div class="panel-body">
        <!--列表开始-->
        <div class="row" style="margin: 0 auto;">
            <c:forEach items="${page.list}" var="g" varStatus="i">
                <div class="col-sm-3">
                    <div class="thumbnail">
                        <img src="${pageContext.request.contextPath}/${g.picture}" width="180" height="180" alt="小米6"/>
                        <div class="caption">
                            <h4>商品名称<a
                                    href="${pageContext.request.contextPath}/product?method=getProductByPid&pid=${g.pid}">${g.name}</a>
                            </h4>
                            <p>热销指数
                                <c:forEach begin="1" end="${g.star}">
                                    <img src="image/star_red.gif" alt="star"/>
                                </c:forEach>
                            </p>
                            <p>上架日期:${g.pubdate}</p>
                            <p style="color:orange">价格:${g.price}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<div class="text-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="${page.curPage==1?'disabled':''}">
                <a href="/product?method=list&curPage=${page.curPage-1}&tid=${param.tid}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <c:forEach begin="1" end="${page.totalPage}" step="1" var="e">
                <li class="${page.curPage==e?'active':''}"><a
                        href="/product?method=list&curPage=${e}&tid=${param.tid}">${e}</a></li>
            </c:forEach>

            <li class="${page.curPage==page.totalPage?'disabled':''}">
                <a href="/product?method=list&curPage=${page.curPage+1}&tid=${param.tid}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<!-- 底部 -->
<%@ include file="footer.jsp" %>
</body>
</html>