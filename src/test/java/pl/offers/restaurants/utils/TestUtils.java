package pl.offers.restaurants.utils;

import pl.offers.restaurants.dto.*;
import pl.offers.restaurants.model.*;
import pl.offers.restaurants.model.enums.*;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static DelivererDTO delivererDTO(String uuid, PersonalDataDTO personalDataDTO, LogginDataDTO logginDataDTO, Archive archive) {
        DelivererDTO delivererDTO = new DelivererDTO();
        delivererDTO.setUuid(UUID.fromString(uuid));
        delivererDTO.setPersonalData(personalDataDTO);
        delivererDTO.setLogginData(logginDataDTO);
        delivererDTO.setArchive(archive);
        delivererDTO.setOrders(new ArrayList<>());
        return delivererDTO;
    }

    public static PersonalDataDTO personalDataDTO(String name, String surname, Sex sex, String phone, String email) {
        return PersonalDataDTO.builder()
                .withName(name)
                .withSurname(surname)
                .withSex(sex)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static LogginDataDTO logginDataDTO(String login, String passwrd) {
        return LogginDataDTO.builder()
                .withLogin(login)
                .withPassword(passwrd)
                .build();
    }

    public static DeliveryAddressDTO deliveryAddressDTO(String uuid, String description, String street, String streetNumber,
                                                        String locaNumber, String postcode, String city, String borough,
                                                        String country, String state, UserDTO userDTO) {
        return DeliveryAddressDTO.builder()
                .withUuid(UUID.fromString(uuid))
                .withDescription(description)
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withLocalNumber(locaNumber)
                .withPostcode(postcode)
                .withCity(city)
                .withBorough(borough)
                .withCounty(country)
                .withState(state)
                .withUser(userDTO)
                .build();
    }

    public static UserDTO userDTO(String uuid, PersonalDataDTO personalDataDTO, List<DeliveryAddressDTO> addresses, LogginDataDTO logginDataDTO, Archive archive) {
        return UserDTO.builder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalDataDTO)
                .withDeliveryAddress(addresses)
                .withLogginData(logginDataDTO)
                .withOperationEvidence(new ArrayList<>())
                .withArchive(archive)
                .build();
    }

    public static DiscountCodeDTO discountCodeDTO(String uuid, String code, BigDecimal discount, DiscountUnit unit, String begin,
                                                  String end, List<UserDTO> users, List<RestaurantDTO> restaurants) {
        return DiscountCodeDTO.builder()
                .withUuid(UUID.fromString(uuid))
                .withCode(code)
                .withDiscount(discount)
                .withDiscountUnit(unit)
                .withPeriod(periodDTO(begin, end))
                .withUsers(users)
                .withRestaurants(restaurants)
                .build();
    }

    public static DiscountCode discountCode(String uuid, String code, BigDecimal discount, DiscountUnit unit, String begin,
                                            String end, List<User> user, List<Restaurant> restaurant) {
        return DiscountCode.builder()
                .withUuid(UUID.fromString(uuid))
                .withCode(code)
                .withDiscount(discount)
                .withDiscountUnit(unit)
                .withPeriod(period(begin, end))
                .withUsers(user)
                .withRestaurants(restaurant)
                .build();
    }

    public static OperationEvidenceDTO operationEvidenceDTO(String dateInstant, EvidenceType evidenceType, BigDecimal amount, UserDTO userDTO) {
        return OperationEvidenceDTO.builder()
                .withDate(Instant.parse(dateInstant))
                .withType(evidenceType)
                .withAmount(amount)
                .withUser(userDTO)
                .build();
    }

    public static PeriodDTO periodDTO(String begin, String end) {
        return PeriodDTO.builder()
                .withBegin(begin != null ? LocalDateTime.parse(begin) : null)
                .withEnd(end != null ? LocalDateTime.parse(end) : null)
                .build();
    }

    public static Period period(String begin, String end) {
        return Period.builder()
                .withBegin(begin != null ? LocalDateTime.parse(begin) : null)
                .withEnd(end != null ? LocalDateTime.parse(end) : null)
                .build();
    }

    public static User user(String uuid, PersonalData personalData, List<DeliveryAddress> addresses, LogginData logginData, Archive archive) {
        return User.builder()
                .withUuid(UUID.fromString(uuid))
                .withPersonalData(personalData)
                .withDeliveryAddress(addresses)
                .withLogginData(logginData)
                .withOperationEvidence(new ArrayList<>())
                .withArchive(archive)
                .build();
    }

    public static OperationEvidence operationEvidence(String dateInstant, EvidenceType evidenceType, BigDecimal amount, User user) {
        return OperationEvidence.builder()
                .withDate(Instant.parse(dateInstant))
                .withType(evidenceType)
                .withAmount(amount)
                .withUser(user)
                .build();
    }

    public static Deliverer deliverer(String uuid, PersonalData personalData, LogginData logginData, Archive archive) {
        Deliverer deliverer = new Deliverer();
        deliverer.setUuid(UUID.fromString(uuid));
        deliverer.setPersonalData(personalData);
        deliverer.setLogginData(logginData);
        deliverer.setArchive(archive);
        deliverer.setOrders(new ArrayList<>());
        return deliverer;
    }

    public static PersonalData personalData(String name, String surname, Sex sex, String phone, String email) {
        return PersonalData.builder()
                .withName(name)
                .withSurname(surname)
                .withSex(sex)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static LogginData logginData(String login, String passwrd) {
        return LogginData.builder()
                .withLogin(login)
                .withPassword(passwrd)
                .build();
    }

    public static DeliveryAddress deliveryAddress(String uuid, String description, String street, String streetNumber,
                                                  String locaNumber, String postcode, String city, String borough,
                                                  String country, String state, User user) {
        return DeliveryAddress.builder()
                .withUuid(UUID.fromString(uuid))
                .withDescription(description)
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withLocalNumber(locaNumber)
                .withPostcode(postcode)
                .withCity(city)
                .withBorough(borough)
                .withCounty(country)
                .withState(state)
                .withUser(user)
                .build();
    }

    public static Restaurant restaurant(String uuid, String name, LogginData logginData, CompanyData companyData, Archive archive) {
        return Restaurant.builder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withLogginData(logginData)
                .withCompanyData(companyData)
                .withArchive(archive)
                .withOrders(new ArrayList<>())
                .withOpenTimes(new ArrayList<>())
                .withMenu(new ArrayList<>())
                .withDiscountCodes(new ArrayList<>())
                .build();
    }

    public static CompanyData companyData(String name, Address address, String NIP, String REGON, String phone, String email) {
        return CompanyData.builder()
                .withName(name)
                .withAddress(address)
                .withNIP(NIP)
                .withREGON(REGON)
                .withPhone(phone)
                .withEmail(email)
                .build();
    }

    public static Address address(String street, String streetNumber, String postcode, String city) {
        return Address.builder()
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withPostcode(postcode)
                .withCity(city)
                .build();
    }

    public static Order order(String uuid, DiscountCode discountCode, String note, User userDTO,
                              Deliverer deliverer, DeliveryAddress deliveryAddress, Restaurant restaurant, BigDecimal nettoPrice,
                              BigDecimal bruttoPrice, BigDecimal amountToPayBrutto, OrderStatus orderStatus, OrderItem... orderItems) {
        return Order.builder()
                .withUuid(UUID.fromString(uuid))
                .withDiscountCode(discountCode)
                .withNote(note)
                .withUser(userDTO)
                .withDeliverer(deliverer)
                .withDeliveryAddress(deliveryAddress)
                .withRestaurant(restaurant)
                .withOrderItems(Arrays.asList(orderItems))
                .withNettoPrice(nettoPrice)
                .withBruttoPrice(bruttoPrice)
                .withAmountToPayBrutto(amountToPayBrutto)
                .withStatus(orderStatus)
                .build();
    }

    public static OrderStatus orderStatus(@Nullable String orderTimeInstant, Boolean isPaid, @Nullable String giveOutTimeInstant, @Nullable String deliveryTimeInstant) {
        return OrderStatus.builder()
                .withOrderTime(orderTimeInstant != null ? Instant.parse(orderTimeInstant) : null)
                .withIsPaid(isPaid)
                .withGiveOutTime(giveOutTimeInstant != null ? Instant.parse(giveOutTimeInstant) : null)
                .withDeliveryTime(deliveryTimeInstant != null ? Instant.parse(deliveryTimeInstant) : null)
                .build();
    }

    public static OrderStatusDTO orderStatusDTO(@Nullable String orderTimeInstant, Boolean isPaid, @Nullable String giveOutTimeInstant, @Nullable String deliveryTimeInstant) {
        return OrderStatusDTO.builder()
                .withOrderTime(orderTimeInstant != null ? Instant.parse(orderTimeInstant) : null)
                .withIsPaid(isPaid)
                .withGiveOutTime(giveOutTimeInstant != null ? Instant.parse(giveOutTimeInstant) : null)
                .withDeliveryTime(deliveryTimeInstant != null ? Instant.parse(deliveryTimeInstant) : null)
                .build();
    }

    public static OrderDTO orderDTO(String uuid, DiscountCodeDTO discountCodeDTO, String note, UserDTO userDTO,
                                    DelivererDTO delivererDTO, DeliveryAddressDTO deliveryAddressDTO, RestaurantDTO restaurantDTO, OrderItemDTO... orderItems) {
        return OrderDTO.builder()
                .withUuid(UUID.fromString(uuid))
                .withDiscountCode(discountCodeDTO)
                .withNote(note)
                .withUser(userDTO).withDeliverer(delivererDTO)
                .withDeliveryAddress(deliveryAddressDTO)
                .withRestaurant(restaurantDTO)
                .withOrderItems(Arrays.asList(orderItems))
                .build();
    }

    public static OrderItem orderItem(String uuid, Integer quantity, MenuItem menuItem) {
        return OrderItem.builder()
                .withUuid(UUID.fromString(uuid))
                .withQuantity(quantity)
                .withMenuItem(menuItem)
                .build();
    }

    public static MenuItem menuItem(String uuid, String name, BigDecimal nettoPrice, VatTax vatTax, BigDecimal bruttoPrice, Restaurant restaurant, Dish... dishes) {
        return MenuItem.builder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withNettoPrice(nettoPrice)
                .withVatTax(vatTax)
                .withBruttoPrice(bruttoPrice)
                .withRestaurant(restaurant)
                .withDishes(Arrays.asList(dishes))
                .build();
    }

    public static Dish dish(String uuid, Integer quantity, Product product) {
        return Dish.builder()
                .withUuid(UUID.fromString(uuid))
                .withQuantity(quantity)
                .withProduct(product)
                .build();
    }

    public static Product product(String uuid, String name, Ingredient... ingredients) {
        return Product.builder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withIngredients(Arrays.asList(ingredients))
                .build();
    }

    public static Ingredient ingredient(String uuid, String name, Boolean isAllergen) {
        return Ingredient.builder()
                .withUuid(UUID.fromString(uuid))
                .withName(name)
                .withIsAllergen(isAllergen)
                .build();
    }

}
