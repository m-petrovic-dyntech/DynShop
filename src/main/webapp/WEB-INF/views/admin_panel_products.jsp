<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
                                        <table class="table table-stripped">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th class="admin_panel-products-col-desc">Description</th>
                                                    <th>Category</th>
                                                    <th>Price</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        Name1
                                                    </td>
                                                    <td>
                                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Veniam dolorem iure magnam enim nobis accusantium laboriosam placeat aperiam, saepe illum pariatur beatae quas, ratione sit obcaecati totam dolores reprehenderit quae!
                                                    </td>
                                                    <td>
                                                        Books
                                                    </td>
                                                    <td>
                                                        123,123.00 din
                                                    </td>
                                                    <td class="admin_panel-products-col-edit">
                                                        <a href="" class="btn btn-primary">
                                                            <span class="glyphicon glyphicon-pencil"></span>
                                                        </a>
                                                    </td>
                                                    <td class="admin_panel-products-col-delete">
                                                        <a href="" class="btn btn-danger">
                                                            <span class="glyphicon glyphicon-trash"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
