package com.jumia.task.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneControllerV1Tests {

    public static final String ENDPOINT = "/internal/v1/phones";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetListOfCustomerPhonesThenReturnCustomersNumbers() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..phone").exists());
    }

    @Test
    public void testGetListOfCustomerPhonesWithCountryName() throws Exception {
        mockMvc.perform(get(ENDPOINT + "?country=Morocco" )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..country").exists());

    }

    @Test
    public void testGetListOfCustomerPhonesWithCountryNameAndValidStatus() throws Exception {
        mockMvc.perform(get(ENDPOINT + "?country=Morocco&valid=false" )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..phone").exists());
    }

    @Test
    public void testGetListOfCustomerPhonesWithCountryNotExists() throws Exception {
        mockMvc.perform(get(ENDPOINT + "?country=Egypt" )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..phone").isEmpty());
    }
}