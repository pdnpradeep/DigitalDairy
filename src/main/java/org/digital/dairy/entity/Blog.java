package org.digital.dairy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
/*	private String url;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;*/
}
