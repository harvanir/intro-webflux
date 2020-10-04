package org.harvanir.demo.introwebflux.webflux.entrypoint.controller;

import org.harvanir.demo.introwebflux.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.domain.entity.UpdateItemReqest;
import org.harvanir.demo.introwebflux.webflux.service.ReactiveItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
@RequestMapping("/items")
@RestController
public class ItemController {

  private final ReactiveItemService reactiveItemService;

  public ItemController(ReactiveItemService reactiveItemService) {
    this.reactiveItemService = reactiveItemService;
  }

  @PostMapping
  public Mono<ItemResponse> create(@RequestBody CreateItemRequest request) {
    return reactiveItemService.create(request);
  }

  @PutMapping
  public Mono<ItemResponse> update(@RequestBody UpdateItemReqest request) {
    return reactiveItemService.update(request);
  }

  @GetMapping("/{id}")
  public Mono<ItemResponse> get(@PathVariable Integer id) {
    return reactiveItemService.get(id);
  }

  @GetMapping("/{id}/{delaySeconds}")
  public Mono<ItemResponse> getWithDelay(
      @PathVariable Integer id, @PathVariable Float delaySeconds) {
    return reactiveItemService.getWithDelay(id, delaySeconds);
  }
}
