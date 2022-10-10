package DAL;

import java.sql.*;
import java.util.*;
import Base.Teacher;

public class TeacherDAL {
  databaseConnect dc = new databaseConnect();

  public List<Teacher> findAll() {
    List<Teacher> teacherList = new ArrayList<Teacher>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select * from person where HireDate IS NULL";
        Statement statement = dc.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
          Teacher std = new Teacher(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("EnrolLmentDate"));
          teacherList.add(std);
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return teacherList;
  }

  public boolean insert(Teacher p) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "insert into person(PersonID, Lastname, Firstname, EnrolLmentDate) values (?, ?, ?, ?)";
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

  public boolean update(Teacher p) {
    boolean result = false;
    if (dc.openConnection()) {
      try {
        String sql = "update person set Lastname=?, Firstname=?, EnrolLmentDate=? where PersonID = ?";
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

  public List<Teacher> findByUserName(String teacherName) {
    List<Teacher> teacherList = new ArrayList<Teacher>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select * from person where concat(Lastname, Firstname) like ? and HireDate IS NULL";
        PreparedStatement statement = dc.connection.prepareCall(sql);
        statement.setString(1, "%" + teacherName + "%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
          Teacher std = new Teacher(
              resultSet.getInt("PersonID"),
              resultSet.getString("Lastname"), resultSet.getString("Firstname"),
              resultSet.getString("EnrolLmentDate"));
          teacherList.add(std);
        }
      } catch (SQLException e) {
        System.out.println(e);
      } finally {
        dc.closeConnection();
      }
    }
    return teacherList;
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