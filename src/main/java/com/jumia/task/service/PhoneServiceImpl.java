package com.jumia.task.service;

import com.jumia.task.api.model.Country;
import com.jumia.task.api.model.Phone;
import com.jumia.task.exceptions.JPayBusinessException;
import com.jumia.task.repo.CustomerRepository;
import com.jumia.task.utils.PhoneValidityChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService{

    CustomerRepository customerRepository;
    PhoneValidityChecker phoneValidityChecker;
    CountryRegistry countryRegistry;

    @Autowired
    PhoneServiceImpl(CustomerRepository customerRepository, PhoneValidityChecker phoneValidityChecker, CountryRegistry countryRegistry) {
        this.customerRepository = customerRepository;
        this.phoneValidityChecker = phoneValidityChecker;
        this.countryRegistry = countryRegistry;
    }


    /**
     * Filter by country and validity
     * @param validFilter
     * @param countryFilter
     * @return List of filtered phones.
     */
    @Override
    public List<Phone> getAllCustomerPhoneNumbersWithFilters(Optional<Boolean> validFilter, Optional<String> countryFilter)  {
        log.info("getCustomerNumbersByCountry: get customers numbers, countryName: {} ", countryFilter);
        List<String> phoneNumbers = this.customerRepository.getAllPhoneNumbers();
        List<Phone> response = getListOfPhonesBeforeFilters(phoneNumbers);

        log.info("getCustomerNumbersByCountry: get customers numbers, applying filters countryName: {} ", countryFilter);
        response = applyCountryFilter(countryFilter, response);
        log.info("getCustomerNumbersByCountry: get customers numbers, applying filters validFilter: {} ", validFilter);
        response = applyValidityFilter(validFilter, response);

        return response;
    }


    /**
     * fetch all Customer Numbers from database without filter
     * validate phone numbers
     * @param phoneNumbers
     * @return List of valid phones.
     */
    private List<Phone> getListOfPhonesBeforeFilters(List<String> phoneNumbers) {
        log.info("getCustomerNumbers get customers numbers ");
        List<Phone> customerNumbers = new ArrayList<>();
        for (String number : phoneNumbers) {
            Boolean isValid = this.phoneValidityChecker.ValidatePhoneNumber(number);
            String code = getCountryCode(number);
            String country = getCountryName(code);
            Phone phoneDto = populateDto(number, code, isValid, country);
            customerNumbers.add(phoneDto);
        }
        log.info("getCustomerNumbers get customers numbers :: {} ",customerNumbers);
        return customerNumbers;
    }

    /**
     * get countryCode for each number
     * @param number
     * @return country code.
     */
    private String getCountryCode(String number) {
        log.debug("getCountryCode get Country Code for number : {} ",number);
        String code = number.substring(1, number.indexOf(')'));
        return code.isEmpty() ? null : code;
    }


    /**
     * fetch country name by country code
     * @param code
     * @return country name.
     */
    private String getCountryName(String code) {
        log.debug("getCountryName :: get Country Name for code : {} ",code);
        Country country = countryRegistry.getCountry(code);
        String name = country.getName();
        if(name == null) throw new JPayBusinessException(code);
        return name;
    }

    /**
     * populate dto responses for each phone.
     * @param number
     * @param code
     * @param validity
     * @param country
     * @return Phone.
     */
    private Phone populateDto(String number, String code, Boolean validity, String country) {
        Phone phoneDto = new Phone();
        phoneDto.setValidityStatus(validity);
        phoneDto.setPhone(number.split(" ")[1]);
        phoneDto.setCountryCode(code);
        phoneDto.setCountry(country);
        return phoneDto;
    }

    /**
     * Filters customer phones list by validity
     * @param validFilter
     * @param unfilteredResponse
     * @return Filtered list by validity
     */
    private List<Phone> applyValidityFilter(Optional<Boolean> validFilter, List<Phone> unfilteredResponse) {
        log.info("apply validity filter for phone list....");
        if (validFilter.isEmpty())
            return unfilteredResponse;
        log.info("apply validity filter for phone list with filter :: {} ",validFilter);
        return validFilter.orElse(true) ? unfilteredResponse.stream()
                .filter(phoneDto -> phoneDto.getValidityStatus())
                .collect(Collectors.toList())
                : unfilteredResponse.stream()
                .filter(phoneDto -> !phoneDto.getValidityStatus())
                .collect(Collectors.toList());
    }


    /**
     * Filters customer phones list by country
     * @param countryFilter
     * @param unfilteredResponse
     * @return Filtered list by country name
     */
    private List<Phone> applyCountryFilter(Optional<String> countryFilter, List<Phone> unfilteredResponse) {
        log.info("apply country filter for phone list....");
        if (countryFilter.isEmpty() || countryFilter.get() =="")
            return unfilteredResponse;
        log.info("apply country filter for phone list with country :: {} ",countryFilter);
        String countryName = countryFilter.get();
        return unfilteredResponse.stream()
                .filter(phoneDto -> phoneDto.getCountry().equals(countryName))
                .collect(Collectors.toList());
    }

}
