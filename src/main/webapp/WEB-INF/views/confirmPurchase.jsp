<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ page contentType="text/html; charset=UTF-8" %>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Products">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box" id="cart-main_box">
                                <div class="container">
                                  Ovde treba da se ubace ponudjeni nacini kupovine, sada je paymentmethod = card
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
                                            <a id="cart-total_box-price-options-confirm" class="btn btn-success" href="${pageContext.request.contextPath}/user/confirmPurchase?paymentMethod=card">
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
