package com.emazon.services.customer.service;

import com.emazon.services.customer.controller.CustomerController;
import com.emazon.services.customer.dao.AddressRepository;
import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Customer;
import com.emazon.services.customer.exception.CustomerAlreadyExistsException;
import com.emazon.services.customer.exception.CustomerNotFoundException;
import com.emazon.services.customer.exception.IllegalModificationException;
import com.emazon.services.customer.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static com.emazon.services.customer.utils.Validator.validate;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
@Validated
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public CollectionModel<EntityModel<Customer>> getCustomers() {
        final List<EntityModel<Customer>> customerModel =
                customerRepository
                        .findAll()
                        .stream()
                        .map(CustomerServiceImpl::getCustomersEntityModel).collect(Collectors.toList());

        return CollectionModel.of(customerModel,
                linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }


    @Override
    public EntityModel<Customer> getCustomerById(String customerId) {

        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> {
                    throw new CustomerNotFoundException(customerId);
                });

        return EntityModel
                .of(customer,
                        linkTo(methodOn(CustomerController.class).one(customerId)).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).all()).withRel("customers")
                );
    }

    @Override
    public EntityModel<Customer> createNewCustomer(Customer customer) {
        throwIfCustomerIdIsModified(customer);
        customer.setCustomerId(UUIDGenerator.generate());

        validate(customer);
        throwIfEmailAlreadyExists(customer.getEmail());
        addressRepository.save(customer.getAddress());

        return EntityModel.of(
                customerRepository.save(customer),
                linkTo(methodOn(CustomerController.class).one(customer.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customers")
        );
    }


    @Override
    public boolean updateCustomer(String customerId, Customer customer) {
        return false;
    }

    @Override
    public boolean removeCustomer(String customerId) {
        return false;
    }


    private static EntityModel<Customer> getCustomersEntityModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).one(customer.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customers")
        );
    }

    private void throwIfCustomerIdIsModified(Customer customer) {
        if (customer.getCustomerId() == null) {
            return;
        }
        throw new IllegalModificationException("Cannot modify the customerId");
    }


    private void throwIfEmailAlreadyExists(String email) {
        if (!customerRepository.existsByEmail(email)) {
            return;
        }
        throw new CustomerAlreadyExistsException(email);
    }
}
