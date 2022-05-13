package com.ren.songlist.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.songlist.constant.RedisKeyPrefix;
import com.ren.songlist.entity.ReclSongList;
import com.ren.songlist.service.ReclSongListService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-11
 */

@Api(tags = "歌单具体信息接口")
@RestController
@RequestMapping("/song-list")
@CrossOrigin
public class ReclSongListController {

    @Autowired
    ReclSongListService reclSongListService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "添加歌单")
    @PostMapping("")
    public Result addSongList(HttpServletRequest req) {
        ReclSongList songList = new ReclSongList();
        //获取请求参数
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        String pic = "/img/songListPic/123.jpg";
        songList.setIntroduction(introduction)
                .setPic(pic)
                .setTitle(title)
                .setStyle(style);
        boolean res = reclSongListService.save(songList);
        if(res) return Result.ok()
                .message("歌单添加成功");
        else return Result.error()
                .message("歌单添加失败，请稍后再试!");
    }

    @ApiOperation(value = "删除歌单")
    @DeleteMapping("/{id}")
    public Result delSongList(@PathVariable String id) {
        boolean res = reclSongListService.removeById(id);
        if(res) return Result.ok()
                .message("歌单删除成功");
        else return Result.error()
                .message("歌单删除失败，请稍后再试！");
    }

    @ApiOperation(value = "获取所有歌单")
    @GetMapping("/all")
    public Result getAllSongList() {
        String key = RedisKeyPrefix.ALL_SONGLIST_CACHE_KEY;
        List<ReclSongList> songLists = null;
        songLists = (List<ReclSongList>) redisTemplate.opsForValue().get(key);
        if(songLists != null) return Result.ok()
                .data("songLists", songLists);
        songLists = reclSongListService.list(null);
        redisTemplate.opsForValue().set(key, songLists, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songLists", songLists);
    }

    @ApiOperation(value = "歌单标题搜索")
    @GetMapping("/title/detail")
    public Result getSongListByTitle(@RequestParam String title) {
        String key = RedisKeyPrefix.SONGLIST_OF_TITLE_CACHE_KEY + title;
        List<ReclSongList> songLists = null;
        songLists = (List<ReclSongList>) redisTemplate.opsForValue().get(key);
        if(songLists != null) return Result.ok()
                .data("songLists", songLists);
        QueryWrapper<ReclSongList> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        songLists = reclSongListService.list(wrapper);
        redisTemplate.opsForValue().set(key, songLists, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songLists", songLists);
    }

    @ApiOperation(value = "指定类型歌单")
    @GetMapping("/style/detail")
    public Result getSongListByDetail(@RequestParam String style) {
        String key = RedisKeyPrefix.SONGLIST_OF_STYLE_CACHE_KEY + style;
        List<ReclSongList> songLists = null;
        songLists = (List<ReclSongList>) redisTemplate.opsForValue().get(key);
        if(songLists != null) return Result.ok()
                .data("songLists", songLists);
        QueryWrapper<ReclSongList> wrapper = new QueryWrapper<>();
        wrapper.like("style", style);
        songLists = reclSongListService.list(wrapper);
        redisTemplate.opsForValue().set(key, songLists, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songLists", songLists);
    }

    @ApiOperation(value = "歌单更新")
    @PutMapping("")
    public Result updateSongList(HttpServletRequest req) {
        //获取请求参数
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        //请求参数封装
        ReclSongList songList = new ReclSongList();
        songList.setId(id)
                .setStyle(style)
                .setTitle(title)
                .setIntroduction(introduction);
        boolean res = reclSongListService.updateById(songList);
        if(res) return Result.ok()
                .message("歌单更新成功");
        else return Result.error()
                .message("歌单更新失败，请稍后再试！");
    }
}

