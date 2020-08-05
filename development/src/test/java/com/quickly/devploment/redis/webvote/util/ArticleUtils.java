package com.quickly.devploment.redis.webvote.util;

import com.quickly.devploment.redis.webvote.pojo.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:34 下午
 * @Version 1.0
 */
public class ArticleUtils {

	private static final int ARTICLE_NUMS = 10;

	private static final List<Article> arrayList = new ArrayList<Article>();

	public static List<Article> buildArticles() {
		for (int i = 0; i < ARTICLE_NUMS; i++) {
			Article article = new Article();
			article.setLink("www.baidu.com");
			article.setPoster("shuaideng" + i);
			article.setTime(DateUtils.addDays(new Date(), - (i % 5)));
			article.setTitle("title1");
			article.setVotes(i+"");
			article.setId("123456"+i);
			arrayList.add(article);
		}
		return arrayList;
	}
}
