package com.bridgelabz.dto;

public class PostDto {
	private String title;
	private String notes;
	
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostDto(String title, String notes) {
		super();
		this.title = title;
		this.notes = notes;
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
	@Override
	public String toString() {
		return "PostDto [title=" + title + ", notes=" + notes + "]";
	}
	

}
