package com.vadimaid.skb_registration.service.impl;

import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.entity.Customer;
import com.vadimaid.skb_registration.mapper.CustomerMapper;
import com.vadimaid.skb_registration.messaging.producer.MessageSender;
import com.vadimaid.skb_registration.repository.CustomerRepository;
import com.vadimaid.skb_registration.validator.CustomerValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerValidator customerValidator;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private MessageSender messageSender;

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(customerService, customerRepository);
    }

    @Test
    public void testFindById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLogin("test");
        customer.setPassword("test");
        customer.setEmail("test@gmail.com");
        customer.setFullname("Test Test");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(eq(1L))).thenReturn(Optional.of(customer));

        customerRepository.save(new Customer());

        Customer byId = customerService.findById(1L);
        Assert.assertEquals(customer.getLogin(), byId.getLogin());
        verify(customerRepository, times(1)).findById(eq(1L));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLogin("test");
        customer.setPassword("test");
        customer.setEmail("test@gmail.com");
        customer.setFullname("Test Test");

        CustomerDto dto = new CustomerDto();
        dto.setLogin("test");
        dto.setPassword("test");
        dto.setEmail("test@gmail.com");
        dto.setFullName("Test Test");

        doNothing().when(customerValidator).validateCustomer(any(CustomerDto.class));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.mapDtoToEntity(eq(dto))).thenReturn(customer);
        doNothing().when(messageSender).sendMessage(any(Customer.class));


        Customer createdCustomer = customerService.create(dto);
        verify(customerRepository, times(1)).save(eq(customer));
        Assert.assertEquals(createdCustomer.getLogin(), dto.getLogin());
    }
}