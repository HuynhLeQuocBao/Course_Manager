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

    public List<OnsiteCourse> findByTitleOnsite(String title) {
        List<OnsiteCourse> courseList = new ArrayList<OnsiteCourse>();

        if (dc.openConnection()) {
            try {
                String sql = "";
                PreparedStatement statement;
                sql = "select * from course INNER JOIN onsitecourse ON course.courseID = onsitecourse.courseID where Title like ?";
                statement = dc.connection.prepareCall(sql);
                statement.setString(1, "%" + title + "%");

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    OnsiteCourse course = new OnsiteCourse(resultSet.getInt("CourseID"),
                            resultSet.getString("Title"), resultSet.getString("Credits"),
                            resultSet.getInt("DepartmentID"), resultSet.getString("Location"),
                            resultSet.getString("Days"), resultSet.getString("Time"));
                    courseList.add(course);
                }

                // query
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return courseList;
    }

    public List<OnlineCourse> findByTitleOnline(String title) {
        List<OnlineCourse> courseOnlineList = new ArrayList<OnlineCourse>();

        if (dc.openConnection()) {
            try {
                String sql = "";
                PreparedStatement statement;
                sql = "select * from course INNER JOIN onlinecourse ON course.courseID = onlinecourse.courseID where Title like ?";
                statement = dc.connection.prepareCall(sql);
                statement.setString(1, "%" + title + "%");

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    OnlineCourse course = new OnlineCourse(
                            resultSet.getInt("CourseID"),
                            resultSet.getString("Title"), resultSet.getString("Credits"),
                            resultSet.getInt("DepartmentID"), resultSet.getString("url"));
                    courseOnlineList.add(course);
                }

                // query
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

    public boolean updateCourse(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse) {
        boolean result = false;
        Course course;
        if (courseType == "onsite") {
            course = onsiteCourse;
        } else {
            course = onlineCourse;
        }
        if (dc.openConnection()) {
            try {
                String sql = "update course set Title=?, Credits=?, DepartmentID=? where courseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setString(1, course.getTitle());
                statement.setString(2, course.getCredits());
                statement.setInt(3, course.getDepartmentID());
                statement.setInt(4, course.getCourseID());

                if (statement.executeUpdate() >= 1 && this.updateCourseByType(courseType, onsiteCourse, onlineCourse)) {
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

    public boolean updateCourseByType(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse)
            throws SQLException {
        String sql = "";
        PreparedStatement statement;
        if (courseType == "onsite") {
            sql = "update onsitecourse set Location=?, Days=?, Time=? where courseID = ?";
            statement = dc.connection.prepareCall(sql);

            statement.setString(1, onsiteCourse.getLocation());
            statement.setString(2, onsiteCourse.getDate());
            statement.setString(3, onsiteCourse.getTime());
            statement.setInt(4, onsiteCourse.getCourseID());
            if (statement.executeUpdate() >= 1) {
                return true;
            }
        } else if (courseType == "online") {
            sql = "update onlinecourse set url=? where courseID = ?";
            statement = dc.connection.prepareCall(sql);

            statement.setString(1, onlineCourse.getUrl());
            statement.setInt(2, onlineCourse.getCourseID());
            if (statement.executeUpdate() >= 1) {
                return true;
            }
        } else { // edit type of a course
            // delete online course and add onsite course
            if (courseType.equals("changeToOnsiteCourse")
                    && this.deleteTypeOfCourse("online", onlineCourse.getCourseID())
                    && this.insertCourseByType("onsite", onlineCourse, onsiteCourse)) {
                return true;
            } else {
                if (this.deleteTypeOfCourse("onsite", onsiteCourse.getCourseID())
                        && this.insertCourseByType("online", onlineCourse, onsiteCourse))
                    return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String courseType, int courseId) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                // query
                String sql = "delete from course where CourseID = ?";
                PreparedStatement statement = dc.connection.prepareCall(sql);

                statement.setInt(1, courseId);

                if (this.deleteTypeOfCourse(courseType, courseId) && statement.executeUpdate() >= 1) {
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

    public boolean deleteTypeOfCourse(String courseType, int courseId) throws SQLException {
        boolean result = false;
        String sql = "delete from " + courseType + "course where CourseID = ?";
        PreparedStatement statement = dc.connection.prepareCall(sql);
        statement.setInt(1, courseId);

        if (statement.executeUpdate() >= 1) {
            result = true;
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

    public boolean courseUsed(int courseID) {
        boolean result = false;
        if (dc.openConnection()) {
            try {
                String sql = "select * from courseinstructor where CourseID=" + courseID;
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
