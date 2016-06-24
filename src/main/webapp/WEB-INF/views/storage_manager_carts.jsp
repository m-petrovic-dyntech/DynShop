<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Storage Management Carts">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box stor_manager-wrapper" id="stor_manager-categories-main_box">
                                <!-- Sidebar -->
                                <div id="stor_manager-sidebar">
                                    <%@include file="/WEB-INF/views/includes/storage_manager-sidebar.jsp"%>
                                </div>
                                <!-- Panel content -->
                                <div id="stor_manager-content_box">
                                    Store manager Carts
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
