<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Admin Panel Categories">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box admin_panel-wrapper" id="admin_panel-categories-main_box">
                                <!-- Sidebar -->
                                <div id="admin_panel-sidebar">
                                    <%@include file="/WEB-INF/views/includes/admin_panel-sidebar.jsp"%>
                                </div>
                                <!-- Panel content -->
                                <div id="admin_panel-content_box">
                                    <h2>Categories</h2>
                                    <div id="admin_panel-categories">
                                        <div class="col-sm-7 row">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Category name</th>
                                                        <th class="categories-col-edit"></th>
                                                        <th class="categories-col-remove"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${categories}" var="category" varStatus="loop">
                                                        <tr>
                                                            <td class="vertical-middle">
                                                                <span class="categories-item-name-value">
                                                                    ${category.name}
                                                                </span>
                                                                <form action="${pageContext.request.contextPath}/admin/panel/editCategory">
                                                                    <input type="hidden" value="${category.id}" name="id" />
                                                                    <input type="hidden" value="${category.enabled}" name="enabled" />
                                                                    <!-- **** -->
                                                                    <input type="text" value="${category.name}" name="name" />
                                                                </form>
                                                            </td>
                                                            <td>
                                                                <a class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span></a>
                                                            </td>
                                                            <td>
                                                                <a class="btn btn-danger"><span class="glyphicon glyphicon-trash "></span></a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- <div class="col-md-3 ">
                                            <div class="admin_panel-categories-item clearfix ${!category.enabled ? 'removed' : ''} ">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <span class="admin_panel-categories-item-value ">${category.name}</span>
                                                            <button class="btn btn-default dropdown-toggle " type="button " id="category_edit_input${loop.index} " data-toggle="dropdown " aria-haspopup="true " aria-expanded="false "><span class="glyphicon glyphicon-pencil "></span></button>
                                                            <div class="dropdown-menu products-item-cart-popup " aria-labelledby="category_edit_input${loop.index} ">
                                                                <div class="input-group ">
                                                                    <form action="${pageContext.request.contextPath}/admin/panel/editCategory " method="GET ">
                                                                        <input type="text " class="form-control " placeholder="Quantity " value="${category.name} ">
                                                                        <span class="input-group-btn ">
                                                                        <button class="btn btn-success " type="submit "><span class="glyphicon glyphicon-ok "></span></button>
                                                                        </span>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="admin_panel-categories-item-remove_icon ">
                                                            <div class="admin_panel-categories-item-remove_icon-box ">
                                                                <a class="btn btn-danger dropdown-toggle " type="button " id="dropdownMenu1 " data-toggle="dropdown " title="Edit " aria-haspopup="true " aria-expanded="false "><span class="glyphicon glyphicon-remove "></span></a>
                                                                <div class="dropdown-menu categories-item-remove_icon-popup " aria-labelledby="dropdownMenu1 ">
                                                                    <button class="btn btn-danger " class="categories-item-remove_icon-popup-close "><span class="glyphicon glyphicon-remove "></span></button>
                                                                    <a href="${pageContext.request.contextPath}/admin/panel/deleteCategory/${category.id} " class="btn btn-success " type="button ">
                                                                        <span class="glyphicon glyphicon-ok "></span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div> -->
                                    </div>
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
