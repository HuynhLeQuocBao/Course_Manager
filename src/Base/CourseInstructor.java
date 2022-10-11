package Base;

public class CourseInstructor extends Course {
  private int courseID;
  private int personID;
  
  public CourseInstructor() {
    super();
    // TODO Auto-generated constructor stub
  }

  public CourseInstructor(int courseID, String title, String credits, int department, String firstName,
      String lastName) {
    super(courseID, title, credits, department, firstName, lastName);
    // TODO Auto-generated constructor stub
  }

  
  public CourseInstructor(int personID, int courseID,String firstName,String lastName, String title) {
    super(title, firstName, lastName);
    this.courseID=courseID;
    this.personID= personID;
  }
  public CourseInstructor(int courseID, int personID) {
    this.courseID=courseID;
    this.personID= personID;
  }
  public int getCourseID() {
    return courseID;
  }

  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  public int getPersonID() {
    return personID;
  }

  public void setPersonID(int personID) {
    this.personID = personID;
  }
  
}
