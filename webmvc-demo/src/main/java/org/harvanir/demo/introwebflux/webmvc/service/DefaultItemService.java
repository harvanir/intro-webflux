package org.harvanir.demo.introwebflux.webmvc.service;

import org.harvanir.demo.introwebflux.webmvc.domain.entity.CreateItemRequest;
import org.harvanir.demo.introwebflux.webmvc.domain.entity.ItemResponse;
import org.harvanir.demo.introwebflux.webmvc.domain.entity.UpdateItemReqest;
import org.harvanir.demo.introwebflux.webmvc.repository.ItemRepository;
import org.harvanir.demo.introwebflux.webmvc.repository.entity.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

/** @author Harvan Irsyadi */
@Service
public class DefaultItemService implements ItemService {

  private static final String FIND_WITH_DELAY =
      "select i.id, i.name, i.price, i.quantity %s from items i where id = ?";

  private static final ResultSetExtractor<ItemResponse> RESULT_SET_EXTRACTOR =
      rs -> {
        if (rs.next()) {
          ItemResponse response = new ItemResponse();
          response.setId(rs.getInt(1));
          response.setName(rs.getString(2));
          response.setPrice(rs.getBigDecimal(3));
          response.setQuantity(rs.getInt(4));

          return response;
        }

        return null;
      };

  private final ItemRepository itemRepository;

  private final ServiceMapper serviceMapper;

  private final JdbcTemplate jdbcTemplate;

  public DefaultItemService(
      ItemRepository itemRepository, ServiceMapper serviceMapper, JdbcTemplate jdbcTemplate) {
    this.itemRepository = itemRepository;
    this.serviceMapper = serviceMapper;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public ItemResponse create(CreateItemRequest request) {
    Item item = serviceMapper.mapToJpaEntity(request);
    return serviceMapper.mapToDomainEntity(itemRepository.save(item));
  }

  private Supplier<DataNotFoundException> getNotFoundSupplier() {
    return () -> new DataNotFoundException("Item not found");
  }

  @Transactional
  @Override
  public ItemResponse update(UpdateItemReqest request) {
    return itemRepository
        .findById(request.getId())
        .map(
            item -> {
              serviceMapper.setPropertyForUpdate(request, item);

              return serviceMapper.mapToDomainEntity(itemRepository.save(item));
            })
        .orElseThrow(getNotFoundSupplier());
  }

  @Override
  public ItemResponse get(Integer id) {
    return itemRepository
        .findById(id)
        .map(serviceMapper::mapToDomainEntity)
        .orElseThrow(getNotFoundSupplier());
  }

  private String getSql(Float delaySeconds) {
    return String.format(FIND_WITH_DELAY, String.format(", pg_sleep(%s)", delaySeconds));
  }

  @Override
  public ItemResponse getWithDelay(Integer id, Float delay) {
    String sql = getSql(delay);
    PreparedStatementSetter preparedStatementSetter = ps -> ps.setLong(1, id);

    return jdbcTemplate.query(sql, preparedStatementSetter, RESULT_SET_EXTRACTOR);
  }
}
