package Base;

public class Teacher extends Person {
  private String enrollmentDate;

  public Teacher() {

  }

  public Teacher(int personID, String lastName, String firstName, String enrollmentDate) {
    super(personID, lastName, firstName);
    this.enrollmentDate = enrollmentDate;
  }

  public String getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(String enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

}
