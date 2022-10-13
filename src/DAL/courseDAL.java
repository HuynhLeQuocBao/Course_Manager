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
                String sql = "select * from course INNER JOIN onsitecourse ON course.courseID = onsitecourse.courseID";
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

    public boolean insertCourse(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse) {
        boolean result = false;
        Course course;
        if (courseType == "onsite") {
            course = onsiteCourse;
        } else {
            course = onlineCourse;
        }
        if (dc.openConnection()) {
            try {
                String sql = "insert into course(Title, Credits, DepartmentID) values (?, ?, ?)";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, course.getTitle());
                statement.setInt(2, Integer.parseInt(course.getCredits()));
                statement.setInt(3, course.getDepartmentID());

                JOptionPane.showMessageDialog(null, course.getTitle());

                if (statement.executeUpdate() >= 1 && this.insertCourseByType(courseType, onlineCourse, onsiteCourse)) {
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

    public boolean insertCourseByType(String courseType, OnlineCourse onlineCourse, OnsiteCourse onsiteCourse)
            throws SQLException {
        boolean result = false;
        String sql = "";
        int courseId = this.getCourseId();
        if (courseType == "onsite") {
            sql = "insert into onsiteCourse(CourseID, Location, Days, Time) values (?, ?, ?, ?)";
            PreparedStatement statement = dc.connection.prepareCall(sql);

            statement.setInt(1, courseId);
            statement.setString(2, onsiteCourse.getLocation());
            statement.setString(3, onsiteCourse.getDate());
            statement.setString(4, onsiteCourse.getTime());
            if (statement.executeUpdate() >= 1) {
                result = true;
            }
        } else {
            sql = "insert into onlineCourse(CourseID, url) values (?, ?)";
            PreparedStatement statement = dc.connection.prepareCall(sql);

            statement.setInt(1, courseId);
            statement.setString(2, onlineCourse.getUrl());
            if (statement.executeUpdate() >= 1) {
                result = true;
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
                    this.deleteTypeOfCourse("onsite", onsiteCourse.getCourseID());
                    // add online course
                    // this.insertOnlineCourse(onlineCourse);
                }
                // current type: ONLINE
                else {
                    // delete online course
                    this.deleteTypeOfCourse("online", onsiteCourse.getCourseID());
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

    public boolean deleteTypeOfCourse(String courseType, int courseId) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                String sql = "delete from onsite" + courseType + " where CourseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);
                statement.setInt(1, courseId);

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

    public int getCourseId() throws SQLException {
        // init course if don't have any course
        int courseId = 1;

        // query
        String sql = "select MAX(CourseID) from course";
        PreparedStatement statement = dc.connection.prepareCall(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            courseId = resultSet.getInt("MAX(CourseID)");
        }
        return courseId;
    }

    // DEPARTMENT
    public List<String> getAllDepartment() {
        List<String> departmentList = new ArrayList<String>();

        if (dc.openConnection()) {
            try {
                // query
                String sql = "select DepartmentID, Name from department";
                Statement statement = dc.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                String idAndNameDepartment = "";
                while (resultSet.next()) {
                    idAndNameDepartment = resultSet.getString("DepartmentID") + "_" + resultSet.getString("Name");
                    departmentList.add(idAndNameDepartment);
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return departmentList;
    }

    public String getDepartmentName(int departmentID) {
        String departmentName = "";
        if (dc.openConnection()) {
            try {
                // query
                String sql = "select Name from department where DepartmentID=" + departmentID;
                Statement statement = dc.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    departmentName = resultSet.getString("Name");
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return departmentName;
    }
}
