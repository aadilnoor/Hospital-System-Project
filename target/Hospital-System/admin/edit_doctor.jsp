<%@page import="com.dao.DoctorDao"%>
<%@page import="com.entity.Specialist"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.database.DBconnection"%>
<%@page import="com.dao.SpecialistDao"%>
<%@page import="com.entity.*"%>
<%@page import="com.dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../component/allcss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>


</head>
<body>

	<%@include file="navbar.jsp"%>

	<div class="container-fluid p-3">
		<div class="row">
			<!-- Left side: Add Doctor -->
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Doctor Details</p>

						<c:if test="${not empty errorMsg}">
							<p class="fs-3 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<c:if test="${not empty succMsg}">
							<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
							<c:remove var="succMsg" scope="session" />
						</c:if>


						<%
						int id = Integer.parseInt(request.getParameter("id"));
						DoctorDao dao2 = new DoctorDao(DBconnection.getConn());
						Doctor doctor = dao2.getDoctorById(id);
						%>

						<form action="../updateDoctor" method="post">
							<!-- Your form fields go here -->
							<div class="mb-3">

								<label class="form-Label"> Full Name</label> <input type="text"
									value="<%=doctor.getFullName()%>" required name="fullname"
									class="form-control">

							</div>

							<div class="mb-3">

								<label class="form-Label">DOB</label> <input type="date"
									value="<%=doctor.getDob()%>" required name="dob"
									class="form-control">

							</div>

							<div class="mb-3">

								<label class="form-Label">Qualification</label> <input required
									value="<%=doctor.getQualification()%>" name="qualification"
									type="text" class="form-control">

							</div>

							<div class="mb-3">

								<label class="form-Label">Specialist</label> <select name="spec"
									required class="form-control">
									<option><%=doctor.getSpecialist()%></option>
									
									<%
									SpecialistDao dao = new SpecialistDao(DBconnection.getConn());
									List<Specialist> list = dao.getAllSpecialist();

									for (Specialist s : list) {
									%>
									<option><%=s.getSpecialistName()%></option>

									<%
									}
									%>

								</select>

							</div>
							<div class="mb-3">

								<label class="form-Label">Email</label> <input type="text"
									value="<%=doctor.getEmail()%>" required name="email"
									class="form-control">

							</div>

							<div class="mb-3">

								<label class="form-Label"> Mob No</label> <input type="text"
									value="<%=doctor.getMobNo()%>" required name="mobno"
									class="form-control">

							</div>

							<div class="mb-3">

								<label class="form-Label ">Password</label> <input required
									value="<%=doctor.getPassword()%>" name="password" type="text"
									class="form-control">

							</div>
							<input type="hidden" name="id" value = "<%=doctor.getId()%>">

							<button type="submit" class="btn btn-primary col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>