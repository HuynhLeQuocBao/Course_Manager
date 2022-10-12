package DAL;

import java.sql.*;
import java.util.*;

import Base.Department;

public class departmentDAL {
    databaseConnect dc = new databaseConnect();

    public List<String> getAllDepartment() {
        List<String> departmentList = new ArrayList<String>();

        if (dc.openConnection()) {
            try {
                // query
                String sql = "select DepartmentID, Name from department";
                Statement statement = dc.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                String idAndNameDepartment = "";
                while (resultSet.next()) {
                    idAndNameDepartment = resultSet.getString("DepartmentID") + "_" + resultSet.getString("Name");
                    departmentList.add(idAndNameDepartment);
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                dc.closeConnection();
            }
        }
        return departmentList;
    }
}
