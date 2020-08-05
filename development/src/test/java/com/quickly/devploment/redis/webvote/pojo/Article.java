package com.quickly.devploment.redis.webvote.pojo;

import java.util.Date;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:28 下午
 * @Version 1.0
 */
public class Article {
	private String title;
	private String link;
	private String poster;
	private String votes;
	private Date time;
	private String id;

	public Article(String title, String link, String poster, String votes, Date time, String id) {
		this.title = title;
		this.link = link;
		this.poster = poster;
		this.votes = votes;
		this.time = time;
		this.id = id;
	}

	public Article() {
	}

	@Override
	public String toString() {
		return "Article{" + "title='" + title + '\'' + ", link='" + link + '\'' + ", poster='" + poster + '\''
				+ ", votes='" + votes + '\'' + ", time=" + time + ", id='" + id + '\'' + '}';
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getVotes() {
		return votes;
	}

	public void setVotes(String votes) {
		this.votes = votes;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
