<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ page contentType="text/html; charset=UTF-8" %>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Products">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid" id="cart-main_box">
                                <div class="container">
                                    <c:forEach items="${cart.getItems()}" var="cartItem" varStatus="loop">
                                        <div class="container-fluid row cart-item">
                                            <div class="col-sm-3">
                                                <div class="cart-item-img" style="background-image: url(<c:url value=" resources/images/picture.png "/>)"></div>
                                            </div>
                                            <div class="col-sm-6">
                                                <h3 class="cart-item-heading">${cartItem.getProduct().getName()}</h3>
                                                <div class="cart-item-desc">${cartItem.getProduct().getDescription()}</div>
                                            </div>
                                            <div class="cart-item-price">
                                                <span class="cart-item-price-value"><fmt:formatNumber value="${cartItem.getProduct().getPrice()}" currencySymbol="" type="currency"/></span>
                                                <div class="dropup cart-item-price-dropup">
                                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        ${cartItem.getQuantity()}
                                                    </button>
                                                    <div class="dropdown-menu cart-item-price-popup" aria-labelledby="dropdownMenu2">
                                                        <div class="input-group">
                                                            <input type="text" class="form-control cart-item-cart-edit_quantity-value" id="cart-item-price-product_quantity-${cartItem.getProduct().getId()}" placeholder="Quantity" value="${cartItem.getQuantity()}">
                                                            <span class="input-group-btn">
	                                                  				<button class="btn btn-success cart-item-cart-edit_quantity" type="button" item-id="${cartItem.getProduct().getId()}" item-cart-link="${pageContext.request.contextPath}/cart/edit/${cartItem.getProduct().getId()}/"><span class="glyphicon glyphicon-ok"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="cart-item-price_total">
                                                    <fmt:formatNumber value="${cartItem.getProduct().getPrice()*cartItem.getQuantity()}" currencySymbol="" type="currency" />
                                                </div>
                                            </div>
                                            <a class="cart-item-remove_button" href="${pageContext.request.contextPath}/cart/delete/${cartItem.getProduct().getId()}">
                                                <span class="glyphicon glyphicon-remove"></span>
                                            </a>
                                        </div>
                                    </c:forEach>
                                    <c:if test="${cart.getItems().size() == 0}">
                                        <div class="alert alert-info">Korpa je prazna. Kako bi DynTech opstao kao firma, i radnici ne koristili novine kao toalet papir, molimo ubacite nešto u korpu. <b>Preporuka:</b> Vladine Usne</div>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${cart.getItems().size() > 0}">
                                <div id="cart-total_box" class="container clearfix">
                                    <div id="cart-total_box-price">
                                        <div id="cart-total_box-price-value">
                                            <fmt:formatNumber value="${cart.getTotalCost()}" currencySymbol="" type="currency" /> din
                                        </div>
                                        <div id="cart-total_box-price-options">
                                            <a href="${pageContext.request.contextPath}/cart/deleteAll" id="cart-total_box-price-options-remove_all" class="btn btn-danger">
	                                    	Poništi
	                                    </a>
                                            <a id="cart-total_box-price-options-confirm" class="btn btn-success" href="${pageContext.request.contextPath}/cartSave">
	                                    	Završi kupovinu
	                                    </a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('.cart-item-cart-edit_quantity').click(function() {
                                    var tempLink = $(this).attr('item-cart-link');
                                    var tempId = $(this).attr('item-id');
                                    var tempQuantity = $('#cart-item-price-product_quantity-' + tempId).val()

                                    if (tempQuantity != 0 && tempQuantity) {
                                        window.location.href = tempLink + '?value=' + tempQuantity;
                                    }
                                });
                            });
                            </script>
                        </jsp:attribute>
                    </t:layout>
