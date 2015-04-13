package org.digital.dairy.entity;


import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

/*@Data
@ToString*/
@Entity
public class Role {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
	@GeneratedValue

	private Integer id;

	private Integer name;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;


}
