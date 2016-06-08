<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                <!-- Content -->
                <t:layout title="Adding funds">
                    <jsp:attribute name="body_area">
                        <div class="container-fluid">
                            <div id="products-filter" class="container-fluid row">
                                <div class="col-md-4 row">
                                    <div class="form-group">
                                        <form:select path="category" items="${categories}" itemValue="id" class="form-control input-sm" id="products-filter-select"></form:select>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${products}" var="product">
                                <div class="products-item col-sm-3">
                                    <h4>${product.name}</h4>
                                    <img class="products-item-img" src="<c:url value=" resources/images/picture.png " />" alt="karadzica">
                                    <span class="products-item-date">10.10.2010.</span>
                                    <div class="products-item-desc">
                                        ${product.description}
                                    </div>
                                    <div class="products-item-price">${product.price.intValue()}.00</div>
                                    <div class="clearfix">
                                        <a class="products-item-more btn btn-primary" href="${pageContext.request.contextPath}/products/${product.id}">Detaljnije</a>
                                        <a class="products-item-cart btn btn-success" href="${pageContext.request.contextPath}/add/${product.id}">U korpu</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </jsp:attribute>
                </t:layout>
                <script type="text/javascript">
                $('#products-filter-select').change(function() {
                    var tempSelected = $(this).find('option:selected').val()

                    window.location.href = '${pageContext.request.contextPath}/products' + (tempSelected ? '?category=' + tempSelected : '');
                })
                </script>
