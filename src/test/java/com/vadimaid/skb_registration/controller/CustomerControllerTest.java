package com.vadimaid.skb_registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadimaid.skb_registration.dto.CustomerDto;
import com.vadimaid.skb_registration.entity.Customer;
import com.vadimaid.skb_registration.service.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testCreateCustomerSuccess() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setLogin("test");
        dto.setPassword("test");
        dto.setEmail("test@gmail.com");
        dto.setFullName("Test Test");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLogin("test");
        customer.setEmail("test@gmail.com");
        customer.setFullname("Test Test");

        when(customerService.create(eq(dto))).thenReturn(customer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/customer/create")
                .content(mapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated());

        verify(customerService, times(1)).create(eq(dto));
    }

}