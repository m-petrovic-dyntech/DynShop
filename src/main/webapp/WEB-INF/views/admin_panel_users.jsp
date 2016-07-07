<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div id="admin_panel-content_box" class="content_box-admin_panel-users">
                <c:set var="bPagination" value="${pagination.numberOfItems/pagination.pageSize > 1}" />
                <div id="admin_panel-customers" class="overflow-auto ${bPagination ? 'pagination_content-height' : 'height-100'}">
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
                            <c:forEach items="${customers}" var="customer" varStatus="loop">
                                <tr class="customers-item ${!customer.enabled ? 'customers-item-disabled' : ''}">
                                    <td>
                                        <input type="hidden" value="${customer.id}" class="customers-item-id">
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
                                        <div class="d-confirm_popup">
                                            <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenu${loop.index}" data-toggle="dropdown" title="Edit" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-trash"></span></button>
                                            <div class="dropdown-menu d-confirm_popup-popup" aria-labelledby="dropdownMenu${loop.index}">
                                                <button class="btn btn-danger" class="admin_panel-products-delete_popup-close"><span class="glyphicon glyphicon-remove"></span></button>
                                                <a href="${pageContext.request.contextPath}/admin/panel/deleteCustomer/${customer.id}${pagination.pageSize < pagination.numberOfItems? ( '?page=' += pagination.currentPage += '&size=' += pagination.pageSize) : ''}" class="btn btn-success" type="button"><span class="glyphicon glyphicon-ok"></span></a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%-- Pagination --%> 
                <c:set var="baseUrl" value="admin/panel/users" />
                <c:if test="${bPagination}">
                    <%-- Constants --%> 
                    <c:set var="numberVisible" value="9" />
                    <c:set var="numberVisibleMinus1" value="${numberVisible - 1}" />
                    <fmt:formatNumber value="${Math.ceil(numberVisible/2)}" type="number" var="numberVisibleMiddleParsed" />
                    <c:set var="numberVisibleMiddle" value="${numberVisibleMiddleParsed}" />
                    <fmt:formatNumber value="${Math.floor(numberVisible/2)}" type="number" var="numberVisibleSideParsed" />
                    <c:set var="numberVisibleSide" value="${numberVisibleSideParsed}" />
                    <c:set var="numberVisibleMiddleSide" value="${numberVisibleSideParsed - 1}" />
                    <%-- Parameters --%> 
                    <fmt:formatNumber value="${Math.ceil(pagination.numberOfItems/pagination.pageSize)}" type="number" var="pageCount" />
                    <c:set var="paginationMove" value="${true}" />
                    <c:if test="${pageCount - numberVisible > 0}">
                        <c:set var="paginationMove" value="${pageCount - numberVisible > numberVisibleMiddleSide}" />
                    </c:if>
                    <c:set var="paginationBegin" value="${pagination.currentPage > numberVisibleMiddle ? 1 + pagination.currentPage - numberVisibleMiddle : 1}" />
                    <c:set var="paginationEndpaginationEnd" value="${pageCount}" />
                    <c:set var="paginationEnd" value="${pageCount}" />
                    <c:if test="${pageCount - numberVisible > 0}">
                        <c:set var="paginationEnd" value="${paginationBegin + numberVisibleMinus1}" />
                    </c:if>
                    <%----%> 
                    <div class="container-fluid pagination-height clear">
                        <ul class="pagination">
                            <%-- Prev --%> 
                            <c:if test="${pagination.currentPage != 1}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage - 1}&size=${pagination.pageSize}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <%-- First page --%> 
                            <c:if test="${pagination.currentPage > numberVisibleMiddle && paginationMove || pagination.currentPage > numberVisibleMiddle}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=1&size=${pagination.pageSize}">
                                        <span aria-hidden="true">1</span>
                                    </a>
                                </li>
                                <li><i class="pagination-separator"> </i></li>
                            </c:if>
                            <%-- Pages --%> 
                            <c:forEach begin="${paginationMove ? paginationBegin : pageCount - numberVisibleMinus1}" end="${paginationMove ? paginationEnd : pageCount}" varStatus="i">
                                <c:set var="activeClass" value="${pagination.currentPage == i.index}" />
                                <li class="${activeClass ? 'active' : ''}"><a href="${pageContext.request.contextPath}/${baseUrl}?page=${i.index}&size=${pagination.pageSize}">${i.index}</a></li>
                            </c:forEach>
                            <%-- Last page --%> 
                            <c:if test="${pageCount - pagination.currentPage > numberVisibleSide && pageCount - numberVisible > 0}">
                                <li><i class="pagination-separator"> </i></li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=${pageCount}&size=${pagination.pageSize}">
                                        <span aria-hidden="true">${pageCount}</span>
                                    </a>
                                </li>
                            </c:if>
                            <%-- Next --%> 
                            <c:if test="${pagination.currentPage != pageCount}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage + 1}&size=${pagination.pageSize}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </c:if>
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
                        <form action="${pageContext.request.contextPath}/admin/panel/editCustomer" method="GET" id="customers-edit_panel-form">
                            <input type="hidden" id="customers-edit_panel-id" name="id">
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
                $(tempPanel).find('#customers-edit_panel-id').val($(tempParent).find('.customers-item-id').val());

                var tempRoles = $(tempParent).find('.customers-role_name');
                var tempRolesLength = tempRoles.length;

                $('#customers-edit_panel-roles').multiselect('deselectAll', false);
                // 
                for (var i = 0; i < tempRolesLength; i++) {
                    $(tempPanel).find('#customers-edit_panel-roles').multiselect('select', $(tempRoles[i]).attr('role-name'));
                }
                
                if (JSON.parse($(tempParent).find('.customers-item-enabled').val())) {
                    $('#customers-edit_panel-status-enable').prop('checked', true);
                } else {
                    $('#customers-edit_panel-status-disable').prop('checked', true);
                }
            });

            $('#admin_panel-customers-page_size_option').change(function() {
                var tempSelectValue = $(this).val();
                var tempLink = '${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage}&size=' + tempSelectValue;
                window.location = tempLink;
            })


            /*----------  Multiselect plugin init  ----------*/
            $('#customers-edit_panel-roles').multiselect({
                numberDisplayed: 1
            });
        });
        </script>
    </jsp:attribute>
</t:layout>
