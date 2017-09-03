package com.gm.dao;

import com.gm.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 9/3/17.
 */
public interface PhoneRepository extends JpaRepository<Phone, String> {
}
