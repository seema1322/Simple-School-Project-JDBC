package com.ty.school_db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ty.school_db.entity.School;
import com.ty.school_db.exceptions.SchoolNotFoundException;
import com.ty.school_db.exceptions.StudentNotFoundexception;
import com.ty.school_db.connectionpool.ConnectionPool;
import com.ty.school_db.entity.Student;

public class SchoolStudentDao {

	public void saveStudentDetails(Student student) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "INSERT INTO STUDENT_INFO VALUES(?,?,?,?,?)";

		PreparedStatement preStatement = conn.prepareStatement(sql);
		preStatement.setInt(1, student.getId());
		preStatement.setInt(2, student.getSchool_id());
		preStatement.setString(3, student.getName());
		preStatement.setInt(4, student.getAge());
		preStatement.setString(5, student.getGender());

		preStatement.execute();
		
		System.out.println("Student details added");
		preStatement.close();
		ConnectionPool.receiveConnectionObject(conn);
	}

	public void saveSchoolDetails(School school) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "INSERT INTO SCHOOL_INFO VALUES(?,?)";

		PreparedStatement preStatement = conn.prepareStatement(sql);
		preStatement.setInt(1, school.getId());
		preStatement.setString(2, school.getName());

		preStatement.execute();
		System.out.println("School details added");
		preStatement.close();
		ConnectionPool.receiveConnectionObject(conn);
	}

	public List<Student> viewStudent(int id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "SELECT * FROM STUDENT_INFO WHERE SCHL_ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();

		List<Student> list = new ArrayList<Student>();

		while (rs.next()) {
			int student_id = rs.getInt(1);
			int school_id = rs.getInt(2);
			String student_name = rs.getString(3);
			int student_age = rs.getInt(4);
			String student_gender = rs.getString(5);

			Student student = new Student(student_id, school_id, student_name, student_age, student_gender);
			list.add(student);
		}
		pstm.close();
		ConnectionPool.receiveConnectionObject(conn);
		return list;
	}

	public void viewSchool(int school_id) throws SQLException, ClassNotFoundException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "SELECT * FROM SCHOOL_INFO WHERE ID=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, school_id);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {

			int id = rs.getInt(1);
			System.out.println("School id is " + id);
			String name = rs.getString(2);
			System.out.println("School name is " + name);
			System.out.println("=====================");
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			SchoolStudentDao std = new SchoolStudentDao();
			List<Student> list = std.viewStudent(school_id);

			for (Student student : list) {

				int sid = student.getId();
				System.out.println("Student id is: " + sid);
				int schl_id = student.getSchool_id();
				System.out.println("Student's school id is: " + schl_id);
				String sname = student.getName();
				System.out.println("Student name is: " + sname);
				int sage = student.getAge();
				System.out.println("Age of student is: " + sage);
				String gender = student.getGender();
				System.out.println("The gender of student is: " + gender);
				System.out.println("====================================");
			}
		}
		throw new SchoolNotFoundException();
	}

	public void deleteSchool(int id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "DELETE FROM SCHOOL_INFO WHERE ID=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		int result = pstm.executeUpdate();
		ResultSet rs = pstm.getResultSet();
		//ResultSet rs = pstm.executeQuery();
		System.out.println(rs);

		if (result >= 1) {
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);

			SchoolStudentDao std = new SchoolStudentDao();
			std.deleteStudent(id);
			System.out.println("School details deleted successfully");
		}
		throw new SchoolNotFoundException();
	}

	public void deleteStudent(int school_id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "DELETE FROM STUDENT_INFO WHERE schl_id=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, school_id);
		pstm.executeUpdate();
		pstm.close();
		ConnectionPool.receiveConnectionObject(conn);

	}

	public Student viewStudentById(int id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();
		String sql = "SELECT * FROM STUDENT_INFO WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		Student student = null;

		if (rs.next()) {
			int student_id = rs.getInt(1);
			int school_id = rs.getInt(2);
			String student_name = rs.getString(3);
			int student_age = rs.getInt(4);
			String student_gender = rs.getString(5);
			student = new Student(student_id, school_id, student_name, student_age, student_gender);
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			return student;
		}
		throw new StudentNotFoundexception();
	}

	public void deleteStudentById(int id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "DELETE FROM STUDENT_INFO WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);
		int result = pstm.executeUpdate();
		if (result >= 1) {
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			System.out.println("Student details deleted");
		}
		throw new StudentNotFoundexception();
	}

	public void updateName(int id, String name) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "UPDATE STUDENT_INFO SET NAME=? WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		pstm.setInt(2, id);
		int result = pstm.executeUpdate();
		if (result >= 1) {
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			System.out.println("Student Name updated");
		}
		throw new StudentNotFoundexception();
	}

	public void updateAge(int id, int age) throws SQLException, ClassNotFoundException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "UPDATE STUDENT_INFO SET AGE=? WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, age);
		pstm.setInt(2, id);
		int result = pstm.executeUpdate();
		if (result >= 1) {
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			System.out.println("Student age updated");
		}
		throw new StudentNotFoundexception();

	}

	public void updateGender(int id, String gender) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "UPDATE STUDENT_INFO SET GENDER=? WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, gender);
		pstm.setInt(2, id);
		int result = pstm.executeUpdate();

		if (result >= 1) {
			System.out.println("Student gender updated");
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
		}
		throw new StudentNotFoundexception();

	}

	public void updateSchoolName(int id, String name) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionPool.getConnectionObject();

		String sql = "UPDATE SCHOOL_INFO SET NAME=? WHERE ID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		pstm.setInt(2, id);
		int result = pstm.executeUpdate();
		if (result >= 1) {
			pstm.close();
			ConnectionPool.receiveConnectionObject(conn);
			System.out.println("School details updated");
		}
		throw new SchoolNotFoundException();
	}

}
