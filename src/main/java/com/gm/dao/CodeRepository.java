package com.gm.dao;

import com.gm.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 8/27/17.
 */
public interface CodeRepository extends JpaRepository<Code, String> {

    public Code findById(String id);
    public Code findFirstByCode(String code);
}
