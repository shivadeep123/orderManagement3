package com.dbs.ordermanagesystem.proxy;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbs.ordermanagesystem.dto.GenericResponseDTO;
import com.dbs.ordermanagesystem.model.OrderItem;

@FeignClient(value="order-item" , url = "localhost:8089")
public interface OrderItemServicesProxy {

	@GetMapping("/retrieveOrderItem")
	OrderItem retrieveOrderItems(@RequestParam Map<String,String> orderItemDetails, String id);
}
