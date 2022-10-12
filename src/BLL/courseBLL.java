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

    public String addCourseOnsite(OnsiteCourse course) {
        if (courseDAL.insertOnsiteCourse(course)) {
            return "Add Course Onsite successfully";
        }
        return "Add Course Onsite failed";
    }

    public String addCourseOnline(OnlineCourse course) {
        if (courseDAL.insertOnlineCourse(course)) {
            return "Add Course Online successfully";
        }
        return "Add Course Online failed";
    }

    public List<String> getAllDepartment() {
        return courseDAL.getAllDepartment();
    }

    public String getDepartmentName(int departmentID) {
        return courseDAL.getDepartmentName(departmentID);
    }
}
