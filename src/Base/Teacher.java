package Base;

public class Teacher extends Person {
  private String hireDate;

  public Teacher() {

  }

  public Teacher(int personID, String lastName, String firstName, String hireDate) {
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
