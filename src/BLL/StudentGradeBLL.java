package BLL;

import java.util.ArrayList;
import Base.*;
import DAL.*;
public class StudentGradeBLL {
    StudentGradeDAL studentGradeDAL = new StudentGradeDAL();
    public ArrayList<StudentGrade> findAll(){
      return studentGradeDAL.findAll();
    }
    public ArrayList<StudentGrade> searchByname(String name)
    {
      return studentGradeDAL.searchByname(name);
    }
    public ArrayList<StudentGrade> searchbyCode(int codeID){
      return studentGradeDAL.searchBycode(codeID);
    }
    public String add(StudentGrade stg)
    {
      if(studentGradeDAL.add(stg))
      {
        return "Thêm kết quả khóa học thành công!!";
      }
      return "Thêm không thành công!!!";
    }
    public String addwithoutGrade(int courseid, int studentid)
    {
      if(studentGradeDAL.addwithoutGrade(courseid, studentid))
      {
        return "Thêm kết quả khóa học thành công!!";
      }
      return "Thêm không thành công!!!";
    }
    public String update(StudentGrade stg)
    {
      if(studentGradeDAL.update(stg))
      {
        return "Sửa kết quả khóa học thành công!!";
      }
      return "Sửa không thành công!!!";
    }
    public String delete(int enrollmentID)
    {
      if(studentGradeDAL.delete(enrollmentID))
      {
        return "xóa kết quả khóa học thành công!!";
      }
      return "Xóa không thành công!!!";
    }
    public ArrayList<String> getAllCourse()
    {
      return studentGradeDAL.getAllCourse();
    }
    public ArrayList<String> getAllStudent()
    {
      return studentGradeDAL.getAllStudent();
    }
    public String courseName(int courseID)
    {
      return studentGradeDAL.CourseName(courseID);
    }
    public String StudentName(int StudentID)
    {
      return studentGradeDAL.StudentName(StudentID);
    }
    
} 
