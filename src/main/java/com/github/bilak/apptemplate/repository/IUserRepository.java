package com.github.bilak.apptemplate.repository;

import com.github.bilak.apptemplate.domain.Role;
import com.github.bilak.apptemplate.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Long> {

    User findOneByEmail(String email);

    @Query(value = "SELECT u.role FROM User u WHERE u.id = ?1")
    List<Role> getUserRoles(long id);
}
