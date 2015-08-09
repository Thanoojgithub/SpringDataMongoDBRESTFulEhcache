package com.springdata.springdatamongodbrestfulehcache.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springdata.springdatamongodbrestfulehcache.vo.User;

public interface UserRepository extends Repository<User, Long> {

	User delete(Long id);

	List<User> findAll();

	User findOne(Long id);

	User save(User persisted);
}
