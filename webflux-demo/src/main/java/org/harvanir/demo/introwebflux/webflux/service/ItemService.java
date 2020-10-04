package org.harvanir.demo.introwebflux.webflux.service;

import org.harvanir.demo.introwebflux.webflux.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.webflux.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.webflux.domain.entity.UpdateItemReqest;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemService {

  Mono<ItemResponse> create(CreateItemRequest request);

  Mono<ItemResponse> update(UpdateItemReqest request);

  Mono<ItemResponse> get(Integer id);

  Mono<ItemResponse> getWithDelay(Integer id, Float delay);
}
