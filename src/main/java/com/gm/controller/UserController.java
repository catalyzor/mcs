package com.gm.controller;

import com.gm.dao.CodeRepository;
import com.gm.dao.UserRepository;
import com.gm.model.Code;
import com.gm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by michael on 8/27/17.
 */
@Controller
public class UserController {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "call/{code}")
    public String showCall(@PathVariable("code") String code, Map<String, Object> map){
        Code cd = codeRepository.findByCode(code);
        if(cd == null){
            map.put("code", code);
        }else{

        }
    }

    @PostMapping("users")
    public User addUser(@RequestBody User user){
        user.setId(UUID.randomUUID().toString());
        user.setStatus(0);
        return userRepository.save(user);
    }

    @GetMapping("users")
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
