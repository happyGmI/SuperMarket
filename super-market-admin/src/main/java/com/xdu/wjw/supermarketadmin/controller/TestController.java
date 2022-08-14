package com.xdu.wjw.supermarketadmin.controller;

import com.xdu.wjw.supermarketserver.test.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /**
     * @Class: TestController
     * @Author: Wei Junwei
     * @Time: 2022/8/13 22:42
     * @Description:
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        Test test = new Test();
        return "hello";
    }

}
