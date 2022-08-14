package com.xdu.wjw.supermarketserver.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Class: UserCustomerController
 * @Author: Wei Junwei
 * @Time: 2022/8/14 2:41
 * @Description:
 */
@RestController
@RequestMapping("/customer")
public class UserCustomerController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam Map<String, String> map
    ) {

        return "hello";
    }
}
