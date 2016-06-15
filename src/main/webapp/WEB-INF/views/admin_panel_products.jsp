<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <%@ page contentType="text/html; charset=UTF-8" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                                <div id="admin_panel-products">
                                    <table class="table table-striped">
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
                                            <c:forEach items="${products}" var="product">
                                                <tr>
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
                                                        ${product.price} din
                                                    </td>
                                                    <td class="admin_panel-products-col-edit">
                                                        <a href="" class="btn btn-primary" data-toggle="tooltip" title="Edit">
                                                            <span class="glyphicon glyphicon-pencil"></span>
                                                        </a>
                                                    </td>
                                                    <td class="admin_panel-products-col-delete">
                                                        <a href="" class="btn btn-danger" data-toggle="tooltip" title="Delete">
                                                            <span class="glyphicon glyphicon-trash"></span>
                                                        </a>
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
                        });
                        </script>
                    </jsp:attribute>
                </t:layout>
