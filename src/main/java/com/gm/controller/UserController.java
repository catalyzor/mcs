package com.gm.controller;

import com.gm.dao.CodeRepository;
import com.gm.dao.OrderRepository;
import com.gm.dao.PhoneRepository;
import com.gm.dao.UserRepository;
import com.gm.model.*;
import com.gm.util.SafePhoneNumberManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by michael on 8/27/17.
 */
@Controller
public class UserController {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private SafePhoneNumberManager safePhoneNumberManager;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    @GetMapping(value = "/call/{code}")
    public String showCall(@PathVariable("code") String code, Map<String, Object> map){
        Code cd = codeRepository.findFirstByCode(code);
        String page = null;
        if(cd == null){
            map.put("code", code);
            page = "login";
        }else{
            map.put("code", cd);
            map.put("phone", "******");
            page = "contactus";
        }
//        SafePhoneNumber safePhoneNumber = safePhoneNumberManager.getUseableSafePhoneNumber("18210882865");
//        safePhoneNumberManager.bindNumber(safePhoneNumber.getBindPhone(), safePhoneNumber.getNumber());
        return page;
    }

    @PostMapping(value = "/show/vphone/{code}")
    @ResponseBody
    public String showVphone(@PathVariable String code){
        Code cd = codeRepository.findFirstByCode(code);
        if(cd != null){
            SafePhoneNumber safePhoneNumber = safePhoneNumberManager.getUseableSafePhoneNumber(cd.getUser().getPhone().getPhone());
            boolean status = safePhoneNumberManager.bindNumber(safePhoneNumber.getBindPhone(), safePhoneNumber.getNumber());
            if(!status){
                if(safePhoneNumberManager.unBindNumber(safePhoneNumber.getNumber())){
                    status = safePhoneNumberManager.bindNumber(safePhoneNumber.getBindPhone(), safePhoneNumber.getNumber());
                }
            }
            if(status) return safePhoneNumber.getNumber();
        }
        return "none";
    }

    @PostMapping("/users")
    @Transactional
    public String addUser(@RequestParam String inputPhone, @RequestParam String inputCarNumber,
                          @RequestParam String inputCode, Map<String, Object> map, HttpSession session){
        Code code = codeRepository.findFirstByCode(inputCode);
        if(code != null && code.getCarNumber() != null){
            map.put("inputPhone", inputPhone);
            map.put("inputCarNumber", inputCarNumber);
            map.put("code", inputCode);
            map.put("error", "此号码已被注册");
            return "login";
        }
        User user = new User();
        Phone phone = new Phone();
        phone.setId(UUID.randomUUID().toString());
        phone.setPhone(inputPhone);
        user.setPhone(phone);
        user.setId(UUID.randomUUID().toString());
        user.setStatus(0);
        Code cd = new Code();
        cd.setCode(inputCode);
        cd.setCarNumber(inputCarNumber);
        cd.setStatus(0);
        cd.setId(UUID.randomUUID().toString());
        cd.setUser(user);
        user.setCodes(Arrays.asList(cd));
//        phoneRepository.save(phone);
//        codeRepository.save(cd);
        userRepository.save(user);
        map.put("code", cd);
        map.put("phone", "000");
        session.setAttribute("code", cd);
        return "fees";
    }

    @PostMapping("/orders")
    public String createOrder(String productNumber, Map<String, Object> map, HttpSession session){
        Code cd = (Code) session.getAttribute("code");
        if(cd != null && StringUtils.isNotEmpty(productNumber)){
            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setOrderNum("CODE" + cd.getCode() + simpleDateFormat.format(Calendar.getInstance().getTime()));
            order.setCode(cd.getCode());
            order.setCarNumber(cd.getCarNumber());
            order.setStatus("0");
            order.setPayStatus("0");
            order.setProductNumber(productNumber);
            order.setTime(Calendar.getInstance().getTime());
            if("1".equals(productNumber)){
                order.setProductNumber("一年");
                order.setPrice(398L);
            }else if("2".equals(productNumber)){
                order.setProductName("永久");
                order.setPrice(998L);
            }
            order = orderRepository.save(order);
            session.setAttribute("order", order);
            return "payment";
        }else{
            map.put("error","绑定失败");
            return "fees";
        }
    }

    @PostMapping("/pay")
    @ResponseBody
    public String doPay(@RequestParam String type, Map<String, Object> map, HttpSession session){

        Order order = (Order) session.getAttribute("order");
        if(order != null && StringUtils.isNotEmpty(type)){
            order.setType(type);
            order = orderRepository.save(order);

        }

        return "test";
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
