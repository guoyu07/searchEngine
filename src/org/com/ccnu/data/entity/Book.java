package org.com.ccnu.data.entity;

public class Book {

	private String abstracts;
	private int id;

	private String name;

	private String other;

	private float score;

	private String url;
	


	public Book(String abstracts, int id, String name, String other, float score, String url) {
		super();
		this.abstracts = abstracts;
		this.id = id;
		this.name = name;
		this.other = other;
		this.score = score;
		this.url = url;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOther() {
		return other;
	}

	public float getScore() {
		return score;
	}

	public String getUrl() {
		return url;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", other=" + other + ", abstracts=" + abstracts + "]";
	}

}
