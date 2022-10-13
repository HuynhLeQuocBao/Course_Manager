package BLL;

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

    public List<String> getAllCourse(){
        return courseDAL.getAllCourse();
      }

    public String addCourse(String courseType, OnsiteCourse onsiteCourse, OnlineCourse onlineCourse) {
        if (courseDAL.insertCourse(courseType, onsiteCourse, onlineCourse)) {
            return "Add Course successfully";
        }
        return "Add Course failed";
    }

    public List<String> getAllDepartment() {
        return courseDAL.getAllDepartment();
    }

    public String getDepartmentName(int departmentID) {
        return courseDAL.getDepartmentName(departmentID);
    }
}