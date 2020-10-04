package org.harvanir.demo.introwebflux.webflux.service;

import org.harvanir.demo.introwebflux.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.domain.entity.UpdateItemReqest;
import org.harvanir.demo.introwebflux.webflux.repository.ReactiveItemRepository;
import org.harvanir.demo.introwebflux.webflux.repository.entity.Item;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/** @author Harvan Irsyadi */
@Service
public class DefaultReactiveItemService implements ReactiveItemService {

  private static final String FIND_WITH_DELAY =
      "select i.id, i.name, i.price, i.quantity %s from items i where id = $1";

  private final ReactiveItemRepository reactiveItemRepository;

  private final ServiceMapper serviceMapper;

  private final DatabaseClient databaseClient;

  public DefaultReactiveItemService(
      ReactiveItemRepository reactiveItemRepository,
      ServiceMapper serviceMapper,
      DatabaseClient databaseClient) {
    this.reactiveItemRepository = reactiveItemRepository;
    this.serviceMapper = serviceMapper;
    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<ItemResponse> create(CreateItemRequest request) {
    Item item = serviceMapper.mapToJpaEntity(request);
    LocalDateTime now = LocalDateTime.now();
    item.setCreatedAt(now);
    item.setUpdatedAt(now);

    return reactiveItemRepository.save(item).map(serviceMapper::mapToDomainEntity);
  }

  private Supplier<DataNotFoundException> getNotFoundSupplier() {
    return () -> new DataNotFoundException("Item not found");
  }

  @Transactional
  @Override
  public Mono<ItemResponse> update(UpdateItemReqest request) {
    return reactiveItemRepository
        .findById(request.getId())
        .switchIfEmpty(Mono.error(getNotFoundSupplier()))
        .flatMap(
            item -> {
              serviceMapper.setPropertyForUpdate(request, item);
              item.setUpdatedAt(LocalDateTime.now());

              return reactiveItemRepository.save(item).map(serviceMapper::mapToDomainEntity);
            });
  }

  @Override
  public Mono<ItemResponse> get(Integer id) {
    return reactiveItemRepository
        .findById(id)
        .switchIfEmpty(Mono.error(getNotFoundSupplier()))
        .map(serviceMapper::mapToDomainEntity);
  }

  private String getSql(Float delaySeconds) {
    return String.format(FIND_WITH_DELAY, String.format(", pg_sleep(%s)", delaySeconds));
  }

  @Override
  public Mono<ItemResponse> getWithDelay(Integer id, Float delay) {
    String sql = getSql(delay);

    return databaseClient
        .execute(sql)
        .bind(0, id)
        .as(Item.class)
        .fetch()
        .one()
        .map(serviceMapper::mapToDomainEntity);
  }
}
