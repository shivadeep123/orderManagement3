package com.dbs.ordermanagesystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.ordermanagesystem.dto.GenericRequestDTO;
import com.dbs.ordermanagesystem.dto.Header;
import com.dbs.ordermanagesystem.dto.OrderDTO;
import com.dbs.ordermanagesystem.model.Order;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.proxy.OrderItemServicesProxy;
import com.dbs.ordermanagesystem.repository.OrderRepository;
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemServicesProxy orderItemServiceProxy;

	public Order saveOrderDetails(Order request) {
		return orderRepository.save(request);
		
	}
	public List<OrderItem> retrieveOrders(Map<String, String> queryParam, String id) {
		//retrieve corresponding orderItemNumbers from orderTables and call OrderItem via fiegn client
		//queryParam.put("custId", id);
		List<OrderItem> items = new ArrayList<OrderItem>();
		 items = (List<OrderItem>) orderItemServiceProxy.retrieveOrderItems(queryParam,id);
		return items;
	}

}
