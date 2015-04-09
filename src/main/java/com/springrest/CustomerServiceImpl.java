package com.springrest;

import java.util.Date;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public String sayHello(String name) {
		Date date = new Date();
		logger.debug("sayHello : "+name +" at : "+date);
		return "Hello " + WordUtils.capitalize(name) + ", openShift welcomes you. TIMESTAMP :: "+date;
	}

	@Override
	public String sayBye(String name) {
		Date date = new Date();
		logger.debug("sayBye : "+name +" at : "+date);
		return "Bye " + WordUtils.capitalize(name) + ", openShift welcomes you. TIMESTAMP :: "+date;
	}

	@Override
	public String beInTouch(String email) {
		Date date = new Date();
		logger.debug("beInTouch : "+email +" at : "+date);
		return "beInTouch " + email + ", openShift welcomes you. TIMESTAMP :: "+date;
	}
}