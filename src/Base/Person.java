package Base;

public class Person {
  private int personID;
  private String lastName;
  private String firstName;

  public Person() {
  }

  public Person(int personID, String lastName, String firstName) {
    this.personID = personID;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public Person(String firstName, String lastName ) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public int getPersonID() {
    return personID;
  }

  public void setPersonID(int personID) {
    this.personID = personID;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

}
