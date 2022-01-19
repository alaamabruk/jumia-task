package com.jumia.task.service;


import com.jumia.task.api.model.Country;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRegistry {
  private final Map<String, Country> countryCache = new HashMap<>();

  public CountryRegistry() {
    initializeCache();
  }

  private void initializeCache() {

    countryCache.put("237", new Country("Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
    countryCache.put("251", new Country("Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$"));
    countryCache.put("212", new Country("Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$"));
    countryCache.put("258", new Country("Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$"));
    countryCache.put("256", new Country("Uganda", "\\(256\\)\\ ?\\d{9}$"));
  }

  public Country getCountry(String countryCode) {
    return countryCache.get(countryCode);
  }

}
