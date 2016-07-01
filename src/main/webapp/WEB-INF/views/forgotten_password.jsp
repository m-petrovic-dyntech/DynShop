<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Content -->
<t:layout title="DynTech Shop | Forgotten Password">
    <jsp:attribute name="body_area">
        <div class="container-fluid" id="register-main_box">
            <div class="container">
                <div class="col-md-4 col-md-offset-4">
                    <div id="login-input_box" style="margin-top: 75px;">
                        <form action="${pageContext.request.contextPath}/changeForgottenPassword" method="POST">
                            <div class="form-group">
                                <label for="forgotten_password-form-username">New password</label>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                    <input type="password" class="form-control" name="password" placeholder="Password" aria-describedby="basic-addon1" id="forgotten_password-form-password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="forgotten_password-form-confirm_password">Confirm password</label>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                    <input type="password" class="form-control" name="matchingPassword" placeholder="Confirm password" aria-describedby="basic-addon1" id="forgotten_password-form-confirm_password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="forgotten_password-form-email">Email</label>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                    <input type="email" class="form-control" name="email" placeholder="Email" aria-describedby="basic-addon1" id="forgotten_password-form-email">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Change password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>
