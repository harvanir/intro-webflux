package org.harvanir.demo.introwebflux.webmvc.repository;

import org.harvanir.demo.introwebflux.webmvc.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/** @author Harvan Irsyadi */
public interface ItemRepository extends JpaRepository<Item, Integer> {}
