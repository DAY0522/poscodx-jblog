package jblog.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserVo {
    @Size(min = 2, max = 8)
    private String id;
    @NotEmpty
    private String name;
    @Size(min = 2, max = 8)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
