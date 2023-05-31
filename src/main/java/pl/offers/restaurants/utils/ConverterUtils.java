package pl.offers.restaurants.utils;

import pl.offers.restaurants.dto.*;
import pl.offers.restaurants.model.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ConverterUtils {

    public static DelivererDTO convert(Deliverer deliverer) {
        DelivererDTO delivererDTO = new DelivererDTO();
        delivererDTO.setUuid(deliverer.getUuid());
        delivererDTO.setPersonalData(convert(deliverer.getPersonalData()));
        delivererDTO.setLogginData(convert(deliverer.getLogginData()));
        delivererDTO.setArchive(deliverer.getArchive());
        delivererDTO.setOrders(convertOrders(deliverer.getOrders()));
        return delivererDTO;
    }

    public static Deliverer convert(DelivererDTO delivererDTO) {
        Deliverer deliverer = new Deliverer();
        deliverer.setUuid(delivererDTO.getUuid());
        deliverer.setPersonalData(convert(delivererDTO.getPersonalData()));
        deliverer.setLogginData(convert(delivererDTO.getLogginData()));
        deliverer.setArchive(delivererDTO.getArchive());
        deliverer.setOrders(convertOrdersDTO(delivererDTO.getOrders()));
        return deliverer;
    }


    public static PersonalData convert(PersonalDataDTO personalDataDTO) {
        return PersonalData.builder()
                .withName(personalDataDTO.getName())
                .withSurname(personalDataDTO.getSurname())
                .withSex(personalDataDTO.getSex())
                .withPhone(personalDataDTO.getPhone())
                .withEmail(personalDataDTO.getEmail())
                .build();
    }

    public static PersonalDataDTO convert(PersonalData personalData) {
        return PersonalDataDTO.builder()
                .withName(personalData.getName())
                .withSurname(personalData.getSurname())
                .withSex(personalData.getSex())
                .withPhone(personalData.getPhone())
                .withEmail(personalData.getEmail())
                .build();
    }


    public static LogginData convert(LogginDataDTO logginDataDTO) {
        return LogginData.builder()
                .withLogin(logginDataDTO.getLogin())
                .withPassword(logginDataDTO.getPassword())
                .build();
    }

    public static LogginDataDTO convert(LogginData logginData) {
        return LogginDataDTO.builder()
                .withLogin(logginData.getLogin())
                .withPassword(logginData.getPassword())
                .build();
    }


    public static List<Order> convertOrdersDTO(List<OrderDTO> orders) {
        if (orders == null) {
            return new ArrayList<>();
        }
        ArrayList<Order> orders = new ArrayList<>();
        for (OrderDTO dto : orders) {
            orders.add(convert(dto));
        }
        return orders;
    }

    public static Order convert(OrderDTO orderDTO) {
        return Order.builder()
                .withUuid(orderDTO.getUuid())
                .withNettoPrice(orderDTO.getNettoPrice())
                .withBruttoPrice(orderDTO.getBruttoPrice())
                .withDiscountCode(convert(orderDTO.getDiscountCode()))
                .withAmountToPayBrutto(orderDTO.getAmountToPayBrutto())
                .withNote(orderDTO.getNote())
                .withStatus(convert(orderDTO.getOrderStatus()))
                .withUser(convert(orderDTO.getUser()))
                .withDeliverer(convert(orderDTO.getDeliverer()))
                .withDeliveryAddress(convert(orderDTO.getDeliveryAddress()))
                .withOrderItems(convertOrderItems(orderDTO.getOrderItems()))
                .withRestaurant(convert(orderDTO.getRestaurant()))
                .build();
    }

    public static List<OrderDTO> convertOrders(List<Order> orders) {
        if (orders == null) {
            return new ArrayList<>();
        }
        ArrayList<OrderDTO> orders = new ArrayList<>();
        for (Order dto : orders) {
            orders.add(convert(dto));
        }
        return orders;
    }

    public static OrderDTO convert(Order order) {
        return OrderDTO.builder()
                .withUuid(order.getUuid())
                .withNettoPrice(order.getNettoPrice())
                .withBruttoPrice(order.getBruttoPrice())
                .withDiscountCode(convert(order.getDiscountCode()))
                .withAmountToPayBrutto(order.getAmountToPayBrutto())
                .withNote(order.getNote())
                .withOrderStatus(convert(order.getStatus()))
                .withUser(convert(order.getUser()))
                .withDeliverer(convert(order.getDeliverer()))
                .withDeliveryAddress(convert(order.getDeliveryAddress()))
                .withOrderItems(convertOrderItems(order.getOrderItems()))
                .withRestaurant(convert(order.getRestaurant()))
                .build();
    }


    public static List<DiscountCode> convertDiscountCodes(List<DiscountCodeDTO> discountCodes) {
        if (discountCodes == null) {
            return new ArrayList<>();
        }
        ArrayList<DiscountCode> discountCodes = new ArrayList<>();
        for (DiscountCodeDTO dto : discountCodes) {
            discountCodes.add(convert(dto));
        }
        return discountCodes;
    }

    public static DiscountCode convert(@Nullable DiscountCodeDTO discountCodeDTO) {
        if (discountCodeDTO == null) {
            return null;
        }
        return DiscountCode.builder()
                .withUuid(discountCodeDTO.getUuid())
                .withCode(discountCodeDTO.getCode())
                .withDiscount(discountCodeDTO.getDiscount())
                .withDiscountUnit(discountCodeDTO.getDiscountUnit())
                .withPeriod(convert(discountCodeDTO.getPeriod()))
                .withUsers(convertUsers(discountCodeDTO.getUsers()))
                .withRestaurants(convertRestaurants(discountCodeDTO.getRestaurants()))
                .build();
    }

    public static List<DiscountCodeDTO> convertDiscountCodes(List<DiscountCode> discountCodes) {
        if (discountCodes == null) {
            return new ArrayList<>();
        }
        ArrayList<DiscountCodeDTO> discountCodes = new ArrayList<>();
        for (DiscountCode dto : discountCodes) {
            discountCodes.add(convert(dto));
        }
        return discountCodes;
    }

    public static DiscountCodeDTO convert(@Nullable DiscountCode discountCode) {
        if (discountCode == null) {
            return null;
        }
        return DiscountCodeDTO.builder()
                .withUuid(discountCode.getUuid())
                .withCode(discountCode.getCode())
                .withDiscount(discountCode.getDiscount())
                .withDiscountUnit(discountCode.getDiscountUnit())
                .withPeriod(convert(discountCode.getPeriod()))
                .withUsers(convertUsers(discountCode.getUsers()))
                .withRestaurants(convertRestaurants(discountCode.getRestaurants()))
                .build();
    }


    public static Period convert(PeriodDTO periodDTO) {
        return Period.builder()
                .withBegin(periodDTO.getBegin())
                .withEnd(periodDTO.getEnd())
                .build();
    }

    public static PeriodDTO convert(Period period) {
        return PeriodDTO.builder()
                .withBegin(period.getBegin())
                .withEnd(period.getEnd())
                .build();
    }


    public static PeriodTime convert(PeriodTimeDTO periodTimeDTO) {
        return PeriodTime.builder()
                .withBegin(periodTimeDTO.getBegin())
                .withEnd(periodTimeDTO.getEnd())
                .build();
    }

    public static PeriodTimeDTO convert(PeriodTime periodTime) {
        return PeriodTimeDTO.builder()
                .withBegin(periodTime.getBegin())
                .withEnd(periodTime.getEnd())
                .build();
    }


    public static OrderStatus convert(OrderStatusDTO orderStatusDTO) {
        return OrderStatus.builder()
                .withIsPaid(orderStatusDTO.getIsPaid())
                .withOrderTime(orderStatusDTO.getOrderTime())
                .withGiveOutTime(orderStatusDTO.getGiveOutTime())
                .withDeliveryTime(orderStatusDTO.getDeliveryTime())
                .build();
    }

    public static OrderStatusDTO convert(OrderStatus orderStatus) {
        return OrderStatusDTO.builder()
                .withIsPaid(orderStatus.getIsPaid())
                .withOrderTime(orderStatus.getOrderTime())
                .withGiveOutTime(orderStatus.getGiveOutTime())
                .withDeliveryTime(orderStatus.getDeliveryTime())
                .build();
    }


    public static List<User> convertUsers(List<UserDTO> users) {
        if (users == null) {
            return new ArrayList<>();
        }
        ArrayList<User> users = new ArrayList<>();
        for (UserDTO dto : users) {
            users.add(convert(dto));
        }
        return users;
    }

    public static User convert(UserDTO userDTO) {
        return User.builder()
                .withUuid(userDTO.getUuid())
                .withPersonalData(convert(userDTO.getPersonalData()))
                .withDeliveryAddress(convertAddresses(userDTO.getDeliveryAddress()))
                .withLogginData(convert(userDTO.getLogginData()))
                .withOrders(convertOrdersDTO(userDTO.getOrders()))
                .withOperationEvidence(convertOperationEvidences(userDTO.getOperationEvidence()))
                .withDiscountCodes(convertDiscountCodes(userDTO.getDiscountCodes()))
                .withArchive(userDTO.getArchive())
                .build();
    }

    public static User convertNoEvidence(UserDTO userDTO) {
        return User.builder()
                .withUuid(userDTO.getUuid())
                .build();
    }

    public static List<UserDTO> convertUsers(List<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }
        ArrayList<UserDTO> users = new ArrayList<>();
        for (User dto : users) {
            users.add(convert(dto));
        }
        return users;
    }

    public static UserDTO convert(User user) {
        return UserDTO.builder()
                .withUuid(user.getUuid())
                .withPersonalData(convert(user.getPersonalData()))
                .withDeliveryAddress(convertAddresss(user.getDeliveryAddress()))
                .withLogginData(convert(user.getLogginData()))
                .withOrders(convertOrders(user.getOrders()))
                .withOperationEvidence(convertOperationEvidences(user.getOperationEvidence()))
                .withDiscountCodes(convertDiscountCodes(user.getDiscountCodes()))
                .withArchive(user.getArchive())
                .build();
    }


    public static Address convert(AddressDTO DeliveryAddresDTO) {
        return Address.builder()
                .withStreet(DeliveryAddresDTO.getStreet())
                .withStreetNumber(DeliveryAddresDTO.getStreetNumber())
                .withLocalNumber(DeliveryAddresDTO.getLocalNumber())
                .withPostcode(DeliveryAddresDTO.getPostcode())
                .withCity(DeliveryAddresDTO.getCity())
                .withBorough(DeliveryAddresDTO.getBorough())
                .withState(DeliveryAddresDTO.getState())
                .build();
    }

    public static AddressDTO convert(Address address) {
        return AddressDTO.builder()
                .withStreet(address.getStreet())
                .withStreetNumber(address.getStreetNumber())
                .withLocalNumber(address.getLocalNumber())
                .withPostcode(address.getPostcode())
                .withCity(address.getCity())
                .withBorough(address.getBorough())
                .withState(address.getState())
                .build();
    }


    public static List<DeliveryAddress> convertAddresses(List<DeliveryAddressDTO> deliveryAddresss) {
        if (deliveryAddresss == null) {
            return new ArrayList<>();
        }
        ArrayList<DeliveryAddress> deliveryAddresses = new ArrayList<>();
        for (DeliveryAddressDTO dto : deliveryAddresss) {
            deliveryAddresses.add(convert(dto));
        }
        return deliveryAddresses;
    }

    public static DeliveryAddress convert(DeliveryAddressDTO DeliveryAddressDTO) {
        return DeliveryAddress.builder()
                .withUuid(DeliveryAddressDTO.getUuid())
                .withDescription(DeliveryAddressDTO.getDescription())
                .withStreet(DeliveryAddressDTO.getStreet())
                .withStreetNumber(DeliveryAddressDTO.getStreetNumber())
                .withLocalNumber(DeliveryAddressDTO.getLocalNumber())
                .withPostcode(DeliveryAddressDTO.getPostcode())
                .withCity(DeliveryAddressDTO.getCity())
                .withBorough(DeliveryAddressDTO.getBorough())
                .withCounty(DeliveryAddressDTO.getCounty())
                .withState(DeliveryAddressDTO.getState())
                .withUser(convert(DeliveryAddressDTO.getUser()))
                .build();
    }

    public static List<DeliveryAddressDTO> convertAddresss(List<DeliveryAddress> deliveryAddresses) {
        if (deliveryAddresses == null) {
            return new ArrayList<>();
        }
        ArrayList<DeliveryAddressDTO> deliveryAddresss = new ArrayList<>();
        for (DeliveryAddress dto : deliveryAddresses) {
            deliveryAddresss.add(convert(dto));
        }
        return deliveryAddresss;
    }

    public static DeliveryAddressDTO convert(DeliveryAddress deliveryAddress) {
        return DeliveryAddressDTO.builder()
                .withUuid(deliveryAddress.getUuid())
                .withDescription(deliveryAddress.getDescription())
                .withStreet(deliveryAddress.getStreet())
                .withStreetNumber(deliveryAddress.getStreetNumber())
                .withLocalNumber(deliveryAddress.getLocalNumber())
                .withPostcode(deliveryAddress.getPostcode())
                .withCity(deliveryAddress.getCity())
                .withBorough(deliveryAddress.getBorough())
                .withCounty(deliveryAddress.getCounty())
                .withState(deliveryAddress.getState())
                .withUser(convert(deliveryAddress.getUser()))
                .build();
    }


    public static List<OrderItem> convertOrderItems(List<OrderItemDTO> orderItems) {
        if (orderItems == null) {
            return new ArrayList<>();
        }
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO dto : orderItems) {
            orderItems.add(convert(dto));
        }
        return orderItems;
    }

    public static OrderItem convert(OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .withUuid(orderItemDTO.getUuid())
                .withQuantity(orderItemDTO.getQuantity())
                .withMenuItem(convert(orderItemDTO.getMenuItem()))
                .build();
    }

    public static List<OrderItemDTO> convertOrderItems(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return new ArrayList<>();
        }
        ArrayList<OrderItemDTO> orderItems = new ArrayList<>();
        for (OrderItem dto : orderItems) {
            orderItems.add(convert(dto));
        }
        return orderItems;
    }

    public static OrderItemDTO convert(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .withUuid(orderItem.getUuid())
                .withQuantity(orderItem.getQuantity())
                .withMenuItem(convert(orderItem.getMenuItem()))
                .build();
    }


    public static List<Restaurant> convertRestaurants(List<RestaurantDTO> restaurants) {
        if (restaurants == null) {
            return new ArrayList<>();
        }
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (RestaurantDTO dto : restaurants) {
            restaurants.add(convert(dto));
        }
        return restaurants;
    }

    public static Restaurant convert(RestaurantDTO restaurantDTO) {
        return Restaurant.builder()
                .withUuid(restaurantDTO.getUuid())
                .withName(restaurantDTO.getName())
                .withLogginData(convert(restaurantDTO.getLogginData()))
                .withCompanyData(convert(restaurantDTO.getCompanyData()))
                .withOpenTimes(convertOpenTimes(restaurantDTO.getOpenTimes()))
                .withOrders(convertOrdersDTO(restaurantDTO.getOrders()))
                .withMenu(convertMenuItems(restaurantDTO.getMenuItems()))
                .withDiscountCodes(convertDiscountCodes(restaurantDTO.getDiscountCodes()))
                .withArchive(restaurantDTO.getArchive())
                .build();
    }

    public static List<RestaurantDTO> convertRestaurants(List<Restaurant> restaurants) {
        if (restaurants == null) {
            return new ArrayList<>();
        }
        ArrayList<RestaurantDTO> restaurants = new ArrayList<>();
        for (Restaurant dto : restaurants) {
            restaurants.add(convert(dto));
        }
        return restaurants;
    }

    public static RestaurantDTO convert(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .withUuid(restaurant.getUuid())
                .withName(restaurant.getName())
                .withLogginData(convert(restaurant.getLogginData()))
                .withCompanyData(convert(restaurant.getCompanyData()))
                .withOpenTimes(convertOpenTimes(restaurant.getOpenTimes()))
                .withOrders(convertOrders(restaurant.getOrders()))
                .withMenuItems(convertMenuItems(restaurant.getMenu()))
                .withDiscountCodes(convertDiscountCodes(restaurant.getDiscountCodes()))
                .withArchive(restaurant.getArchive())
                .build();
    }


    public static List<OperationEvidence> convertOperationEvidences(List<OperationEvidenceDTO> operationEvidences) {
        if (operationEvidences == null) {
            return new ArrayList<>();
        }
        ArrayList<OperationEvidence> operationEvidences = new ArrayList<>();
        for (OperationEvidenceDTO dto : operationEvidences) {
            operationEvidences.add(convert(dto));
        }
        return operationEvidences;
    }

    public static OperationEvidence convert(OperationEvidenceDTO operationEvidenceDTO) {
        return OperationEvidence.builder()
                .withDate(operationEvidenceDTO.getDate())
                .withType(operationEvidenceDTO.getType())
                .withAmount(operationEvidenceDTO.getAmount())
                .withUser(convertNoEvidence(operationEvidenceDTO.getUser()))
                .build();
    }

    public static List<OperationEvidenceDTO> convertOperationEvidences(List<OperationEvidence> operationEvidences) {
        if (operationEvidences == null) {
            return new ArrayList<>();
        }
        ArrayList<OperationEvidenceDTO> operationEvidences = new ArrayList<>();
        for (OperationEvidence dto : operationEvidences) {
            operationEvidences.add(convert(dto));
        }
        return operationEvidences;
    }

    public static OperationEvidenceDTO convert(OperationEvidence operationEvidence) {
        return OperationEvidenceDTO.builder()
                .withDate(operationEvidence.getDate())
                .withType(operationEvidence.getType())
                .withAmount(operationEvidence.getAmount())
                .withUser(convert(operationEvidence.getUser()))
                .build();
    }


    public static List<MenuItem> convertMenuItems(List<MenuItemDTO> menuItems) {
        if (menuItems == null) {
            return new ArrayList<>();
        }
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        for (MenuItemDTO dto : menuItems) {
            menuItems.add(convert(dto));
        }
        return menuItems;
    }

    public static MenuItem convert(MenuItemDTO menuItemDTO) {
        return MenuItem.builder()
                .withUuid(menuItemDTO.getUuid())
                .withName(menuItemDTO.getName())
                .withNettoPrice(menuItemDTO.getNettoPrice())
                .withVatTax(menuItemDTO.getVatTax())
                .withBruttoPrice(menuItemDTO.getBruttoPrice())
                .withDishes(convertDishs(menuItemDTO.getDishes()))
                .withRestaurant(convert(menuItemDTO.getRestaurant()))
                .build();
    }

    public static List<MenuItemDTO> convertMenuItems(List<MenuItem> menuItems) {
        if (menuItems == null) {
            return new ArrayList<>();
        }
        ArrayList<MenuItemDTO> menuItems = new ArrayList<>();
        for (MenuItem dto : menuItems) {
            menuItems.add(convert(dto));
        }
        return menuItems;
    }

    public static MenuItemDTO convert(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .withUuid(menuItem.getUuid())
                .withName(menuItem.getName())
                .withNettoPrice(menuItem.getNettoPrice())
                .withVatTax(menuItem.getVatTax())
                .withBruttoPrice(menuItem.getBruttoPrice())
                .withDishes(convertDishes(menuItem.getDishes()))
                .withRestaurant(convert(menuItem.getRestaurant()))
                .build();
    }


    public static CompanyData convert(CompanyDataDTO companyDataDTO) {
        return CompanyData.builder()
                .withName(companyDataDTO.getName())
                .withAddress(convert(companyDataDTO.getAddress()))
                .withNIP(companyDataDTO.getNIP())
                .withPhone(companyDataDTO.getPhone())
                .withEmail(companyDataDTO.getEmail())
                .build();
    }

    public static CompanyDataDTO convert(CompanyData companyData) {
        return CompanyDataDTO.builder()
                .withName(companyData.getName())
                .withAddress(convert(companyData.getAddress()))
                .withNIP(companyData.getNIP())
                .withPhone(companyData.getPhone())
                .withEmail(companyData.getEmail())
                .build();
    }


    public static List<OpenTime> convertOpenTimes(List<OpenTimeDTO> openTimes) {
        if (openTimes == null) {
            return new ArrayList<>();
        }
        ArrayList<OpenTime> openTimes = new ArrayList<>();
        for (OpenTimeDTO dto : openTimes) {
            openTimes.add(convert(dto));
        }
        return openTimes;
    }

    public static OpenTime convert(OpenTimeDTO openTimeDTO) {
        return OpenTime.builder()
                .withUuid(openTimeDTO.getUuid())
                .withDayOfWeek(openTimeDTO.getDayOfWeek())
                .withPeriodTime(convert(openTimeDTO.getPeriodTime()))
                .withRestaurant(convert(openTimeDTO.getRestaurant()))
                .build();
    }

    public static List<OpenTimeDTO> convertOpenTimes(List<OpenTime> openTimes) {
        if (openTimes == null) {
            return new ArrayList<>();
        }
        ArrayList<OpenTimeDTO> openTimes = new ArrayList<>();
        for (OpenTime dto : openTimes) {
            openTimes.add(convert(dto));
        }
        return openTimes;
    }

    public static OpenTimeDTO convert(OpenTime openTime) {
        return OpenTimeDTO.builder()
                .withUuid(openTime.getUuid())
                .withDayOfWeek(openTime.getDayOfWeek())
                .withPeriodTime(convert(openTime.getPeriodTime()))
                .withRestaurant(convert(openTime.getRestaurant()))
                .build();
    }


    public static List<Dish> convertDishs(List<DishDTO> dishs) {
        if (dishs == null) {
            return new ArrayList<>();
        }
        ArrayList<Dish> dishes = new ArrayList<>();
        for (DishDTO dto : dishs) {
            dishes.add(convert(dto));
        }
        return dishes;
    }

    public static Dish convert(@Nullable DishDTO dishDTO) {
        if (dishDTO == null) {
            return null;
        }
        return Dish.builder()
                .withUuid(dishDTO.getUuid())
                .withQuantity(dishDTO.getQuantity())
                .withProduct(convert(dishDTO.getProduct()))
                .withMenuItems(convertMenuItems(dishDTO.getMenuItems()))
                .build();
    }

    public static List<DishDTO> convertDishes(List<Dish> dishes) {
        if (dishes == null) {
            return new ArrayList<>();
        }
        ArrayList<DishDTO> dishs = new ArrayList<>();
        for (Dish dto : dishes) {
            dishs.add(convert(dto));
        }
        return dishs;
    }

    public static DishDTO convert(@Nullable Dish dish) {
        if (dish == null) {
            return null;
        }
        return DishDTO.builder()
                .withUuid(dish.getUuid())
                .withQuantity(dish.getQuantity())
                .withProduct(convert(dish.getProduct()))
                .withMenuItems(convertMenuItems(dish.getMenuItems()))
                .build();
    }


    public static Product convert(ProductDTO productDTO) {
        return Product.builder()
                .withUuid(productDTO.getUuid())
                .withName(productDTO.getName())
                .withIngredients(convertIngredients(productDTO.getIngredients()))
                .withDish(convert(productDTO.getDish()))
                .build();
    }

    public static ProductDTO convert(Product product) {
        return ProductDTO.builder()
                .withUuid(product.getUuid())
                .withName(product.getName())
                .withIngredients(convertIngredients(product.getIngredients()))
                .withDishDTO(convert(product.getDish()))
                .build();
    }


    public static List<Ingredient> convertIngredients(List<IngredientDTO> ingredients) {
        if (ingredients == null) {
            return new ArrayList<>();
        }
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO dto : ingredients) {
            ingredients.add(convert(dto));
        }
        return ingredients;
    }

    public static Ingredient convert(IngredientDTO ingredientDTO) {
        return Ingredient.builder()
                .withUuid(ingredientDTO.getUuid())
                .withName(ingredientDTO.getName())
                .withIsAllergen(ingredientDTO.getIsAllergen())
                .build();
    }

    public static List<IngredientDTO> convertIngredients(List<Ingredient> ingredients) {
        if (ingredients == null) {
            return new ArrayList<>();
        }
        ArrayList<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient dto : ingredients) {
            ingredients.add(convert(dto));
        }
        return ingredients;
    }

    public static IngredientDTO convert(Ingredient ingredient) {
        return IngredientDTO.builder()
                .withUuid(ingredient.getUuid())
                .withName(ingredient.getName())
                .withIsAllergen(ingredient.getIsAllergen())
                .build();
    }


    public static Employee convert(EmployeeDTO employeeDTO) {
        return Employee.baseBuilder()
                .withUuid(employeeDTO.getUuid())
                .withPersonalData(convert(employeeDTO.getPersonalData()))
                .withLogginData(convert(employeeDTO.getLogginData()))
                .withArchive(employeeDTO.getArchive())
                .build();
    }

    public static EmployeeDTO convert(Employee employee) {
        return EmployeeDTO.baseBuilder()
                .withUuid(employee.getUuid())
                .withPersonalData(convert(employee.getPersonalData()))
                .withLogginData(convert(employee.getLogginData()))
                .withArchive(employee.getArchive())
                .build();
    }

}
