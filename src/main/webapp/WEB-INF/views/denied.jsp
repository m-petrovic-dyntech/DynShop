<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Denied">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid page-main_box" id="login-main_box">
                                <div class="container">
                                    Dear ${user}, you do not have permission to access this page!
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
