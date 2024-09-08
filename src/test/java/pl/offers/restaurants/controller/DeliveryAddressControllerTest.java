package pl.offers.restaurants.controller;

import com.google.common.truth.Truth8;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import pl.offers.restaurants.config.JPAConfiguration;
import pl.offers.restaurants.dto.DeliveryAddressDTO;
import pl.offers.restaurants.model.DeliveryAddress;
import pl.offers.restaurants.model.User;
import pl.offers.restaurants.model.enums.Archive;
import pl.offers.restaurants.model.enums.Sex;
import pl.offers.restaurants.repo.DeliveryAddressRepo;
import pl.offers.restaurants.repo.UserRepo;
import pl.offers.restaurants.service.DeliveryAddressService;
import pl.offers.restaurants.service.DeliveryAddressServiceImpl;
import pl.offers.restaurants.utils.AssertionUtils;
import pl.offers.restaurants.utils.ConverterUtils;
import pl.offers.restaurants.utils.TestUtils;

import java.util.UUID;

@SpringBootTest(classes = {
        JPAConfiguration.class,
        DeliveryAddressControllerTest.Config.class
})
class DeliveryAddressControllerTest {

    private static final String STR_UUID = "14818fb1-ad14-484a-ad3c-e12113c4c3ab";
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeliveryAddressRepo deliveryAddressRepo;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private DeliveryAddressController deliveryAddressController;

    @Autowired
    private PlatformTransactionManager txManager;

    // add
    @Test
    @Transactional
    public void put1() {
        User user = TestUtils.user("9986208e-961a-48d4-bf7a-112c627779c2",
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"), null,
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);

        DeliveryAddressDTO deliveryAddressJson = TestUtils.deliveryAddressDTO(STR_UUID, "My address", "Street",
                "51", "", "00-000", "Warsaw", null, "Polans", null, ConverterUtils.convert(user));
        deliveryAddressController.put(UUID.fromString(STR_UUID), deliveryAddressJson);

        DeliveryAddressDTO deliveryAddressDB = deliveryAddressService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(deliveryAddressJson, deliveryAddressDB);
    }

    // update
    @Test
    @Transactional
    public void put2() {
        User user = TestUtils.user("9986208e-961a-48d4-bf7a-112c627779c2",
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"), null,
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);
        DeliveryAddress deliveryAddress = TestUtils.deliveryAddress(STR_UUID, "My address", "Street",
                "51", "", "00-000", "Warsaw", null, "Polans", null, user);
        deliveryAddressRepo.save(deliveryAddress);

        DeliveryAddressDTO deliveryAddressJson = TestUtils.deliveryAddressDTO(STR_UUID, "My address1", "Street1",
                "511", "1", "00-001", "Warsaw1", "1", "Polans1", "1", ConverterUtils.convert(user));
        deliveryAddressController.put(UUID.fromString(STR_UUID), deliveryAddressJson);

        DeliveryAddressDTO deliveryAddressDB = deliveryAddressService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(deliveryAddressJson, deliveryAddressDB);
    }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus status1 = txManager.getTransaction(TransactionDefinition.withDefaults());
        User user = TestUtils.user("9986208e-961a-48d4-bf7a-112c627779c2",
                TestUtils.personalData("John", "Smith", Sex.MALE, "501-501-501", "jh512@gmail.com"), null,
                TestUtils.logginData("jSmith", "I@mIronM@n12"), Archive.CURRENT);
        userRepo.save(user);
        DeliveryAddress deliveryAddress = TestUtils.deliveryAddress(STR_UUID, "My address", "Street",
                "51", "", "00-000", "Warsaw", null, "Polans", null, user);
        deliveryAddressRepo.save(deliveryAddress);
        txManager.commit(status1);

        TransactionStatus status2 = txManager.getTransaction(TransactionDefinition.withDefaults());
        deliveryAddressController.delete(UUID.fromString(STR_UUID));
        txManager.commit(status2);

        TransactionStatus status3 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Truth8.assertThat(deliveryAddressService.getByUuid(UUID.fromString(STR_UUID))).isEmpty();
        txManager.commit(status3);
    }

    @Configuration
    public static class Config {
        @Bean
        public DeliveryAddressService deliveryAddressService(DeliveryAddressRepo deliveryAddressRepo, UserRepo userRepo) {
            return new DeliveryAddressServiceImpl(deliveryAddressRepo, userRepo);
        }

        @Bean
        public DeliveryAddressController deliveryAddressController(DeliveryAddressService deliveryAddressService) {
            return new DeliveryAddressController(deliveryAddressService);
        }
    }

}
