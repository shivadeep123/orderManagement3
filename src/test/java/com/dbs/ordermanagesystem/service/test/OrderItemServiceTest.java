package com.dbs.ordermanagesystem.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.dbs.ordermanagesystem.model.Order;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.repository.OrderItemRepository;
import com.dbs.ordermanagesystem.service.OrderItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OrderItemServiceTest.class,OrderItemService.class})
public class OrderItemServiceTest {

	@MockBean
	OrderItemRepository orderItemRepositorytest;
	OrderItemService orderItemService;
	OrderItem orderItem;
	@Before
	public void Setup() {
		orderItemService = new OrderItemService();
		orderItem=new OrderItem();		
		Order order1 =new Order();
		order1.setCustId("123");
		order1.setCustomerName("shiva");
		
		orderItem.setOrder(order1);
		orderItem.setOrderItemNumber(0);
		orderItem.setProductCode("001");
		orderItem.setProductName("laptop");
		orderItem.setQuantity(3);
	}
	@Test
	public void saveOrderItemDetailsTest() throws Exception {
		when(orderItemRepositorytest.save(orderItem)).thenReturn(orderItem);
		FieldSetter.setField(orderItemService, orderItemService.getClass().getDeclaredField("OrderItemRepository"), orderItemRepositorytest);
		
		orderItem = orderItemService.saveOrderItemDetails(orderItem);
		assertEquals("001", orderItem.getProductCode());		
}
	

	@Test
	public void getAllOrderItemsByCustomerTest() throws Exception {
		List<OrderItem>oi1 = new ArrayList<>();
		oi1.add(orderItem);
		
		when(orderItemRepositorytest.findByCustId(Mockito.anyString())).thenReturn(oi1);
		FieldSetter.setField(orderItemService, orderItemService.getClass().getDeclaredField("OrderItemRepository"), orderItemRepositorytest);
		orderItem = (OrderItem) orderItemService.getAllOrderItemsByCustomer("123");
		assertNotNull(orderItem);	
	}
	
}
