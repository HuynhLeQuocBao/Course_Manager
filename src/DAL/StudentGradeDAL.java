package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSOutput;

import com.mysql.jdbc.PreparedStatement;

import Base.*;

public class StudentGradeDAL {
  databaseConnect dc = new databaseConnect();
  
  public ArrayList<StudentGrade> findAll()
  {
    ArrayList<StudentGrade> StudenGradeList = new ArrayList<StudentGrade>();
    
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT * FROM `studentgrade`";
        Statement statement = dc.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next())
        {
          StudentGrade stg = new StudentGrade(
              rs.getInt("enrollmentID"),
              rs.getInt("courseID"),
              rs.getInt("StudentID"),
              rs.getDouble("Grade"));
          StudenGradeList.add(stg);
        }
      }
      catch(SQLException ex)
      {
        System.out.print(ex);
      }
      finally {
        dc.closeConnection();
      }
    }
    return StudenGradeList;
    
  }
  public boolean add(StudentGrade std)
  {
    boolean rs = false;
    if(dc.openConnection())
    {
      try
      {
        String sql = "INSERT INTO `studentgrade`( `CourseID`, `StudentID`, `Grade`) VALUES ("
      +std.getCourseID()+","+std.getStudenID()+","+ std.getGrade()+")";
        Statement statement = dc.connection.createStatement();
        int i= statement.executeUpdate(sql);
        if(i > 0)
        {
          rs = true;
        }   
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
      }
      finally {
        dc.closeConnection();
      }
    }
    return rs;
  }
  public boolean addwithoutGrade(int courseid, int studentid)
  {
    boolean rs = false;
    if(dc.openConnection())
    {
      try
      {
        String sql = "INSERT INTO `studentgrade`( `CourseID`, `StudentID`, `Grade`) VALUES ("
      +courseid+","+studentid+",NULL)";
        Statement statement = dc.connection.createStatement();
        int i= statement.executeUpdate(sql);
        if(i > 0)
        {
          rs = true;
        }   
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
      }
      finally {
        dc.closeConnection();
      }
    }
    return rs;
  }
  public boolean update(StudentGrade std)////cập nhật điểm
  {
    boolean rs = false;
    if(dc.openConnection())
    {
      try
      {
        String sql = "UPDATE `studentgrade` SET `Grade`= "+
            std.getGrade()+" WHERE `EnrollmentID` =" +std.getEnrollmentID();
            Statement stm = dc.connection.createStatement();
            int i = stm.executeUpdate(sql);
            if(i>0) {
              rs = true;
            }
      }
      catch(SQLException ex)
      {
        System.out.print(ex);
      }
      finally
      {
        dc.closeConnection();
      }
    }
    return rs;
  }
  public boolean delete(int EnrollmentID)
  {
    boolean rs =false;
    if(dc.openConnection())
    {
      try
      {
        String sql = "DELETE FROM `studentgrade` WHERE `EnrollmentID` = " + EnrollmentID;
        Statement stm = dc.connection.createStatement();
        int i = stm.executeUpdate(sql);
        if(i > 0)
        {
          rs = true;
        }
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
      }
      finally {
        dc.openConnection();
      }
    }
    return rs;
  }
  public ArrayList<String> getAllCourse()
  {
    ArrayList<String> s =new ArrayList<String>();
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT courseID FROM course";
        Statement stm = dc.connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
          s.add(""+rs.getInt("courseID"));
        }
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
        
      }
      finally
      {
        dc.closeConnection();
      }
    }
    return s;
  }
  public String CourseName(int courseID)
  {
    String s="";
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT Title FROM course WHERE CourseID = "+courseID;
        Statement stm = dc.connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
          s=rs.getString("Title");
        }
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
      }
      finally
      {
        dc.closeConnection();
      }
    }
    return s;
  }
  public ArrayList<String> getAllStudent()
  {
    ArrayList<String> s =new ArrayList<String>();
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT PersonID FROM person;";
        Statement stm = dc.connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
          s.add(""+rs.getInt("PersonID"));
        }
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
        
      }
      finally
      {
        dc.closeConnection();
      }
    }
    return s;
  }
  public String StudentName(int StudentID)
  {
    String s="";
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT Firstname,Lastname FROM `person` WHERE PersonID = "+StudentID;
        Statement stm = dc.connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next())
        {
          s=rs.getString("Firstname")+" "+ rs.getString("Lastname");
        }
      }
      catch(SQLException ex)
      {
        System.out.println(ex);
      }
      finally
      {
        dc.closeConnection();
      }
    }
    return s;
  }
  public ArrayList<StudentGrade> searchBycode(int codeID)
  {
    ArrayList<StudentGrade> searchBycodeList = new ArrayList<StudentGrade>();
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT * FROM `studentgrade` "
            + "WHERE EnrollmentID = "+codeID+" OR CourseID = "+codeID+" OR StudentID ="+codeID;
        Statement statement = dc.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next())
        {
          StudentGrade stg = new StudentGrade(
              rs.getInt("enrollmentID"),
              rs.getInt("courseID"),
              rs.getInt("StudentID"),
              rs.getDouble("Grade"));
          searchBycodeList.add(stg);
        }
      }
      catch(SQLException ex)
      {
        System.out.print(ex);
      }
      finally {
        dc.closeConnection();
      }

    }
    return searchBycodeList;
  }
  public ArrayList<StudentGrade> searchByname(String name)
  {
    ArrayList<StudentGrade> searchBynameList = new ArrayList<StudentGrade>();
    if(dc.openConnection())
    {
      try
      {
        String sql = "SELECT *\r\n"
            + "FROM studentgrade \r\n"
            + "WHERE CourseID IN (\r\n"
            + "SELECT CourseID\r\n"
            + "FROM course\r\n"
            + "WHERE Title LIKE '%"+name+"%'\r\n"
            + ")\r\n"
            + "UNION\r\n"
            + "SELECT *\r\n"
            + "FROM studentgrade \r\n"
            + "WHERE StudentID IN (\r\n"
            + "SELECT personID\r\n"
            + "FROM person\r\n"
            + "WHERE FIRSTNAME LIKE '%"+name+"%' OR lastname LIKE '%"+name+"%'\r\n"
            + ")";
        Statement statement = dc.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next())
        {
          StudentGrade stg = new StudentGrade(
              rs.getInt("enrollmentID"),
              rs.getInt("courseID"),
              rs.getInt("StudentID"),
              rs.getDouble("Grade"));
          searchBynameList.add(stg);
        }
      }
      catch(SQLException ex)
      {
        System.out.print(ex);
      }
      finally {
        dc.closeConnection();
      }

    }
    return searchBynameList;
  }
  public static void main(String[] args) {
    StudentGradeDAL a = new StudentGradeDAL();
    StudentGrade ab = new StudentGrade(25, 4061, 21, 3.55);
    
    System.out.println(a.searchByname("a").toString());
  }
}
