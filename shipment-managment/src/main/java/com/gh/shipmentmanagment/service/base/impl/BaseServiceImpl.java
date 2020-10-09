package com.gh.shipmentmanagment.service.base.impl;

import com.gh.shipmentmanagment.service.base.BaseService;
import com.gh.shipmentmanagment.template.TemplateMapper;
import com.gh.shipmentmanagment.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * base service
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */
public class BaseServiceImpl<T extends TemplateMapper, E> implements BaseService<E> {

    final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    T t;

    @Override
    public E get(Long id) {
        return (E) t.selectByPrimaryKey(id);
    }

    @Override
    public long batchAdd(List<E> e) {
        return t.insertList(e);
    }

    @Override
    public long add(E e) {

        int result = t.insert(e);
        Object id = ReflectionUtils.getFieldValue(e, "id");
        if (null != id) {
            return (Long) id;
        } else {
            logger.error("Error, id is null");
        }
        return result;
    }


    @Override
    public int update(E e) {
        return t.updateByPrimaryKey(e);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return t.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(E e) {
        return t.delete(e);
    }

    @Override
    public List<E> selectAll() {
        return t.selectAll();
    }

    @Override
    public List<E> select(E e) {
        return t.select(e);
    }

}
