package com.order.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersApplicationTests {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderController orderController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetOrders() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(new Order(1, "footwear"));
		when(orderRepository.findAllByCustomerId(1)).thenReturn(orderList);

		List<Order> result = orderController.getOrders(1);
		assertEquals(1, result.size());
	}

	@Test
	public void testSaveOrder() {
		Integer custoId = 1;
		Order order = new Order(1, "electronics");
		when(orderRepository.save(order)).thenReturn(order);
		Order result = orderController.addCustomerOrder(order);
		assertEquals(custoId, result.getCustomerId());
		assertEquals("electronics", result.getOrderName());
	}

}
