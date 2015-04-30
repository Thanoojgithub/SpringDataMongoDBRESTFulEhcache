package com.springdata.springdatamongodbrestfulehcache.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.springdata.springdatamongodbrestfulehcache.vo.User;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void delete(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoOperations.findAndRemove(query, User.class);
	}

	@Override
	public List<User> findAll() {
		return mongoOperations.findAll(User.class);
	}

	@Override
	public User findOne(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoOperations.findOne(query, User.class);
	}

	@Override
	public User save(User persisted) {
		mongoOperations.save(persisted);
		return persisted;
	}

}
