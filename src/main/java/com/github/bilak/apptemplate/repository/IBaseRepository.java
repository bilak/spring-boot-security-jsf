package com.github.bilak.apptemplate.repository;

import com.github.bilak.apptemplate.domain.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBaseRepository extends PagingAndSortingRepository<BaseEntity, Long> {
}
