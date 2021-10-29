package com.douzone.jblog.vo;

public class BlogVO {
	private String id;
	private String title;
	private String log;

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

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "BlogVO [id=" + id + ", title=" + title + ", log=" + log + "]";
	}

}
