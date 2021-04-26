package com.hanul.user.dto;

public class UserReviewDTO {
	private String users_nick, user_review, user_reviewTitle, ent_id;
	//가상의 이미지파일 경로?(불러올때 필요한 경로?)
	private String dbImgPath;

	
	public UserReviewDTO(String users_nick, String user_review, String ent_id, String dbImgPath) {
		super();
		this.users_nick = users_nick;
		this.user_review = user_review;
		this.ent_id = ent_id;
		this.dbImgPath = dbImgPath;
	}

	public String getDbImgPath() {
		return dbImgPath;
	}

	public void setDbImgPath(String dbImgPath) {
		this.dbImgPath = dbImgPath;
	}

	public UserReviewDTO(String users_nick, String user_review, String ent_id) {
		super();
		this.users_nick = users_nick;
		this.user_review = user_review;
		this.ent_id = ent_id;
	}

	public UserReviewDTO() {}

	public String getUsers_nick() {
		return users_nick;
	}

	public void setUsers_nick(String users_nick) {
		this.users_nick = users_nick;
	}

	public String getUser_review() {
		return user_review;
	}

	public void setUser_review(String user_review) {
		this.user_review = user_review;
	}

	public String getUser_reviewTitle() {
		return user_reviewTitle;
	}

	public void setUser_reviewTitle(String user_reviewTitle) {
		this.user_reviewTitle = user_reviewTitle;
	}

	public String getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(String ent_id) {
		this.ent_id = ent_id;
	}
	
}