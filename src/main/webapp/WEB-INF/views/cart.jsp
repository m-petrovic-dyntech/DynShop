<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                        <!--  <div class="col-sm-3 cart-item-price_box">
	                            </div> -->
                                        <div class="cart-item-price">
                                            <span class="cart-item-price-value"><fmt:formatNumber value="${cartItem.getProduct().getPrice()}" currencySymbol="" type="currency"/></span>
                                            <div class="dropup cart-item-price-dropup">
                                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    ${cartItem.getQuantity()}
                                                </button>
                                                <div class="dropdown-menu cart-item-price-popup" aria-labelledby="dropdownMenu2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control cart-item-cart-edit_quantity-value" placeholder="Quantity" value="${cartItem.getQuantity()}">
                                                        <span class="input-group-btn">
	                                                  <button class="btn btn-success cart-item-cart-edit_quantity" type="button" item-index="${loop.index}" item-cart-link="${pageContext.request.contextPath}/cart/edit/${product.id}/"><span class="glyphicon glyphicon-ok"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="cart-item-price_total">
                                                <fmt:formatNumber value="${cartItem.getProduct().getPrice()*cartItem.getQuantity()}" currencySymbol="" type="currency" />
                                            </div>
                                        </div>
                                        <a class="cart-item-remove_button"><span class="glyphicon glyphicon-remove"></span></a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div id="cart-total_box">Total</div>
                        <script type="text/javascript">
                        $(document).ready(function() {

                        });
                        </script>
                    </jsp:attribute>
                </t:layout>
