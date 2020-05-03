package com.quickly.devploment.supers;

import java.util.List;

/**
 * @ClassName E
 * @Description
 * @Author LiDengJin
 * @Date 2019/12/27 10:36
 * @Version V-1.0
 **/
public interface E {
	List<? extends A> getExtendsAll();
	List<? super A> getSuperAll();
}
