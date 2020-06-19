package com.quickly.devploment;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.RateLimiter;
import com.quickly.devploment.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName TestGuava
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/29 18:53
 * @Version V-1.0
 **/
@Slf4j
public class TestGuava {

	@Test
	public void testListMap() {
		List<UserPojo> userList = Arrays
				.asList(new UserPojo("123", "31", 2), new UserPojo("321", "12345", 2), new UserPojo("433", "4321", 1));
		Multimap<Integer, UserPojo> stageMultiMap = ArrayListMultimap.create();
		userList.forEach(r -> stageMultiMap.put(r.getId(), r));
		System.out.println(stageMultiMap);
	}


	@Test
	public void testHash(){
		UserPojo userPojo1 = new UserPojo("123","password1",2);
		UserPojo userPojo2 = new UserPojo("234","password2",2);
		UserPojo userPojo3 = new UserPojo("345","password3",3);

		List<UserPojo> list = Arrays.asList(userPojo1, userPojo2, userPojo3);

		Map<Integer, BigInteger> collect = list.stream()
				.collect(Collectors.toMap(UserPojo::getId, this::getBigInteger, BigInteger::add));
		log.info("集合{} ", list);
		log.info("Map{} ", collect);

	}

	private BigInteger getBigInteger(UserPojo userPojo) {
		return new BigInteger(userPojo.getUsername());
	}

	@Test
	public void testRateLimiter(){

		RateLimiter limiter = RateLimiter.create(5.0, 5, TimeUnit.SECONDS);
		limiter.setRate(10.0);
		ImmutableSet.Builder<Object> builder = ImmutableSet.builder();
		builder.build();
	}

	@Test
	public void testShiftOperation(){
		int i = -10;
		int shift = 4;
		int shiftResult = i << shift;
		System.out.println(shiftResult);

	}

}
