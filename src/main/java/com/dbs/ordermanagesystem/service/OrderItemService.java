package com.dbs.ordermanagesystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;

	public OrderItem saveOrderItemDetails(OrderItem request) {
		OrderItem oi= orderItemRepository.save(request);
		return oi;
		
		/*
		 * orderItemRepository.save(OrderItem.builder()
		 * .productName(request.getTxnResponse().getProductName())
		 * .productCode(request.getTxnResponse().getProductCode())
		 * .orderItemNumber(request.getTxnResponse().getOrderItemNumber())
		 * .quantity(utility.doubleConvert(request.getTxnResponse().getQuantity()))
		 * .build());
		 */
	}
	
    public List<OrderItem> getAllOrderItemsByCustomer(String id) {
        final List<OrderItem> items = new ArrayList<>();
        orderItemRepository.findByCustId(id).forEach(item -> items.add(item));
        return items;
    }
	
}
