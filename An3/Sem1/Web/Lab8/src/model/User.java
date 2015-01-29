package model;

import java.io.InputStream;

public class User {
	private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String age;
    private String hometown;
    private String photoPath;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String name, String email, String age, String hometown, String photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.hometown = hometown;
        this.photoPath = photo;
    }

    public User(String username, String password, String name, String email, String age, String hometown) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.hometown = hometown;
    }

    public User(int id, String username, String password, String name, String email, String age, String hometown) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.hometown = hometown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}

