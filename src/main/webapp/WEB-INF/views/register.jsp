<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Register">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid" id="register-main_box">
                                <div class="container">
                                    <div class="col-md-4 col-md-offset-4">
                                        <form:form method="POST" action="/register">
                                            <div id="register-input_box">
                                                <div class="form-group" id="register-input_box-username_box">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                        <form:input path="" class="form-control" placeholder="Username" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                        <form:password path="" class="form-control" placeholder="Password" />
                                                    </div>
                                                </div>
                                                <div class="form-group text-center">
                                                    <input type="submit" class="btn btn-primary" id="register-register_button" value="Register" />
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
