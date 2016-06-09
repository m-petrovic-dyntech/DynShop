<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <!-- Content -->
            <t:layout title="DynTech Shop | Products">
                <jsp:attribute name="body_area">
                    <div class="container">
                        <div class="container-fluid row cart-item">
                            <div class="col-sm-3">
                                <img src="<c:url value=" resources/images/picture.png "/>"/>
                            </div>
                            <div class="col-sm-6">
                                <h3 class="cart-item-heading">Heading</h3>
                                <div class="cart-item-desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Veniam quam at debitis incidunt modi nesciunt odio dolorum enim esse exercitationem voluptatibus tempora maxime facilis sunt ipsam vero animi accusamus atque perspiciatis, repellendus eaque.</div>
                            </div>
                            <div class="col-sm-3">
                                <div class="cart-item-price">
                                    <span class="cart-item-price-value">${123123.00}</span>
                                    <div class="dropdown cart-item-price-dropdown">
                                        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ${9}
                                        </button>
                                        <div class="dropdown-menu cart-item-price-popup" aria-labelledby="dropdownMenu2">
                                            <div class="input-group">
                                                <input type="text" class="form-control cart-item-cart-edit_quantity-value" placeholder="Quantity" value="1">
                                                <span class="input-group-btn">
                                                  <button class="btn btn-success cart-item-cart-edit_quantity" type="button" item-index="${loop.index}" item-cart-link="${pageContext.request.contextPath}/cart/edit/${product.id}/"><span class="glyphicon glyphicon-ok"></span></button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </jsp:attribute>
            </t:layout>
