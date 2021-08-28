<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="/pttk/list" class="navbar-brand"> User Management App </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${user != null}">
 							Edit User
 						</c:if>
						<c:if test="${user == null}">
 							Add New User
 						</c:if>
					</h2>
				</caption>
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="${user.id}" />
				</c:if>
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="${user.name}" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="${user.email}" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="${user.country}" class="form-control"
						name="country">
				</fieldset>
				<fieldset class="form-group">
					<label>User Gender</label>
					<c:choose>
						<c:when test="${user == null }">
							<select name="gender" class="form-control">
								<option value="Nam">Nam</option>
								<option value="Nữ">Nữ</option>
								<option value="Khác">Khác</option>
							</select>
						</c:when>
						<c:when test="${user.gender == 'Nam' }">
							<select name="gender" class="form-control">
								<option value="Nam" selected>Nam</option>
								<option value="Nữ">Nữ</option>
								<option value="Khác">Khác</option>
							</select>
						</c:when>
						<c:when test="${user.gender == 'Nữ' }">
							<select name="gender" class="form-control">
								<option value="Nam">Nam</option>
								<option value="Nữ" selected>Nữ</option>
								<option value="Khác">Khác</option>
							</select>
						</c:when>
						<c:when test="${user.gender == 'Khác' }">
							<select name="gender" class="form-control">
								<option value="Nam">Nam</option>
								<option value="Nữ">Nữ</option>
								<option value="Khác" selected>Khác</option>
							</select>
						</c:when>
					</c:choose>
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>