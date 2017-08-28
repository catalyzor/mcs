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

    @GetMapping(value = "/call/{code}")
    public String showCall(@PathVariable("code") String code, Map<String, Object> map){
        Code cd = codeRepository.findByCode(code);
        String page = null;
        if(cd == null){
            map.put("code", code);
            page = "showReg";
        }else{
            map.put("code", cd);
            map.put("phone", "000");
            page = "showCall";
        }
        return page;
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String inputPhone, @RequestParam String inputCarNumber,
                          @RequestParam String inputCode, Map<String, Object> map){
        User user = new User();
        user.setPhone(inputPhone);
        user.setId(UUID.randomUUID().toString());
        user.setStatus(0);
        Code cd = new Code();
        cd.setCode(inputCode);
        cd.setCarNumber(inputCarNumber);
        cd.setStatus(0);
        cd.setId(UUID.randomUUID().toString());
        codeRepository.save(cd);
        userRepository.save(user);
        map.put("code", cd);
        map.put("phone", "000");
        return "showCall";
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
