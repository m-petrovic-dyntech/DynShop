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
                                        <div id="login-input_box" style="margin-top: 75px;">
                                            <div class="form-group">
                                                <label for="registration-form-username">Username</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" id="registration-form-username">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">Password</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" id="registration-form-password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">Confirm Password</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="password" class="form-control" placeholder="Confirm Password" aria-describedby="basic-addon1" id="registration-form-confirm_password">
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="form-group">
                                                <label for="registration-form-username">Email</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="email" class="form-control" placeholder="Email" aria-describedby="basic-addon1" id="registration-form-email">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">First Name</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="First Name" aria-describedby="basic-addon1" id="registration-form-first_name">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">Last Name</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="Last Name" aria-describedby="basic-addon1" id="registration-form-last_name">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">City</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="City" aria-describedby="basic-addon1" id="registration-form-city">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">Address</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="Address" aria-describedby="basic-addon1" id="registration-form-address">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="registration-form-username">Phone</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input type="text" class="form-control" placeholder="Phone" aria-describedby="basic-addon1" id="registration-form-phone">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <button class="btn btn-primary" id="registration-form-submit">Register</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                            $(document).ready(function() {

                                $('#registration-form-submit').click(function() {

                                    var tempRegisterObject = {
                                        username: $('#registration-form-username').val(),
                                        password: $('#registration-form-password').val(),
                                        confirmPassword: $('#registration-form-confirm_password').val(),
                                        city: $('#registration-form-city').val(),
                                        address: $('#registration-form-address').val(),
                                        phone: $('#registration-form-phone').val(),
                                        email: $('#registration-form-email').val(),
                                        firstName: $('#registration-form-first_name').val(),
                                        lastName: $('#registration-form-last_name').val()
                                    }


                                    ajaxRegister(tempRegisterObject).done(function(result) {
                                        console.log('DONE');

                                    }).fail(function() {
                                        console.log('FAILED');
                                    });
                                });


                                function ajaxRegister(registerData) {
                                    console.log(registerData);
                                    var data = {
                                            username: registerData.username,
                                            password: registerData.password,
                                            matchingPassword: registerData.matchingPassword,
                                            firstName: registerData.firstName,
                                            lastName: registerData.lastName,
                                            city: registerData.city,
                                            address: registerData.address,
                                            phone: registerData.phone,
                                            email: registerData.email,
                                        };
                                    return $.ajax({
                                    	method:'POST',
                                    	url: '${pageContext.request.contextPath}/registration',
                                    	contentType: "application/json",
                                    	dataType: 'json',
                                    	data: JSON.stringify(data)
                                    });
                                    
                                }

                            });
                            </script>
                        </jsp:attribute>
                    </t:layout>
