package com.github.bilak.apptemplate.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional(readOnly = true)
public interface IBaseService<T extends Serializable> {

    @Transactional
    T save(T t);

    @Transactional
    void delete(T t);

    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    T get(Long id);

}
