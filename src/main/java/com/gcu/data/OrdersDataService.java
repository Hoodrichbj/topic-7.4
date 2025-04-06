package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderEntity> {

	@Autowired
	private OrdersRepository ordersRepository;
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * Non-Default Constructor for Constructor Injection
	 * @param ordersRepository 
	 */
	public OrdersDataService(OrdersRepository ordersRepository, DataSource dataSource) {
		this.ordersRepository = ordersRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * CRUD: finder to return all entities
	 */
	public List<OrderEntity> findAll() {
		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		try {
			// Get all the entity Orders
			Iterable<OrderEntity> ordersIterable = ordersRepository.findAll();
			orders = new ArrayList<OrderEntity>();
			ordersIterable.forEach(orders::add);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public OrderEntity findById(int id) {
		return null;
	}

	/**
	 * CRUD: create an entity
	 */
	public boolean create(OrderEntity order) {
		String sql = "INSERT INTO orders(order_number, product_name, price, quantity) VALUES(?, ?, ?, ?)";
		
		try {
			jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity());
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(OrderModel order) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean delete(OrderModel order) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean update(OrderEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(OrderEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
}
