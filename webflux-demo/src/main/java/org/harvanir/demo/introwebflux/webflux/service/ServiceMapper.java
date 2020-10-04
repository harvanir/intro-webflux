package org.harvanir.demo.introwebflux.webflux.service;

import org.harvanir.demo.introwebflux.webflux.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.webflux.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.webflux.domain.entity.UpdateItemReqest;
import org.harvanir.demo.introwebflux.webflux.repository.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public interface ServiceMapper {

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Item mapToJpaEntity(CreateItemRequest request);

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  void setPropertyForUpdate(UpdateItemReqest source, @MappingTarget Item target);

  ItemResponse mapToDomainEntity(Item item);
}
