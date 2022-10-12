package BLL;

import java.util.*;

import javax.swing.JOptionPane;

import DAL.courseDAL;
import Base.Course;
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
            return "Add Course successfully";
        }
        return "Add course failed";
    }

    public String addCourseOnline(OnlineCourse course) {
        if (courseDAL.insertOnlineCourse(course)) {
            return "Add Course successfully";
        }
        return "Add course failed";
    }

    public int getCourseId() {
        return courseDAL.getCourseId();
    }

}
