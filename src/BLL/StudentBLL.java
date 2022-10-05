package BLL;

import java.util.*;
import DAL.StudentDAL;
import Base.Student;

public class StudentBLL {
  StudentDAL stuDAL = new StudentDAL();

  public List<Student> getAllStudent() {
    return stuDAL.findAll();
  }
}
