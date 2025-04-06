package com.gcu.data.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.gcu.data.entity.OrderEntity;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {
	// Example of truly overriding a method from the CrudRepository and using
	@Override
	@Query(value = "SELECT * FROM orders")
	public List<OrderEntity> findAll();
}
