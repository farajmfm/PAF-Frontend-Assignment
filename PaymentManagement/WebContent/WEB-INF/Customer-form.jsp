<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Customer Payment Details (ElectroGrid)</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Customer Bank Details </a>
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
            			Edit Customer Details
            		</c:if>
						<c:if test="${user == null}">
            			Add Payment Deatils
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Payment Method</label> <input type="text"
						value="<c:out value='${user.paymentMethod}' />" class="form-control"
						name="paymentmethod" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Amount</label> <input type="text"
						value="<c:out value='${user.amount}' />" class="form-control"
						name="amount">
				</fieldset>

				<fieldset class="form-group">
					<label>Bill No</label> <input type="text"
						value="<c:out value='${user.billNo}' />" class="form-control"
						name="billno">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Bank Name</label> <input type="text"
						value="<c:out value='${user.bankName}' />" class="form-control"
						name="bankname">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>