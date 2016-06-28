<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Storage Management Orders">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box stor_manager-wrapper" id="stor_manager-categories-main_box">
                                <!-- Sidebar -->
                                <div id="stor_manager-sidebar">
                                    <%@include file="/WEB-INF/views/includes/storage_manager-sidebar.jsp"%>
                                </div>
                               
                                 <!-- Panel content -->
                                    <divid="stor_manager-content_box">
                                        <div id="admin_panel-categories">
                                            <div class="col-sm-8" id="admin_panel-categories-items">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th><span>Customer Name</span></th>
                                                            <th><span>Total Cost</span></th>
                                                            <th><span>Date</span></th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${deliveries}" var="order" varStatus="loop">
                                                            <form action="${pageContext.request.contextPath}/storage_management/pendingOrders" method="GET" class="categories-item-form">
                                                                <tr class="categories-item">
                                                                    <td class="vertical-middle">
                                                                        ${order.getCustomer().getFirstName()} ${order.getCustomer().getLastName()} 
                                                                    </td>
                                                                    <td class="vertical-middle">
                                                                        <fmt:formatNumber value="${order.getCart().getTotalCost()}" currencySymbol="" type="currency" /> din
                                                                        ${order.getCart().getTotalCost()}
                                                                    </td>
                                                                    <td>
                                                                        ${order.getCart().getShoppingDate()}
                                                                    </td>
                                                                    <td class="text-right vertical-middle categories-col-edit">
                                                                        <a class="btn btn-primary categories-item-edit_button" href="${pageContext.request.contextPath}/storage_management/sentOrder/${order.getDelivery().getId()}"><span class="glyphicon glyphicon-ok"></span></a>
                                                                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/storage_management/cancelOrder/${order.getDelivery().getId()}"><span class="glyphicon glyphicon-remove"></span></a>
                                                                    </td>
                                                                </tr>
                                                            </form>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div id="categories-pagination">
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
