package DAL;

import java.sql.*;
import java.util.*;
import Base.Student;

public class StudentDAL {
  databaseConnect dc = new databaseConnect();

  public List<Student> findAll() {
    List<Student> studentList = new ArrayList<Student>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select * from person where HireDate IS NULL";
        Statement statement = dc.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
          Student std = new Student(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("EnrollmentDate"));
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

  public boolean insert(Student p) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "insert into person(PersonID, Lastname, Firstname, EnrollmentDate) values (?, ?, ?, ?)";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        statement.setInt(1, p.getPersonID());
        statement.setString(2, p.getLastName());
        statement.setString(3, p.getFirstName());
        statement.setString(4, p.getEnrollmentDate());

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
        String sql = "update person set Lastname=?, Firstname=?, EnrollmentDate=? where PersonID = ?";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        statement.setString(1, p.getLastName());
        statement.setString(2, p.getFirstName());
        statement.setString(3, p.getEnrollmentDate());
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
        String sql = "select * from person where concat(Lastname, Firstname) like ? and HireDate IS NULL";
        PreparedStatement statement = dc.connection.prepareCall(sql);
        statement.setString(1, "%" + studentName + "%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
          Student std = new Student(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("EnrollmentDate"));
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

  public boolean hasPersonIdWhenRemove(int personID) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "select * from studentgrade where StudentID=" + personID;
        String sql1 = "select * from courseinstructor where PersonID=" + personID;
        Statement statement = dc.connection.createStatement();
        Statement statement1 = dc.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSet resultSet1 = statement1.executeQuery(sql1);
        if (resultSet.next() && resultSet1.next()) {
          result = true;
        } else {
          result = false;
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return result;
  }
}
