<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                <%@ page contentType="text/html; charset=UTF-8" %>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Admin Panel Products">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box admin_panel-wrapper" id="admin_panel-products-main_box">
                                <!-- Sidebar -->
                                <div id="admin_panel-sidebar">
                                    <%@include file="/WEB-INF/views/includes/admin_panel-sidebar.jsp"%>
                                </div>
                                <!-- Panel content -->
                                <div id="admin_panel-content_box">
                                    <h2>Products</h2>
                                    <div id="admin_panel-products">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th class="admin_panel-products-col-desc">Description</th>
                                                    <th class="text-center">Category</th>
                                                    <th class="text-center">Price</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${products}" var="product" varStatus="loop">
                                                    <tr class="${!product.enabled ? 'admin_panel-product-disabled' : ''}">
                                                        <td class=" vertical-middle">
                                                            ${product.name}
                                                        </td>
                                                        <td>
                                                            ${fn:substring(product.description, 0, 120)}${product.description.length() > 120 ? '...' : ''}
                                                        </td>
                                                        <td class="text-center vertical-middle">
                                                            ${product.category.name}
                                                        </td>
                                                        <td class="text-center vertical-middle">
                                                            <fmt:formatNumber value="${product.price} " currencySymbol="" type="currency" /> din
                                                        </td>
                                                        <td class="admin_panel-products-col-edit">
                                                            <a class="btn btn-primary" data-toggle="tooltip" title="Edit">
                                                                <span class="glyphicon glyphicon-pencil"></span>
                                                            </a>
                                                        </td>
                                                        <td class="admin_panel-products-col-delete">
                                                            <div class="admin_panel-products-delete_box">
                                                                <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenu${loop.index}" data-toggle="dropdown" title="Edit" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-trash"></span></button>
                                                                <div class="dropdown-menu admin_panel-products-delete_popup" aria-labelledby="dropdownMenu${loop.index}">
                                                                    <button class="btn btn-danger" class="admin_panel-products-delete_popup-close"><span class="glyphicon glyphicon-remove"></span></button>
                                                                    <a href="${pageContext.request.contextPath}/admin/panel/deleteProduct/${product.id}" class="btn btn-success" type="button"><span class="glyphicon glyphicon-ok"></span></a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- JS -->
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('[data-toggle="tooltip"]').tooltip();
                                $('.admin_panel-products-delete_popup-close').modal('hide');
                            });
                            </script>
                        </jsp:attribute>
                    </t:layout>
