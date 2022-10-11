package DAL;

import java.sql.*;
import java.util.*;
import Base.CourseInstructor;

public class courseInstructorDAL {
  databaseConnect dc = new databaseConnect();

  public List<courseInstructorDAL> findAll() {
    List<CourseInstructor> courseInstructorList = new ArrayList<CourseInstructor>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select courseinstructor.PersonID, courseinstructor.CourseID, person.FirstName, person.LastName , course.Title "
            + "from person,course, courseinstructor "
            + "where person.PersonID= courseinstructor.PersonID "
            + "and "
            + "course.CourseID= courseinstructor.CourseID ";
        Statement statement = dc.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
          CourseInstructor std = new CourseInstructor(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("HireDate"));
          courseInstructorList.add(std);
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return courseInstructorList;
  }

  public boolean insert(Student p) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "insert into person(PersonID, Lastname, Firstname, HireDate) values (?, ?, ?, ?)";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        statement.setInt(1, p.getPersonID());
        statement.setString(2, p.getLastName());
        statement.setString(3, p.getFirstName());
        statement.setString(4, p.getHireDate());

        if (statement.executeUpdate() >= 1) {
          result = true;
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return result;
  }

  public boolean update(Student p) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "update person set Lastname=?, Firstname=?, HireDate=? where PersonID = ?";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        statement.setString(1, p.getLastName());
        statement.setString(2, p.getFirstName());
        statement.setString(3, p.getHireDate());
        statement.setInt(4, p.getPersonID());

        if (statement.executeUpdate() >= 1) {
          result = true;
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return result;
  }

  public boolean delete(int id) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "delete from person where PersonID = ?";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        statement.setInt(1, id);

        if (statement.executeUpdate() >= 1) {
          result = true;
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return result;
  }

  public List<Student> findByUserName(String studentName) {
    List<Student> studentList = new ArrayList<Student>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select * from person where concat(Lastname, Firstname) like ? and EnrolLmentDate IS NULL";
        PreparedStatement statement = dc.connection.prepareCall(sql);
        statement.setString(1, "%" + studentName + "%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
          Student std = new Student(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("HireDate"));
          studentList.add(std);
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return studentList;
  }

  public boolean hasPersonID(int personID) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "select * from person where PersonID=" + personID;
        Statement statement = dc.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        result = resultSet.next();
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return result;
  }
}
