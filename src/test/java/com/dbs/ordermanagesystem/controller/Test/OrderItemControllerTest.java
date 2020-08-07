package com.dbs.ordermanagesystem.controller.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.dbs.ordermanagesystem.controller.OrderItemController;
import com.dbs.ordermanagesystem.model.Order;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.service.OrderItemService;
import com.dbs.ordermanagesystem.service.test.OrderItemServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OrderItemControllerTest.class,OrderItemController.class})
public class OrderItemControllerTest {
	@MockBean
	OrderItemService orderItemServicetest;
	OrderItemController orderItemController;
	OrderItem orderItem;
	Order order;
	@Before
	public void Setup() {
		orderItem = new OrderItem();
		order = new Order();
		
		orderItem.setOrder(order);
		orderItem.setOrderItemNumber(0);
		orderItem.setProductCode("001");
		orderItem.setProductName("mac");
		order.setCustId("111");
		//order.setOrderDate(new Date(date)));
		order.setShippingAddress("singapore");
	}
	
	@Test
	public void createOrderItemTest() throws Exception {
		when(orderItemServicetest.saveOrderItemDetails(Mockito.any())).thenReturn(orderItem);
		FieldSetter.setField(orderItemController, orderItemController.getClass().getDeclaredField("orderItemService"), orderItemServicetest);
		
		ResponseEntity<String> orderitm = orderItemController.createOrderItem(orderItem, null);
		assertNotNull(orderitm);
	}
	
	
	@Test
	public void retrieveOrderItemsTest() throws Exception {
		List<OrderItem> items1 = new ArrayList<>();
		items1.add(orderItem);
		when(orderItemServicetest.getAllOrderItemsByCustomer(Mockito.anyString())).thenReturn(items1);
		FieldSetter.setField(orderItemController, orderItemController.getClass().getDeclaredField("orderItemService"), orderItemServicetest);
		
		List<OrderItem> items = (List<OrderItem>) orderItemController.retrieveOrderItems(null, "111");
		assertEquals("Singapore", items.get(0).getOrder().getShippingAddress());
		
	}
}
