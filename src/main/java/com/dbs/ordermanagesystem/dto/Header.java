package com.dbs.ordermanagesystem.dto;



import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Header {

	private String msgId;
	private String timestamp;
}
