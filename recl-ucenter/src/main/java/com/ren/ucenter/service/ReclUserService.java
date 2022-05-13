package com.ren.ucenter.service;

import com.ren.ucenter.entity.ReclUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ren.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RZY
 * @since 2022-05-13
 */
public interface ReclUserService extends IService<ReclUser> {
    Result login(HttpServletRequest req);

    Result register(HttpServletRequest req);

    boolean existUserName(String username);

    boolean existPhoneNum(String phone_num);

    boolean existEmail(String email);

    Result update(HttpServletRequest req);
}
