<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                    <%@ page contentType="text/html; charset=UTF-8" %>
                        <!-- Content -->
                        <t:layout title="DynTech Shop | Products">
                            <jsp:attribute name="body_area">
                                <div class="container page-main_box">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Total Cost</th>
                                                <th>Purchase Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${carts}" var="cart">
                                                <tr>
                                                    <td>${cart.totalCost}</td>
                                                    <td>${cart.shoppingDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </jsp:attribute>
                        </t:layout>
