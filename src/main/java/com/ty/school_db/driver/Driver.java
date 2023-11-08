package com.ty.school_db.driver;

import java.sql.SQLException;
import java.util.Scanner;

import com.ty.school_db.dao.SchoolStudentDao;
import com.ty.school_db.entity.School;
import com.ty.school_db.entity.Student;
import com.ty.school_db.exceptions.SchoolNotFoundException;
import com.ty.school_db.exceptions.StudentNotFoundexception;

public class Driver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		SchoolStudentDao schoolStudentDao = new SchoolStudentDao();

		while (true) {

			System.out.println("Select the option to perform operation");
			System.out.println("1. Add school details \n2. Add Student details \n3. View Student details \n4. View school details");
			System.out.println("5. Delete School details \n6. Delete student details \n7. Update student details \n8. Update School details");
			System.out.println("9. Exit");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {

				System.out.println("Enter id");
				int id = scanner.nextInt();
				System.out.println("Enter name");
				String name = scanner.next();
				
				System.out.println("Enter count of students to add");
				int count = scanner.nextInt();

				School sc = new School(id, name);
				try {
					schoolStudentDao.saveSchoolDetails(sc);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i = 0; i < count; i++) {
					System.out.println("Enter id of student");
					int sid = scanner.nextInt();
					System.out.println("Enter name of student");
					String sname = scanner.next();
					System.out.println("Enter age of student");
					int age = scanner.nextInt();
					System.out.println("Enter gender of student");
					String gender = scanner.next();

					Student student = new Student(sid, id, sname, age, gender);
					try {
						schoolStudentDao.saveStudentDetails(student);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

				break;

			case 2: {

				System.out.println("Enter the count of students to add");
				int count = scanner.nextInt();

				for (int i = 0; i < count; i++) {
					System.out.println("Enter id of student");
					int id = scanner.nextInt();
					System.out.println("Enter id of school");
					int sid = scanner.nextInt();
					System.out.println("Enter name of student");
					String sname = scanner.next();
					System.out.println("Enter age of student");
					int age = scanner.nextInt();
					System.out.println("Enter gender of student");
					String gender = scanner.next();

					Student student = new Student(id, sid, sname, age, gender);
					try {
						schoolStudentDao.saveStudentDetails(student);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
				break;

			case 3: {
				System.out.println("Enter id of student");
				int id = scanner.nextInt();

				try {
					Student student = schoolStudentDao.viewStudentById(id);
					System.out.println("The student id is: " + student.getId());
					System.out.println("The school id is: " + student.getSchool_id());
					System.out.println("The student name is: " + student.getName());
					System.out.println("The student age is: " + student.getAge());
					System.out.println("The student gender is: " + student.getGender());
					System.out.println("==============================");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (StudentNotFoundexception e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case 4: {
				System.out.println("Enter school id");
				int id = scanner.nextInt();

				try {
					schoolStudentDao.viewSchool(id);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (SchoolNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
				break;
			case 5: {

				System.out.println("Enter school id");
				int id = scanner.nextInt();

				try {
					schoolStudentDao.deleteSchool(id);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (SchoolNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
				break;

			case 6: {
				System.out.println("Enter student id to delete");
				int id = scanner.nextInt();

				try {
					schoolStudentDao.deleteStudentById(id);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (StudentNotFoundexception e) {
					System.out.println(e.getMessage());
				}
			}
				break;

			case 7: {
				System.out.println("Select to update details");
				System.out.println("1.Name, 2.Age, 3.Gender");
				int option = scanner.nextInt();

				System.out.println("Enter student id to update");
				int id = scanner.nextInt();
				switch (option) {
				case 1: {
					System.out.println("Enter name");
					String name = scanner.next();

					try {
						schoolStudentDao.updateName(id, name);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (StudentNotFoundexception e) {
						System.out.println(e.getMessage());
					}
				}

					break;
				case 2: {
					System.out.println("Enter age");
					int age = scanner.nextInt();

					try {
						schoolStudentDao.updateAge(id, age);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (StudentNotFoundexception e) {
						System.out.println(e.getMessage());
					}
				}

					break;
				case 3: {
					System.out.println("Enter gender");
					String gender = scanner.next();

					try {
						schoolStudentDao.updateGender(id, gender);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (StudentNotFoundexception e) {
						System.out.println(e.getMessage());
					}
				}

					break;

				default: {
					System.out.println("Invalid choice");
				}
				}
			}
				break;
			case 8: {

				System.out.println("Enter the id of school");
				int id = scanner.nextInt();
				System.out.println("Enter name of school to update");
				String name = scanner.next();

				try {
					schoolStudentDao.updateSchoolName(id, name);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (SchoolNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case 9: {
				System.out.println("Exited successfully");
				return;
			}
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

}
