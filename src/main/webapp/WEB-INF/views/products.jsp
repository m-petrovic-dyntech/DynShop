<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<t:layout title="Adding funds">
    <jsp:attribute name="body_area">    
        <div class="container-fluid">
            <c:forEach items="${products}" var="product">
                <div class="products-item col-sm-3">
                    <h4>${product.id}</h4>
                    <img class="products-item-img" src="<c:url value="resources/images/picture.png" />" alt="karadzica">
                    <span class="products-item-date">10.10.2010.</span>
                    <div class="products-item-desc">
                        ${product.description}
                    </div>
                    <div class="products-item-price">${product.price}</div>
                    <div class="clearfix">
                        <a class="products-item-more btn btn-primary">Detaljnije</a>
                        <a class="products-item-cart btn btn-success">U korpu</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>
</t:layout>