package com.jumia.task.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {
  private String name;
  private String validityRule;
}
