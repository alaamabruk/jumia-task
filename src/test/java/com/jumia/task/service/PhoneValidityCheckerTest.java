package com.jumia.task.service;

import com.jumia.task.utils.PhoneValidityChecker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PhoneValidityCheckerTest {

    CountryRegistry countryRegistry = new CountryRegistry();
    PhoneValidityChecker phoneValidityChecker = new PhoneValidityChecker(countryRegistry);

    @Test
    void TestValidNumber(){
        //Given
        String validNumber = "(212) 698054317";
        //When
        Boolean isValid = this.phoneValidityChecker.ValidatePhoneNumber(validNumber);
        //Then
        Assert.assertTrue(isValid);
    }

    @Test
    void TestInvalidNumber(){
        //Given
        String invalidNumber = "(212) 6546545369";
        //When
        Boolean isValid = this.phoneValidityChecker.ValidatePhoneNumber(invalidNumber);
        //Then
        Assert.assertFalse(isValid);
    }

}