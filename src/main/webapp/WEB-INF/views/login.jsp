<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
            <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                    <!-- Content -->
                    <t:layout title="DynTech Shop | Login">
                        <jsp:attribute name="body_area">
                            <div class="container-fluid" id="login-main_box">
                                <div class="container">
                                    <div class="col-md-4 col-md-offset-4">
                                        <c:url value='/j_spring_security_check' var='loginPath' />
                                        <form:form action="${loginPath}" modelAttribute="customer" method="POST">
                                            <div id="login-input_box">
                                                <div class="form-group" id="login-input_box-username_box">
                                                    <div class="input-group">
                                                        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                        <form:input path="username" class="form-control" placeholder="Username" aria-describedby="basic-addon1"></form:input>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
                                                        <form:password path="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1"></form:password>
                                                    </div>
                                                </div>
                                                <div class="form-group text-center">
                                                    <input type="submit" class="btn btn-primary" id="login-login_button" value="Sign in" />
                                                </div>
                                                <div id="login-input_box-register">
                                                    <a href="${pageContext.request.contextPath}/userRegister">Don't have account? Register here.</a>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:layout>
