<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                            <c:set var="bPagination" value="${pagination.numberOfItems/pagination.pageSize > 1}" />
                                            <!---->
                                            <div class="col-sm-8 ${!bPagination ? 'overflow-auto' : ''}" id="admin_panel-categories-items">
                                                <div class=" ${bPagination ? 'overflow-auto pagination_content-height' : ''}">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th><span>Category name</span></th>
                                                                <th colspan="2" class="categories-col-edit">
                                                                    <select id="admin_panel-categories-page_size_option" class="form-control">
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
                                                            <c:forEach items="${categories}" var="category" varStatus="loop">
                                                                <form action="${pageContext.request.contextPath}/admin/panel/editCategory" method="GET" class="categories-item-form">
                                                                    <tr class="categories-item ${!category.enabled ? 'categories-item-disabled' : ''}">
                                                                        <td class="vertical-middle">
                                                                            <span class="categories-item-name-value">
                                                                                ${category.name}
                                                                            </span>
                                                                            <input type="hidden" name="page" value="${pagination.pageSize < pagination.numberOfItems? pagination.currentPage : ''}" />
                                                                            <input type="hidden" name="size" value="${pagination.pageSize < pagination.numberOfItems? pagination.pageSize : ''}" />
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
                                                                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/panel/deleteCategory/${category.id}${pagination.pageSize < pagination.numberOfItems? '?page=' += pagination.currentPage += '&size=' += pagination.pageSize : ''}"><span class="glyphicon glyphicon-trash"></span></a>
                                                                        </td>
                                                                    </tr>
                                                                </form>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <c:set var="baseUrl" value="admin/panel/categories" />
                                                <c:if test="${bPagination}">
                                                    <!-- Pagination -->
                                                    <!-- Constants -->
                                                    <c:set var="numberVisible" value="9" />
                                                    <c:set var="numberVisibleMinus1" value="${numberVisible - 1}" />
                                                    <fmt:formatNumber value="${Math.ceil(numberVisible/2)}" type="number" var="numberVisibleMiddleParsed" />
                                                    <c:set var="numberVisibleMiddle" value="${numberVisibleMiddleParsed}" />
                                                    <fmt:formatNumber value="${Math.floor(numberVisible/2)}" type="number" var="numberVisibleSideParsed" />
                                                    <c:set var="numberVisibleSide" value="${numberVisibleSideParsed}" />
                                                    <c:set var="numberVisibleMiddleSide" value="${numberVisibleSideParsed - 1}" />
                                                    <!-- Parameters -->
                                                    <fmt:formatNumber value="${Math.ceil(pagination.numberOfItems/pagination.pageSize)}" type="number" var="pageCount" />
                                                    <c:set var="paginationMove" value="${true}" />
                                                    <c:if test="${pageCount - numberVisible > 0}">
                                                        <c:set var="paginationMove" value="${pageCount - pagination.currentPage > numberVisibleMiddleSide}" />
                                                    </c:if>
                                                    <c:set var="paginationBegin" value="${pagination.currentPage > numberVisibleMiddle ? 1 + pagination.currentPage - numberVisibleMiddle : 1}" />
                                                    <c:set var="paginationEndpaginationEnd" value="${pageCount}" />
                                                    <c:set var="paginationEnd" value="${pageCount}" />
                                                    <c:if test="${pageCount - numberVisible > 0}">
                                                        <c:set var="paginationEnd" value="${paginationBegin + numberVisibleMinus1}" />
                                                    </c:if>
                                                    <!---->
                                                    <div class="container-fluid row pagination-height clear">
                                                        <ul class="pagination">
                                                            <!-- Prev -->
                                                            <c:if test="${pagination.currentPage != 1}">
                                                                <li>
                                                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage - 1}&size=${pagination.pageSize}" aria-label="Previous">
                                                                        <span aria-hidden="true">&laquo;</span>
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                            <!-- First page -->
                                                            <c:if test="${pagination.currentPage > numberVisibleMiddle && paginationMove || pagination.currentPage > numberVisibleMiddle}">
                                                                <li>
                                                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=1&size=${pagination.pageSize}">
                                                                        <span aria-hidden="true">1</span>
                                                                    </a>
                                                                </li>
                                                                <li><i class="pagination-separator"> </i></li>
                                                            </c:if>
                                                            <!-- Pages -->
                                                            <c:forEach begin="${paginationMove ? paginationBegin : pageCount - numberVisibleMinus1}" end="${paginationMove ? paginationEnd : pageCount}" varStatus="i">
                                                                <c:set var="activeClass" value="${pagination.currentPage == i.index}" />
                                                                <li class="${activeClass ? 'active' : ''}"><a href="${pageContext.request.contextPath}/${baseUrl}?page=${i.index}&size=${pagination.pageSize}">${i.index}</a></li>
                                                            </c:forEach>
                                                            <!-- Last page -->
                                                            <c:if test="${pageCount - pagination.currentPage > numberVisibleSide && pageCount - numberVisible > 0}">
                                                                <li><i class="pagination-separator"> </i></li>
                                                                <li>
                                                                    <a href="${pageContext.request.contextPath}/${baseUrl}?page=${pageCount}&size=${pagination.pageSize}">
                                                                        <span aria-hidden="true">${pageCount}</span>
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                            <!-- Next -->
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
                                            <div class="col-sm-4">
                                                <div class="col-sm-10 row">
                                                    <h4>Add Category</h4>
                                                    <form action="${pageContext.request.contextPath}/admin/panel/addCategory" method="POST" class="categories-item-form">
                                                        <div class="input-group">
                                                            <input type="hidden" value="true" name="enabled" />
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                            <input type="hidden" name="size" value="${pagination.pageSize < pagination.numberOfItems? pagination.pageSize : ''}" />
                                                            <input type="hidden" name="page" value="${pagination.pageSize < pagination.numberOfItems? pagination.currentPage : ''}" />
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
                                    /*----------  Edit category  ----------*/

                                    $('.categories-item-edit_button').click(function() {
                                        $('.categories-item-edit_button-edited').removeClass('categories-item-edit_button-edited');
                                        $(this).closest('.categories-item').addClass('categories-item-edit_button-edited');
                                    });
                                    $('.categories-item-edit-no').click(function() {
                                        $('.categories-item-edit_button-edited').removeClass('categories-item-edit_button-edited');
                                    });
                                    /*----------  Choose page size  ----------*/
                                    $('#admin_panel-categories-page_size_option').change(function() {
                                        var tempSelectValue = $(this).val();
                                        var tempLink = '${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage}&size=' + tempSelectValue;
                                        window.location = tempLink;
                                    })
                                });
                                </script>
                            </jsp:attribute>
                        </t:layout>
