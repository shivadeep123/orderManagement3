package com.dbs.ordermanagesystem.controller.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.ordermanagesystem.controller.OrderController;
import com.dbs.ordermanagesystem.controller.OrderItemController;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {OrderControllerTest.class,OrderController.class})
public class OrderControllerTest {
	@MockBean
	OrderService orderServicetest;
	OrderController orderController;
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
	public void createOrderTest() throws Exception {
		
		when(orderServicetest.saveOrderDetails(Mockito.any())).thenReturn(order);
		FieldSetter.setField(orderController, orderController.getClass().getDeclaredField("orderService"), orderServicetest);
		
		ResponseEntity<Order> o1 = orderController.createOrder(order, null);
		assertNotNull(o1);
	}
	
	@Test
	public void retrieveOrdersTest() throws Exception {
		List<OrderItem> items1 = new ArrayList<>();
		items1.add(orderItem);
		
		when(orderServicetest.retrieveOrders(Mockito.any(), Mockito.anyString())).thenReturn(items1);
		FieldSetter.setField(orderController, orderController.getClass().getDeclaredField("orderService"), orderServicetest);
	
		List<OrderItem> items = (List<OrderItem>) orderController.retrieveOrders(null, "111");
		assertNotNull(items.get(0).getProductCode());
	}
	
	
}
