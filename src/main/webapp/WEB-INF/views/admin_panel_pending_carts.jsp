<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Admin Panel Pending Carts">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box admin_panel-wrapper" id="admin_panel-categories-main_box">
                                <!-- Sidebar -->
                                <div id="admin_panel-sidebar">
                                    <%@include file="/WEB-INF/views/includes/admin_panel-sidebar.jsp"%>
                                </div>
                                <!-- Panel content -->
                                <div id="admin_panel-content_box">
                                    <div id="admin_panel-categories">
                                        <div class="col-sm-6" id="admin_panel-categories-items">
                                            <table class="table">
                                            	<thead>
                                                    <tr>
                                                        <th><span>Date</span></th>
                                                        <th><span>Total Cost</span></th>
                                                        <th><span>Customer Name</span></th>
                                                        <th></th>
                                                       
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${carts}" var="cart" varStatus="loop">
                                                        <form action="${pageContext.request.contextPath}/admin/panel/editCategory" method="GET" class="categories-item-form">
                                                            <tr class="categories-item ${!category.enabled ? 'categories-item-disabled' : ''}">
                                                                <td class="vertical-middle">
                                                                        ${cart.shoppingDate}
                                                                </td>
                                                                <td class="vertical-middle">
                                                                        ${cart.totalCost}
                                                                </td>
                                                                <td>
                                                                        ${cart.getCustomer().getUsername()}
                                                                </td>
                                                                <td class="text-right vertical-middle categories-col-edit">
                                                                    <a class="btn btn-primary categories-item-edit_button" href="${pageContext.request.contextPath}/admin/panel/changeCartStatus/${cart.id}?status=finished"><span class="glyphicon glyphicon-ok"></span></a>                                                               
                                                               		<a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/panel/changeCartStatus/${cart.id}?status=canceled"><span class="glyphicon glyphicon-remove"></span></a>
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
