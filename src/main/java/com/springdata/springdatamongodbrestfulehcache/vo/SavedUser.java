package com.springdata.springdatamongodbrestfulehcache.vo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "savedUser")
public class SavedUser extends User{

	private static final long serialVersionUID = 1L;


}
