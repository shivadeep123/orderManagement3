package com.dbs.ordermanagesystem.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.ordermanagesystem.dto.ErrorDTO;
import com.dbs.ordermanagesystem.dto.GenericResponseDTO;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity handleOrderNotFound(OrderNotFoundException ex) {
		GenericResponseDTO<ErrorDTO> response = new GenericResponseDTO<ErrorDTO>();
		//response.setHeader(utility.createHeader());
		response.setTxnResponse(ErrorDTO.builder().errorCode("201").errorDescription("Order Not Found").build());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity handleOrderNotFound(InvalidRequestException ex) {
		GenericResponseDTO<ErrorDTO> response = new GenericResponseDTO<ErrorDTO>();
		//response.setHeader(utility.createHeader());
		response.setTxnResponse(ErrorDTO.builder().errorCode("400").errorDescription("Invalid request Format").build());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
