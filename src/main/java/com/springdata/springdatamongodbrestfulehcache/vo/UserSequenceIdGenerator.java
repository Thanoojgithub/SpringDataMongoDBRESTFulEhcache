package com.springdata.springdatamongodbrestfulehcache.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userSequenceIdGenerator")
public class UserSequenceIdGenerator {

	@Id
	private String _id;
	private long seq;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "UserSequenceIdGenerator [_id=" + _id + ", seq=" + seq + "]";
	}
}
