package com.quickly.devploment.redis.webvote.vote;

import com.alibaba.fastjson.JSON;
import com.quickly.devploment.redis.webvote.decoupling.DecouplingUtil;
import com.quickly.devploment.redis.webvote.pojo.Article;
import com.quickly.devploment.redis.webvote.pojo.ArticleUser;
import com.quickly.devploment.redis.webvote.util.ArticleUtils;
import com.quickly.devploment.redis.webvote.util.DateUtils;
import com.quickly.devploment.redis.webvote.util.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:33 下午
 * @Version 1.0
 */
@Slf4j
public class Vote {

	private static final String REDIS_SCORE_KEY = "article_hmset_3:";

	private static final int VOTE_SCORE = 432; // 86400 / 200 = 432 ,200 数量，86400 一天的秒数。 文章每获取一张票 就是增加 432 分

	private static final int WEEK_IN_DAYS = 3 * 86400;

	private static final String REDIS_USER_KEY = "user_";

	private static final String REDIS_USER_HAVE_ARTICLE_KEY = "users_have_article_";

	private static final String REDIS_ARTICLE_KEY = REDIS_USER_KEY + "article_";


	private static final String SCORE_KEY = "score_";

	private static final String NUMS_ARTICLE = "article_nums_";


	public static String voteArticle(ArticleUser articleUser) {
		Jedis jedis = RedisManager.getJedis();
		List<Article> articles = ArticleUtils.buildArticles();
		articles.forEach(article -> {

			HashMap<String, String> objectObjectHashMap = new HashMap<>();
			objectObjectHashMap.put("id",article.getId());
			objectObjectHashMap.put("poster",article.getPoster());
			objectObjectHashMap.put("time", DateUtils.formatDate2String(article.getTime(),"yyyy-MM-dd"));
			objectObjectHashMap.put("link", article.getLink());
			objectObjectHashMap.put("votes", article.getVotes());
			objectObjectHashMap.put("title", article.getTitle());
			jedis.hmset(REDIS_SCORE_KEY+article.getId(), objectObjectHashMap);
//			jedis.set(REDIS_SCORE_KEY + article.getId(), article.getVotes());
			// 索引 分数的高低
			jedis.zadd(REDIS_SCORE_KEY, Double.parseDouble(article.getVotes()), article.getId());
			jedis.expire(REDIS_SCORE_KEY + article.getId(), WEEK_IN_DAYS);
		});
		// 投票文章 id
		String voteId = articleUser.getVoteId();
		voteId = REDIS_SCORE_KEY + voteId;
		if (!jedis.exists(voteId)) {
			return "文章不存在";
		}
		Set<String> user_members = jedis.smembers(REDIS_USER_KEY + articleUser.getVoteId());
		if (user_members.contains(articleUser.getId())) {
			return "该用户已经投过，请勿再投票";
		}
		jedis.zincrby(REDIS_ARTICLE_KEY + articleUser.getVoteId(), VOTE_SCORE, JSON.toJSONString(articleUser));
		jedis.incrBy(SCORE_KEY + articleUser.getVoteId(), VOTE_SCORE);
		jedis.hincrBy(NUMS_ARTICLE + articleUser.getVoteId(), "votes", 1);
		jedis.sadd(REDIS_USER_KEY + articleUser.getVoteId(), articleUser.getId());
		jedis.sadd(REDIS_USER_HAVE_ARTICLE_KEY + articleUser.getId(), articleUser.getVoteId());
		// 文章自增 分数 取出评分最高的几个
		Set<String> zrevrange = jedis.zrevrange(REDIS_SCORE_KEY, 0, 2);
		log.info("result {}", zrevrange);
		zrevrange.stream().forEach(id->{
			Map<String, String> stringStringMap = jedis.hgetAll(REDIS_SCORE_KEY + id);
			log.info("result map {}", stringStringMap);
		});
		return "success";
	}

	public static void main(String[] args) {
		// 执行投票
		ArticleUser articleUser = new ArticleUser();
		articleUser.setId("10006");
		articleUser.setName("投票人1");
		articleUser.setVoteId("123456" + "2");
		String result = DecouplingUtil.executeVote(() -> voteArticle(articleUser));
		log.info("vote article {}", result);
	}

	@Test
	public void testJedis() {
		RedisManager.getConnection();
		Jedis jedis = RedisManager.getJedis();
//		ArticleUser articleUser = new ArticleUser();
//		articleUser.setId("11411");
//		articleUser.setName("投票人1");
//		articleUser.setVoteId("100861");
//		//		jedis.sadd();
//		//		jedis.sadd(articleUser.getId(), JSON.toJSONString(articleUser));
//		//		jedis.zincrby("10086113", 20, JSON.toJSONString(articleUser));
//		jedis.incrBy("10086113", 20);
//		String s = jedis.get("10086113");
//		System.out.println(s);

//		List<Article> articles = ArticleUtils.buildArticles();
//		articles.forEach(article -> {
//			HashMap<String, String> objectObjectHashMap = new HashMap<>();
//			objectObjectHashMap.put("id",article.getId());
//			objectObjectHashMap.put("poster",article.getPoster());
//			objectObjectHashMap.put("time", DateUtils.formatDate2String(article.getTime(),"yyyy-MM-dd"));
//			objectObjectHashMap.put("votes", article.getVotes());
//			jedis.hmset(REDIS_SCORE_KEY+article.getId(), objectObjectHashMap);
////			jedis.zadd("mulu", article.getId());
//			// 索引
//			jedis.zadd(REDIS_SCORE_KEY, Double.parseDouble(article.getVotes()), article.getId());
////			jedis.expire(REDIS_SCORE_KEY+article.getId(), WEEK_IN_DAYS);
//		});
		//			jedis.set(REDIS_SCORE_KEY + article.getId(), article.getVotes());
//		if (jedis.exists("100861")) {
//			System.out.println("bucunzai");
//		}
//
//		jedis.zadd("111",10,"name2");
//		jedis.zadd("121",2,"name2");
//		jedis.zadd("131",40,"name2");
//
//
//		Map<String, String> stringStringMap = jedis.hgetAll(REDIS_SCORE_KEY + "1234561");
//		log.info("result map {}", stringStringMap);
		// 文章自增 分数
		Set<String> zrevrange = jedis.zrevrange(REDIS_SCORE_KEY, 0, 2);
		log.info("result {}", zrevrange);
		zrevrange.stream().forEach(id->{
			Map<String, String> stringStringMap = jedis.hgetAll(REDIS_SCORE_KEY + id);
			log.info("result map {}", stringStringMap);
		});
		//		jedis.hincrBy("h10086113", "votes", 1);
		jedis.close();


	}
}
