package com.quickly.devploment.mapper;

import com.quickly.devploment.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/16 10:46
 * @Version V-1.0
 **/
public interface UserMapper {
	int saveUser(@Param("user") UserPojo userPojo);
}
