package com.emazon.services.userinfo.config;

import com.emazon.services.userinfo.dao.AddressRepository;
import com.emazon.services.userinfo.dao.UserInfoRepository;
import com.emazon.services.userinfo.entity.Address;
import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.utils.UUIDGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class ServiceConfig {

    @Bean
    CommandLineRunner loadDatabase(UserInfoRepository userInfoRepository, AddressRepository addressRepository){
        return args -> {
            Address address = new Address();

            address.setStreetNumber(41);
            address.setStreetName("Rue du chien la");

            address.setZipCode("13013");
            address.setCity("Marseille");
            address.setCountry("France");

            addressRepository.save(address);

            UserInfo userInfo = new UserInfo();

            userInfo.setFirstName("Ben");
            userInfo.setLastName("Beckman");
            userInfo.setAddress(address);
            userInfo.setEmail("email@example.fr");
            userInfo.setBirthdate(LocalDate.of(1990, Month.APRIL,2));

            userInfo.setCustomerId(UUIDGenerator.generate());

            userInfoRepository.save(userInfo);
        };
    }
}
