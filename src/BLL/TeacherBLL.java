package BLL;

import java.util.*;
import DAL.TeacherDAL;
import Base.Teacher;

public class TeacherBLL {
  TeacherDAL teaDAL = new TeacherDAL();

  public List<Teacher> getAllTeacher() {
    return teaDAL.findAll();
  }

  public String addTeacher(Teacher p) {
    if (teaDAL.hasPersonID(p.getPersonID())) {
      return "Mã giáo viên bị trùng. Vui lòng nhập lại";
    }
    if (teaDAL.insert(p)) {
      return "Thêm giáo viên thành công";
    }
    return "Thêm giáo viên không thành công";
  }

  public String deleteTeacher(int id) {
    if (teaDAL.delete(id)) {
      return "Xóa giáo viên thành công";
    }
    return "Xóa giáo viên không thành công";
  }

  public String editTeacher(Teacher p) {
    if (teaDAL.update(p)) {
      return "Sửa giáo viên thành công";
    }
    return "Sửa giáo viên không thành công";
  }

  public List<Teacher> searchTeacherByName(String TeacherName) {
    return teaDAL.findByUserName(TeacherName);
  }
}