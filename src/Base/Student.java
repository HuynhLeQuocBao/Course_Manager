package Base;

import java.util.Date;

public class Student extends Person {
  private Date hireDate;

  public Student() {

  }

  public Student(int personID, String lastName, String firstName, Date hireDate) {
    super(personID, lastName, firstName);
    this.hireDate = hireDate;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

}
