package com.springrest;

import java.util.Calendar;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.statistics.StatisticsGateway;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.springrest.vo.User;

@Component
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private MongoOperations mongoOperations;

	public String sayHello(String name) {
		logger.debug("sayHello before sleep : " + name + " at : "+ Calendar.getInstance().getTime());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.debug("sayHello after sleep: " + name + " at : "+ Calendar.getInstance().getTime());
		String str = new String("Hello " + WordUtils.capitalize(name) + ", openShift welcomes you. TIMESTAMP :: "
				+ Calendar.getInstance().getTime());
		return str;
	}

	public void getCacheStatistics() {
		Cache cache = this.cacheManager.getCache("customerServiceCache");
		if (null != cache && Status.STATUS_ALIVE.equals(cache.getStatus())) {
			StatisticsGateway statistics = cache.getStatistics();
			ToStringBuilder.reflectionToString(statistics,ToStringStyle.MULTI_LINE_STYLE);
		}
		
	}
	
	@Override
	public User getUser(String id) {
		return mongoOperations.findById(id, User.class);
	}
	
	@Override
	public User deleteUser(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User findAndRemove = mongoOperations.findAndRemove(query, User.class);
		return findAndRemove;
	}

	@Override
	public User upsertUser(String id, String firstName, String lastName, String location) {
		System.out.println("upsertUser :: "+id + " "+firstName +" "+lastName+" "+location);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("id", id);
		update.set("firstName", firstName);
		update.set("lastName", lastName);
		update.set("location", location);
		System.out.println(query + " "+update);
		mongoOperations.upsert(query, update, User.class);
		User user= mongoOperations.findById(id, User.class);
		System.out.println("user :: " + user);
		return user;
	}
}