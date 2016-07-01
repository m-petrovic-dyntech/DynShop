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
                                            <th>Name</th>
                                            <th>Username</th>
                                            <th>City</th>
                                            <th>Addres</th>
                                            <th>Phone</th>
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
                                                    <input type="hidden" value="${customer.enabled}" class="customers-item-enabled">
                                                    <span class="customers-item-first_name">${customer.firstName}</span>
                                                    <span class="customers-item-last_name">${customer.lastName}</span>
                                                </td>
                                                <td class="customers-item-username">${customer.username}</td>
                                                <td class="customers-item-city">${customer.city}</td>
                                                <td class="customers-item-address">${customer.address}</td>
                                                <td class="customers-item-phone">${customer.phone}</td>
                                                <td>
                                                    <c:forEach items="${customer.getRoles()}" var="customerRole">
                                                        <span class="customers-role_name" role-name="${customerRole.role}">${customerRole}</span>
                                                    </c:forEach>
                                                </td>
                                                <td class="text-right customers-column-edit">
                                                    <button type="button" class="btn btn-primary customers-item-edit_button" data-toggle="modal" data-target="#customers-edit_panel">
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
                    <div class="modal fade" id="customers-edit_panel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Edit User</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/admin/panel/editCustomer" method="POST" id="customers-edit_panel-form">
                                        <div class="container-fluid row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-first_name">First Name</label>
                                                    <input type="text" class="form-control" id="customers-edit_panel-first_name" name="firstName" placeholder="First Name">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-last_name">Last Name</label>
                                                    <input type="text" class="form-control" id="customers-edit_panel-last_name" name="lastName" placeholder="Last Name">
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="container-fluid row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-city">City</label>
                                                    <input type="text" class="form-control" id="customers-edit_panel-city" name="city" placeholder="City">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-address">Address</label>
                                                    <input type="text" class="form-control" id="customers-edit_panel-address" name="address" placeholder="Address">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-phone">Phone</label>
                                                    <input type="text" class="form-control" id="customers-edit_panel-phone" name="phone" placeholder="Phone">
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="container-fluid row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="customers-edit_panel-password">Password</label>
                                                    <input type="password" class="form-control" id="customers-edit_panel-password" name="password" placeholder="Password">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <div class="container-fluid row">
                                                        <label for="customers-edit_panel-password">Roles</label>
                                                    </div>
                                                    <select name="customer_roles" id="customers-edit_panel-roles" multiple="multiple">
                                                        <c:forEach items="${roles}" var="role">
                                                            <option value="${role.role}">${role}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>User status</label>
                                                    <div class="container-fluid row">
                                                        <span class="customers-edit_panel-status"> 
                                                        <input type="radio" name="enabled" value="true" id="customers-edit_panel-status-enable"/><br/><label for="customers-edit_panel-status-enable">Enable</label>
                                                    </span>
                                                        <span class="customers-edit_panel-status"> 
                                                        <input type="radio" name="enabled" value="false" id="customers-edit_panel-status-disable"/><br/><label for="customers-edit_panel-status-disable">Disable</label>
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" id="customers-edit_panel-cancel">Close</button>
                                    <button type="submit" class="btn btn-primary" form="customers-edit_panel-form">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- JavaScript -->
                    <script type="text/javascript">
                    $(document).ready(function() {
                        $('.customers-item-edit_button').click(function() {
                            var tempParent = $(this).closest('.customers-item');

                            var tempPanel = $('#customers-edit_panel');

                            $(tempPanel).find('#customers-edit_panel-first_name').val($(tempParent).find('.customers-item-first_name').text());
                            $(tempPanel).find('#customers-edit_panel-last_name').val($(tempParent).find('.customers-item-last_name').text());
                            $(tempPanel).find('#customers-edit_panel-city').val($(tempParent).find('.customers-item-city').text());
                            $(tempPanel).find('#customers-edit_panel-address').val($(tempParent).find('.customers-item-address').text());
                            $(tempPanel).find('#customers-edit_panel-phone').val($(tempParent).find('.customers-item-phone').text());

                            var tempRoles = $(tempParent).find('.customers-role_name');
                            var tempRolesLength = tempRoles.length;

                            $('#customers-edit_panel-roles').multiselect('deselectAll', false);
                            // 
                            for (var i = 0; i < tempRolesLength; i++) {
                                $(tempPanel).find('#customers-edit_panel-roles').multiselect('select', $(tempRoles[i]).attr('role-name'));
                            }
                            console.log($(tempParent).find('.customers-item-enabled').val())
                            if (JSON.parse($(tempParent).find('.customers-item-enabled').val())) {
                                $('#customers-edit_panel-status-enable').prop('checked', true);
                            } else {
                                $('#customers-edit_panel-status-disable').prop('checked', true);
                            }
                        });

                        /*----------  Multiselect plugin init  ----------*/
                        $('#customers-edit_panel-roles').multiselect({
                            numberDisplayed: 1
                        });
                    });
                    </script>
                </jsp:attribute>
            </t:layout>
