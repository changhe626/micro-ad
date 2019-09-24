package com.onyx.ad.sponsor.service.impl;

import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.constant.Constants;
import com.onyx.ad.sponsor.dao.AdUserRepository;
import com.onyx.ad.sponsor.entity.AdUser;
import com.onyx.ad.sponsor.service.IUserService;
import com.onyx.ad.sponsor.util.CommonUtils;
import com.onyx.ad.sponsor.vo.CreateUserRequest;
import com.onyx.ad.sponsor.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 15:34
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = adUserRepository.
                findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser = adUserRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));
        //可以考虑使用BeanUtil 工具类
        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }

}
