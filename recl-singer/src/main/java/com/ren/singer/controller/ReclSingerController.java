package com.ren.singer.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.singer.entity.ReclSinger;
import com.ren.singer.service.ReclSingerService;
import com.ren.singer.utils.DateUtil;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-12
 */

@Api(tags = "歌手接口")
@RestController
@RequestMapping("/singer")
@CrossOrigin
public class ReclSingerController {

    @Autowired
    ReclSingerService reclSingerService;

    @ApiOperation("添加歌手")
    @PostMapping("")
    public Result addSinger(HttpServletRequest req) {
        //获取请求参数
        String name = req.getParameter("name").trim();
        Integer sex = Integer.valueOf(req.getParameter("sex").trim());
        Date birth = DateUtil.stringToDate(req.getParameter("birth").trim());
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/avatorImages/user.jpg";
        //封装参数信息
        ReclSinger singer = new ReclSinger();
        singer.setName(name)
                .setSex(sex)
                .setBirth(birth)
                .setLocation(location)
                .setIntroduction(introduction)
                .setPic(pic);
        boolean res = reclSingerService.save(singer);
        if(res) return Result.ok()
                .message("歌手添加成功");
        else return Result.error()
                .message("歌手添加失败，请稍后再试");
    }

    @ApiOperation(value = "删除歌手")
    @DeleteMapping("/{id}")
    public Result delSinger(@PathVariable String id) {
        boolean res = reclSingerService.removeById(id);
        if(res) return Result.ok()
                .message("歌手删除成功");
        else return Result.error()
                .message("歌手删除失败，请稍后再试");
    }

    @ApiOperation(value = "获取所有歌手")
    @GetMapping("/all")
    public Result getAllSinger() {
        List<ReclSinger> singers = reclSingerService.list(null);
        return Result.ok()
                .data("singers", singers);
    }

    @ApiOperation(value = "歌手名查找")
    @GetMapping("/name/detail")
    public Result getSingerByName(@RequestParam String name) {
        List<ReclSinger> singers = reclSingerService.list(new QueryWrapper<ReclSinger>()
                .like("name", name));
        return Result.ok()
                .data("singers", singers);
    }

    @ApiOperation(value = "性别查找")
    @GetMapping("/sex/detail")
    public Result getSingerBySex(@RequestParam Integer sex) {
        List<ReclSinger> singers = reclSingerService.list(new QueryWrapper<ReclSinger>()
                .like("sex", sex));
        return Result.ok()
                .data("singers", singers);
    }

    @ApiOperation(value = "歌手信息修改")
    @PutMapping("")
    public Result updateSinger(HttpServletRequest req) {
        //获取请求参数
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        Date birth = DateUtil.stringToDate(req.getParameter("birth").trim());
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        //请求参数封装
        ReclSinger singer = new ReclSinger();
        singer.setId(id)
                .setName(name)
                .setSex(Integer.valueOf(sex))
                .setBirth(birth)
                .setLocation(location)
                .setIntroduction(introduction);
        boolean res = reclSingerService.updateById(singer);
        if(res) return Result.ok()
                .message("修改成功");
        else return Result.error()
                .message("修改失败，请稍后再试");
    }
}

