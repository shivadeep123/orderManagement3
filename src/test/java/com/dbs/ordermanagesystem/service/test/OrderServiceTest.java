package com.dbs.ordermanagesystem.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.dbs.ordermanagesystem.dto.GenericRequestDTO;
import com.dbs.ordermanagesystem.dto.Header;
import com.dbs.ordermanagesystem.dto.OrderDTO;
import com.dbs.ordermanagesystem.model.Order;
import com.dbs.ordermanagesystem.model.OrderItem;
import com.dbs.ordermanagesystem.proxy.OrderItemServicesProxy;
import com.dbs.ordermanagesystem.repository.OrderRepository;
import com.dbs.ordermanagesystem.service.OrderItemService;
import com.dbs.ordermanagesystem.service.OrderService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OrderServiceTest.class,OrderService.class})
public class OrderServiceTest {

	@MockBean
	OrderRepository orderRepositoryTest;
	@MockBean
	OrderItemServicesProxy orderItemServiceProxyTest;
	Order order1;
	OrderService orderService;
	OrderItem orderItem;
	
	@Before
	public void Setup() {
		 order1 =new Order();
		 orderService =  new OrderService();
		 order1.setCustId("123");
		 order1.setCustomerName("shiva");
		 orderItem = new OrderItem();
		 orderItem.setOrder(order1);
		 orderItem.setProductCode("001");
	}
	
	
	@Test
	public void saveOrderDetailsTest() throws Exception {
		when(orderRepositoryTest.save(order1)).thenReturn(order1);
		FieldSetter.setField(orderService, orderService.getClass().getDeclaredField("OrderRepository"), orderRepositoryTest);
		
		Order o1= orderService.saveOrderDetails(order1);
		assertNotNull(o1);
	}

	@Test
	public void retrieveOrdersTest() throws Exception {
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("orderItemNumber","001");
		
		when(orderItemServiceProxyTest.retrieveOrderItems(Mockito.any(),Mockito.anyString())).thenReturn(orderItem);
		FieldSetter.setField(orderService, orderService.getClass().getDeclaredField("OrderRepository"), orderRepositoryTest);
		
		List<OrderItem> items1 = (List<OrderItem>) orderItemServiceProxyTest.retrieveOrderItems(m1, "111");
		assertEquals("001", items1.get(0).getProductCode());
		//TODO
	}
}
