package com.shop.shoppingapp.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @Test
    void canGetAllCustomers() {
        underTest.findAllCustomers();
        verify(customerRepository).findAll();
    }

    @Test
    void canGetCustomerById() {
        // Given
        Long id = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(id);
        given(customerRepository.existsById(id)).willReturn(true);
        given(customerRepository.findById(id)).willReturn(Optional.of(expectedCustomer));

        // When
        Customer actualCustomer = underTest.findCustomerById(id);
        // Then
        verify(customerRepository).findById(any());
        assertThat(actualCustomer.getId()).isEqualTo(expectedCustomer.getId());
    }

    @Test
    void canAddNewCustomer() {
        // Given
        Customer customer = new Customer("John", "Doe", "100 Main Street", "Town", "10001");

        // When
        underTest.addNewCustomer(customer);
        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer).isEqualTo(customer);
    }

    @Test
    void canDeleteCustomer() {
        // Given
        Long id = 1L;
        given(customerRepository.existsById(id)).willReturn(true);
        // When
        underTest.deleteCustomer(id);
        // Then
        verify(customerRepository).deleteById(id);
    }

    @Test
    void willThrowIfIdNotFound() {
        // Given
        Long id = 1L;
        given(customerRepository.existsById(id)).willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.deleteCustomer(id)).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer with ID " + id + " does not exist");
        verify(customerRepository, never()).deleteById(any());

        assertThatThrownBy(() -> underTest.findCustomerById(id)).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer with ID " + id + " does not exist");
        verify(customerRepository, never()).deleteById(any());


    }

    @Test
    void canUpdateCustomer(){
        // Given
        Long id = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(id);
        given(customerRepository.existsById(id)).willReturn(true);
        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        // When
        Customer updatedCustomer = new Customer("John", "Doe", "100 Main Street", "Town", "10001");
        underTest.updateCustomer(id, updatedCustomer);


        // Then
        assertThat(underTest.findCustomerById(id).getFirstName()).isEqualTo(updatedCustomer.getFirstName());
        assertThat(underTest.findCustomerById(id).getLastName()).isEqualTo(updatedCustomer.getLastName());
        assertThat(underTest.findCustomerById(id).getAddress()).isEqualTo(updatedCustomer.getAddress());
        assertThat(underTest.findCustomerById(id).getCity()).isEqualTo(updatedCustomer.getCity());
        assertThat(underTest.findCustomerById(id).getZipCode()).isEqualTo(updatedCustomer.getZipCode());
    }

}
