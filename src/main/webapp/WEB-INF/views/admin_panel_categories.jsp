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
                                    <div id="admin_panel-categories">
                                        <div class="col-sm-6" id="admin_panel-categories-items">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th><span>Category name</span></th>
                                                        <th class="categories-col-edit"></th>
                                                        <th class="categories-col-remove"></th>
                                                    </tr>
                                                </thead>
                                            </table>
                                            <table class="table">
                                                <tbody>
                                                    <c:forEach items="${categories.getPageList()}" var="category" varStatus="loop">
                                                        <form action="${pageContext.request.contextPath}/admin/panel/editCategory" method="GET" class="categories-item-form">
                                                            <tr class="categories-item ${!category.enabled ? 'categories-item-disabled' : ''}">
                                                                <td class="vertical-middle">
                                                                    <span class="categories-item-name-value">
                                                                        ${category.name}
                                                                    </span>
                                                                    <input type="hidden" value="${category.id}" name="id" />
                                                                    <input type="hidden" value="${category.enabled}" name="enabled" />
                                                                    <!-- **** -->
                                                                    <input type="text" class="form-control categories-item-name-input" value="${category.name}" name="name" />
                                                                </td>
                                                                <td class="text-right vertical-middle categories-col-edit">
                                                                    <a class="btn btn-primary categories-item-edit_button"><span class="glyphicon glyphicon-pencil"></span></a>
                                                                    <button type="submit" class="btn btn-success categories-item-edit-yes"><span class="glyphicon glyphicon-ok"></span></button>
                                                                    <a class="btn btn-danger categories-item-edit-no"><span class="glyphicon glyphicon-remove"></span></a>
                                                                </td>
                                                                <td class="vertical-middle categories-col-remove">
                                                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/panel/deleteCategory/${category.id}"><span class="glyphicon glyphicon-trash"></span></a>
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-6">
                                                <h4>Add Category</h4>
                                                <form id="product" action="${pageContext.request.contextPath}/admin/panel/addCategory" method="GET" class="categories-item-form">
                                                    <div class="input-group">
                                                        <input type="hidden" value="true" name="enabled" />
                                                        <input type="text" name="name" class="form-control" placeholder="Category Name">
                                                        <span class="input-group-btn">
                                                        <button type="submit" class="btn btn-success glyphicon glyphicon-ok" id="categories-add-button"></button>
                                                    </span>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('.categories-item-edit_button').click(function() {
                                    $('.categories-item-edit_button-edited').removeClass('categories-item-edit_button-edited');
                                    $(this).closest('.categories-item').addClass('categories-item-edit_button-edited');
                                });
                                $('.categories-item-edit-no').click(function() {
                                    $('.categories-item-edit_button-edited').removeClass('categories-item-edit_button-edited');
                                });
                            });
                            </script>
                        </jsp:attribute>
                    </t:layout>
