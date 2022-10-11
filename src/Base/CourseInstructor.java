package Base;

public class CourseInstructor extends Person {
  private String hireDate;
  public CourseInstructor() {

  }

  public CourseInstructor(int personID, int courseID , String firstName, String lastName, String titleCourse, String nameDepartment) {
    this.hireDate = hireDate;
  }

  public String getHireDate() {
    return hireDate;
  }

  public void setHireDate(String hireDate) {
    this.hireDate = hireDate;
  }

}
