package com.bridgelabz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	@Column(length = 400)
	private String notes;
	private boolean archive=false;
	private boolean trash=false;
	private int day;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int id, String title, String notes, boolean archive, boolean trash,int day) {
		super();
		this.id = id;
		this.title = title;
		this.notes = notes;
		this.archive = archive;
		this.trash = trash;
		this.day=day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", notes=" + notes + ", archive=" + archive + ", trash=" + trash
				+ ", day=" + day + "]";
	}

}
