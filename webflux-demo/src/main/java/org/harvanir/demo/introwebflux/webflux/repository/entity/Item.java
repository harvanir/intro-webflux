package org.harvanir.demo.introwebflux.webflux.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** @author Harvan Irsyadi */
@Data
@Table("items")
public class Item {

  @Id private Integer id;

  private String name;

  private Integer quantity;

  private BigDecimal price;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Version private long version;
}
