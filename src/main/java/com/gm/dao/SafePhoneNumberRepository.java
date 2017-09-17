package com.gm.dao;

import com.gm.model.SafePhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 9/3/17.
 */
public interface SafePhoneNumberRepository extends JpaRepository<SafePhoneNumber, String> {
    public SafePhoneNumber findFirstByOrderByBindTimeAsc();
    public SafePhoneNumber findFirstByBindPhone(String phone);
}
