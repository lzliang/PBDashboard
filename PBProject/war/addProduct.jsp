<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Product</title>
</head>
<body>
	<div>
		<form method="POST" action="addProduct.do">
			<table border="0" style="text-align: left" align="center">
				<tr>
					<th>Price ($):&nbsp;</th>
					<td><input type="text" name="price" id="price"
						value="${form.price}" /></td>
				</tr>
				<tr>
					<th>Product Type :&nbsp;</th>
					<td><input type="text" name="type" id="type"
						value="${form.type}" /></td>
				</tr>
				<tr>
					<th>Description :&nbsp;</th>
					<td><input type="text" name="description" id="description"
						value="${form.description}" /></td>
				</tr>
				<tr>
					<td>
						<button type="submit" value="Submit" id="create">Submit</button>
					</td>
					<td>
						<button type="button" value="Reset" id="reset"
							onClick="clearForm(this.form)">Reset</button>
					</td>
				</tr>
			</table>

		</form>
	</div>
	<div>
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>
</body>
</html>