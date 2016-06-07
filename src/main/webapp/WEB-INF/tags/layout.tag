<%@attribute name="title"%>
<%@attribute name="body_area" fragment="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title>${title}</title>

	<%@include file="/WEB-INF/views/includes/header.jsp"%>
</head>
<body>
	<!-- Navigation bar -->
    <header>
    	<%@include file="/WEB-INF/views/includes/nav.jsp"%>
    </header>

	<section>
		<jsp:invoke fragment="body_area" />
	</section>

</body>
</html>