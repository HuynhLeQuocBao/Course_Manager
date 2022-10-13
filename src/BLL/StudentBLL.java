package BLL;

import java.util.*;
import DAL.StudentDAL;
import Base.Student;

public class StudentBLL {
    StudentDAL stuDAL = new StudentDAL();

    public List<Student> getAllStudent() {
        return stuDAL.findAll();
    }

    public String addStudent(Student p) {
        if (stuDAL.hasPersonID(p.getPersonID())) {
            return "Mã sinh viên bị trùng. Vui lòng nhập lại";
        }
        if (stuDAL.insert(p)) {
            return "Thêm sinh viên thành công";
        }
        return "Thêm sinh viên không thành công";
    }

    public String deleteStudent(int id) {
        if (stuDAL.hasPersonIdWhenRemove(id)) {
            return "Mã sinh viên đang được sử dụng. Không được xóa";
        }
        if (stuDAL.delete(id)) {
            return "Xóa sinh viên thành công";
        }
        return "Xóa sinh viên không thành công";
    }

    public String editStudent(Student p) {
        if (stuDAL.update(p)) {
            return "Sửa sinh viên thành công";
        }
        return "Sửa sinh viên không thành công";
    }

    public List<Student> searchStudentByName(String studentName) {
        return stuDAL.findByUserName(studentName);
    }

}
