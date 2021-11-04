package com.douzone.jblog.vo;

public class CategoryDTO {
	private int no;
	private String name;
	private String desc;
	private String blogId;
	private int countPost;
	
	public int getCountPost() {
		return countPost;
	}

	public void setCountPost(int countPost) {
		this.countPost = countPost;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "CategoryDTO [no=" + no + ", name=" + name + ", desc=" + desc + ", blogId=" + blogId + ", countPost="
				+ countPost + "]";
	}

}
