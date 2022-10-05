package Base;

import java.util.Date;

public class Teacher extends Person {
  private Date enrollmentDate;

  public Teacher() {

  }

  public Teacher(int personID, String lastName, String firstName, Date enrollmentDate) {
    super(personID, lastName, firstName);
    this.enrollmentDate = enrollmentDate;
  }

  public Date getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(Date enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

}
