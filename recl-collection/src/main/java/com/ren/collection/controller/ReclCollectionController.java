package com.ren.collection.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ren.collection.entity.ReclCollection;
import com.ren.collection.service.ReclCollectionService;
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
 * @since 2022-05-12
 */

@Api(tags = "收藏接口")
@RestController
@RequestMapping("/collection")
@CrossOrigin
public class ReclCollectionController {

    @Autowired
    ReclCollectionService reclCollectionService;

    @ApiOperation(value = "歌曲收藏")
    @PostMapping("/song")
    public Result addSongCollection(HttpServletRequest req) {
        //获取请求参数
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_id = req.getParameter("songId");
        String song_list_id = req.getParameter("songListId");
        //请求参数封装
        ReclCollection collection = new ReclCollection();
        collection.setUserId(user_id)
                .setSongId(song_id)
                .setSongListId(song_list_id)
                .setType(Integer.valueOf(type));
        boolean res = reclCollectionService.save(collection);
        if(res) return Result.ok()
                .message("歌曲收藏成功");
        else return Result.error()
                .message("歌曲收藏失败，请稍后再试!");
    }

    @ApiOperation(value = "取消歌曲收藏")
    @DeleteMapping("/{userId}/{songId}")
    public Result delSongCollection(@PathVariable String userId, @PathVariable String songId) {
        boolean res = reclCollectionService.remove(new UpdateWrapper<ReclCollection>()
                .set("user_id", userId)
                .set("song_id", songId));
        if(res) return Result.ok()
                .message("取消收藏成功");
        else return Result.error()
                .message("取消收藏失败，请稍后再试");
    }

    @ApiOperation(value = "收藏状态")
    @PostMapping("/status")
    public Result getCollectionStatus(HttpServletRequest req) {
        //获取请求参数
        String user_id = req.getParameter("userId").trim();
        String song_id = req.getParameter("songId").trim();
        int count = reclCollectionService.count(new QueryWrapper<ReclCollection>()
                .eq("user_id", user_id)
                .eq("song_id", song_id)
                .select("id"));
        if(count != 0) return Result.ok()
                .message("已收藏")
                .data("status", true);
        else return Result.ok()
                .message("未收藏")
                .data("status", false);
    }

    @ApiOperation(value = "获取用户收藏列表")
    @GetMapping("/user/detail/{userId}")
    public Result getCollectionByUserId(@PathVariable String userId) {
        List<ReclCollection> collections = reclCollectionService.list(new QueryWrapper<ReclCollection>()
                .eq("user_id", userId));
        return Result.ok()
                .data("collections", collections);
    }

}

