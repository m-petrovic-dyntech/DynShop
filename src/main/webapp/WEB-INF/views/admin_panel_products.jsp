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
                                <div id="admin_panel-content_box" class="content_box-admin_panel_products">
                                    <c:set var="bPagination" value="${pagination.numberOfItems/pagination.pageSize > 1}" />
                                    <div id="admin_panel-products" class="overflow-auto ${bPagination ? 'pagination_content-height' : 'height-100'}">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th class="admin_panel-products-col-desc">Description</th>
                                                    <th class="text-center">Category</th>
                                                    <th class="text-center">Price</th>
                                                    <th colspan="2">
                                                        <select id="admin_panel-products-page_size_option" class="form-control">
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
                                    <c:set var="baseUrl" value="admin/panel/products" />
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
                                        <div class="container-fluid pagination-height clear">
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
                            </div>
                            <!-- JS -->
                            <script type="text/javascript">
                            $(document).ready(function() {
                                $('[data-toggle="tooltip"]').tooltip();
                                $('.admin_panel-products-delete_popup-close').modal('hide');
                                /*----------  Choose page size  ----------*/
                                $('#admin_panel-products-page_size_option').change(function() {
                                    var tempSelectValue = $(this).val();
                                    var tempLink = '${pageContext.request.contextPath}/${baseUrl}?page=${pagination.currentPage}&size=' + tempSelectValue;
                                    window.location = tempLink;
                                })
                            });
                            </script>
                        </jsp:attribute>
                    </t:layout>
