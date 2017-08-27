package com.gm.dao;

import com.gm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 8/27/17.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
