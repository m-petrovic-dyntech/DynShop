<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <%@ page contentType="text/html; charset=UTF-8" %>
            <!-- Content -->
            <t:layout title="DynTech Shop | Admin Panel Users">
                <jsp:attribute name="body_area">
                    <div class="container-fluid page-main_box admin_panel-wrapper" id="admin_panel-users-main_box">
                        <!-- Sidebar -->
                        <div id="admin_panel-sidebar">
                            <%@include file="/WEB-INF/views/includes/admin_panel-sidebar.jsp"%>
                        </div>
                        <!-- Panel content -->
                        <div id="admin_panel-content_box">
                            <div id="admin_panel-customers" class="height-100 overflow-auto">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Username</th>
                                            <th>Role</th>
                                            <th colspan="2">
                                                <select id="admin_panel-customers-page_size_option" class="form-control">
                                                    <option value="" ${pagination.pageSize==null? 'selected' : ''}></option>
                                                    <option value="5" ${pagination.pageSize==5 ? 'selected' : ''}>5</option>
                                                    <option value="10" ${pagination.pageSize==10 ? 'selected' : ''}>10</option>
                                                    <option value="15" ${pagination.pageSize==15 ? 'selected' : ''}>15</option>
                                                    <option value="20" ${pagination.pageSize==20 ? 'selected' : ''}>20</option>
                                                    <option value="30" ${pagination.pageSize==30 ? 'selected' : ''}>30</option>
                                                </select>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${customers}" var="customer">
                                            <tr class="customers-item">
                                                <td>
                                                    <span class="customer-item-username-value">${customer.firstName} ${customer.lastName}</span>
                                                </td>
                                                <td>
                                                    <c:forEach items="${customer.getRoles()}" var="customerRole">
                                                        <span class="customers-role_name">${customerRole}</span>
                                                    </c:forEach>
                                                </td>
                                                <td class="text-right customers-column-edit">
                                                    <button type="button" class="btn btn-primary customers-item-edit_button" data-toggle="modal" data-target="#editUserModal">
                                                        <span class="glyphicon glyphicon-pencil"></span>
                                                    </button>
                                                </td>
                                                <td class="text-right customers-column-delete">
                                                    <button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- Edit customers modal -->
                    <div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- JavaScript -->
                    <script type="text/javascript">
                    $(document).ready(function() {
                        $('.customers-item-edit_button').click(function() {
                            cosnole.log(123)
                        });
                    });
                    </script>
                </jsp:attribute>
            </t:layout>
