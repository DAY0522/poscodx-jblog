package jblog.vo;

public class CategoryVo {
    private Long id;
    private String blogId;
    private String name;
    private String description;
    private Integer count;

    public CategoryVo() {
    }

    public CategoryVo(Long id, String blogId, String name, String description) {
        this.id = id;
        this.blogId = blogId;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", blogId='" + blogId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
