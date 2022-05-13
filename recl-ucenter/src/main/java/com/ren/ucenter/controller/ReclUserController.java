package com.ren.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.ucenter.entity.ReclUser;
import com.ren.ucenter.service.ReclUserService;
import com.ren.ucenter.utils.MD5;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-13
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/ucenter")
@CrossOrigin
public class ReclUserController {

    @Autowired
    ReclUserService reclUserService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(HttpServletRequest req) {
        return reclUserService.login(req);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(HttpServletRequest req) {
        return reclUserService.register(req);
    }

    @ApiOperation(value = "返回所有用户信息")
    @GetMapping("/all")
    public Result getAllUsers() {
        List<ReclUser> users = reclUserService.list(null);
        return Result.ok()
                .data("users", users);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Result delUser(@PathVariable String id) {
        boolean res = reclUserService.removeById(id);
        if(res) return Result.ok()
                .message("用户删除成功");
        else return Result.error()
                .message("用户删除失败,请稍后再试");
    }

    @ApiOperation(value = "返回指定id用户")
    @GetMapping("/{id}")
    public Result getUser(@PathVariable String id) {
        ReclUser user = reclUserService.getById(id);
        return Result.ok()
                .data("user", user);
    }

    @ApiOperation(value = "用户信息修改")
    @PutMapping("")
    public Result updateUser(HttpServletRequest req) {
        return reclUserService.update(req);
    }

    @ApiOperation(value = "密码修改")
    @PutMapping("/password")
    public Result updatePassword(HttpServletRequest req) {
        //获取请求参数
        String id = req.getParameter("id").trim();
        String old_password = req.getParameter("old_password").trim();
        String password = req.getParameter("password").trim();
        ReclUser user = reclUserService.getOne(new QueryWrapper<ReclUser>()
                .eq("id", id)
                .select("password"));
        //密码判断
        if(MD5.encrypt(old_password).equals(user.getPassword())) {
            //MD5加密并保存新密码
            boolean res = reclUserService.updateById(user.setId(id)
                    .setPassword(MD5.encrypt(password)));
            if(res) return Result.ok()
                    .message("密码修改成功");
            else return Result.error()
                    .message("密码修改失败");
        }
        return Result.error()
                .message("原始密码有误，请输入重试!");
    }

}

