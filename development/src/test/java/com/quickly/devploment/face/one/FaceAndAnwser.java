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

	2 MySQL的数据如何恢复到任意时间点？
		1 首先 MySQL 一条SQL 经历哪些事情？ MySQL 分为 Server 和 存储引擎层。server 层 包含连接器，分析器，优化器。执行器。存储引擎层 就是数据的存储和提取。
			select {
				客户端连接
				查询缓存 （有返回，没有下一步）
				解析器，语法解析，解析树，预处理器，查询优化器。
				执行计划，查询执行引擎（通过API 查询 innodb 等 数据。---此时涉及到了 server层与存储层的交互）
				返回结果，缓存结果。
			}
			长链接与短连接，优点与缺点。如何解决？定期断开长链接。不然随着连接数的增加，导致内存占用升高。当释放了连接之后，会回滚所有的事务，释放所有的表锁，临时表会被清除等等。
			缓存功能，不一定好，当有大量的查询和大量的修改时，cache 机制可能会造成性能下降。当每次表的结构或者数据发生改变的时候，缓存中的数据不再有效。
			词法解析 判断是查询还是修改。
			优化器，当是select 的时候，会分析怎么才会更快。选择合适的索引，选择走表还是走索引，优化 where 子句等等。
			执行器 和存储引擎打交道。进行数据的交互。
		2 update 是如何执行的？ （http://blog.itpub.net/31559358/viewspace-2221403/）
			update 和 select 执行流程一致，只是最后的执行器 是找到这条数据，然后进行更新。另外更新过程 涉及到一个重要的日志模块，即 redo log 重做日志和 binlog 归档日志。
			redo log ，innodb 记录了对数据文件的物理更改，保证总是日志先行。在数据持久化之前，保证 redo log 已经写到了磁盘。每一次更新 先 redo log ,再更新到 内存，适当的时候 更新到磁盘。
			在对数据更改的时候，先把数据页从磁盘读到 buffer pool,然后在buffer pool 中进行修改。此时 buffer pool 和磁盘的数据 内容不一致，所以此时称buffer pool 的数据页 为脏数据。
			如果此时db 重启，那么数据就会丢失。所以需要一个文件进行记录日志，这个文件就是 redo log.进行顺序记录。
			在事务开始之后 就产生 redo log ,redo log 的落盘 不是随着事务的提交才写入，而是在事务的执行过程中，便开始写入redo log。
			redo log 文件以 ib_命名，顺序写入文件。写满的时候回溯到第一个文件 进行覆盖写。覆盖之前，要保证对应的数据 已经刷到了磁盘上。redo log 文件是循环写入的。在覆盖写之前 要保证对应的脏页已经到了磁盘上。
			非常大的负载之下，redo log 可能会产生的非常快。导致频繁的刷脏操作，导致性能下降。建议调整 大 redo log 的大小。
			redo log 有缓存区，默认8m ，innodb 先将重做日志 写入到 innodb_log_buffer 缓存中.
				{
				   1、Master Thread 每秒一次执行刷新 Innodb_log_buffer 到重做日志文件；
				   2、每个事务提交时会将重做日志刷新到重做日志文件；
				   3、当 redo log 缓存可用空间少于一半时，重做日志缓存被刷新到重做日志文件；
				}
			redo log 属于引擎层的 特有的日志。而server 有自己的日志 ，binlog
			binlog 可以认为是 执行过 事务中的 sql 语句。逻辑格式的日志。
			当事务提交的时候 一次性 将事务中的sql 语句 按照一定的格式 写入到 binlog 。
			redo log 与 binlog 区别
			{
				redo log 是 innodb 引擎持有的，binlog 是 server 层的，所有的引擎都可以使用。
				内容不同，redo log 是物理日志。记录的是数据页上的改变。正在执行中的dml 或者 ddl 语句。binlog 是逻辑日志。记录的是语句的原始逻辑。已经提交之后的语句。
				写的方式不同，redo log 是循环使用的。binlog 是一直追加写的。不会被覆盖
				作用不同 redo log 保证事务的安全。作为异常 down 或者故障导致的数据恢复使用。binlog 作为 主从复制 和即时点恢复。
			}
			所以更新的时候	语句执行流程
				1 执行器 找到引擎 找到目标数据，如果数据所在的数据页 在内存中，就直接返回，否则 先从磁盘读入到内存。然后再返回
				2 执行器拿到数据，把数据修改，然后再写入到引擎接口
				3 引擎拿到数据更新到内存中，同时将更新操作 写入到 redo log ，此时 redo log 处于 prepare 状态，然后告知执行器 完成了，可以随时提交事务了
				4 执行器 生成这个操作的 binlog ,并将 binlog 写入磁盘。
				5 执行器 调用引擎的事务提交接口。把 刚才的redo log 改成 commit ,更新完成。
			redo log 和 binlog 两阶段提交。prepare 和 commit ,所以事务的数据 同时存在redo log ,和 binlog 或者同时不存在，保证了顺序一致性。
			那么如何恢复到任意时间的数据？
				1 首先找到最近的一次全量的备份。把备份恢复到临时库
				2 从备份的时间点开始 将备份的binlog 依次取出来。重放到误删除表之前的时刻。 这样就把表数据从临时库取出来，恢复到线上库去。
			1、prepare 阶段； 2、写binlog 阶段；3、commit 阶段
			当在2之前崩溃时 重启恢复后发现没有commit，回滚。备份恢复：没有 binlog 。一致。
			当在3之前崩溃，重启恢复：虽没有commit，但满足prepare和binlog完整，所以重启后会自动commit，备份：有binlog，一致，总结起来说就是如果一个事务在 prepare 阶段中落盘成功，并在 MySQL Server 层中的 binlog 也写入成功，那这个事务必定 commit 成功
	 */

	/*
	 3 输入 ping IP 后敲回车，发包前会发生什么
	 	1 ping目标ip时，先查路由表，确定出接口
	 	2 如果落在直连接口子网内，此时若为以太网等多路访问网络 则先查询arp缓存，命中则直接发出，否则在该接口上发arp询问目标ip的mac地址，取得后发出，若为ppp等 点对点网络 ，则直接可以发出
	 	3 如果查表落在缺省路由上，此时若为以太网等多路访问网络 则先查询网关arp缓存，命中则直接发出，否则在该接口上发arp询问网关的mac地址，取得后发出，若为ppp等 点对点网络 ，则直接可以发出；
	 	若查表未命中，则返回不可达
	 */
}