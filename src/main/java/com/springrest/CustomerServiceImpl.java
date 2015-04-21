package com.springrest;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public Future<String> sayHello(String name) {
		try {
			logger.debug("sayHello before sleep : "+name +" at : "+Calendar.getInstance().getTime());
			Thread.sleep(6000);
			logger.debug("sayHello after sleep: "+name +" at : "+Calendar.getInstance().getTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<String>("Hello " + WordUtils.capitalize(name) + ", openShift welcomes you. TIMESTAMP :: "+Calendar.getInstance().getTime());
	}

	public String sayBye(String name) {
		Date date = new Date();
		logger.debug("sayBye : "+name +" at : "+date);
		return "Bye " + WordUtils.capitalize(name) + ", openShift welcomes you. TIMESTAMP :: "+date;
	}

	public String beInTouch(String email) {
		Date date = new Date();
		logger.debug("beInTouch : "+email +" at : "+date);
		return "beInTouch " + email + ", openShift welcomes you. TIMESTAMP :: "+date;
	}
}