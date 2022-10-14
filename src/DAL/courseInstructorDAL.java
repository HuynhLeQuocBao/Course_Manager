package DAL;

import java.sql.*;
import java.util.*;
import Base.CourseInstructor;

public class courseInstructorDAL {
  databaseConnect dc = new databaseConnect();

  public List<CourseInstructor> findAll() {
    List<CourseInstructor> courseInstructorList = new ArrayList<CourseInstructor>();

    if (dc.openConnection()) {
      try {
        // query
        String sql = "select courseinstructor.PersonID, courseinstructor.CourseID,  person.FirstName ,person.LastName, course.Title"
            + "from "
            + "person,course, courseinstructor "
            + "where person.PersonID= courseinstructor.PersonID "
            + "and "
            + "course.CourseID= courseinstructor.CourseID ";
        Statement statement = dc.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
          CourseInstructor std = new CourseInstructor(
              resultSet.getInt("PersonID"),resultSet.getInt("CourseID"),  resultSet.getString("FirstName"), 
              resultSet.getString("LastName"), resultSet.getString("Title")
          );
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

 public boolean insert(CourseInstructor p) {
   boolean result = false;
   if (dc.openConnection()) {
     try {
       String sql = "insert into courseinstructor( CourseID,PersonID) values ( ?, ?)";
       PreparedStatement statement = dc.connection.prepareCall(sql);

       statement.setInt(1, p.getCourseID());
       statement.setInt(2, p.getPersonID());
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

 public boolean update(CourseInstructor p, int personID, int courseID) {
   boolean result = false;
   if (dc.openConnection()) {
     try {
       String sql = "update courseinstructor set CourseID=?, PersonID=? where PersonID = ? and CourseID= ? ";
       PreparedStatement statement = dc.connection.prepareCall(sql);

       statement.setInt(1, p.getCourseID());
       statement.setInt(2, p.getPersonID());
       statement.setInt(3, personID);
       statement.setInt(4, courseID);

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

 public boolean delete(int personID, int courseID) {
   boolean result = false;
   if (dc.openConnection()) {
     try {
       String sql = "delete from courseinstructor where PersonID = ? and CourseID = ?";
       PreparedStatement statement = dc.connection.prepareCall(sql);

       statement.setInt(1, personID);
       statement.setInt(2, courseID);

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

 public List<CourseInstructor> findCourseInstructor(String keyword) {
  
   List<CourseInstructor> courseInstructorList = new ArrayList<CourseInstructor>();
  System.out.println("dal"+keyword);
   if (dc.openConnection()) {
     try {
       // query
      String sqlSearchPersonName= " person.PersonID= courseinstructor.PersonID and course.CourseID= courseinstructor.CourseID and concat(person.FirstName, person.LastName) like ?";

      String sqlSearchCourseName= " person.PersonID= courseinstructor.PersonID and course.CourseID= courseinstructor.CourseID and course.Title like ?";
                            
       String sql = "select courseinstructor.PersonID, courseinstructor.CourseID, person.FirstName, person.LastName , course.Title "
                    + "from "
                    + "person , course, courseinstructor "
                    +"where"
                    + sqlSearchPersonName + " or " + sqlSearchCourseName;

       PreparedStatement statement = dc.connection.prepareCall(sql);
       statement.setString(1, "%" + keyword + "%");
       statement.setString(2, "%" + keyword + "%");
       System.out.println(sql);
       ResultSet resultSet = statement.executeQuery();

       while (resultSet.next()) {
        System.out.println("log"+resultSet.getString("Title"));
        CourseInstructor std = new CourseInstructor(
          resultSet.getInt("PersonID"),resultSet.getInt("CourseID"),
          resultSet.getString("FirstName"), resultSet.getString("LastName"),
          resultSet.getString("Title"));
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
}
