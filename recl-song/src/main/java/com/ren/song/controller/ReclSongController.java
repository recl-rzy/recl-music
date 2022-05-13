package com.ren.song.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.dubboservice.ListSongService;
import com.ren.song.entity.ReclSong;
import com.ren.song.service.ReclSongService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-10
 */

@Api(tags = "recl歌曲接口")
@RestController
@RequestMapping("/song")
@CrossOrigin
public class ReclSongController {

    @Autowired
    ReclSongService reclSongService;

    @Reference(parameters = {"unicast", "false"}, mock = "com.ren.dubboservice.impl.ListSongServiceImpl")
    ListSongService listSongService;

    @ApiOperation(value = "歌曲删除")
    @DeleteMapping("/{id}")
    public Result delSong(@PathVariable String id) {
        boolean res = reclSongService.removeById(id);
        if(res) return Result.ok()
                .message("删除成功");
        else return Result.error()
                .message("删除失败");
    }

    @ApiOperation(value = "获取所有歌曲")
    @GetMapping("/songs")
    public Result getAllSongs() {
        List<ReclSong> songList = reclSongService.list(null);
        return Result.ok()
                .data("songs", songList);
    }

    @ApiOperation(value = "获取指定歌曲")
    @GetMapping("/{id}")
    public Result getSongById(@PathVariable String id) {
        ReclSong song = reclSongService.getById(id);
        return Result.ok()
                .data("song", song);
    }

    @ApiOperation(value = "通过歌手id获取歌曲")
    @GetMapping("/singer/{singerId}")
    public Result getSongBySingerId(@PathVariable String singerId) {
        QueryWrapper<ReclSong> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id", singerId);
        List<ReclSong> songs = reclSongService.list(wrapper);
        return Result.ok()
                .data("songs", songs);
    }

    @ApiOperation(value = "通过歌手名获取歌曲")
    @GetMapping("/singer-name")
    public Result getSongBySingerName(@RequestParam String name) {
        QueryWrapper<ReclSong> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        List<ReclSong> songs = reclSongService.list(wrapper);
        return Result.ok()
                .data("songs", songs);
    }

    @ApiOperation(value = "获取歌单中的所有歌曲")
    @GetMapping("/list-song/{songListId}")
    public Result getSongsFromListSongId(@PathVariable String songListId) {
        List<String> songIds = listSongService.getSongIds(songListId);
        if(songIds.isEmpty()) return Result.ok()
                .data("songs", new ArrayList<ReclSong>());
        List<ReclSong> songs = (List<ReclSong>) reclSongService.listByIds(songIds);
        return Result.ok()
                .data("songs", songs);
    }

}

