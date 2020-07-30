package com.quickly.devploment.face.one;

/**
 * @Author lidengjin
 * @Date 2020/7/30 6:10 下午
 * @Version 1.0
 */
public interface FaceAndAnwser {
	/*
	1 为何 innodb 的索引不应该过长？
		如果是 MyISAM ，那么无所谓，因为索引与数据分离开，在两个文件中，如果是 innodb 的话，索引与数据在一个文件中，那么 普通索引存放着主键索引的地址
		当查询数据的时候 ，先去找主键索引，再去找数据，当数据量大的时候，会浪费内存，索引的占用内存多了，那么存储的数据就少了，所以性能就低了。详见 https://www.cnblogs.com/xibuhaohao/p/11669906.html
	 */
}
