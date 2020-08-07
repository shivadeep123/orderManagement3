package com.dbs.ordermanagesystem.exception;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class InvalidRequestException extends RuntimeException{	
	public InvalidRequestException () {super();}
	public InvalidRequestException (String message) {super(message);}
}
