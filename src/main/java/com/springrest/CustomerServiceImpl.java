package com.springrest;

import java.util.Calendar;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.statistics.StatisticsGateway;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springrest.vo.User;

@Component
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepositoryImpl customerRepositoryImpl;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
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

	@Override
	public String getCacheStatistics() {
		String reflectionToString = "cache not yet created";
		Cache cache = this.cacheManager.getCache("customerServiceCache");
		if (null != cache && Status.STATUS_ALIVE.equals(cache.getStatus())) {
			StatisticsGateway statistics = cache.getStatistics();
			reflectionToString = 
			ToStringBuilder.reflectionToString(statistics.getAssociatedCacheName(),RecursiveToStringStyle.JSON_STYLE)
			+ "----------||----------" + ToStringBuilder.reflectionToString(statistics.getCore(),RecursiveToStringStyle.JSON_STYLE)
			+ "----------||----------" + ToStringBuilder.reflectionToString(statistics.getExtended(),RecursiveToStringStyle.JSON_STYLE);
		}
		return reflectionToString;
	}
	
	@Override
	public User getUser(Long id) {
		return customerRepositoryImpl.findOne(id);
	}
	
	@Override
	public Boolean deleteUser(Long id) {
		return customerRepositoryImpl.delete(id);
	}

	@Override
	public void upsertUser(Long id, String firstName, String lastName, String location) {
		User persisted = new User();
		persisted.setId(id);
		persisted.setFirstName(firstName);
		persisted.setLastName(lastName);
		persisted.setLocation(location);
		customerRepositoryImpl.save(persisted);
	}

	@Override
	public List<User> getUsers() {
		return customerRepositoryImpl.findAll();
	}
}