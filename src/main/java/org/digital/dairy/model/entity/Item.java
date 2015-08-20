package org.digital.dairy.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String title;
/*
	private String description;
	
	@Column(name="published_date")
	private Date publishedDate;
	
	private String link;
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;*/
	
}
