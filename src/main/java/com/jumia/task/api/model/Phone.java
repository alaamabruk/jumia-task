package com.jumia.task.api.model;

import lombok.Data;

@Data
public class Phone {
    private String phone;
    private String country;
    private String countryCode;
    private Boolean validityStatus;

}
