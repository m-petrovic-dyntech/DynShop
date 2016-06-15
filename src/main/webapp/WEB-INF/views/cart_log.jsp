<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
                    <%@ page contentType="text/html; charset=UTF-8" %>
                        <!-- Content -->
                        <t:layout title="DynTech Shop | Products">
                            <jsp:attribute name="body_area">
                               ${carts.get(0).getItems().get(0).getProduct().getName()}
                            </jsp:attribute>
                        </t:layout>
