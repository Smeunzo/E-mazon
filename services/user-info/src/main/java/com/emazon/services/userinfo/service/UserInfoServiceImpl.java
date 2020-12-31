package com.emazon.services.userinfo.service;

import com.emazon.services.userinfo.controller.UserInfoController;
import com.emazon.services.userinfo.dao.AddressRepository;
import com.emazon.services.userinfo.dao.UserInfoRepository;
import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.exception.UserInfoAlreadyExistsException;
import com.emazon.services.userinfo.exception.UserInfoNotFoundException;
import com.emazon.services.userinfo.exception.IllegalModificationException;
import com.emazon.services.userinfo.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static com.emazon.services.userinfo.utils.Validator.validate;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
@Validated
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final AddressRepository addressRepository;

    @Override
    public CollectionModel<EntityModel<UserInfo>> getCustomers() {
        final List<EntityModel<UserInfo>> customerModel =
                userInfoRepository
                        .findAll()
                        .stream()
                        .map(UserInfoServiceImpl::getCustomersEntityModel).collect(Collectors.toList());

        return CollectionModel.of(customerModel,
                linkTo(methodOn(UserInfoController.class).all()).withSelfRel());
    }


    @Override
    public EntityModel<UserInfo> getCustomerById(String customerId) {

        UserInfo userInfo = userInfoRepository.findByCustomerId(customerId)
                .orElseThrow(() -> {
                    throw new UserInfoNotFoundException(customerId);
                });

        return EntityModel
                .of(userInfo,
                        linkTo(methodOn(UserInfoController.class).one(customerId)).withSelfRel(),
                        linkTo(methodOn(UserInfoController.class).all()).withRel("customers")
                );
    }

    @Override
    public EntityModel<UserInfo> createNewCustomer(UserInfo userInfo) {
        throwIfCustomerIdIsModified(userInfo);
        userInfo.setCustomerId(UUIDGenerator.generate());

        validate(userInfo);
        throwIfEmailAlreadyExists(userInfo.getEmail());
        addressRepository.save(userInfo.getAddress());

        return EntityModel.of(
                userInfoRepository.save(userInfo),
                linkTo(methodOn(UserInfoController.class).one(userInfo.getCustomerId())).withSelfRel(),
                linkTo(methodOn(UserInfoController.class).all()).withRel("customers")
        );
    }


    @Override
    public boolean updateCustomer(String customerId, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean removeCustomer(String customerId) {
        return false;
    }


    private static EntityModel<UserInfo> getCustomersEntityModel(UserInfo userInfo) {
        return EntityModel.of(userInfo,
                linkTo(methodOn(UserInfoController.class).one(userInfo.getCustomerId())).withSelfRel(),
                linkTo(methodOn(UserInfoController.class).all()).withRel("customers")
        );
    }

    private void throwIfCustomerIdIsModified(UserInfo userInfo) {
        if (userInfo.getCustomerId() == null) {
            return;
        }
        throw new IllegalModificationException("Cannot modify the customerId");
    }


    private void throwIfEmailAlreadyExists(String email) {
        if (!userInfoRepository.existsByEmail(email)) {
            return;
        }
        throw new UserInfoAlreadyExistsException(email);
    }
}
