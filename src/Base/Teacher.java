package Base;

public class Student extends Person {
  private String hireDate;

  public Student() {

  }

  public Student(int personID, String lastName, String firstName, String hireDate) {
    super(personID, lastName, firstName);
    this.hireDate = hireDate;
  }

  public String getHireDate() {
    return hireDate;
  }

  public void setHireDate(String hireDate) {
    this.hireDate = hireDate;
  }

}
