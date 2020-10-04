package org.harvanir.demo.introwebflux.webflux.repository;

import org.harvanir.demo.introwebflux.webflux.repository.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/** @author Harvan Irsyadi */
public interface ItemRepository extends ReactiveCrudRepository<Item, Integer> {}
