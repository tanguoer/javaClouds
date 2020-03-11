package com.clouds.domain;

import java.util.Objects;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 14:05
 * @Version V1.0
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Long MaxStorage;
    private Long UseStorage;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", MaxStorage=" + MaxStorage +
                ", UseStorage=" + UseStorage +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMaxStorage() {
        return MaxStorage;
    }

    public void setMaxStorage(Long maxStorage) {
        MaxStorage = maxStorage;
    }

    public Long getUseStorage() {
        return UseStorage;
    }

    public void setUseStorage(Long useStorage) {
        UseStorage = useStorage;
    }

    public User(Integer id, String username, String password, String email, Long maxStorage, Long useStorage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        MaxStorage = maxStorage;
        UseStorage = useStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(MaxStorage, user.MaxStorage) &&
                Objects.equals(UseStorage, user.UseStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, MaxStorage, UseStorage);
    }

    public User() {
    }
}
