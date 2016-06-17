<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!-- Path  -->
            <c:set var="pagePath" value="${fn:split(requestScope['javax.servlet.forward.request_uri'], '/')}" />
            <c:set var="pagePathLength" value="${fn:length(pagePath)}" />
            <span>${pagePath[pagePathLength - 1]} - adadasd</span>
            <!-- Page content -->
            <h4 id="admin_panel-sidebar-header">Admin Control Panel</h4>
            <ul id="admin_panel-sidebar-nav">
                <li class="sidebar-nav_item">
                    <a href="${pageContext.request.contextPath}/admin/panel/products" class="sidebar-nav_item-link ${pagePath[pagePathLength - 1] == 'products' ? 'active' : ''}">
                        <span class="glyphicon glyphicon-th-list"></span> Products
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/panel/categories" class="sidebar-nav_item-link ${pagePath[pagePathLength - 1] == 'categories' ? 'active' : ''}">
                        <span class="glyphicon glyphicon-align-left"></span> Categories
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/panel/users" class="sidebar-nav_item-link ${pagePath[pagePathLength - 1] == 'users' ? 'active' : ''}">
                        <span class="glyphicon glyphicon-user"></span> Users
                    </a>
                </li>
            </ul>
            <div id="admin_panel-sidebar-toggle">
                <span class="glyphicon glyphicon-chevron-left" id="admin_panel-sidebar-toggle-left"></span>
                <span class="glyphicon glyphicon-chevron-right" id="admin_panel-sidebar-toggle-right"></span>
            </div>
            <!-- Script -->
            <script>
            $("#admin_panel-sidebar-toggle").click(function(e) {
                e.preventDefault();
                $(".admin_panel-wrapper").toggleClass("toggled");
            });
            </script>
