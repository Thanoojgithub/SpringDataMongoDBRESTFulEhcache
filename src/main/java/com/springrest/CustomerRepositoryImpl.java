package com.springrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.springrest.vo.User;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public Boolean delete(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User findAndRemove = mongoOperations.findAndRemove(query, User.class);
		return findAndRemove.getId() != null ? true : false;
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
	public void save(User persisted) {
		mongoOperations.save(persisted);
	}

}
