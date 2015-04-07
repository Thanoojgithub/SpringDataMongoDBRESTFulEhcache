package com.springrest;

import org.apache.commons.lang3.text.WordUtils;

public class CustomerServiceImpl implements CustomerService {

	public String sayHello(String name) {
		return "Hello " + WordUtils.capitalize(name) + ", openShift welcomes you.";
	}
}
