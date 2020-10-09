package com.gh.shipmentmanagment.service.base;

import java.util.List;

/**
 * base service
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */

public interface BaseService<E> {
    E get(Long id);

    long batchAdd(List<E> e);

    long add(E e);

    int update(E e);

    int delete(E e);

    int deleteByPrimaryKey(Long id);

    List<E> selectAll();

    List<E> select(E e);
}
