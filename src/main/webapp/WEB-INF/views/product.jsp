<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Product">
                        <jsp:attribute name="body_area">
                            <div class="container">
                                <div class="product_container">
                                    <h1>${product.name}</h1>
                                    <img class="products-item-img" src="${pageContext.request.contextPath}/resources/images/maci_mlad.jpg" alt="karadzica">
                                    <div class="product-item-desc">
                                        ${product.description}
                                    </div>
                                    <div class="products-item-price">
                                        <fmt:formatNumber value="${product.price.intValue()}" currencySymbol="" type="currency" />
                                    </div>
                                    <div class="clearfix">
                                        <div class="dropup products-item-cart">
                                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                U korpu
                                            </button>
                                            <div class="dropdown-menu products-item-cart-popup" aria-labelledby="dropdownMenu2">
                                                <div class="input-group">
                                                    <input type="text" class="form-control products-item-cart-add_item-input" placeholder="Quantity" value="1">
                                                    <span class="input-group-btn">
                                                    <button class="btn btn-success products-item-cart-add_item" type="button" item-cart-link="${pageContext.request.contextPath}/product/add/${product.id}/"><span class="glyphicon glyphicon-ok"></span></button>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('.products-item-cart-add_item').click(function() {
                                    addItem($(this));
                                });

                                $('.products-item-cart-add_item-input').keypress(function(event) {
                                    if (event.which == 13) {
                                        addItem($('products-item-cart-add_item'));
                                    }
                                });

                                function addItem(element) {
                                    var tempValue = $('.products-item-cart-add_item-input').val();
                                    var tempLink = $(element).attr('item-cart-link');

                                    var tempCategory = '${categoryId}';

                                    if (tempValue) {
                                        window.location.href = tempLink + (tempValue != '' && tempValue != 0 ? tempValue : 1) + '?categoryId=' + (tempCategory ? tempCategory : 0);

                                    }
                                }
                            })
                            </script>
                        </jsp:attribute>
                    </t:layout>
