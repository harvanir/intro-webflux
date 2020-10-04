package org.harvanir.demo.introwebflux.webmvc.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/** @author Harvan Irsyadi */
@Data
public class CreateItemRequest {

  private String name;

  private Integer quantity;

  private BigDecimal price;
}
