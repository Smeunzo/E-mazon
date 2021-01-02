package com.emazon.services.userinfo;


import com.emazon.services.userinfo.dao.AddressRepository;
import com.emazon.services.userinfo.dao.UserInfoRepository;
import com.emazon.services.userinfo.entity.Address;
import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.exception.IllegalModificationException;
import com.emazon.services.userinfo.exception.UserInfoAlreadyExistsException;
import com.emazon.services.userinfo.exception.UserInfoNotFoundException;
import com.emazon.services.userinfo.service.UserInfoService;
import com.emazon.services.userinfo.service.UserInfoServiceImpl;
import com.emazon.services.userinfo.utils.UUIDGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserInfoServiceTests {

    private static UserInfo user1;
    private static UserInfo user2;
    private static UserInfo user3;
    private static UserInfo newUser;

    private static Address address1;
    private static Address address2;
    private static Address address3;

    private static final String NEW_USER_ID = "U220ZK";

    @BeforeAll
    static void createUser() {
        address1 = new Address();
        address1.setStreetNumber(234);
        address1.setStreetName("Rue Dupont");
        // locality can be null
        address1.setLocality(null);
        address1.setZipCode("75009");
        address1.setCity("Paris");
        address1.setCountry("France");

        user1 = new UserInfo();
        user1.setCustomerId("U9ZZO2");
        user1.setFirstName("Jean");
        user1.setMiddleName("Dubois");
        user1.setLastName("Phillipe");
        user1.setEmail("jean.phillipe@example.com");
        user1.setBirthdate(LocalDate.of(1990, 1, 1));
        user1.setAddress(address1);

        address2 = new Address();
        address2.setStreetNumber(31);
        address2.setStreetName("Boulevard Du Cabaret");
        address2.setLocality(null);
        address2.setZipCode("75001");
        address2.setCity("Paris");
        address2.setCountry("France");

        user2 = new UserInfo();
        user2.setCustomerId("UEO424");
        user2.setFirstName("Marc");
        // middleName can be null
        user2.setMiddleName(null);
        user2.setLastName("Bouvier");
        user2.setEmail("bouvier777@example.com");
        user2.setBirthdate(LocalDate.of(1990, 1, 1));
        user2.setAddress(address2);

        address3 = new Address();
        address3.setStreetNumber(23);
        address3.setStreetName("Avenue Elixir");
        address3.setLocality(null);
        address3.setZipCode("13010");
        address3.setCity("Marseille");
        address3.setCountry("France");

        user3 = new UserInfo();
        user3.setCustomerId("U29ZE4");
        user3.setFirstName("Francois");
        user3.setMiddleName(null);
        user3.setLastName("Pierre");
        user3.setEmail("francois.pierre928@example.com");
        user3.setBirthdate(LocalDate.of(1990, 2, 2));
        user3.setAddress(address3);

        Address newUserAddress = new Address();
        newUserAddress.setStreetNumber(10);
        newUserAddress.setStreetName("Rue Alphonse");
        newUserAddress.setZipCode("75003");
        newUserAddress.setCity("Paris");
        newUserAddress.setCountry("France");

        newUser = new UserInfo();
        newUser.setFirstName("Claire");
        newUser.setMiddleName("LeDuc");
        newUser.setLastName("Marie");
        newUser.setEmail("marie.claire@example.com");
        newUser.setBirthdate(LocalDate.of(2002, 5, 13));
        newUser.setAddress(newUserAddress);

    }

    private static final UserInfoRepository userInfoRepository = mock(UserInfoRepository.class);

    private static final AddressRepository addressRepository = mock(AddressRepository.class);

    private static final MockedStatic<UUIDGenerator> uuidGenerator = mockStatic(UUIDGenerator.class);

    @BeforeAll
    static void beforeAll() {
        uuidGenerator.when(UUIDGenerator::generate).thenReturn(NEW_USER_ID);
        when(userInfoRepository.findByCustomerId("DUMMY"))
                .thenThrow(UserInfoNotFoundException.class);
    }

    @BeforeEach
    void mockRepositories() {
        when(userInfoRepository.save(newUser))
                .thenReturn(newUser);

        when(userInfoRepository.findByCustomerId(user1.getCustomerId()))
                .thenReturn(Optional.of(user1));
        when(userInfoRepository.findByCustomerId(user2.getCustomerId()))
                .thenReturn(Optional.of(user2));
        when(userInfoRepository.findByCustomerId(user3.getCustomerId()))
                .thenReturn(Optional.of(user3));

        when(userInfoRepository.findAll())
                .thenReturn(List.of(user1, user2, user3));

        when(addressRepository.save(address1))
                .thenReturn(address1);
        when(addressRepository.save(address2))
                .thenReturn(address2);
        when(addressRepository.save(address3))
                .thenReturn(address3);
    }

    UserInfoService userInfoService = new UserInfoServiceImpl(userInfoRepository, addressRepository);

    @Test
    void shouldReturnAllUsersInfo() {
        List<UserInfo> userInfoList = userInfoService.getCustomers();

        assertEquals(List.of(user1, user2, user3), userInfoList);
    }

    @Test
    void shouldReturnUserFromUserId() {
        UserInfo ui1 = userInfoService.getCustomerById("U9ZZO2");
        UserInfo ui2 = userInfoService.getCustomerById("UEO424");
        UserInfo ui3 = userInfoService.getCustomerById("U29ZE4");

        assertEquals(user1, ui1);
        assertEquals(user2, ui2);
        assertEquals(user3, ui3);
    }

    @Test
    void shouldThrowUserInfoNotFoundException() {
        assertThrows(UserInfoNotFoundException.class, () -> {
            userInfoService.getCustomerById("DUMMY");
        });
    }

    @Test
    void shouldCreateANewCustomer() {
        UserInfo addedUser = userInfoService.createNewCustomer(newUser);

        newUser.setCustomerId(NEW_USER_ID);
        uuidGenerator.verify(UUIDGenerator::generate);
        uuidGenerator.clearInvocations();
        assertEquals(addedUser, newUser);
        newUser.setCustomerId(null);
    }

    @Test
    void shouldThrowIllegalModificationException() {
        newUser.setCustomerId("RANDOM");
        assertThrows(IllegalModificationException.class, () -> userInfoService.createNewCustomer(newUser));
        newUser.setCustomerId(null);
    }

    @Test
    void shouldThrowUserInfoAlreadyExistsException() {
        enableExistsByMailMock(true);
        assertThrows(UserInfoAlreadyExistsException.class, () -> System.out.println(userInfoService.createNewCustomer(newUser)));
        enableExistsByMailMock(false);
    }

    private void enableExistsByMailMock(boolean b) {
        when(userInfoRepository.existsByEmail(anyString())).thenReturn(b);
    }
}
