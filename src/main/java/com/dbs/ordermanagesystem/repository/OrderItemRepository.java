package com.dbs.ordermanagesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dbs.ordermanagesystem.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

	@Query(value ="select * from OrderItem as oi where oi.CUSTOMER_ID = :custId", nativeQuery=true)
	public List<OrderItem> findByCustId(String custId);
}
