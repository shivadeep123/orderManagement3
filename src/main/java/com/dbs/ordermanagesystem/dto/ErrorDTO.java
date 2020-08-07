package com.dbs.ordermanagesystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

	private String errorCode;
	private String errorDescription;
}
