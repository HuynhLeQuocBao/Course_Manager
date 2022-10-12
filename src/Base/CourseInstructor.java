package Base;

public class CourseInstructor extends Course {
  private int courseID;
  private int personID;
  
  
  public CourseInstructor(int courseID, int personID) {
    super();
    this.courseID = courseID;
    this.personID = personID;
  }
  public CourseInstructor(int personID, int courseID,String firstName,String lastName, String title) {
    super(title, firstName, lastName);
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
