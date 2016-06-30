<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                    <%@ page contentType="text/html; charset=UTF-8" %>
                        <!-- Content -->
                        <t:layout title="DynTech Shop | Products">
                            <jsp:attribute name="body_area">
                                <div class="container-fluid overflow-auto height-100">
                                    <div class="container page-main_box" id="products-main_box">
                                        <div id="products-filter" class="container-fluid row">
                                            <div class="col-md-4 row">
                                                <div class="form-group">
                                                    <form:select path="category.id" class="form-control input-sm" id="products-filter-select">
                                                        <form:option value="">-- Select Category --</form:option>
                                                        <form:options items="${categories}" itemValue="id" itemLabel="name" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <c:forEach items="${products}" var="product" varStatus="loop">
                                            <div class="products-item col-sm-3 products-item-${loop.index} clearfix">
                                                <!-- <div class="clearfix products-item-head row"> -->
                                                <h4>${product.name}</h4>
                                                <!-- <span class="products-item-category">${product.category}</span> -->
                                                <!-- </div> -->
                                                <div class="products-item-text_img">
                                                    <img class="products-item-img" src="${pageContext.request.contextPath}/resources/images/maci.jpg" alt="karadzica">
                                                    <div class="products-item-desc">
                                                        ${product.description}
                                                    </div>
                                                </div>
                                                <div class="products-item-price">
                                                    <fmt:formatNumber value="${product.price}" currencySymbol="" type="currency" />
                                                </div>
                                                <div class="clearfix products-item-options row">
                                                    <a class="products-item-more btn btn-default" href="${pageContext.request.contextPath}/product/${product.id}?categoryId=${category.id != null ? category.id : 0}">Detaljnije</a>
                                                    <div class="dropup products-item-cart">
                                                        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            U korpu
                                                        </button>
                                                        <div class="dropdown-menu products-item-cart-popup" aria-labelledby="dropdownMenu2">
                                                        	<form action="${pageContext.request.contextPath}/product/add/${product.id}${category.id != null ? '?categoryId=' += category.id : ''}" method="GET">
                                                        		<div class="input-group">
	                                                                <input type="number" class="form-control products-item-cart-add_item-input" placeholder="Quantity" name="quantity" value="1" min="1" required>
	                                                                 <input type="hidden" name="category" value="${category.id}"/>
	                                                                 <input type="hidden" name="page" value="${pagination.pageSize < pagination.numberOfItems? pagination.currentPage : ''}"/>
	                                                                <input type="hidden" name="size" value="${pagination.pageSize < pagination.numberOfItems? pagination.pageSize : ''}"/>
	                                                               
	                                                                <span class="input-group-btn">
	                                                                <button class="btn btn-success products-item-cart-add_item" type="submit"><span class="glyphicon glyphicon-ok"></span></button>
	                                                                </span>
	                                                            </div>
                                                        	</form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <c:if test="${category.id == 0}">
                                            Ne moze
                                        </c:if>
                                    </div>
                                    <!-- JS -->
                                    <script type="text/javascript">
                                    $(document).ready(function() {
                                        $('#products-filter-select').change(function() {
                                            var tempSelected = $(this).find('option:selected').val()
                                            
                                            var tempQuestionMark = tempSelected ? '' : '?';
                                            
                                            var tempPagination = "${pagination.pageSize < pagination.numberOfItems ? tempQuestionMark += '&page=' += pagination.currentPage += '&size=' += pagination.pageSize : ''}";

                                            window.location.href = '${pageContext.request.contextPath}/products' + (tempSelected ? '?category=' + tempSelected : '') + tempPagination;
                                        });
                                    })
                                    </script>
                                </div>
                            </jsp:attribute>
                        </t:layout>
