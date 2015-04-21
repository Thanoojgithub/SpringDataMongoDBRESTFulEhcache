package com.springrest;

import java.util.Calendar;
import java.util.Date;

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
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = LoggerFactory
			.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CacheManager cacheManager;

	public String sayHello(String name) {
		logger.debug("sayHello before sleep : " + name + " at : "
				+ Calendar.getInstance().getTime());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.debug("sayHello after sleep: " + name + " at : "
				+ Calendar.getInstance().getTime());
		String str = new String("Hello " + WordUtils.capitalize(name)
				+ ", openShift welcomes you. TIMESTAMP :: "
				+ Calendar.getInstance().getTime());
		return str;
	}

	/*
	 * public Future<String> sayHello(String name) { try {
	 * logger.debug("sayHello before sleep : "+name
	 * +" at : "+Calendar.getInstance().getTime()); Thread.sleep(6000);
	 * logger.debug("sayHello after sleep: "+name
	 * +" at : "+Calendar.getInstance().getTime()); } catch
	 * (InterruptedException e) { e.printStackTrace(); } String str = new
	 * String("Hello " + WordUtils.capitalize(name) +
	 * ", openShift welcomes you. TIMESTAMP :: "
	 * +Calendar.getInstance().getTime()); return new AsyncResult<String>(str);
	 * }
	 */

	public String sayBye(String name) {
		Date date = new Date();
		logger.debug("sayBye : " + name + " at : " + date);
		return "Bye " + WordUtils.capitalize(name)
				+ ", openShift welcomes you. TIMESTAMP :: " + date;
	}

	public String beInTouch(String email) {
		Date date = new Date();
		logger.debug("beInTouch : " + email + " at : " + date);
		return "beInTouch " + email + ", openShift welcomes you. TIMESTAMP :: "
				+ date;
	}

	public void getCacheStatistics() {
		Cache cache = this.cacheManager.getCache("customerServiceCache");
		if (null != cache && Status.STATUS_ALIVE.equals(cache.getStatus())) {
			StatisticsGateway statistics = cache.getStatistics();
			ToStringBuilder.reflectionToString(statistics,ToStringStyle.MULTI_LINE_STYLE);
		}
		
	}

}