package Base;

public class StudentGrade {
      private int enrollmentID;
      private int courseID;
      private int studenID;
      private double grade;
      
      public StudentGrade(int enrollmentID,int courseID, int studentID, double grade) {
        // TODO Auto-generated constructor stub
        this.enrollmentID = enrollmentID;
        this.courseID = courseID;
        this.studenID = studentID;
        this.grade = grade;
      }
      
      public void setCourseID(int courseID) {
        this.courseID = courseID;
      }
      
      public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
      }
      
      public void setStudenID(int studenID) {
        this.studenID = studenID;
      }
      
      public void setGrade(int grade) {
        this.grade = grade;
      }
      
      public int getEnrollmentID() {
        return enrollmentID;
      }
      
      public int getCourseID() {
        return courseID;
      }
      
      public int getStudenID() {
        return studenID;
      }
      
      public double getGrade() {
        return grade;
      }
}
