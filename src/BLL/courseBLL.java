package BLL;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import DAL.courseDAL;
import Base.Course;
import Base.Department;
import Base.OnsiteCourse;
import Base.OnlineCourse;

public class courseBLL {
    courseDAL courseDAL = new courseDAL();

    public List<OnsiteCourse> getAllOnsiteCourse() {
        return courseDAL.findAllOnsiteCourse();
    }

    public List<OnlineCourse> getAllOnlineCourse() {
        return courseDAL.findAllOnlineCourse();
    }

    public List<OnsiteCourse> findOnsiteCourse(String title) {
        return courseDAL.findByTitleOnsite(title);
    }

    public List<OnlineCourse> findOnlineCourse(String title) {
        return courseDAL.findByTitleOnline(title);
    }

    public String addCourse(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse) {
        if (courseDAL.insertCourse(courseType, onsiteCourse, onlineCourse)) {
            return "Thêm khóa học thành công";
        }
        return "Thêm khóa học không thành công";
    }

    public String editCourse(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse) {
        if (courseDAL.updateCourse(courseType, onsiteCourse, onlineCourse)) {
            return "Cập nhật khóa học thành công";
        }
        return "Cập nhật khóa học không thành công";
    }

    public String deleteCourse(String courseType, int courseID) {
        if (courseDAL.courseUsed(courseID)) {
            return "Khóa học này đã được sử dụng, không được xóa";
        }
        if (courseDAL.deleteCourse(courseType, courseID)) {
            return "Xóa khóa học thành công";
        }
        return "Xóa khóa học không thành công";
    }

    public List<String> getAllDepartment() {
        return courseDAL.getAllDepartment();
    }

    public String getDepartmentName(int departmentID) {
        return courseDAL.getDepartmentName(departmentID);
    }
}
