package com.ren.ucenter.service.dubbo;

import com.ren.dubboservice.UserService;
import com.ren.ucenter.entity.ReclUser;
import com.ren.ucenter.mapper.ReclUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/13 16:33
 * @Version: v1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ReclUserMapper reclUserMapper;

    @Override
    public boolean updateUserAvatar(String id, String url) {
        ReclUser user = new ReclUser();
        user.setId(id).setAvatar(url);
        int insert = reclUserMapper.updateById(user);
        return insert != 0;
    }
}
