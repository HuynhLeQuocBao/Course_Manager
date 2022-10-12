package DAL;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.swing.JOptionPane;

import Base.Course;
import Base.OnlineCourse;
import Base.OnsiteCourse;

public class courseDAL {
    databaseConnect dc = new databaseConnect();

    // List<Product> productList = new ArrayList<Product>();
    public List<OnsiteCourse> findAllOnsiteCourse() {
        List<OnsiteCourse> courseOnsiteList = new ArrayList<OnsiteCourse>();

        if (dc.openConnection()) {
            try {
                // query
                String sql = "select * from course INNER JOIN onsitecourse ON course.courseID = onsitecourse.courseID INNER JOIN department ON course.departmentID = department.departmentID";
                Statement statement = dc.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    OnsiteCourse course = new OnsiteCourse(
                            resultSet.getInt("CourseID"),
                            resultSet.getString("Title"), resultSet.getString("Credits"),
                            resultSet.getInt("DepartmentID"), resultSet.getString("Location"),
                            resultSet.getString("Days"), resultSet.getString("Time"));
                    courseOnsiteList.add(course);
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return courseOnsiteList;
    }

    public List<OnlineCourse> findAllOnlineCourse() {
        List<OnlineCourse> courseOnlineList = new ArrayList<OnlineCourse>();

        if (dc.openConnection()) {
            try {
                // query
                String sql = "select * from course, onlinecourse where course.courseID = onlinecourse.courseID";
                Statement statement = dc.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    OnlineCourse course = new OnlineCourse(
                            resultSet.getInt("CourseID"),
                            resultSet.getString("Title"), resultSet.getString("Credits"),
                            resultSet.getInt("DepartmentID"), resultSet.getString("url"));
                    courseOnlineList.add(course);
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return courseOnlineList;
    }

    public List<String> getAllCourse() {
      List<String> courseList = new ArrayList<String>();

      if (dc.openConnection()) {
          try {
              // query
              String sql = "select CourseID, Title from course";
              Statement statement = dc.connection.createStatement();

              ResultSet resultSet = statement.executeQuery(sql);

              while (resultSet.next()) {
                  String courseItem = resultSet.getString("CourseID") + "_" + resultSet.getString("Title");
                  courseList.add(courseItem);
              }
          } catch (SQLException e) {
              System.out.println(e);
          } finally {
              dc.closeConnection();
          }
      }
      return courseList;
  }
    
    public boolean insertOnsiteCourse(OnsiteCourse onsiteCourse) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // add course
                String sql = "insert into course(Title, Credits, DepartmentID) values (?, ?, ?)";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, onsiteCourse.getTitle());
                statement.setInt(2, Integer.parseInt(onsiteCourse.getCredits()));
                statement.setInt(3, onsiteCourse.getDepartmentID());
                if (statement.executeUpdate() >= 1) {
                    int courseId = this.getCourseId();
                    // add onsiteCourse
                    String sqlOnsiteCourse = "insert into onsitecourse(CourseID, Location, Days, Time) values (?, ?, ?, ?)";
                    PreparedStatement statementOnsite = dc.connection.prepareCall(sqlOnsiteCourse);

                    statementOnsite.setInt(1, courseId);
                    statementOnsite.setString(2, onsiteCourse.getLocation());
                    statementOnsite.setString(3, onsiteCourse.getDate());
                    statementOnsite.setString(4, onsiteCourse.getTime());
                    if (statementOnsite.executeUpdate() >= 1) {
                        result = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return result;
    }

    public boolean insertOnlineCourse(OnlineCourse onlineCourse) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // add course
                String sql = "insert into course(Title, Credits, DepartmentID) values (?, ?, ?)";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, onlineCourse.getTitle());
                statement.setString(2, onlineCourse.getCredits());
                statement.setInt(3, onlineCourse.getDepartmentID());

                // get courseID
                int courseId = this.getCourseId();
                // add onlineCourse
                String sqlOnlineCourse = "insert into onlineCourse(CourseID, Location, Days, Time) values (?, ?, ?, ?)";
                PreparedStatement statementOnsite = dc.connection.prepareCall(sqlOnlineCourse);

                statement.setInt(1, courseId);
                statement.setString(2, onlineCourse.getUrl());

                if (statement.executeUpdate() >= 1 && statementOnsite.executeUpdate() >= 1) {
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

    public boolean updateOnsiteCourse(OnsiteCourse course) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // update course
                String sql = "update course set Title=?, Credits=?, DepartmentID=? where courseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, course.getTitle());
                statement.setString(2, course.getCredits());
                statement.setInt(3, course.getDepartmentID());
                statement.setInt(4, course.getCourseID());

                // update onsiteCourse
                String sqlOnsite = "update onsitecourse set Location=?, Days=?, Time=? where courseID = ?";
                PreparedStatement statementOnsite = dc.connection.prepareCall(sqlOnsite);

                statementOnsite.setString(1, course.getLocation());
                statementOnsite.setString(2, course.getDate());
                statementOnsite.setString(3, course.getTime());
                statementOnsite.setInt(4, course.getCourseID());

                if (statement.executeUpdate() >= 1 && statementOnsite.executeUpdate() >= 1) {
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

    // edit type of the course (onsite, online)
    public boolean updateTypeOfCourse(OnsiteCourse onsiteCourse, OnlineCourse onlineCourse, int currentType) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // update course
                String sql = "update course set Title=?, Credits=?, DepartmentID=? where courseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, onsiteCourse.getTitle());
                statement.setString(2, onsiteCourse.getCredits());
                statement.setInt(3, onsiteCourse.getDepartmentID());
                statement.setInt(4, onsiteCourse.getCourseID());

                // update type of the course
                // current type: ONSITE
                if (currentType == 0) {
                    // delete onsite course
                    this.deleteOnsiteCourse(onsiteCourse.getCourseID());
                    // add online course
                    this.insertOnlineCourse(onlineCourse);
                }
                // current type: ONLINE
                else {
                    // delete online course
                    // add onsite course
                }

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

    public boolean deleteOnsiteCourse(int courseId) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // query
                String sql = "delete from course where CourseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setInt(1, courseId);

                String sqlOnsite = "delete from onsitecourse where CourseID = ?";
                PreparedStatement statementOnsite = dc.connection.prepareCall(sqlOnsite);

                statementOnsite.setInt(1, courseId);

                if (statementOnsite.executeUpdate() >= 1 && statement.executeUpdate() >= 1) {
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

    public int getCourseId() {
        // init course if don't have any course
        int courseId = 1;

        if (dc.openConnection()) {
            try {
                // query
                String sql = "select MAX(CourseID) from course";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    courseId = resultSet.getInt("MAX(CourseID)");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return courseId;
    }
}
