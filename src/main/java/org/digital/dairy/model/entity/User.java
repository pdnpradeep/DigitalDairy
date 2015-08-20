package org.digital.dairy.model.entity;

import javax.persistence.*;

/*@Data
@ToString*/
@Entity
public class User {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
/*
    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }*/

        public User() {
            super();
            this.enabled=false;
        }
        @Id
		@GeneratedValue
		private Integer id;
        private String firstname;
        private String lastname;
        @Column(unique=true)
		private String username;
        @Column(unique=true)
		private String email;
        @Column(unique=true)
        private String phoneno;
		private String password;
		@OneToOne(cascade = CascadeType.ALL)
		private Role roles;
        @Column(name = "enabled")
        private boolean enabled;

	/*	@OneToMany(mappedBy = "user")
		private List<Blog> blogs;
		@OneToMany(mappedBy = "blog")
		private List<Item> items;
*/

}
