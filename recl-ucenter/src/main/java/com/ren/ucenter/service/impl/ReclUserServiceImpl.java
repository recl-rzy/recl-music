package com.ren.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.ucenter.entity.ReclUser;
import com.ren.ucenter.mapper.ReclUserMapper;
import com.ren.ucenter.service.ReclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ren.ucenter.utils.DateUtil;
import com.ren.ucenter.utils.MD5;
import com.ren.utils.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RZY
 * @since 2022-05-13
 */
@Service
public class ReclUserServiceImpl extends ServiceImpl<ReclUserMapper, ReclUser> implements ReclUserService {

    @Override
    public Result login(HttpServletRequest req) {
        //获取请求参数
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        ReclUser user = this.baseMapper.selectOne(new QueryWrapper<ReclUser>()
                .eq("username", username));
        if(user == null) return Result.error()
                .message("用户不存在");
        if(MD5.encrypt(password).equals(user.getPassword())) return Result.ok()
                .message("欢迎登录")
                .data("user", user);
        return Result.error()
                .message("密码错误，请输入正确密码!");
    }

    @Override
    public Result register(HttpServletRequest req) {
        String username = req.getParameter("username").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        //判断用户名、手机号、邮箱是否已存在
        if(existUserName(username)) return Result.error()
                .message("用户名已存在");
        if(existPhoneNum(phone_num)) return Result.error()
                .message("该电话已注册");
        if(existEmail(email)) return Result.error()
                .message("邮箱已注册");

        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        String avatar = "/img/avatarImages/user.jpg";
        //注册信息封装
        ReclUser user = new ReclUser();
        user.setUsername(username)
                .setPassword(MD5.encrypt(password))
                .setPhoneNum(phone_num)
                .setEmail(email)
                .setSex(Integer.valueOf(sex))
                .setBirth(DateUtil.stringToDate(birth))
                .setAvatar(avatar)
                .setLocation(location)
                .setIntroduction(introduction);
        int res = this.baseMapper.insert(user);
        if(res == 0) return Result.error()
                .message("注册失败，请稍后再试!");
        return Result.ok()
                .message("注册成功!");
    }

    @Override
    public boolean existUserName(String username) {
        Integer count = this.baseMapper.selectCount(new QueryWrapper<ReclUser>()
                .eq("username", username));
        return count != 0;
    }

    @Override
    public boolean existPhoneNum(String phone_num) {
        Integer count = this.baseMapper.selectCount(new QueryWrapper<ReclUser>()
                .eq("phone_num", phone_num));
        return count != 0;
    }

    @Override
    public boolean existEmail(String email) {
        Integer count = this.baseMapper.selectCount(new QueryWrapper<ReclUser>()
                .eq("email", email));
        return count != 0;
    }

    @Override
    public Result update(HttpServletRequest req) {
        //获取请求参数
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        //用户信息封装
        ReclUser user = new ReclUser();
        user.setUsername(username)
                .setId(id)
                .setPhoneNum(phone_num)
                .setEmail(email)
                .setSex(Integer.valueOf(sex))
                .setBirth(DateUtil.stringToDate(birth))
                .setLocation(location)
                .setIntroduction(introduction);
        int res = this.baseMapper.updateById(user);
        if(res == 0) return Result.error()
                .message("修改失败，请稍后再试!");
        return Result.ok()
                .message("修改成功!");
    }


}
