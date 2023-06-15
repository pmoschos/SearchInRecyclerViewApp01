package gr.aueb.cf.newsapp001.models;

public class News {

    private String heading;
    private int titleImage;

    public News() {}

    public News(String heading, int titleImage) {
        this.heading = heading;
        this.titleImage = titleImage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
