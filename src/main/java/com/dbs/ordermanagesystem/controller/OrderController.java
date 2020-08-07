package com.dbs.ordermanagesystem.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.ordermanagesystem.dto.GenericRequestDTO;
import com.dbs.ordermanagesystem.dto.GenericResponseDTO;
import com.dbs.ordermanagesystem.dto.Header;
import com.dbs.ordermanagesystem.dto.OrderDTO;
import com.dbs.ordermanagesystem.exception.InvalidRequestException;
import com.dbs.ordermanagesystem.model.Order;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.repository.OrderRepository;
import com.dbs.ordermanagesystem.service.OrderService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping(value="/orderCreate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order request, BindingResult result){
		if(result.getErrorCount()>0) {
			throw new InvalidRequestException();
		}
		Order o1=orderService.saveOrderDetails(request);
		return ResponseEntity.status(HttpStatus.OK).body(o1);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="customerName",
				value="Customer Name",
				required=false,
				dataType="string",
				paramType="query"),
		@ApiImplicitParam(name="orderDate",
				value="Order Date",
				required=false,
				dataType="string",
				paramType="query"),
		@ApiImplicitParam(name="shippingAddress",
				value="",
				required=false,
				dataType="string",
				paramType="query"),
		@ApiImplicitParam(name="total",
				value="Total",
				required=false,
				dataType="string",
				paramType="query")
	})
	@GetMapping("/retrieveOrder/{id}")
	public ResponseEntity<List<OrderItem>> retrieveOrders( @ApiParam(hidden = true)@RequestParam Map<String, String> queryParam, @PathVariable String id){
		List<OrderItem> items = orderService.retrieveOrders(queryParam,id);
		
		return ResponseEntity.status(HttpStatus.OK).body(items);
	}
			

}
