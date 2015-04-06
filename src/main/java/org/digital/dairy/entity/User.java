package org.digital.dairy.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;
/*
@Data
@ToString*/
@Entity
public class User {

		@Id
		@GeneratedValue
		private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Column(unique=true)
		private String username;
		
		private String email;
		
		private String password;
		@OneToOne
		private Role roles;
		@OneToMany(mappedBy = "user")
		private List<Blog> blogs;
		@OneToMany(mappedBy = "blog")
		private List<Item> items;


}
