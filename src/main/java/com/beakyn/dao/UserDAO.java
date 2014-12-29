package com.beakyn.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.beakyn.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, String> {

    User findOneByEmail(String email);

    Page<User> findAll(Pageable pageable);


}
