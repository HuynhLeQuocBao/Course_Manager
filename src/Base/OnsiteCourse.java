package Base;

import java.util.Date;

public class OnsiteCourse extends Course {
    private String location;
    private String date;
    private String time;

    public OnsiteCourse() {

    }

    public OnsiteCourse(int courseID, String title, String credits, int departmentID,
            String location, String date, String time) {
        super(courseID, title, credits, departmentID);
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
