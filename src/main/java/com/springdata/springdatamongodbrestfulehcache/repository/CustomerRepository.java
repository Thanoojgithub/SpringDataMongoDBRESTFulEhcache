package com.springdata.springdatamongodbrestfulehcache.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springdata.springdatamongodbrestfulehcache.vo.SavedUser;
import com.springdata.springdatamongodbrestfulehcache.vo.User;

public interface CustomerRepository extends Repository<User, Long> {

	void delete(Long id);

	List<User> findAll();

	User findOne(Long id);

	SavedUser save(SavedUser persisted);
}
