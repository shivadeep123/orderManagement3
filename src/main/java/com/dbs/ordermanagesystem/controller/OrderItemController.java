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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.ordermanagesystem.exception.InvalidRequestException;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.service.OrderItemService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
@RestController
public class OrderItemController {
	@Autowired
	OrderItemService orderItemService;
	
	@PostMapping(value="/orderItemCreate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOrderItem (@Valid @RequestBody OrderItem request, BindingResult result) throws InvalidRequestException{
		if(result.getErrorCount()>0) {
			throw new InvalidRequestException();
		}		
		OrderItem orderitm= orderItemService.saveOrderItemDetails(request);
		
		//orderitm vale can be send as a response as well.
		return ResponseEntity.status(HttpStatus.OK).body("Orderitems Created successfully.");
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="productName",
				value="Product Name",
				required=false,
				dataType="string",
				paramType="query"),
		@ApiImplicitParam(name="productCode",
				value="Product Code",
				required=false,
				dataType="string",
				paramType="query"),
		@ApiImplicitParam(name="orderItemNumber",
				value="order ItemNumber",
				required=false,
				dataType="Long",
				paramType="query"),
		@ApiImplicitParam(name="quantity",
				value="Quantity",
				required=false,
				dataType="string",
				paramType="query")
	})
	@GetMapping("/retrieveOrderItem")
	public ResponseEntity<List<OrderItem>> retrieveOrderItems(@ApiParam(hidden = true)@RequestParam Map<String, String> queryParam, String id){
		
	System.out.println("in retrieveOrderItem method");
	List<OrderItem> items = orderItemService.getAllOrderItemsByCustomer(id);
	
	if (items !=null && items.size()>0)
		return ResponseEntity.status(HttpStatus.OK).body(items);
	else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
}
