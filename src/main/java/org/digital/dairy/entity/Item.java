package org.digital.dairy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
