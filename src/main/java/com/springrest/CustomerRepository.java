package com.springrest;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springrest.vo.User;

public interface CustomerRepository extends Repository<User, Long> {

	Boolean delete(Long id);

	List<User> findAll();

	User findOne(Long id);

	void save(User persisted);
}
