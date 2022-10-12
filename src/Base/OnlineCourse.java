package Base;

public class OnlineCourse extends Course {
    private String url;

    public OnlineCourse() {

    }

    public OnlineCourse(int courseID, String title, String credits, int departmentID, String url) {
        super(courseID, title, credits, departmentID);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
