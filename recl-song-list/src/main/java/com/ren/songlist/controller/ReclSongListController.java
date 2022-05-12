package com.ren.songlist.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.songlist.entity.ReclSongList;
import com.ren.songlist.service.ReclSongListService;
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
 * @since 2022-05-11
 */

@Api(tags = "歌单接口")
@RestController
@RequestMapping("/song-list")
@CrossOrigin
public class ReclSongListController {

    @Autowired
    ReclSongListService reclSongListService;

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
        List<ReclSongList> songLists = reclSongListService.list(null);
        return Result.ok()
                .data("songLists", songLists);
    }

    @ApiOperation(value = "歌单标题搜索")
    @GetMapping("/title/detail")
    public Result getSongListByTitle(@RequestParam String title) {
        QueryWrapper<ReclSongList> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        List<ReclSongList> songLists = reclSongListService.list(wrapper);
        return Result.ok()
                .data("songLists", songLists);
    }

    @ApiOperation(value = "指定类型歌单")
    @GetMapping("/style/detail")
    public Result getSongListByDetail(@RequestParam String style) {
        QueryWrapper<ReclSongList> wrapper = new QueryWrapper<>();
        wrapper.like("style", style);
        List<ReclSongList> songLists = reclSongListService.list(wrapper);
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

