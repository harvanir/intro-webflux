package org.harvanir.demo.introwebflux.webmvc.service;

import org.harvanir.demo.introwebflux.webmvc.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.webmvc.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.webmvc.domain.entity.UpdateItemReqest;

/** @author Harvan Irsyadi */
public interface ItemService {

  ItemResponse create(CreateItemRequest request);

  ItemResponse update(UpdateItemReqest request);

  ItemResponse get(Integer id);

  ItemResponse getWithDelay(Integer id, Float delay);
}
