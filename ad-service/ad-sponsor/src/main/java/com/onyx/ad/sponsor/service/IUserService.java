package com.onyx.ad.sponsor.service;

import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.vo.CreateUserRequest;
import com.onyx.ad.sponsor.vo.CreateUserResponse;

public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * */
    CreateUserResponse createUser(CreateUserRequest request)
            throws AdException;
}