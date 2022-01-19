package com.jumia.task.utils;


import com.jumia.task.api.model.Country;
import com.jumia.task.exceptions.JPayBusinessException;
import com.jumia.task.service.CountryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PhoneValidityChecker {

    CountryRegistry countryRegistry;

    @Autowired
    public PhoneValidityChecker(CountryRegistry countryRegistry) {

        this.countryRegistry = countryRegistry;
    }


    /**
    * validate phone numbers with regex
     * @param phoneNumber
     * @return true if it's valid phone number else false.
     */
    public Boolean ValidatePhoneNumber(String phoneNumber) {
        log.debug("validate phone number... {}", phoneNumber);
        if (phoneNumber == null || phoneNumber.isEmpty())
            return false;

        String countryCode = phoneNumber.substring(1, phoneNumber.indexOf(')'));
        if (countryCode.isEmpty() || countryCode == null)
            return false;

        log.debug("validate phone number with county code :: {}", countryCode);
        Country country = countryRegistry.getCountry(countryCode);
        String correspondingRegex = country.getValidityRule();
        if (correspondingRegex == null)throw new JPayBusinessException("invalid");

        log.debug("validate phone number for country {} with correspondingRegex :: {}", country ,correspondingRegex);
        Pattern pattern = Pattern.compile(correspondingRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        while (matcher.find()) {
            return true;
        }
        return false;
    }
}
