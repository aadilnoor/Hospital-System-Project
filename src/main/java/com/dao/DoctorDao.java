package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Doctor;

public class DoctorDao {

	private Connection conn;

	public DoctorDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean registerDoctor(Doctor d) {
		boolean f = false;

		try {

			String query = "insert into doctor(fullName, dob, qualification, specialist, email, mobNo, password) values(?,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getFullName());
			ps.setString(2, d.getDob());
			ps.setString(3, d.getQualification());
			ps.setString(4, d.getSpecialist());
			ps.setString(5, d.getEmail());
			ps.setString(6, d.getMobNo());
			ps.setString(7, d.getPassword());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;
	}

	public List<Doctor> getAllDoctor() {
		List<Doctor> list = new ArrayList<Doctor>();
		Doctor doctor = null;
		try {
			String query = "select * from doctor order by id desc";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setFullName(rs.getString(2));
				doctor.setDob(rs.getString(3));
				doctor.setQualification(rs.getString(4));
				doctor.setSpecialist(rs.getString(5));
				doctor.setEmail(rs.getString(6));
				doctor.setMobNo(rs.getString(7));
				doctor.setPassword(rs.getString(8));

				list.add(doctor);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Doctor getDoctorById(int id) {

		Doctor doctor = null;
		try {
			String query = "select * from doctor where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setFullName(rs.getString(2));
				doctor.setDob(rs.getString(3));
				doctor.setQualification(rs.getString(4));
				doctor.setSpecialist(rs.getString(5));
				doctor.setEmail(rs.getString(6));
				doctor.setMobNo(rs.getString(7));
				doctor.setPassword(rs.getString(8));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	public boolean updateDoctor(Doctor doctor) {
		boolean f = false;

		try {

			String query = "update doctor set fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=?, password=? where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, doctor.getFullName());
			ps.setString(2, doctor.getDob());
			ps.setString(3, doctor.getQualification());
			ps.setString(4, doctor.getSpecialist());
			ps.setString(5, doctor.getEmail());
			ps.setString(6, doctor.getMobNo());
			ps.setString(7, doctor.getPassword());
			ps.setInt(8, doctor.getId());
			int i = ps.executeUpdate();

			if (i == 1) {

				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;
	}

	public boolean deleteDoctor(int id) {

		boolean f = false;

		try {

			String query = "delete from doctor where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	public Doctor login(String email, String password) {
		Doctor doctor = null;
		try {
			String query = "select * from doctor where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setFullName(rs.getString(2));
				doctor.setDob(rs.getString(3));
				doctor.setQualification(rs.getString(4));
				doctor.setSpecialist(rs.getString(5));
				doctor.setEmail(rs.getString(6));
				doctor.setMobNo(rs.getString(7));
				doctor.setPassword(rs.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;

	}

	public int countDoctor() {
		int i = 0;
		try {

			String query = "select * from doctor";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countAppointment() {
		int i = 0;
		try {

			String query = "select * from appointment";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countAppointmentByDoctorId(int did) {
		int i = 0;
		try {

			String query = "select * from appointment where doctorId = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, did);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countUser() {
		int i = 0;
		try {

			String query = "select * from user_details";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countSpecialist() {
		int i = 0;
		try {

			String query = "select * from specialist";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public boolean checkOldPassword(int userid, String oldPassword) {
		boolean f = false;
		try {
			String query = "select * from doctor where id = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setString(2, oldPassword);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean changePassword(int userid, String newPassword) {
		boolean f = false;
		try {
			String query = "update doctor set password = '"+newPassword+"' WHERE id = "+userid+"";
			PreparedStatement ps = conn.prepareStatement(query);
		//	ps.setString(1, newPassword);
		//	ps.setInt(2, userid);
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean editDoctorProfile(Doctor doctor) {
		boolean f = false;

		try {

			String query = "UPDATE doctor SET fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=? WHERE id=?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, doctor.getFullName());
			ps.setString(2, doctor.getDob());
			ps.setString(3, doctor.getQualification());
			ps.setString(4, doctor.getSpecialist());
			ps.setString(5, doctor.getEmail());
			ps.setString(6, doctor.getMobNo());
			ps.setInt(7, doctor.getId());
			int i = ps.executeUpdate();

			if (i == 1) {

				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;
	}

}
