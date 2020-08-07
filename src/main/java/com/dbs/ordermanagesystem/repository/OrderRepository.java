package com.dbs.ordermanagesystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.ordermanagesystem.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{


}
