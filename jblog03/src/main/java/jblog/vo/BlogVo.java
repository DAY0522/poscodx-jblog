package jblog.vo;

public class BlogVo {
    private String id;
    private String title;
    private String profile;

    public BlogVo() {
    }

    public BlogVo(String id, String title, String profile) {
        this.id = id;
        this.title = title;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
