package com.dbs.ordermanagesystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GenericResponseDTO<T> {

	private Header header;
	private T txnResponse;
}
