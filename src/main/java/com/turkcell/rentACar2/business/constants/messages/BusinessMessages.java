package com.turkcell.rentACar2.business.constants.messages;

public class BusinessMessages {

    // Global Messages

    public static final String DATA_LISTED = "All data listed";
    public static final String DATA_ADDED = "Data added";
    public static final String DATA_BROUGHT = "Data brought: ";
    public static final String DATA_UPDATED = "Data updated: ";
    public static final String DATA_DELETED = "Data deleted: ";
    public static final String DATA_LISTED_BY_CAR_ID = "Data listed by car id: ";
    public static final String INVALID_DAILY_PRICE = "Daily price cannot be less than 1";
    public static final String INVALID_RETURN_DATE = "Return date cannot be earlier than rent date";

    // Additional Service Messages

    public static final String ADDITIONAL_SERVICE_DOES_NOT_EXISTS = "Additional service does not exist.";
    public static final String ADDITIONAL_SERVICE_ALREADY_EXISTS = " Additional service already exists. Please enter different service.";
    public static final String INVALID_ADDITIONAL_SERVICE_NAME = "Additional service name must contain at least 2 letters";

    // Brand Messages

    public static final String BRAND_DOES_NOT_EXISTS = "Brand does not exist.";
    public static final String BRAND_ALREADY_EXISTS = " Brand exists. Please enter different model.";

    // Car Messages

    public static final String CARS_LIST_BY_DAILY_PRICE = "Cars listed by daily price: ";
    public static final String CARS_LIST_BY_PAGE_NO = "Cars listed by page no: ";
    public static final String CARS_SORTED_BY_DAILY_PRICE = "Sorted cars by daily price listed";
    public static final String CARS_SORTED_BY_COLOR_ID = "Cars listed by color id: ";
    public static final String CAR_DOES_NOT_EXISTS = "No car with id: ";
    public static final String CARS_LIST_BY_MODEL_YEAR = "Cars listed by model year: ";
    public static final String DAILY_PRICE_DOES_NOT_VALID = "Daily price does not valid: ";
    public static final String PAGE_NO_DOES_NOT_VALID = "Page number does not valid: ";
    public static final String PAGE_SIZE_DOES_NOT_VALID = "Page size does not valid: ";
    public static final String MODEL_YEAR_DOES_NOT_VALID = "Model year does not valid: ";
    public static final String INVALID_MODEL_YEAR = "Model year cannot be less than 1930";

    // Car Damage Messages

    public static final String CAR_DAMAGE_DOES_NOT_EXISTS = "No car damage with id: ";
    public static final String CAR_DAMAGE_DOES_NOT_EXISTS_BY_CAR_ID = "No car damage with car id: ";
    public static final String CAR_DAMAGE_EXISTS = " Car damage already exists.";

    //Car Maintenance Messages

    public static final String CAR_MAINTENANCE_DOES_NOT_EXISTS = "No car maintenance with id: ";
    public static final String CAR_RENTED = "Car rented";
    public static final String INVALID_CAR_MAINTENANCE_DESCRIPTION = "Car maintenance description must contain at least 5 letters";

    // City Messages

    public static final String CITY_ALREADY_EXISTS = " City already exists. Please enter different city: ";
    public static final String CITY_DOES_NOT_EXISTS = "City does not exist.";
    public static final String INVALID_CITY_NAME = "City name must contain at least 3 letters";

    //Color Messages

    public static final String COLOR_DOES_NOT_EXISTS = "Color does not exist.";
    public static final String COLOR_ALREADY_EXISTS = " Color already exists. Please enter different color.";

    // Customer Messages

    public static final String CUSTOMER_DOES_NOT_EXISTS = "Customer does not exist.";
    public static final String INVALID_TAX_NUMBER = "Tax number must be 10 digits.";
    public static final String INVALID_COMPANY_NAME = "Company name must contain at least 3 letters";
    public static final String INVALID_PASSWORD = "Password must contain at least 6 letters";
    public static final String INVALID_FIRST_NAME = "First name must contain at least 2 letters";
    public static final String INVALID_LAST_NAME = "Last name must contain at least 2 letters";
    public static final String INVALID_NATIONAL_IDENTITY_NUMBER = "National identity number must be 11 digits.";
    public static final String CORPORATE_CUSTOMER_ALREADY_EXISTS = "Corporate customer already exists.";
    public static final String EMAIL_ALREADY_EXISTS = "Email address already exists.";

    // Invoice Messages

    public static final String INVOICES_LISTED_BY_INVOICE_DATE = "All invoices listed by date: ";
    public static final String INVOICES_LISTED_BY_CUSTOMER_ID = "All invoices listed by customer id: ";
    public static final String INVOICES_LISTED_BY_PAYMENT_ID = "Invoice brought by payment id";
    public static final String INVOICE_DOES_NOT_EXISTS = "No invoice with id: ";
    public static final String INVALID_INVOICE_DATES = "End date cannot be earlier than start date";

    // Ordered Additional Service Messages

    public static final String ORDERED_SERVICE_LISTED_BY_RENTAL_CAR_ID = "Ordered services listed by rental car id: ";
    public static final String ORDERED_SERVICE_LISTED_BY_ADDITIONAL_SERVICE_ID = "Ordered services listed by additional service id: ";
    public static final String ORDERED_ADDITIONAL_SERVICE_DOES_NOT_EXISTS = "Ordered additional service does not exist.";
    public static final String ORDERED_ADDITIONAL_SERVICE_ALREADY_EXISTS = " Ordered additional service already exists. Please enter different service.";

    // Payment Messages

    public static final String PAYMENT_DOES_NOT_EXISTS = "No payment with id: ";
    public static final String PAYMENT_FAILED = "Card is invalid. Please check your card details.";
    public static final String INVALID_CVV = "Cvv must be 3 digits.";
    public static final String INVALID_EXPIRE_MONTH = "Expire month must be between 1 and 12";
    public static final String INVALID_EXPIRE_YEAR = "Expire year must be between 2020 and 2030";
    public static final String INVALID_CARD_NO = "Card number must be 16 digits.";
    public static final String INVALID_CARD_HOLDER = "Card holder must be between 2 and 50";


    // Rental Car Messages

    public static final String RENTAL_CAR_DOES_NOT_EXISTS = "No rental car with id: ";
    public static final String CAR_IN_MAINTENANCE = "Car in maintenance";
    public static final String INVALID_LAST_DISTANCE = "Last distance cannot be less than current distance";
    public static final String CAR_STILL_RENTED = "Car is still rented";
    public static final String CAR_RETURNED_FROM_RENTAL = "Update: Car returned from rental.";
    public static final String CAR_ALREADY_RETURNED_FROM_RENTAL = "Car is already return";

    // User Card Information Messages

    public static final String USER_CARD_DOES_NOT_EXISTS = "No user card with id: ";
    public static final String USER_CARD_EXISTS = "User card already exists";
}
