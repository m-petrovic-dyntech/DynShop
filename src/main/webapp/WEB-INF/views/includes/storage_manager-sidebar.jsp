<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!-- Path  -->
            <c:set var="pagePath" value="${fn:split(requestScope['javax.servlet.forward.request_uri'], '/')}" />
            <c:set var="pagePathLength" value="${fn:length(pagePath)}" />
            <!-- Page content -->
            <h4 id="stor_management-sidebar-header">Admin Control Panel</h4>
            <ul id="stor_management-sidebar-nav">
                <li class="sidebar-nav_item">
                    <a href="${pageContext.request.contextPath}/storage_management/orders" class="sidebar-nav_item-link ${pagePath[pagePathLength - 1] == 'orders' ? 'active' : ''}">
                        <span class="glyphicon glyphicon-th-list"></span> Orders
                    </a>
                </li>
            </ul>
            <div id="stor_management-sidebar-toggle">
                <span class="glyphicon glyphicon-chevron-left" id="stor_management-sidebar-toggle-left"></span>
                <span class="glyphicon glyphicon-chevron-right" id="stor_management-sidebar-toggle-right"></span>
            </div>
            <!-- Script -->
            <script>
            $("#stor_management-sidebar-toggle").click(function(e) {
                e.preventDefault();
                $(".stor_management-wrapper").toggleClass("toggled");
            });
            </script>
