package com.quickly.devploment.redis.webvote.pojo;

/**
 * @Author lidengjin
 * @Date 2020/8/4 3:21 下午
 * @Version 1.0
 */
public class ArticleUser {
	private String name;
	private String id;
	private String voteId;

	public ArticleUser(String name, String id, String voteId) {
		this.name = name;
		this.id = id;
		this.voteId = voteId;
	}

	public ArticleUser() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	// commit -3
}
