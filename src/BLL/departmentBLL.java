package BLL;

import java.util.*;
import Base.Department;
import DAL.departmentDAL;

public class departmentBLL {
    departmentDAL departmentDAL = new departmentDAL();

    public List<String> getAllDepartment() {
        return departmentDAL.getAllDepartment();
    }
}
