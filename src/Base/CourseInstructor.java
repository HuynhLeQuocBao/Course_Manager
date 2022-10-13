package Base;

public class CourseInstructor extends Course {
  private int courseID;
  private int personID;
  
  
  public CourseInstructor() {
    super();
    // TODO Auto-generated constructor stub
  }
  public CourseInstructor(int courseID, String title, String credits, int departmentID) {
    super(courseID, title, credits, departmentID);
    // TODO Auto-generated constructor stub
  }
  public CourseInstructor(String title, String firstName, String lastName) {
    super(title, firstName, lastName);
    // TODO Auto-generated constructor stub
  }
  public CourseInstructor(int courseID, int personID) {
    super();
    this.courseID = courseID;
    this.personID = personID;
  }
  public CourseInstructor(int personID, int courseID,  String firstName, String lastName, String title) {
    super(firstName, lastName , title);
    this.personID= personID;
    this.courseID=courseID;
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
