package com.jumia.task.service;

import com.jumia.task.api.model.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {

    List<Phone> getAllCustomerPhoneNumbersWithFilters(Optional<Boolean> validFilter, Optional<String> countryFilter);


}
