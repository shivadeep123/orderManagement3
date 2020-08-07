package com.dbs.ordermanagesystem.dto;

import lombok.Data;

@Data
public class GenericRequestDTO<H,T> {

	private H header;
	private T txnResponse;
}
