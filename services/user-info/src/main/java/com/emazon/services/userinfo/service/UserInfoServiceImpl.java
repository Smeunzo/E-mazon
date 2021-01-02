package com.emazon.services.userinfo.service;

import com.emazon.services.userinfo.dao.AddressRepository;
import com.emazon.services.userinfo.dao.UserInfoRepository;
import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.exception.IllegalModificationException;
import com.emazon.services.userinfo.exception.UserInfoAlreadyExistsException;
import com.emazon.services.userinfo.exception.UserInfoNotFoundException;
import com.emazon.services.userinfo.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.emazon.services.userinfo.utils.Validator.validate;

@Service
@AllArgsConstructor
@Validated
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<UserInfo> getCustomers() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo getCustomerById(String customerId) {

        return userInfoRepository.findByCustomerId(customerId)
                .orElseThrow(() -> {
                    throw new UserInfoNotFoundException(customerId);
                });
    }

    @Override
    public UserInfo createNewCustomer(UserInfo userInfo) {
        throwIfCustomerIdIsModified(userInfo);
        userInfo.setCustomerId(UUIDGenerator.generate());

        validate(userInfo);
        throwIfEmailAlreadyExists(userInfo.getEmail());
        addressRepository.save(userInfo.getAddress());

        return userInfoRepository.save(userInfo);
    }

    @Override
    public boolean updateCustomer(String customerId, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean removeCustomer(String customerId) {
        return false;
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
