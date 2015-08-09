package com.springdata.springdatamongodbrestfulehcache.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.springdata.springdatamongodbrestfulehcache.vo.User;
import com.springdata.springdatamongodbrestfulehcache.vo.UserSequenceIdGenerator;

@Component
public class UserRepositoryImpl implements UserRepository {

	private static final String SEQ_KEY = "userSequenceId";

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOperations;

	@Override
	public User delete(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoOperations.findAndRemove(query, User.class);
	}

	@Override
	public List<User> findAll() {
		return mongoOperations.findAll(User.class);
	}

	@Override
	public User findOne(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoOperations.findOne(query, User.class);
	}

	/**
	 * Save – It should rename to saveOrUpdate(), it performs insert() if “_id”
	 * is NOT exist or update() if “_id” is existed”. Insert – Only insert, if
	 * “_id” is existed, an error is generated.
	 */
	@Override
	public User save(User persisted) {
		persisted.setId(getNextSequenceId(SEQ_KEY));
		mongoOperations.save(persisted);
		return persisted;
	}

	public long getNextSequenceId(String key) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(SEQ_KEY));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		UserSequenceIdGenerator userSequenceIdGenerator = mongoOperations.findAndModify(query, update, options, UserSequenceIdGenerator.class);
		return userSequenceIdGenerator.getSeq();

	}

}