package com.onyx.ad.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.service.IUserService;
import com.onyx.ad.sponsor.vo.CreateUserRequest;
import com.onyx.ad.sponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserOPController {

    private final IUserService userService;

    @Autowired
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }


    /**
     * 日志的记录!
     * @param request
     * @return
     * @throws AdException
     */
    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}",JSON.toJSONString(request));
        return userService.createUser(request);
    }

}
