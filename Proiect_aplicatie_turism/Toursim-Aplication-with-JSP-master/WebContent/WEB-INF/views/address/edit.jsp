<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotels Page</title>
	<script
	  src="https://code.jquery.com/jquery-3.2.1.min.js"
	  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" />" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />" />
</head>
<script type="text/javascript">

<% if (session.getAttribute("error") != null) { %>

	$(function () { 
		toastr.error('<%= session.getAttribute("error") %>', 'Error!');
	});
<% } %>
    
</script>
<body>
     <jsp:include page="../_menu.jsp"></jsp:include>
	
	<%	
			// spunei browserului sa nu memoreze nimic in cache, sa rezolvi problema cu back
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache"); // same thing, but for HTTP 1.0
			 response.setHeader("Expires", "0");//Proxies
			if(session.getAttribute("username") == null) {
				response.sendRedirect("loginView.jsp");
			}
			 
	%>
	
	<div class="container">
      <h3>Address : ${ address.getName() }</h3>
      <form action="?action=update&id=${ address.getId() }" method="POST">
		  <div class="form-group">
		    <label for="email">Name:</label>
		    <input type="text" class="form-control" id="email" value="${address.getName()}" name="name">
		  </div>
		  
		  <div class="form-group">
		    <label for="email">Postal code:</label>
		    <input type="text" class="form-control" id="email" value="${address.getPostal_code()}" name="postalCode">
		  </div>
		  <div class="form-group">
		    <label for="email">Longitude:</label>
		    <input type="text" class="form-control" id="email" value="${address.getLongitude()}" name="longitude">
		  </div>
		  <div class="form-group">
		    <label for="email">Latitude:</label>
		    <input type="text" class="form-control" id="email" value="${address.getLatitude()}" name="latitude">
		  </div>
		  
		  <div class="form-group">
		    <label for="type">City:</label>
			<select class="form-control" id="type" name="city_id">
				<c:forEach items="${cities}" var="city">
					<option value="${city.getId()}" ${address.getCity_id() == city.getId() ? 'selected' : ''}>
						${city.getName()}
					</option>
				</c:forEach>
			</select>
		  </div>
		  
		  
		  <button type="submit" class="btn btn-default">Save</button>
		</form>
    </div>

</body>
</html>