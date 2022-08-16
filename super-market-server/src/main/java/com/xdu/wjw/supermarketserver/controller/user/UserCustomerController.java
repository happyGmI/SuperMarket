package com.xdu.wjw.supermarketserver.controller.user;

import com.xdu.wjw.supermarketserver.api.ApiPageResponse;
import com.xdu.wjw.supermarketserver.api.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ApiResponse register(
            @RequestParam Map<String, String> map
    ) {
        return ApiResponse.buildSuccessEmptyResponse();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login() {
        return ApiResponse.buildSuccessPageResponse(
                ApiPageResponse.buildApiPageResponse(new ArrayList<>())
        );
    }
}
