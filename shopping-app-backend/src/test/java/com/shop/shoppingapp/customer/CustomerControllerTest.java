package com.shop.shoppingapp.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void canGetAllCustomers() throws Exception {
        Customer customer = new Customer("John", "Smith", "123 Location Street", "Town", "12345");

        List<Customer> customers = List.of(customer);

        given(customerService.findAllCustomers()).willReturn(customers);

        mvc.perform(get("/api/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(customer.getFirstName())));
    }

    @Test
    public void canGetByCustomerId() throws Exception {

        Long id = 1L;

        Customer customer = new Customer("John", "Smith", "123 Location Street", "Town", "12345");
        customer.setCustomerId(id);

        given(customerService.findCustomerById(id)).willReturn(customer);

        mvc.perform(get("/api/customer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.address", is(customer.getAddress())))
                .andExpect(jsonPath("$.city", is(customer.getCity())))
                .andExpect(jsonPath("$.zipCode", is(customer.getZipCode())));

    }

    @Test
    public void canAddCustomer() throws Exception{

        Customer customer = new Customer("John", "Smith", "123 Location Street", "Town", "12345");


        mvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void canDeleteCustomer() throws Exception {

        Long id = 1L;

        Customer customer = new Customer("John", "Smith", "123 Location Street", "Town", "12345");
        customer.setCustomerId(id);

        given(customerService.findCustomerById(id)).willReturn(customer);

        mvc.perform(delete("/api/customer/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void canUpdateCustomer() throws Exception {

        Long id = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(id);
        given(customerService.findCustomerById(id)).willReturn(customer);

        Customer updatedCustomer = new Customer("John", "Smith", "123 Location Street", "Town", "12345");
        updatedCustomer.setCustomerId(id);
        mvc.perform(put("/api/customer/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedCustomer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
