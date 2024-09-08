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
import pl.offers.restaurants.dto.DiscountCodeDTO;
import pl.offers.restaurants.model.DiscountCode;
import pl.offers.restaurants.model.enums.DiscountUnit;
import pl.offers.restaurants.repo.DiscountCodeRepo;
import pl.offers.restaurants.repo.RestaurantRepo;
import pl.offers.restaurants.repo.UserRepo;
import pl.offers.restaurants.service.DiscountCodeService;
import pl.offers.restaurants.service.DiscountCodeServiceImpl;
import pl.offers.restaurants.utils.AssertionUtils;
import pl.offers.restaurants.utils.TestUtils;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest(classes = {
        JPAConfiguration.class,
        DiscountCodeControllerTest.Config.class
})
class DiscountCodeControllerTest {

    private static final String STR_UUID = "9986208e-961a-48d4-bf7a-112c627779c2";
    @Autowired
    private DiscountCodeRepo discountCodeRepo;

    @Autowired
    private DiscountCodeService discountCodeService;

    @Autowired
    private DiscountCodeController discountCodeController;

    @Autowired
    private PlatformTransactionManager txManager;

    // add
    @Test
    @Transactional
    public void put1() {
        DiscountCodeDTO discountCodeJson = TestUtils.discountCodeDTO(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
        discountCodeController.put(UUID.fromString(STR_UUID), discountCodeJson);

        DiscountCodeDTO discountCodeDB = discountCodeService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(discountCodeJson, discountCodeDB);
    }

    // update
    @Test
    @Transactional
    public void put2() {
        DiscountCode discountCode = TestUtils.discountCode(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
        discountCodeRepo.save(discountCode);

        DiscountCodeDTO discountCodeJson = TestUtils.discountCodeDTO(STR_UUID, "BLACK FRIDAY1", new BigDecimal("20.00"), DiscountUnit.PLN,
                "2020-05-01T00:00:00", "2020-06-01T00:00:00", null, null);
        discountCodeController.put(UUID.fromString(STR_UUID), discountCodeJson);

        DiscountCodeDTO discountCodeDB = discountCodeService.getByUuid(UUID.fromString(STR_UUID)).orElseThrow();
        AssertionUtils.assertEquals(discountCodeJson, discountCodeDB);
    }

    @Test
    @Transactional
    public void delete() {
        TransactionStatus status1 = txManager.getTransaction(TransactionDefinition.withDefaults());
        DiscountCode discountCode = TestUtils.discountCode(STR_UUID, "BLACK FRIDAY", new BigDecimal("25.00"), DiscountUnit.PERCENT,
                "2020-01-01T00:00:00", "2020-02-01T00:00:00", null, null);
        discountCodeRepo.save(discountCode);
        txManager.commit(status1);

        TransactionStatus status2 = txManager.getTransaction(TransactionDefinition.withDefaults());
        discountCodeController.delete(UUID.fromString(STR_UUID));
        txManager.commit(status2);

        TransactionStatus status3 = txManager.getTransaction(TransactionDefinition.withDefaults());
        Truth8.assertThat(discountCodeService.getByUuid(UUID.fromString(STR_UUID))).isEmpty();
        txManager.commit(status3);
    }

    @Configuration
    public static class Config {
        @Bean
        public DiscountCodeService discountCodeService(DiscountCodeRepo discountCodeRepo, UserRepo userRepo, RestaurantRepo restaurantRepo) {
            return new DiscountCodeServiceImpl(discountCodeRepo, userRepo, restaurantRepo);
        }

        @Bean
        public DiscountCodeController discountCodeController(DiscountCodeService discountCodeService) {
            return new DiscountCodeController(discountCodeService);
        }
    }

}
