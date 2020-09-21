package com.rest.assignment.daofab.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PageableHelper {
  private PageableHelper() {
  }

  public static Pageable setPageable(Integer page, Integer size, Direction direction,
      String sort) {
    Direction setDirection = setDirection(direction);

    String setSort = setSortParam(sort);

    Integer setSize = maxSizePaginated(size);

    return PageRequest.of(page, setSize, Sort.by(new Order(direction, setSort)));
  }

  public static Integer maxSizePaginated(Integer size) {
    Integer newSize = size;
    if (size > 1000) {
      newSize = 1000;
    }
    return newSize;
  }

  public static Direction setDirection(Direction sort) {
    if (sort == null ) {
      return Direction.ASC;
    }

    return sort;
  }

  public static String setSortParam(String sortParam) {
    if (sortParam == null) {
      return "id";
    }

    return sortParam;
  }
}
