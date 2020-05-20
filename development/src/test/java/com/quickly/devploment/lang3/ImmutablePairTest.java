package com.quickly.devploment.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lidengjin
 * @Date 2020/5/18 9:50 上午
 * @Version 1.0
 */
@Slf4j
public class ImmutablePairTest {

	@Test
	public void testImmutablePair(){
		ImmutablePair immutablePair = new ImmutablePair("s","1");
		ImmutablePair immutablePair1 = new ImmutablePair("s","2");
		log.info("the value of immutablePair {} , {}", immutablePair,immutablePair1);

		Map<ImmutablePair<String,String>, Integer> map = new HashMap<>();
		map.put(immutablePair,1);
		map.put(immutablePair1,2);

		log.info("the immutablePair map is {}", map);

	}
}
