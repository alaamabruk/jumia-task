package com.jumia.task.service;


import com.jumia.task.api.model.Phone;
import com.jumia.task.repo.CustomerRepository;
import com.jumia.task.utils.DummyCustomerRepo;
import com.jumia.task.utils.PhoneValidityChecker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
class PhoneServiceImplTest {

    CustomerRepository repository = new DummyCustomerRepo();
    CountryRegistry countryRegistry = new CountryRegistry();
    PhoneValidityChecker phoneValidityChecker = new PhoneValidityChecker(countryRegistry);
    PhoneService phoneService = new PhoneServiceImpl(repository, phoneValidityChecker, countryRegistry);

    @Test
    void getAllCustomerPhoneNumbersWithCountryTest() {

        //Given
        //When
        List<Phone> response = this.phoneService.getAllCustomerPhoneNumbersWithFilters(Optional.empty(), Optional.empty());

        // Then
        Assert.assertEquals(12, response.size());

        //Assert for 1st returned phone info
        Phone phone1 = response.get(0);
        Assert.assertEquals("6007989253", phone1.getPhone());
        Assert.assertEquals("Morocco", phone1.getCountry());
        Assert.assertEquals("212", phone1.getCountryCode());
        Assert.assertFalse(phone1.getValidityStatus());

        //Assert for 2st returned phone info
        Phone phone2 = response.get(1);
        Assert.assertEquals("698054317", phone2.getPhone());
        Assert.assertEquals("Morocco", phone2.getCountry());
        Assert.assertEquals("212", phone2.getCountryCode());
        Assert.assertTrue(phone2.getValidityStatus());

        //Assert for 3st returned phone info
        Phone phone3 = response.get(2);
        Assert.assertEquals("6546545369", phone3.getPhone());
        Assert.assertEquals("Morocco", phone3.getCountry());
        Assert.assertEquals("212", phone3.getCountryCode());
        Assert.assertFalse(phone3.getValidityStatus());

    }

    @Test
    void getAllCustomerPhoneNumbersWithCountryWithValidFilterTest() {
        //Given valid = true
        //When
        List<Phone> response = this.phoneService.getAllCustomerPhoneNumbersWithFilters(Optional.of(true), Optional.empty());

        //Then
        Assert.assertEquals(6, response.size());

        //Assert for 1st returned phone info
        Phone phone1 = response.get(0);
        Assert.assertEquals("698054317", phone1.getPhone());
        Assert.assertEquals("Morocco", phone1.getCountry());
        Assert.assertEquals("212", phone1.getCountryCode());
        Assert.assertTrue(phone1.getValidityStatus());

        //Assert for 2st returned phone info
        Phone phone2 = response.get(1);
        Assert.assertEquals("849181828", phone2.getPhone());
        Assert.assertEquals("Mozambique", phone2.getCountry());
        Assert.assertEquals("258", phone2.getCountryCode());
        Assert.assertTrue(phone2.getValidityStatus());
    }

    @Test
    void getAllCustomerPhoneNumbersWithCountryWithCountryFilterTest() {
        //Given country = Morocco
        //When
        List<Phone> response = this.phoneService.getAllCustomerPhoneNumbersWithFilters(Optional.empty(), Optional.of("Morocco"));

        //Then
        Assert.assertEquals(4, response.size());

        //Assert for 1st returned phone info
        Phone phone1 = response.get(0);
        Assert.assertEquals("6007989253", phone1.getPhone());
        Assert.assertEquals("Morocco", phone1.getCountry());
        Assert.assertEquals("212", phone1.getCountryCode());
        Assert.assertFalse(phone1.getValidityStatus());

        //Assert for 2st returned phone info
        Phone phone2 = response.get(1);
        Assert.assertEquals("698054317", phone2.getPhone());
        Assert.assertEquals("Morocco", phone2.getCountry());
        Assert.assertEquals("212", phone2.getCountryCode());
        Assert.assertTrue(phone2.getValidityStatus());

    }
    @Test
    void getAllCustomerPhoneNumbersWithCountryWithCountryAndValidFiltersTest() {

        //Given valid = true and country = Mozambique
        //When
        List<Phone> response = this.phoneService.getAllCustomerPhoneNumbersWithFilters(Optional.of(true), Optional.of("Mozambique"));

        //Then
        Assert.assertEquals(2, response.size());

        //Assert for 1st returned phone info
        Phone phone1 = response.get(0);
        Assert.assertEquals("849181828", phone1.getPhone());
        Assert.assertEquals("Mozambique", phone1.getCountry());
        Assert.assertEquals("258", phone1.getCountryCode());
        Assert.assertTrue(phone1.getValidityStatus());

        //Assert for 2st returned phone info
        Phone phone2 = response.get(1);
        Assert.assertEquals("849181927", phone2.getPhone());
        Assert.assertEquals("Mozambique", phone2.getCountry());
        Assert.assertEquals("258", phone2.getCountryCode());
        Assert.assertTrue(phone2.getValidityStatus());
    }
}