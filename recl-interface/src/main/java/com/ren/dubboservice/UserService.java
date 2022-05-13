package com.ren.dubboservice;

/**
 * @InterfaceName: UserService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/13 16:31
 * @Version: v1.0
 */

public interface UserService {
    boolean updateUserAvatar(String id, String url);
}
