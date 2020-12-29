package com.emazon.services.customer.controller;

import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    CollectionModel<EntityModel<Customer>> all() {
        final List<EntityModel<Customer>> customerModel =
                customerRepository
                        .findAll()
                        .stream()
                        .map(CustomerController::getCustomersEntityModel).collect(Collectors.toList());

        return CollectionModel.of(customerModel,
                linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    private static EntityModel<Customer> getCustomersEntityModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).one(customer.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customer")
        );
    }

    @GetMapping("/customers/{customerId}")
    EntityModel<Customer> one(@PathVariable String customerId) {

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
}
