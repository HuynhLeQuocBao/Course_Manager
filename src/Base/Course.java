package Base;

public class Course extends Person {
    private int courseID;
    private String title;
    private String credits;
    private int department;
    
    public Course() {
      super();
      // TODO Auto-generated constructor stub
    }

   

    public Course(int courseID, String title, String credits, int department, String firstName, String lastName) {
      super(firstName, lastName);
      this.courseID = courseID;
      this.title = title;
      this.credits = credits;
      this.department = department;
    }
    public Course( String title,String firstName, String lastName) {
      super(firstName, lastName);
      this.title = title;
    }


    public int getCourseID() {
      return courseID;
    }

    public void setCourseID(int courseID) {
      this.courseID = courseID;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

   

    public String getCredits() {
      return credits;
    }



    public void setCredits(String credits) {
      this.credits = credits;
    }



    public int getDepartment() {
      return department;
    }

    public void setDepartment(int department) {
      this.department = department;
    }
    
}
