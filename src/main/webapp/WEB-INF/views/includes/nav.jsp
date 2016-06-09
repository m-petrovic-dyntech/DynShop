<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">DynTech Shop</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="navbar-cart_view">
                    <li>
                        <span id="navbar-cart_view-totalCost">${sessionScope.cart.getTotalCost() != null ? sessionScope.cart.getTotalCost().intValue() : 0}.00</span>
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                        <span id="navbar-cart_view-totalCount">${sessionScope.cart.items.size() != null ? sessionScope.cart.items.size() : 0}</span>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
                        <ul class="dropdown-menu mini_cart">
                            <c:forEach items="${sessionScope.cart.getItems()}" var="item" varStatus="loop">
                                <li>
                                    <a class="mini_cart-item">
                                        <span class="mini_cart-name">${item.getProduct().getName()}</span>
                                        <span class="mini_cart-price">${item.getProduct().getPrice().intValue()}.00</span>
                                        <span class="mini_cart-quantity">${item.getQuantity()}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
