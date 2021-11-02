package com.douzone.jblog.vo;

public class BlogVO {
	private String id;
	private String title;
	private String logo;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BlogVO [id=" + id + ", title=" + title + ", logo=" + logo + "]";
	}

}
