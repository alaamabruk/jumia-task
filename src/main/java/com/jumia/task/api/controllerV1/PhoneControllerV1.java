package com.jumia.task.api.controllerV1;


import com.jumia.task.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/internal/v1")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = "Phone")
public class PhoneControllerV1 {

    private PhoneService phoneService;

    PhoneControllerV1(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @ApiOperation("Get Customers Phones per country")
    @GetMapping(value = "/phones", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getListOfCustomerPhones(@RequestParam(name = "valid", required = false) Optional<Boolean> validFilter,
                                                     @RequestParam(name = "country", required = false) Optional<String> countryFilter) {
        log.info("getCustomers Phone: get customers Phone request for customer");
        return new ResponseEntity(phoneService.getAllCustomerPhoneNumbersWithFilters(validFilter, countryFilter), HttpStatus.OK);
    }
}
