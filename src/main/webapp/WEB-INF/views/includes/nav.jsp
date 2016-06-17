<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="container-fluid">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/products">DynTech Shop</a>
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <sec:authorize access="isAnonymous()">
                                    <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li><a href="${pageContext.request.contextPath}/admin/panel/products">Control panel</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/products">Products</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/cartLogs">Cart Logs</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
                                </sec:authorize>
                            </ul>
                            <ul class="nav navbar-nav navbar-right" id="navbar-cart_view">
                                <sec:authorize access="isAuthenticated()">
                                    <li class="dropdown" id="navbar-username_dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="navbar-username_preview">
                                            <span class="username">
                                                  <sec:authentication property="principal.username" /> 
                                            </span>
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu" id="logout_dropdown">
                                            <li>
                                                <c:url value='/logout' var='logoutUrl' />
                                                <form action="${logoutUrl}" method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                    <input class="btn btn-primary" type="submit" name="submit" value="Logout" />
                                                </form>
                                            </li>
                                        </ul>
                                    </li>
                                </sec:authorize>
                                <li>
                                    <span id="navbar-cart_view-totalCost">
                                        <fmt:formatNumber value="${sessionScope.cart.getTotalCost() != null ? sessionScope.cart.getTotalCost() : 0}" currencySymbol="" type="currency"/>
                                    </span>
                                    <a href="${pageContext.request.contextPath}/cart" id="navbar-cart_view-link">
                                        <span class="glyphicon glyphicon-shopping-cart"></span>
                                        <span id="navbar-cart_view-totalCount">${sessionScope.cart.items.size() != null ? sessionScope.cart.items.size() : 0}</span>
                                    </a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
                                    <ul class="dropdown-menu mini_cart">
                                        <c:forEach items="${sessionScope.cart.getItems()}" var="item" varStatus="loop">
                                            <li>
                                                <a class="mini_cart-item">
                                                    <span class="mini_cart-name">${item.getProduct().getName()} <span class="mini_cart-quantity">${item.getQuantity()}</span></span>
                                                    <span class="mini_cart-price"><fmt:formatNumber value="${item.getProduct().getPrice()}" currencySymbol="" type="currency"/></span>
                                                </a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${sessionScope.cart.getItems().isEmpty() || sessionScope.cart.getItems() == null}">
                                            <div class="mini_cart-message">Nothing in cart</div>
                                        </c:if>
                                    </ul>
                                </li>
                                <sec:authorize access="isAnonymous()">
                                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </div>
                </nav>
