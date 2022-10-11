package BLL;

import java.util.*;
import DAL.courseInstructorDAL;
import Base.CourseInstructor;

public class courseInstructorBLL {
  courseInstructorDAL courseInstructorDAL = new courseInstructorDAL();

    public List<CourseInstructor> getAllCourseInstructor() {
        return courseInstructorDAL.findAll();
    }

   public String addCourseInstructor(CourseInstructor p) {
       if (courseInstructorDAL.insert(p)) {
           return "Thêm phân công khóa học thành công";
       }
       return "Thêm phân công khóa học không thành công";
   }

   public String deleteCourseInstructor(int personID, int courseID) {
       if (courseInstructorDAL.delete(personID, courseID)) {
           return "Xóa phân công khóa học thành công";
       }
       return "Xóa phân công khóa học không thành công";
   }

   public String editCourseInstructor(CourseInstructor p, int personID, int courseID ) {
       if (courseInstructorDAL.update(p, personID, courseID)) {
           return "Sửa phân công khóa học thành công";
       }
       return "Sửa phân công khóa học không thành công";
   }

   public List<CourseInstructor> searchCourseInstructor(String keyword) {
       return courseInstructorDAL.findByName(keyword);
   }

}
