package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDao;
import com.database.DBconnection;
import com.entity.Appointment;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int userId = Integer.parseInt(req.getParameter("userId"));
		String fullname = req.getParameter("fullname");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String appointmentDate = req.getParameter("appointmentDate");
		String email = req.getParameter("email");
		String phoneNo = req.getParameter("phoneNo");
		String diseases = req.getParameter("diseases");
		int doctorId = Integer.parseInt(req.getParameter("doctorId"));
		String address = req.getParameter("address");

		Appointment ap = new Appointment(userId, fullname, gender, age, appointmentDate, email, phoneNo, diseases,
				doctorId, address, "pending");
		AppointmentDao dao = new AppointmentDao(DBconnection.getConn());
		HttpSession session = req.getSession();

		if (dao.addAppointment(ap)) {
			session.setAttribute("succMsg", "Appointment Successfully");
			resp.sendRedirect("user_appointment.jsp");

		} else {
			session.setAttribute("errorMsg", "Something Wrong on server ");
			resp.sendRedirect("user_appointment.jsp");
		}

	}

}
