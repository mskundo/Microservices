package com.customer.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
public class CustomersApplicationTests {

	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerController customerController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetCustomers(){
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("madhu","gunupur", "odisha",23,123654));
		when(customerRepository.findAll()).thenReturn(customerList);
		
		List<Customer> result = customerController.getCustomers();
		assertEquals(1, result.size());
	}
	
	
	@Test
	public void testSaveCustomer(){
		Customer customer = new Customer("priya","mg road","koramangla",23,123654);
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerController.addCustomerTest(customer);
		assertEquals("priya", result.getName());
		assertEquals("mg road", result.getAddressLine1());
		assertEquals("koramangla", result.getAddressLine2());
		assertEquals(23, result.getAge());
		assertEquals(123654, result.getPostCode());	
	}
	
	
	@Test
	public void testDeleteCustomer(){
		Customer customer = new Customer("priya","mg road","koramangla",23,123654);
		customerController.deleteCustomer(customer.getId());
        verify(customerRepository, times(1)).deleteById(customer.getId());
	}
	
	@Test
	public void testUpdateCustomer(){
		Customer customer = new Customer("madhu","mg road","koramangla",22,123654);
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerController.updateCustomerTest(customer);
		assertEquals("priya", result.getName());
		assertEquals("mg road", result.getAddressLine1());
		assertEquals("koramangla", result.getAddressLine2());
		assertEquals(23, result.getAge());
		assertEquals(123654, result.getPostCode());	
	}

}
