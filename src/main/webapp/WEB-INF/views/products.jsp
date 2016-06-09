<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Products">
                        <jsp:attribute name="body_area">
                            <div class="container">
                                <div id="products-filter" class="container-fluid row">
                                    <div class="col-md-4 row">
                                        <div class="form-group">
                                            <form:select path="category.id" items="${categories}" itemValue="id" class="form-control input-sm" id="products-filter-select"></form:select>
                                        </div>
                                    </div>
                                </div>
                                <c:forEach items="${products}" var="product" varStatus="loop">
                                    <div class="products-item col-sm-3 products-item-${loop.index}">
                                        <h4>${product.name}</h4>
                                        <img class="products-item-img" src="<c:url value=" resources/images/picture.png " />" alt="karadzica">
                                        <span class="products-item-date">10.10.2010.</span>
                                        <div class="products-item-desc">
                                            ${product.description}
                                        </div>
                                        <div class="products-item-price">
                                            <fmt:formatNumber value="${product.price.intValue()}" currencySymbol="" type="currency" />
                                        </div>
                                        <div class="clearfix">
                                            <a class="products-item-more btn btn-default" href="${pageContext.request.contextPath}/products/${product.id}">Detaljnije</a>
                                            <div class="dropup products-item-cart">
                                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    U korpu
                                                </button>
                                                <div class="dropdown-menu products-item-cart-popup" aria-labelledby="dropdownMenu2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control products-item-cart-add_item-input" placeholder="Quantity" value="1">
                                                        <span class="input-group-btn">
                                                        <button class="btn btn-success products-item-cart-add_item" type="button" item-index="${loop.index}" item-cart-link="${pageContext.request.contextPath}/products/add/${product.id}/"><span class="glyphicon glyphicon-ok"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- JS -->
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('#products-filter-select').change(function() {
                                    var tempSelected = $(this).find('option:selected').val()

                                    window.location.href = '${pageContext.request.contextPath}/products' + (tempSelected ? '?category=' + tempSelected : '');
                                });
                                $('.products-item-cart-add_item').click(function() {
                                    addItem($(this));
                                });

                                $('.products-item-cart-add_item-input').keypress(function(event) {
                                    var tempElement = $(this).next('.input-group-btn').find('.products-item-cart-add_item');
                                    if (event.which == 13) {
                                        addItem($(tempElement));
                                    }
                                });

                                function addItem(element) {
                                    var tempElementIndex = $(element).attr('item-index');
                                    var tempValue = $('.products-item-' + tempElementIndex).find('.products-item-cart-add_item-input').val();
                                    var tempLink = $(element).attr('item-cart-link');

                                    var tempCategory = '${category.id}';
                                    var tempCategoryId = '?categoryId=' + (tempCategory ? '${category.id}' : '0');

                                    window.location.href = tempLink + (tempValue != '' && tempValue != 0 ? tempValue : 1) + tempCategoryId;
                                }
                            })
                            </script>
                        </jsp:attribute>
                    </t:layout>
